package softuni.banksters.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "faqs")
public class Faq extends BaseEntity{

    public String question;
    public String answer;

    public Faq() {
    }

    @Column(name = "question", columnDefinition = "TEXT", nullable = false)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Column(name = "answer", columnDefinition = "TEXT", nullable = false)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
