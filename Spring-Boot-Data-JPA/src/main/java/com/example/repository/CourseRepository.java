package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	/**
	 * using = equals operator with WHERE condition
	 */
	//SQL: select * from course where ven=?
	List<Course> findByCourseVen(String courseVen);
	List<Course> findByCourseVenIs(String courseVen);
	List<Course> findByCourseVenEquals(String courseVen);
	
	/**
	 * using >, >=, < , <=, != operator with WHERE condition
	 */
	List<Course> findByCourseFeeLessThan(Double courseFee);
	List<Course> findByCourseIdGreaterThanEqual(Integer courseId);
	List<Course> findByCourseIdNot(Integer courseId);
	
	/**
	 * in, no in operator
	 */
	List<Course> findByCourseIdIn(List<Integer> ids);
	List<Course> findByCourseIdNotIn(List<Integer> ids);

	/**
	 * Null, not null condition
	 */
	//SQL: SELECT * FROM PROD WHERE ven IS NULL
	List<Course> findByCourseVenIsNull();
	List<Course> findByCourseVenIsNotNull();
	
	/***
	 * like and not like
	 */
	List<Course> findByCourseCodeLike(String pattern);
	List<Course> findByCourseCodeStartingWith(String pattern);
	List<Course> findByCourseCodeEndingWith(String pattern);
	List<Course> findByCourseCodeContaining(String pattern);
	
	/**
	 * and, or
	 */
	//SQL: select * from course where id=? or code=? 
	List<Course> findByCourseIdOrCourseCode(Integer courseId, String courseCode);
	Optional<Course> findByCourseIdAndCourseCode(Integer courseId, String courseCode);

	/***
	 * between
	 */
	List<Course> findByCourseIdBetween(Integer from,Integer to);
	
	/**
	 * Sorted data
	 */
	//SQL: select * from course where ven=? order by code ASC
	List<Course> findByCourseVenOrderByCourseCode(String courseVen);
	List<Course> findByCourseVenOrderByCourseCodeDesc(String courseVen);

}
