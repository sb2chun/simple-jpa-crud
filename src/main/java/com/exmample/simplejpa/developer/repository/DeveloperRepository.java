package com.exmample.simplejpa.developer.repository;

import com.exmample.simplejpa.global.code.StatusCode;
import com.exmample.simplejpa.developer.entity.Developer;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long>{
    Optional<Developer> findByMemberId(String memberId);
    List<Developer> findDevelopersByStatusEquals(StatusCode status);
}
