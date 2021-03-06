package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import domain.Dream;
import repos.DreamRepo;

@Service
public class DreamServiceDB implements DreamService {
	private DreamRepo repo;

	public DreamServiceDB(DreamRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Dream addDream(Dream dream) {
		return this.repo.save(dream);
	}

	@Override
	public List<Dream> getAll() {
		return this.repo.findAll();
	}

	@Override
	public Dream getDreamById(Long id) {
		Optional<Dream> optDream = this.repo.findById(id);
		return optDream.get();
	}

	@Override
	public Dream updateDream(Long id, Dream newDream) {
		Dream existingDream = this.getDreamById(id);
		existingDream.setDate(newDream.getDate());
		existingDream.setDescription(newDream.getDescription());
		existingDream.setTag(newDream.getTag());
		return this.repo.save(existingDream);
	}

	@Override
	public boolean deleteDream(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
