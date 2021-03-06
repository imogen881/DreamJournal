package rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Dream;
import service.DreamService;

@RestController
public class DreamController {
	private DreamService service;

	public DreamController(DreamService service) {
		super();
		this.service = service;
	}

	@PostMapping("/addDream")
	public ResponseEntity<Dream> addDream(Dream dream) {
		return new ResponseEntity<Dream>(this.service.addDream(dream), HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Dream>> getAll() {
		return ResponseEntity.ok(this.service.getAll());
	}

	@GetMapping("/getById/{id}")
	public Dream getDreamById(@PathVariable Long id) {
		return this.service.getDreamById(id);
	}

	@PutMapping("/update/{id}")
	public Dream updateDream(@PathVariable Long id, Dream dream) {
		return this.service.updateDream(id, dream);
	}

	@DeleteMapping("/delete/{id}")
	public boolean deleteDream(@PathVariable Long id) {
		return this.service.deleteDream(id);
	}

}
