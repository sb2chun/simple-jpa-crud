package com.exmample.simplejpa.developer.repository;

import com.exmample.simplejpa.developer.entity.Developer;
import com.exmample.simplejpa.developer.entity.RetiredDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetiredDeveloperRepository extends JpaRepository<RetiredDeveloper, Long> {
}
