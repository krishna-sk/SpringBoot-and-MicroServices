package com.krishna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class SpringBootRestCrudMySqlExApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("CREATING PRODUCT")
	@Order(1)
	public void testCreateProduct() throws Exception {
		// I. create one Request using Mocking
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/rest/product/save")
				.contentType(MediaType.APPLICATION_JSON).content("{\"code\":\"PEN\",\"cost\":320.0}");

		// II. Execute Request and get Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// III. Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// IV. Assert Result using JUnit.
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		if (!response.getContentAsString().contains("created")) {
			fail("Product Not created!");
		}

	}

	@Test
	@DisplayName("FETCH ALL PRODUCTS")
	@Order(2)
	public void testFetchAllProducts() throws Exception {
		// I. create one Request using Mocking
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/rest/product/all");

		// II. Execute Request and get Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// III. Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// IV. Assert Result using JUnit.
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
	}

	@Test
	@DisplayName("FETCH ONE PRODUCT")
	@Order(3)
	public void testfetchOneProductExist() throws Exception {
		// I. create one Request using Mocking
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/rest/product/one/{id}", 1);

		// II. Execute Request and get Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// III. Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// IV. Assert Result using JUnit.
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());

	}

	@Test
	@DisplayName("FETCH ONE PRODUCT NOT EXIST")
	@Order(4)
	public void testfetchOneProductNotFound() throws Exception {
		// I. create one Request using Mocking
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/rest/product/one/{id}", 999);

		// II. Execute Request and get Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// III. Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// IV. Assert Result using JUnit.
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
		if (!response.getContentAsString().contains("not exist")) {
			fail("MY BE DATA IS PRESENT");
		}
	}

	@Test
	@DisplayName("UPDATE PRODUCT")
	@Order(5)
	public void testUpdateProduct() throws Exception {
		// I. create one Request using Mocking
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/rest/product/modify")
				.contentType(MediaType.APPLICATION_JSON).content("{\"id\":1,\"code\":\"NEW PEN\",\"cost\":420.0}");

		// II. Execute Request and get Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// III. Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// IV. Assert Result using JUnit.
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if (!response.getContentAsString().contains("Updated")) {
			fail("DATA NOT FOUND FOR UPDATE");
		}
	}

	@Test
	@DisplayName("PARTIAL UPDATE PRODUCT")
	@Order(6)
	public void testUpdateProductById() throws Exception {
		// I. create one Request using Mocking
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.patch("/rest/product/update/{id}/{code}", 1,
				"UPDATED PEN");

		// II. Execute Request and get Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// III. Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// IV. Assert Result using JUnit.
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if (!response.getContentAsString().contains("Code is Updated !!!")) {
			fail("DATA NOT FOUND FOR UPDATE");
		}
	}

	@Test
	@DisplayName("DELETE PRODUCT")
	@Order(7)
	public void testDeleteProduct() throws Exception {
		// I. create one Request using Mocking
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/rest/product/remove/{id}", 1);

		// II. Execute Request and get Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// III. Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// IV. Assert Result using JUnit.
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if (!response.getContentAsString().contains("Removed")) {
			fail("Not Deleted!");
		}
	}

	@Test
	@DisplayName("DELETE PRODUCT NOT EXIST")
	@Order(8)
	public void testDeleteProductNotExist() throws Exception {
		// I. create one Request using Mocking
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/rest/product/remove/{id}", 500);

		// II. Execute Request and get Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// III. Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// IV. Assert Result using JUnit.
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
		if (!response.getContentAsString().contains("not exist")) {
			fail("MY BE DATA IS PRESENT");
		}
	}
}
