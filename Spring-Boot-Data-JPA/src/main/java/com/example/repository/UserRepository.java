package com.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

}
