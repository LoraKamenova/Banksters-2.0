package softuni.banksters.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "facts")
public class Fact extends BaseEntity{
    private String factText;

    public Fact() {
    }

    @Column(name = "fact_text", columnDefinition = "TEXT", nullable = false)
    public String getFactText() {
        return factText;
    }

    public void setFactText(String factText) {
        this.factText = factText;
    }
}
