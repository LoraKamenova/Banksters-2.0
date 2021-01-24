package softuni.banksters.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "analyses")
public class Analysis extends BaseEntity{

    private String company;
    private String ticker;

    private String profile;
    private String strengths;
    private String weaknesses;
    private String opportunities;
    private String threats;

    public Analysis() {
    }

    @Column(name = "company", columnDefinition = "TEXT", nullable = false)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Column(name = "ticker", columnDefinition = "TEXT", nullable = false)
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Column(name = "opportunities", columnDefinition = "TEXT", nullable = false)
    public String getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(String history) {
        this.opportunities = history;
    }

    @Column(name = "profile", columnDefinition = "TEXT", nullable = false)
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Column(name = "strengths", columnDefinition = "TEXT", nullable = false)
    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    @Column(name = "weaknesses", columnDefinition = "TEXT", nullable = false)
    public String getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses = weaknesses;
    }

    @Column(name = "threats", columnDefinition = "TEXT", nullable = false)
    public String getThreats() {
        return threats;
    }

    public void setThreats(String forecast) {
        this.threats = forecast;
    }
}
