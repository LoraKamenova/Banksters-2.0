package softuni.banksters.domain.models.view;

public class CompanyDetailsViewModel {
    private String id;
    private String name;
    private String industry;
    private String description;
    private String founded;
    private String logoURL;
    private String headquarters;
    private String brandsURL;
    private String revenues;
    private String website;
    private String employees;

    public CompanyDetailsViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public String getBrandsURL() {
        return brandsURL;
    }

    public void setBrandsURL(String brandsURL) {
        this.brandsURL = brandsURL;
    }

    public String getRevenues() {
        return revenues;
    }

    public void setRevenues(String revenues) {
        this.revenues = revenues;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }
}
