package com.exmample.simplejpa.developer.dto;

import com.exmample.simplejpa.developer.entity.Developer;
import com.exmample.simplejpa.developer.type.DeveloperLevel;
import com.exmample.simplejpa.developer.type.DeveloperSkillType;
import com.exmample.simplejpa.global.code.StatusCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperDetailDTO {
    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;
    private Integer experienceYears;
    private String memberId;
    private String name;
    private Integer age;
    private StatusCode status;

    public static DeveloperDetailDTO fromEntity(Developer developer) {
        return DeveloperDetailDTO.builder()
                .developerLevel(developer.getDeveloperLevel())
                .developerSkillType(developer.getDeveloperSkillType())
                .experienceYears(developer.getExperienceYears())
                .memberId(developer.getMemberId())
                .name(developer.getName())
                .age(developer.getAge())
                .status(developer.getStatus())
                .build();
    }
}
