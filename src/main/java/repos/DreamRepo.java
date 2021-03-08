package repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Dream;

@Repository
public interface DreamRepo extends JpaRepository<Dream, Long> {

}
