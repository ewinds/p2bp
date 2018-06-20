package io.github.ewinds.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A OriginEnterprise.
 */
@Entity
@Table(name = "origin_enterprise")
public class OriginEnterprise extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "abbr_company_name")
    private String abbrCompanyName;

    @Column(name = "registered_capital")
    private String registeredCapital;

    @Column(name = "legal_person")
    private String legalPerson;

    @Column(name = "business_num")
    private String businessNum;

    @Column(name = "industry")
    private String industry;

    @Column(name = "earning")
    private String earning;

    @Column(name = "simple_desc")
    private String simpleDesc;

    @Size(max = 2048)
    @Column(name = "company_introduce", length = 2048)
    private String companyIntroduce;

    @Column(name = "business_checked")
    private Boolean businessChecked;

    @Column(name = "legal_card_checked")
    private Boolean legalCardChecked;

    @Column(name = "bonding_checked")
    private Boolean bondingChecked;

    @Column(name = "platform_checked")
    private Boolean platformChecked;

    @Column(name = "address_checked")
    private Boolean addressChecked;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public OriginEnterprise companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAbbrCompanyName() {
        return abbrCompanyName;
    }

    public OriginEnterprise abbrCompanyName(String abbrCompanyName) {
        this.abbrCompanyName = abbrCompanyName;
        return this;
    }

    public void setAbbrCompanyName(String abbrCompanyName) {
        this.abbrCompanyName = abbrCompanyName;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public OriginEnterprise registeredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
        return this;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public OriginEnterprise legalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
        return this;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getBusinessNum() {
        return businessNum;
    }

    public OriginEnterprise businessNum(String businessNum) {
        this.businessNum = businessNum;
        return this;
    }

    public void setBusinessNum(String businessNum) {
        this.businessNum = businessNum;
    }

    public String getIndustry() {
        return industry;
    }

    public OriginEnterprise industry(String industry) {
        this.industry = industry;
        return this;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getEarning() {
        return earning;
    }

    public OriginEnterprise earning(String earning) {
        this.earning = earning;
        return this;
    }

    public void setEarning(String earning) {
        this.earning = earning;
    }

    public String getSimpleDesc() {
        return simpleDesc;
    }

    public OriginEnterprise simpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
        return this;
    }

    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
    }

    public String getCompanyIntroduce() {
        return companyIntroduce;
    }

    public OriginEnterprise companyIntroduce(String companyIntroduce) {
        this.companyIntroduce = companyIntroduce;
        return this;
    }

    public void setCompanyIntroduce(String companyIntroduce) {
        this.companyIntroduce = companyIntroduce;
    }

    public Boolean isBusinessChecked() {
        return businessChecked;
    }

    public OriginEnterprise businessChecked(Boolean businessChecked) {
        this.businessChecked = businessChecked;
        return this;
    }

    public void setBusinessChecked(Boolean businessChecked) {
        this.businessChecked = businessChecked;
    }

    public Boolean isLegalCardChecked() {
        return legalCardChecked;
    }

    public OriginEnterprise legalCardChecked(Boolean legalCardChecked) {
        this.legalCardChecked = legalCardChecked;
        return this;
    }

    public void setLegalCardChecked(Boolean legalCardChecked) {
        this.legalCardChecked = legalCardChecked;
    }

    public Boolean isBondingChecked() {
        return bondingChecked;
    }

    public OriginEnterprise bondingChecked(Boolean bondingChecked) {
        this.bondingChecked = bondingChecked;
        return this;
    }

    public void setBondingChecked(Boolean bondingChecked) {
        this.bondingChecked = bondingChecked;
    }

    public Boolean isPlatformChecked() {
        return platformChecked;
    }

    public OriginEnterprise platformChecked(Boolean platformChecked) {
        this.platformChecked = platformChecked;
        return this;
    }

    public void setPlatformChecked(Boolean platformChecked) {
        this.platformChecked = platformChecked;
    }

    public Boolean isAddressChecked() {
        return addressChecked;
    }

    public OriginEnterprise addressChecked(Boolean addressChecked) {
        this.addressChecked = addressChecked;
        return this;
    }

    public void setAddressChecked(Boolean addressChecked) {
        this.addressChecked = addressChecked;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OriginEnterprise originEnterprise = (OriginEnterprise) o;
        if (originEnterprise.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), originEnterprise.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OriginEnterprise{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", abbrCompanyName='" + getAbbrCompanyName() + "'" +
            ", registeredCapital='" + getRegisteredCapital() + "'" +
            ", legalPerson='" + getLegalPerson() + "'" +
            ", businessNum='" + getBusinessNum() + "'" +
            ", industry='" + getIndustry() + "'" +
            ", earning='" + getEarning() + "'" +
            ", simpleDesc='" + getSimpleDesc() + "'" +
            ", companyIntroduce='" + getCompanyIntroduce() + "'" +
            ", businessChecked='" + isBusinessChecked() + "'" +
            ", legalCardChecked='" + isLegalCardChecked() + "'" +
            ", bondingChecked='" + isBondingChecked() + "'" +
            ", platformChecked='" + isPlatformChecked() + "'" +
            ", addressChecked='" + isAddressChecked() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
