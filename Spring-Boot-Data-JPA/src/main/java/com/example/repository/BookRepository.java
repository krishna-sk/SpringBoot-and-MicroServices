package com.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.entity.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

}
