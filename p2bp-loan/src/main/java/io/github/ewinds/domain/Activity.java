package io.github.ewinds.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A Activity.
 */
@Entity
@Table(name = "activity")
public class Activity extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "begin_time")
    private Instant beginTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "activity_type")
    private Integer activityType;

    @Column(name = "cash_id")
    private Integer cashId;

    @Column(name = "activity_rate", precision=10, scale=2)
    private BigDecimal activityRate;

    @Column(name = "extra_rate", precision=10, scale=2)
    private BigDecimal extraRate;

    @Column(name = "activity_tab")
    private String activityTab;

    @Size(max = 1024)
    @Column(name = "activity_desc", length = 1024)
    private String activityDesc;

    @Column(name = "del_flag")
    private Boolean delFlag;

    @Column(name = "image_url")
    private String imageUrl;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public Activity activityName(String activityName) {
        this.activityName = activityName;
        return this;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Instant getBeginTime() {
        return beginTime;
    }

    public Activity beginTime(Instant beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public void setBeginTime(Instant beginTime) {
        this.beginTime = beginTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Activity endTime(Instant endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public Activity activityType(Integer activityType) {
        this.activityType = activityType;
        return this;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Integer getCashId() {
        return cashId;
    }

    public Activity cashId(Integer cashId) {
        this.cashId = cashId;
        return this;
    }

    public void setCashId(Integer cashId) {
        this.cashId = cashId;
    }

    public BigDecimal getActivityRate() {
        return activityRate;
    }

    public Activity activityRate(BigDecimal activityRate) {
        this.activityRate = activityRate;
        return this;
    }

    public void setActivityRate(BigDecimal activityRate) {
        this.activityRate = activityRate;
    }

    public BigDecimal getExtraRate() {
        return extraRate;
    }

    public Activity extraRate(BigDecimal extraRate) {
        this.extraRate = extraRate;
        return this;
    }

    public void setExtraRate(BigDecimal extraRate) {
        this.extraRate = extraRate;
    }

    public String getActivityTab() {
        return activityTab;
    }

    public Activity activityTab(String activityTab) {
        this.activityTab = activityTab;
        return this;
    }

    public void setActivityTab(String activityTab) {
        this.activityTab = activityTab;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public Activity activityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
        return this;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public Boolean isDelFlag() {
        return delFlag;
    }

    public Activity delFlag(Boolean delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Activity imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
        Activity activity = (Activity) o;
        if (activity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), activity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Activity{" +
            "id=" + getId() +
            ", activityName='" + getActivityName() + "'" +
            ", beginTime='" + getBeginTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", activityType=" + getActivityType() +
            ", cashId=" + getCashId() +
            ", activityRate=" + getActivityRate() +
            ", extraRate=" + getExtraRate() +
            ", activityTab='" + getActivityTab() + "'" +
            ", activityDesc='" + getActivityDesc() + "'" +
            ", delFlag='" + isDelFlag() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
