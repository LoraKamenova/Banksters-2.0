package softuni.banksters.domain.models.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContractViewModel {

    private String id;
    private String fullName;
    private String signing;
    private BigDecimal initialAccountBalance;

    private UserProfileViewModel user;
    private LocalDateTime finishedOn;

    public ContractViewModel() {
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

    public String getSigning() {
        return signing;
    }

    public void setSigning(String signing) {
        this.signing = signing;
    }

    public UserProfileViewModel getUser() {
        return user;
    }

    public void setUser(UserProfileViewModel user) {
        this.user = user;
    }

    public LocalDateTime getFinishedOn() {
        return finishedOn;
    }

    public void setFinishedOn(LocalDateTime finishedOn) {
        this.finishedOn = finishedOn;
    }

    public BigDecimal getInitialAccountBalance() {
        return initialAccountBalance;
    }

    public void setInitialAccountBalance(BigDecimal initialAccountBalance) {
        this.initialAccountBalance = initialAccountBalance;
    }
}
