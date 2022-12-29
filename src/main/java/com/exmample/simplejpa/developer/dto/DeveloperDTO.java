package com.exmample.simplejpa.developer.dto;

import com.exmample.simplejpa.developer.entity.Developer;
import com.exmample.simplejpa.developer.type.DeveloperLevel;
import com.exmample.simplejpa.developer.type.DeveloperSkillType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperDTO {
    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;
    private String memberId;

    public static DeveloperDTO fromEntity(Developer developer) {
        return DeveloperDTO.builder()
                .developerLevel(developer.getDeveloperLevel())
                .developerSkillType(developer.getDeveloperSkillType())
                .memberId(developer.getMemberId())
                .build();
    }
}