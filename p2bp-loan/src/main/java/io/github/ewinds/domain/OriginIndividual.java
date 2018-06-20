package io.github.ewinds.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A OriginIndividual.
 */
@Entity
@Table(name = "origin_individual")
public class OriginIndividual extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "abbr_name")
    private String abbrName;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "age")
    private Integer age;

    @Size(max = 2048)
    @Column(name = "purpose", length = 2048)
    private String purpose;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public OriginIndividual realName(String realName) {
        this.realName = realName;
        return this;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAbbrName() {
        return abbrName;
    }

    public OriginIndividual abbrName(String abbrName) {
        this.abbrName = abbrName;
        return this;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName;
    }

    public String getIdCard() {
        return idCard;
    }

    public OriginIndividual idCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobile() {
        return mobile;
    }

    public OriginIndividual mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getGender() {
        return gender;
    }

    public OriginIndividual gender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public OriginIndividual age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPurpose() {
        return purpose;
    }

    public OriginIndividual purpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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
        OriginIndividual originIndividual = (OriginIndividual) o;
        if (originIndividual.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), originIndividual.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OriginIndividual{" +
            "id=" + getId() +
            ", realName='" + getRealName() + "'" +
            ", abbrName='" + getAbbrName() + "'" +
            ", idCard='" + getIdCard() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", gender=" + getGender() +
            ", age=" + getAge() +
            ", purpose='" + getPurpose() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
