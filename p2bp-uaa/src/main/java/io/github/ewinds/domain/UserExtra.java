package io.github.ewinds.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UserExtra.
 */
@Entity
@Table(name = "user_extra")
public class UserExtra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "points")
    private Long points;

    @Column(name = "referral_code")
    private String referralCode;

    @Column(name = "id_card_verified")
    private Boolean idCardVerified;

    @Column(name = "id_card_error_times")
    private Integer idCardErrorTimes;

    @Column(name = "jhi_comment")
    private String comment;

    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "university")
    private String university;

    @Column(name = "diploma")
    private String diploma;

    @Column(name = "company")
    private String company;

    @Column(name = "industry")
    private String industry;

    @Column(name = "scale")
    private String scale;

    @Column(name = "position")
    private String position;

    @Column(name = "income")
    private String income;

    @Column(name = "recommended_by_phone")
    private String recommendedByPhone;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public UserExtra phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public UserExtra nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public UserExtra realName(String realName) {
        this.realName = realName;
        return this;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public UserExtra idCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Long getPoints() {
        return points;
    }

    public UserExtra points(Long points) {
        this.points = points;
        return this;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public UserExtra referralCode(String referralCode) {
        this.referralCode = referralCode;
        return this;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Boolean isIdCardVerified() {
        return idCardVerified;
    }

    public UserExtra idCardVerified(Boolean idCardVerified) {
        this.idCardVerified = idCardVerified;
        return this;
    }

    public void setIdCardVerified(Boolean idCardVerified) {
        this.idCardVerified = idCardVerified;
    }

    public Integer getIdCardErrorTimes() {
        return idCardErrorTimes;
    }

    public UserExtra idCardErrorTimes(Integer idCardErrorTimes) {
        this.idCardErrorTimes = idCardErrorTimes;
        return this;
    }

    public void setIdCardErrorTimes(Integer idCardErrorTimes) {
        this.idCardErrorTimes = idCardErrorTimes;
    }

    public String getComment() {
        return comment;
    }

    public UserExtra comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public UserExtra districtCode(String districtCode) {
        this.districtCode = districtCode;
        return this;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getUniversity() {
        return university;
    }

    public UserExtra university(String university) {
        this.university = university;
        return this;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDiploma() {
        return diploma;
    }

    public UserExtra diploma(String diploma) {
        this.diploma = diploma;
        return this;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getCompany() {
        return company;
    }

    public UserExtra company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIndustry() {
        return industry;
    }

    public UserExtra industry(String industry) {
        this.industry = industry;
        return this;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getScale() {
        return scale;
    }

    public UserExtra scale(String scale) {
        this.scale = scale;
        return this;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getPosition() {
        return position;
    }

    public UserExtra position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIncome() {
        return income;
    }

    public UserExtra income(String income) {
        this.income = income;
        return this;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getRecommendedByPhone() {
        return recommendedByPhone;
    }

    public UserExtra recommendedByPhone(String recommendedByPhone) {
        this.recommendedByPhone = recommendedByPhone;
        return this;
    }

    public void setRecommendedByPhone(String recommendedByPhone) {
        this.recommendedByPhone = recommendedByPhone;
    }

    public User getUser() {
        return user;
    }

    public UserExtra user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
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
        UserExtra userExtra = (UserExtra) o;
        if (userExtra.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userExtra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserExtra{" +
            "id=" + getId() +
            ", phone='" + getPhone() + "'" +
            ", nickname='" + getNickname() + "'" +
            ", realName='" + getRealName() + "'" +
            ", idCard='" + getIdCard() + "'" +
            ", points=" + getPoints() +
            ", referralCode='" + getReferralCode() + "'" +
            ", idCardVerified='" + isIdCardVerified() + "'" +
            ", idCardErrorTimes=" + getIdCardErrorTimes() +
            ", comment='" + getComment() + "'" +
            ", districtCode='" + getDistrictCode() + "'" +
            ", university='" + getUniversity() + "'" +
            ", diploma='" + getDiploma() + "'" +
            ", company='" + getCompany() + "'" +
            ", industry='" + getIndustry() + "'" +
            ", scale='" + getScale() + "'" +
            ", position='" + getPosition() + "'" +
            ", income='" + getIncome() + "'" +
            ", recommendedByPhone='" + getRecommendedByPhone() + "'" +
            "}";
    }
}
