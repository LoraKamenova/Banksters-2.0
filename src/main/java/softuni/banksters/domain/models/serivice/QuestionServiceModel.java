package softuni.banksters.domain.models.serivice;

import softuni.banksters.domain.entities.User;

import java.time.LocalDateTime;

public class QuestionServiceModel {

    private String id;
    private String question;
    private String answer;

    private LocalDateTime finishedOn;
    private UserServiceModel user;

    public QuestionServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public LocalDateTime getFinishedOn() {
        return finishedOn;
    }

    public void setFinishedOn(LocalDateTime finishedOn) {
        this.finishedOn = finishedOn;
    }
}
