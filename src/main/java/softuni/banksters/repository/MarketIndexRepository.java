package softuni.banksters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.banksters.domain.entities.MarketIndex;

@Repository
public interface MarketIndexRepository extends JpaRepository<MarketIndex, String> {
}
