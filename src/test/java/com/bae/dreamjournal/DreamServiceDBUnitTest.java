package com.bae.dreamjournal;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.dreamjournal.domain.Dream;
import com.bae.dreamjournal.repos.DreamRepo;
import com.bae.dreamjournal.service.DreamServiceDB;

@SpringBootTest
@ActiveProfiles("test")
public class DreamServiceDBUnitTest {
	@Autowired
	private DreamServiceDB service;

	@MockBean
	private DreamRepo repo;

	@Test
	void testCreate() {
		Dream newDream = new Dream(LocalDate.of(2020, 1, 8), "New dream", "Test");
		Dream savedDream = new Dream(1L, LocalDate.of(2020, 1, 8), "New dream", "Test");
		Mockito.when(this.repo.save(newDream)).thenReturn(savedDream);
		assertThat(this.service.addDream(newDream)).isEqualTo(savedDream);

		Mockito.verify(this.repo, Mockito.times(1)).save(newDream);
	}

	@Test
	void testUpdate() {
		Long id = 1L;
		Dream dreamUpdates = new Dream(LocalDate.of(2020, 1, 8), "Updated dream", "Updates");
		Optional<Dream> existingDream = Optional
				.of(new Dream(id, LocalDate.of(2008, 12, 10), "Existing dream", "Existing"));
		Dream updatedDream = new Dream(id, dreamUpdates.getDate(), dreamUpdates.getDescription(),
				dreamUpdates.getTag());
		Mockito.when(this.repo.findById(id)).thenReturn(existingDream);
		Mockito.when(this.repo.save(updatedDream)).thenReturn(updatedDream);
		assertThat(this.service.updateDream(id, dreamUpdates)).isEqualTo(updatedDream);

		Mockito.verify(this.repo, Mockito.times(1)).save(updatedDream);
	}

	@Test
	void testDelete() {
		Long id = 1L;
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		assertThat(this.service.deleteDream(id)).isEqualTo(true);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}

	@Test
	void testRead() {
		Dream testDream = new Dream(LocalDate.of(2015, 5, 23), "Test dream", "Test");
		List<Dream> allDreams = List.of(testDream);
		Mockito.when(this.repo.findAll()).thenReturn(allDreams);
		assertThat(this.service.getAll()).isEqualTo(allDreams);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

}
