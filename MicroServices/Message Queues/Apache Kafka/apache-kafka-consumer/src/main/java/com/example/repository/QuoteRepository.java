package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {

}