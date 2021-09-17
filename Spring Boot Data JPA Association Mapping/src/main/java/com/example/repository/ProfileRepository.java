package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}