package softuni.banksters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.banksters.domain.entities.Faq;

@Repository
public interface FaqRepository extends JpaRepository<Faq, String> {


}
