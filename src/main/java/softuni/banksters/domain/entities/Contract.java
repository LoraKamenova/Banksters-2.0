package softuni.banksters.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity{

    private String fullName;
    private String PIN;
    private String idCard;
    private String address;
    private String profession;
    private BigDecimal netIncome;
    private String mobile;
    private String signing;
    private BigDecimal initialAccountBalance;

    private LocalDateTime finishedOn;
    private User user;

    public Contract() {
    }

    @Column(name = "full_name", columnDefinition = "TEXT", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "PIN", columnDefinition = "TEXT", nullable = false)
    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    @Column(name = "id_card", columnDefinition = "TEXT", nullable = false)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Column(name = "address", columnDefinition = "TEXT", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "profession", columnDefinition = "TEXT", nullable = false)
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Column(name = "mobile", columnDefinition = "TEXT", nullable = false)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "signing", columnDefinition = "TEXT", nullable = false)
    public String getSigning() {
        return signing;
    }

    public void setSigning(String signing) {
        this.signing = signing;
    }

    @Column(name = "finished_on", columnDefinition = "TEXT", nullable = false)
    public LocalDateTime getFinishedOn() {
        return finishedOn;
    }

    public void setFinishedOn(LocalDateTime finishedOn) {
        this.finishedOn = finishedOn;
    }

    @Column(name = "net_income", nullable = false)
    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
    }

    @Column(name = "initial_balance", nullable = false)
    public BigDecimal getInitialAccountBalance() {
        return initialAccountBalance;
    }

    public void setInitialAccountBalance(BigDecimal initialAccountBalance) {
        this.initialAccountBalance = initialAccountBalance;
    }

    @ManyToOne(targetEntity = User.class)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
