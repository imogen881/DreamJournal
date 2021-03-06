package service;

import java.util.List;

import domain.Dream;

public interface DreamService {

	Dream addDream(Dream dream);

	List<Dream> getAll();

	Dream getDreamById(Long id);

	Dream updateDream(Long id, Dream dream);

	boolean deleteDream(Long id);

}
