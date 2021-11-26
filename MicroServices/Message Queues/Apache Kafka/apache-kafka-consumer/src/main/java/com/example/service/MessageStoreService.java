package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Quote;
import com.example.repository.QuoteRepository;
import com.example.util.JsonUtil;

@Service
public class MessageStoreService {

	@Autowired
	private QuoteRepository repo;

	@Autowired
	private JsonUtil util;

	public void save(String message) {
		Quote quote = util.fromJson(message);
		repo.save(quote);
	}

	public List<Quote> getAll() {
		return repo.findAll();
	}
}