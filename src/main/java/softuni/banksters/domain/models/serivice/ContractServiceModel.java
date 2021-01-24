package softuni.banksters.domain.models.serivice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContractServiceModel {

    private String id;
    private String fullName;
    private String PIN;
    private String idCard;
    private String address;
    private String profession;
    private BigDecimal netIncome;
    private String mobile;
    private String signing;
    private BigDecimal initialAccountBalance;

    private UserServiceModel user;
    private LocalDateTime finishedOn;

    public ContractServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSigning() {
        return signing;
    }

    public void setSigning(String signing) {
        this.signing = signing;
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

    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
    }

    public BigDecimal getInitialAccountBalance() {
        return initialAccountBalance;
    }

    public void setInitialAccountBalance(BigDecimal initialAccountBalance) {
        this.initialAccountBalance = initialAccountBalance;
    }
}
