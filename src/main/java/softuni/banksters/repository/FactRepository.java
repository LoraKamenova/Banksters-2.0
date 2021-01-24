package softuni.banksters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.banksters.domain.entities.Fact;

@Repository
public interface FactRepository extends JpaRepository<Fact, String> {
}
