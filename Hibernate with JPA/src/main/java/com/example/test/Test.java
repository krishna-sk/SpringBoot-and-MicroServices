package com.example.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.example.entity.Student;

public class Test {

	public static void main(String[] args) {

		// Load Driver, Create Connection, support statement preparation
		EntityTransaction tx = null;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("AppDB");
			System.out.println(emf.getClass().getName());

			EntityManager em = emf.createEntityManager();
			System.out.println(em.getClass().getName());

			tx = em.getTransaction();
			tx.begin();
			Student student = new Student();
			student.setStdId(102);
			student.setStdName("SAMPLE2");
			student.setStdFee(200.0);
			em.persist(student);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

	}
}
