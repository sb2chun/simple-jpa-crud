package com.exmample.simplejpa.developer.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DeveloperLevel {
    NEW("신입 개발자"),
    JUNIOR("주니어 개발자"),
    JUNGNIOR("중간 개발자"),
    SENIOR("시니어 개발자");

    private final String description;
}