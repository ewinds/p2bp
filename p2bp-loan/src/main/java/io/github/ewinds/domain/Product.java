package io.github.ewinds.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import io.github.ewinds.domain.enumeration.ProductState;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
public class Product extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_no")
    private String contractNo;

    @Column(name = "contract_id")
    private String contractId;

    @Column(name = "products_type")
    private String productsType;

    @Column(name = "title")
    private String title;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "hits")
    private Integer hits;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private ProductState state;

    @Column(name = "litpic_file_id")
    private String litpicFileId;

    @Column(name = "cancel_time")
    private Instant cancelTime;

    @Column(name = "cancel_remark")
    private String cancelRemark;

    @Column(name = "comment_status")
    private String commentStatus;

    @Column(name = "assignment")
    private Integer assignment;

    @Column(name = "assignment_id")
    private Integer assignmentId;

    @Column(name = "origin_info")
    private Integer originInfo;

    @Column(name = "frozen_request_no")
    private String frozenRequestNo;

    @Column(name = "products_group_id")
    private Integer productsGroupId;

    @Column(name = "confine")
    private String confine;

    @Column(name = "audit_info")
    private Integer auditInfo;

    @Column(name = "audit_info_id")
    private Integer auditInfoId;

    @Column(name = "auto_finance_publish_valid_time")
    private Integer autoFinancePublishValidTime;

    @OneToOne
    @JoinColumn(unique = true)
    private OriginEnterprise originEnterprise;

    @OneToOne
    @JoinColumn(unique = true)
    private OriginIndividual originIndividual;

    @ManyToOne
    private Activity activity;

    @ManyToOne
    private ProductType type;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNo() {
        return contractNo;
    }

    public Product contractNo(String contractNo) {
        this.contractNo = contractNo;
        return this;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractId() {
        return contractId;
    }

    public Product contractId(String contractId) {
        this.contractId = contractId;
        return this;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getProductsType() {
        return productsType;
    }

    public Product productsType(String productsType) {
        this.productsType = productsType;
        return this;
    }

    public void setProductsType(String productsType) {
        this.productsType = productsType;
    }

    public String getTitle() {
        return title;
    }

    public Product title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortName() {
        return shortName;
    }

    public Product shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getHits() {
        return hits;
    }

    public Product hits(Integer hits) {
        this.hits = hits;
        return this;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public ProductState getState() {
        return state;
    }

    public Product state(ProductState state) {
        this.state = state;
        return this;
    }

    public void setState(ProductState state) {
        this.state = state;
    }

    public String getLitpicFileId() {
        return litpicFileId;
    }

    public Product litpicFileId(String litpicFileId) {
        this.litpicFileId = litpicFileId;
        return this;
    }

    public void setLitpicFileId(String litpicFileId) {
        this.litpicFileId = litpicFileId;
    }

    public Instant getCancelTime() {
        return cancelTime;
    }

    public Product cancelTime(Instant cancelTime) {
        this.cancelTime = cancelTime;
        return this;
    }

    public void setCancelTime(Instant cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCancelRemark() {
        return cancelRemark;
    }

    public Product cancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
        return this;
    }

    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public Product commentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
        return this;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Integer getAssignment() {
        return assignment;
    }

    public Product assignment(Integer assignment) {
        this.assignment = assignment;
        return this;
    }

    public void setAssignment(Integer assignment) {
        this.assignment = assignment;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public Product assignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
        return this;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getOriginInfo() {
        return originInfo;
    }

    public Product originInfo(Integer originInfo) {
        this.originInfo = originInfo;
        return this;
    }

    public void setOriginInfo(Integer originInfo) {
        this.originInfo = originInfo;
    }

    public String getFrozenRequestNo() {
        return frozenRequestNo;
    }

    public Product frozenRequestNo(String frozenRequestNo) {
        this.frozenRequestNo = frozenRequestNo;
        return this;
    }

    public void setFrozenRequestNo(String frozenRequestNo) {
        this.frozenRequestNo = frozenRequestNo;
    }

    public Integer getProductsGroupId() {
        return productsGroupId;
    }

    public Product productsGroupId(Integer productsGroupId) {
        this.productsGroupId = productsGroupId;
        return this;
    }

    public void setProductsGroupId(Integer productsGroupId) {
        this.productsGroupId = productsGroupId;
    }

    public String getConfine() {
        return confine;
    }

    public Product confine(String confine) {
        this.confine = confine;
        return this;
    }

    public void setConfine(String confine) {
        this.confine = confine;
    }

    public Integer getAuditInfo() {
        return auditInfo;
    }

    public Product auditInfo(Integer auditInfo) {
        this.auditInfo = auditInfo;
        return this;
    }

    public void setAuditInfo(Integer auditInfo) {
        this.auditInfo = auditInfo;
    }

    public Integer getAuditInfoId() {
        return auditInfoId;
    }

    public Product auditInfoId(Integer auditInfoId) {
        this.auditInfoId = auditInfoId;
        return this;
    }

    public void setAuditInfoId(Integer auditInfoId) {
        this.auditInfoId = auditInfoId;
    }

    public Integer getAutoFinancePublishValidTime() {
        return autoFinancePublishValidTime;
    }

    public Product autoFinancePublishValidTime(Integer autoFinancePublishValidTime) {
        this.autoFinancePublishValidTime = autoFinancePublishValidTime;
        return this;
    }

    public void setAutoFinancePublishValidTime(Integer autoFinancePublishValidTime) {
        this.autoFinancePublishValidTime = autoFinancePublishValidTime;
    }

    public OriginEnterprise getOriginEnterprise() {
        return originEnterprise;
    }

    public Product originEnterprise(OriginEnterprise originEnterprise) {
        this.originEnterprise = originEnterprise;
        return this;
    }

    public void setOriginEnterprise(OriginEnterprise originEnterprise) {
        this.originEnterprise = originEnterprise;
    }

    public OriginIndividual getOriginIndividual() {
        return originIndividual;
    }

    public Product originIndividual(OriginIndividual originIndividual) {
        this.originIndividual = originIndividual;
        return this;
    }

    public void setOriginIndividual(OriginIndividual originIndividual) {
        this.originIndividual = originIndividual;
    }

    public Activity getActivity() {
        return activity;
    }

    public Product activity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ProductType getType() {
        return type;
    }

    public Product type(ProductType productType) {
        this.type = productType;
        return this;
    }

    public void setType(ProductType productType) {
        this.type = productType;
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
        Product product = (Product) o;
        if (product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", contractNo='" + getContractNo() + "'" +
            ", contractId='" + getContractId() + "'" +
            ", productsType='" + getProductsType() + "'" +
            ", title='" + getTitle() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", hits=" + getHits() +
            ", state='" + getState() + "'" +
            ", litpicFileId='" + getLitpicFileId() + "'" +
            ", cancelTime='" + getCancelTime() + "'" +
            ", cancelRemark='" + getCancelRemark() + "'" +
            ", commentStatus='" + getCommentStatus() + "'" +
            ", assignment=" + getAssignment() +
            ", assignmentId=" + getAssignmentId() +
            ", originInfo=" + getOriginInfo() +
            ", frozenRequestNo='" + getFrozenRequestNo() + "'" +
            ", productsGroupId=" + getProductsGroupId() +
            ", confine='" + getConfine() + "'" +
            ", auditInfo=" + getAuditInfo() +
            ", auditInfoId=" + getAuditInfoId() +
            ", autoFinancePublishValidTime=" + getAutoFinancePublishValidTime() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
