package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{
	
	//case#1 select all columns
	//SQL: select * from persontab
	//@Query("FROM Person P")
	@Query("SELECT P FROM Person P")
	List<Person> getAllPersons();
	
	//case#2 select one column
	//SQL: select pid from persontab
	@Query("SELECT P.perId FROM Person P")
	List<Integer> getAllPersonsIds();
	
	//case#3 select one column
	//SQL: select pid,pbankbalance from persontab
	@Query("SELECT P.perId,P.perBankBalance FROM Person P")
	List<Object[]> getAllPersonIdsAndBankBalance();
}
