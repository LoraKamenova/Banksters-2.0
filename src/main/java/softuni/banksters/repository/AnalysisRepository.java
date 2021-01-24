package softuni.banksters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.banksters.domain.entities.Analysis;

import java.util.Optional;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, String> {

    Optional<Analysis> findByCompany(String name);
}
