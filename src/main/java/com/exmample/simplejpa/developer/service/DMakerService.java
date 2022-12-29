package com.exmample.simplejpa.developer.service;

import com.exmample.simplejpa.developer.dto.DeveloperDetailDTO;
import com.exmample.simplejpa.developer.dto.EditDeveloper;
import com.exmample.simplejpa.developer.entity.RetiredDeveloper;
import com.exmample.simplejpa.global.code.StatusCode;
import com.exmample.simplejpa.developer.dto.CreateDeveloper;
import com.exmample.simplejpa.developer.dto.DeveloperDTO;
import com.exmample.simplejpa.developer.entity.Developer;
import com.exmample.simplejpa.developer.repository.DeveloperRepository;
import com.exmample.simplejpa.developer.repository.RetiredDeveloperRepository;
import com.exmample.simplejpa.developer.type.DeveloperLevel;
import com.exmample.simplejpa.global.exception.DMakerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.exmample.simplejpa.global.code.DMakerErrorCode.*;
import static com.exmample.simplejpa.global.constant.DMakerConstant.MAX_JUNIOR_EXPERIENCE_YEARS;
import static com.exmample.simplejpa.global.constant.DMakerConstant.MIN_SENIOR_EXPERIENCE_YEARS;

@Service
@RequiredArgsConstructor
public class DMakerService {

    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    public List<DeveloperDTO> getAllEmployeeDevelopers() {
//        return developerRepository.findAll().stream()
//                .map(DeveloperDTO::fromEntity)
//                .collect(Collectors.toList());

        return developerRepository.findDevelopersByStatusEquals(StatusCode.EMPLOYED)
                .stream().map(DeveloperDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        this.validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .memberId(request.getMemberId())
                .name(request.getName())
                .age(request.getAge())
                .build();
        developerRepository.save(developer);

        return CreateDeveloper.Response.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {
        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer) -> {
                    throw new DMakerException(DUPLICATED_MEMBER_ID);
                });

        if (request.getDeveloperLevel() == DeveloperLevel.SENIOR
                && request.getExperienceYears() < MIN_SENIOR_EXPERIENCE_YEARS) {
            throw new DMakerException(LEVEL_AND_EXPERIENCE_YEARS_NOT_MATCH);
        }

        if (request.getDeveloperLevel() == DeveloperLevel.JUNGNIOR
                && (request.getExperienceYears() > MIN_SENIOR_EXPERIENCE_YEARS
                    || request.getExperienceYears() < MAX_JUNIOR_EXPERIENCE_YEARS)
        ) {
            throw new DMakerException(LEVEL_AND_EXPERIENCE_YEARS_NOT_MATCH);
        }

        if (request.getDeveloperLevel() == DeveloperLevel.JUNIOR
                && request.getExperienceYears() > MAX_JUNIOR_EXPERIENCE_YEARS) {
            throw new DMakerException(LEVEL_AND_EXPERIENCE_YEARS_NOT_MATCH);
        }
    }

    @Transactional
    public DeveloperDetailDTO getDeveloper(String memberId) {

        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDetailDTO::fromEntity)
                .orElseThrow( () -> new DMakerException(NO_DEVELOPER) );
    }

    public DeveloperDetailDTO updateDeveloper(String memberId, EditDeveloper.Request request) {

        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow( () -> new DMakerException(NO_DEVELOPER) );

        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYears());
        developer.setName(request.getName());
        developer.setAge(request.getAge());

        return DeveloperDetailDTO.fromEntity(developer);
    }

    @Transactional
    public DeveloperDetailDTO deleteDeveloper( String memberId ) {

        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow( () -> new DMakerException(NO_DEVELOPER) );

        //삭제
        developer.setStatus(StatusCode.RETIRED);

        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberId(developer.getMemberId())
                .name(developer.getName())
                .build();

        retiredDeveloperRepository.save(retiredDeveloper);

        return DeveloperDetailDTO.fromEntity(developer);
    }

}
