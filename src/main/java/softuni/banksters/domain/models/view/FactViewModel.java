package softuni.banksters.domain.models.view;

public class FactViewModel {

    private String id;
    private String factText;

    public FactViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFactText() {
        return factText;
    }

    public void setFactText(String factText) {
        this.factText = factText;
    }
}
