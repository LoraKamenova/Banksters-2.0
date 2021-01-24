package softuni.banksters.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity{

    private String type;
    private String question;
    private String answer;
    private String date;
    private String alerted;

    private String user;

    public Notification() {
    }

    @Column(name = "type", columnDefinition = "TEXT", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "question", columnDefinition = "TEXT")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Column(name = "answer", columnDefinition = "TEXT")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Column(name = "date", columnDefinition = "TEXT")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "alerted", columnDefinition = "TEXT", nullable = false)
    public String getAlerted() {
        return alerted;
    }

    public void setAlerted(String alert) {
        this.alerted = alert;
    }

    @Column(name = "user", columnDefinition = "TEXT", nullable = false)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
