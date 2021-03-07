package com.bae.dreamjournal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.dreamjournal.domain.Dream;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:dream-schema.sql",
		"classpath:dream-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class DreamControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testAddDream() throws Exception {
		Dream newDream = new Dream(LocalDate.of(2020, 1, 8), "Second dream", "test");
		String newDreamAsJSON = this.mapper.writeValueAsString(newDream);
		RequestBuilder mockRequest = post("/addDream").contentType(MediaType.APPLICATION_JSON).content(newDreamAsJSON);
		Dream savedDream = new Dream(2L, LocalDate.of(2020, 1, 8), "Second dream", "test");
		String savedDreamAsJSON = this.mapper.writeValueAsString(savedDream);
		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(savedDreamAsJSON);
		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}

	@Test
	void testUpdateDream() throws Exception {
		Dream dreamUpdates = new Dream(LocalDate.of(2020, 1, 8), "Updated dream", "update");
		String dreamUpdatesAsJSON = this.mapper.writeValueAsString(dreamUpdates);
		RequestBuilder mockRequest = put("/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(dreamUpdatesAsJSON);
		Dream updatedDream = new Dream(1L, LocalDate.of(2020, 1, 8), "Updated dream", "update");
		String updatedDreamAsJSON = this.mapper.writeValueAsString(updatedDream);
		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(updatedDreamAsJSON);
		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}

	@Test
	void testGetAll() throws Exception {
		Dream testDream = new Dream(1L, LocalDate.of(2021, 3, 7), "Test dream", "test");
		List<Dream> allDreams = List.of(testDream);
		String testListAsJSON = this.mapper.writeValueAsString(allDreams);
		RequestBuilder mockRequest = get("/getAll");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testListAsJSON);
		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDeleteDream() throws Exception {
		RequestBuilder mockRequest = delete("/delete/1").contentType(MediaType.APPLICATION_JSON);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string("true");
		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

}
