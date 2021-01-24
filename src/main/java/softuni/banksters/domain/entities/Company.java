package softuni.banksters.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity{

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

    public Company() {
    }

    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "industry", columnDefinition = "TEXT", nullable = false)
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "founded", columnDefinition = "TEXT", nullable = false)
    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    @Column(name = "logo_URL", columnDefinition = "TEXT", nullable = false)
    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    @Column(name = "headquarters", columnDefinition = "TEXT", nullable = false)
    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    @Column(name = "brands_URL", columnDefinition = "TEXT", nullable = false)
    public String getBrandsURL() {
        return brandsURL;
    }

    public void setBrandsURL(String brandsURL) {
        this.brandsURL = brandsURL;
    }

    @Column(name = "revenues", columnDefinition = "TEXT", nullable = false)
    public String getRevenues() {
        return revenues;
    }

    public void setRevenues(String revenues) {
        this.revenues = revenues;
    }

    @Column(name = "website", columnDefinition = "TEXT", nullable = false)
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Column(name = "employees", columnDefinition = "TEXT", nullable = false)
    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }
}
