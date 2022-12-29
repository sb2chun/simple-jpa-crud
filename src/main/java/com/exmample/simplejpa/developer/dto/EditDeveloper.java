package com.exmample.simplejpa.developer.dto;

import com.exmample.simplejpa.developer.type.DeveloperLevel;
import com.exmample.simplejpa.developer.type.DeveloperSkillType;
import lombok.*;

public class EditDeveloper {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private DeveloperLevel developerLevel;
        private DeveloperSkillType developerSkillType;
        private Integer experienceYears;
        private String name;
        private Integer age;
    }
}