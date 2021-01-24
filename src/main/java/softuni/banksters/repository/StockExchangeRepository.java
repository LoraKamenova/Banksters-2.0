package softuni.banksters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.banksters.domain.entities.StockExchange;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange, String> {

}
