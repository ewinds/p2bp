package io.github.ewinds.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import io.github.ewinds.domain.enumeration.ProductCategory;

/**
 * A ProductSpec.
 */
@Entity
@Table(name = "product_spec")
public class ProductSpec extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ProductCategory category;

    @Column(name = "name")
    private String name;

    @Column(name = "start_interest_type")
    private String startInterestType;

    @Column(name = "start_interest_time")
    private String startInterestTime;

    @Column(name = "repay_time_type")
    private String repayTimeType;

    @Column(name = "principal_assign_accounts")
    private String principalAssignAccounts;

    @Column(name = "principal_special_accounts_id")
    private String principalSpecialAccountsId;

    @Column(name = "interest_assign_accounts")
    private String interestAssignAccounts;

    @Column(name = "interest_special_accounts_id")
    private String interestSpecialAccountsId;

    @Column(name = "bail")
    private String bail;

    @Column(name = "bail_amount", precision=10, scale=2)
    private BigDecimal bailAmount;

    @Column(name = "product_join_cost")
    private String productJoinCost;

    @Column(name = "join_cost", precision=10, scale=2)
    private BigDecimal joinCost;

    @Column(name = "product_exit_cost")
    private String productExitCost;

    @Column(name = "exit_cost", precision=10, scale=2)
    private BigDecimal exitCost;

    @Column(name = "file_contract_id")
    private String fileContractId;

    @Column(name = "img_view_id")
    private String imgViewId;

    @Column(name = "struts")
    private String struts;

    @Size(max = 1024)
    @Column(name = "products_description", length = 1024)
    private String productsDescription;

    @Column(name = "del_flag")
    private Boolean delFlag;

    @Column(name = "pub_date")
    private Instant pubDate;

    @Column(name = "day_rate_calculation")
    private String dayRateCalculation;

    @Column(name = "prod_flg")
    private String prodFlg;

    @Column(name = "extend_attribute")
    private String extendAttribute;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public ProductSpec category(ProductCategory category) {
        this.category = category;
        return this;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public ProductSpec name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartInterestType() {
        return startInterestType;
    }

    public ProductSpec startInterestType(String startInterestType) {
        this.startInterestType = startInterestType;
        return this;
    }

    public void setStartInterestType(String startInterestType) {
        this.startInterestType = startInterestType;
    }

    public String getStartInterestTime() {
        return startInterestTime;
    }

    public ProductSpec startInterestTime(String startInterestTime) {
        this.startInterestTime = startInterestTime;
        return this;
    }

    public void setStartInterestTime(String startInterestTime) {
        this.startInterestTime = startInterestTime;
    }

    public String getRepayTimeType() {
        return repayTimeType;
    }

    public ProductSpec repayTimeType(String repayTimeType) {
        this.repayTimeType = repayTimeType;
        return this;
    }

    public void setRepayTimeType(String repayTimeType) {
        this.repayTimeType = repayTimeType;
    }

    public String getPrincipalAssignAccounts() {
        return principalAssignAccounts;
    }

    public ProductSpec principalAssignAccounts(String principalAssignAccounts) {
        this.principalAssignAccounts = principalAssignAccounts;
        return this;
    }

    public void setPrincipalAssignAccounts(String principalAssignAccounts) {
        this.principalAssignAccounts = principalAssignAccounts;
    }

    public String getPrincipalSpecialAccountsId() {
        return principalSpecialAccountsId;
    }

    public ProductSpec principalSpecialAccountsId(String principalSpecialAccountsId) {
        this.principalSpecialAccountsId = principalSpecialAccountsId;
        return this;
    }

    public void setPrincipalSpecialAccountsId(String principalSpecialAccountsId) {
        this.principalSpecialAccountsId = principalSpecialAccountsId;
    }

    public String getInterestAssignAccounts() {
        return interestAssignAccounts;
    }

    public ProductSpec interestAssignAccounts(String interestAssignAccounts) {
        this.interestAssignAccounts = interestAssignAccounts;
        return this;
    }

    public void setInterestAssignAccounts(String interestAssignAccounts) {
        this.interestAssignAccounts = interestAssignAccounts;
    }

    public String getInterestSpecialAccountsId() {
        return interestSpecialAccountsId;
    }

    public ProductSpec interestSpecialAccountsId(String interestSpecialAccountsId) {
        this.interestSpecialAccountsId = interestSpecialAccountsId;
        return this;
    }

    public void setInterestSpecialAccountsId(String interestSpecialAccountsId) {
        this.interestSpecialAccountsId = interestSpecialAccountsId;
    }

    public String getBail() {
        return bail;
    }

    public ProductSpec bail(String bail) {
        this.bail = bail;
        return this;
    }

    public void setBail(String bail) {
        this.bail = bail;
    }

    public BigDecimal getBailAmount() {
        return bailAmount;
    }

    public ProductSpec bailAmount(BigDecimal bailAmount) {
        this.bailAmount = bailAmount;
        return this;
    }

    public void setBailAmount(BigDecimal bailAmount) {
        this.bailAmount = bailAmount;
    }

    public String getProductJoinCost() {
        return productJoinCost;
    }

    public ProductSpec productJoinCost(String productJoinCost) {
        this.productJoinCost = productJoinCost;
        return this;
    }

    public void setProductJoinCost(String productJoinCost) {
        this.productJoinCost = productJoinCost;
    }

    public BigDecimal getJoinCost() {
        return joinCost;
    }

    public ProductSpec joinCost(BigDecimal joinCost) {
        this.joinCost = joinCost;
        return this;
    }

    public void setJoinCost(BigDecimal joinCost) {
        this.joinCost = joinCost;
    }

    public String getProductExitCost() {
        return productExitCost;
    }

    public ProductSpec productExitCost(String productExitCost) {
        this.productExitCost = productExitCost;
        return this;
    }

    public void setProductExitCost(String productExitCost) {
        this.productExitCost = productExitCost;
    }

    public BigDecimal getExitCost() {
        return exitCost;
    }

    public ProductSpec exitCost(BigDecimal exitCost) {
        this.exitCost = exitCost;
        return this;
    }

    public void setExitCost(BigDecimal exitCost) {
        this.exitCost = exitCost;
    }

    public String getFileContractId() {
        return fileContractId;
    }

    public ProductSpec fileContractId(String fileContractId) {
        this.fileContractId = fileContractId;
        return this;
    }

    public void setFileContractId(String fileContractId) {
        this.fileContractId = fileContractId;
    }

    public String getImgViewId() {
        return imgViewId;
    }

    public ProductSpec imgViewId(String imgViewId) {
        this.imgViewId = imgViewId;
        return this;
    }

    public void setImgViewId(String imgViewId) {
        this.imgViewId = imgViewId;
    }

    public String getStruts() {
        return struts;
    }

    public ProductSpec struts(String struts) {
        this.struts = struts;
        return this;
    }

    public void setStruts(String struts) {
        this.struts = struts;
    }

    public String getProductsDescription() {
        return productsDescription;
    }

    public ProductSpec productsDescription(String productsDescription) {
        this.productsDescription = productsDescription;
        return this;
    }

    public void setProductsDescription(String productsDescription) {
        this.productsDescription = productsDescription;
    }

    public Boolean isDelFlag() {
        return delFlag;
    }

    public ProductSpec delFlag(Boolean delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Instant getPubDate() {
        return pubDate;
    }

    public ProductSpec pubDate(Instant pubDate) {
        this.pubDate = pubDate;
        return this;
    }

    public void setPubDate(Instant pubDate) {
        this.pubDate = pubDate;
    }

    public String getDayRateCalculation() {
        return dayRateCalculation;
    }

    public ProductSpec dayRateCalculation(String dayRateCalculation) {
        this.dayRateCalculation = dayRateCalculation;
        return this;
    }

    public void setDayRateCalculation(String dayRateCalculation) {
        this.dayRateCalculation = dayRateCalculation;
    }

    public String getProdFlg() {
        return prodFlg;
    }

    public ProductSpec prodFlg(String prodFlg) {
        this.prodFlg = prodFlg;
        return this;
    }

    public void setProdFlg(String prodFlg) {
        this.prodFlg = prodFlg;
    }

    public String getExtendAttribute() {
        return extendAttribute;
    }

    public ProductSpec extendAttribute(String extendAttribute) {
        this.extendAttribute = extendAttribute;
        return this;
    }

    public void setExtendAttribute(String extendAttribute) {
        this.extendAttribute = extendAttribute;
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
        ProductSpec productSpec = (ProductSpec) o;
        if (productSpec.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productSpec.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductSpec{" +
            "id=" + getId() +
            ", category='" + getCategory() + "'" +
            ", name='" + getName() + "'" +
            ", startInterestType='" + getStartInterestType() + "'" +
            ", startInterestTime='" + getStartInterestTime() + "'" +
            ", repayTimeType='" + getRepayTimeType() + "'" +
            ", principalAssignAccounts='" + getPrincipalAssignAccounts() + "'" +
            ", principalSpecialAccountsId='" + getPrincipalSpecialAccountsId() + "'" +
            ", interestAssignAccounts='" + getInterestAssignAccounts() + "'" +
            ", interestSpecialAccountsId='" + getInterestSpecialAccountsId() + "'" +
            ", bail='" + getBail() + "'" +
            ", bailAmount=" + getBailAmount() +
            ", productJoinCost='" + getProductJoinCost() + "'" +
            ", joinCost=" + getJoinCost() +
            ", productExitCost='" + getProductExitCost() + "'" +
            ", exitCost=" + getExitCost() +
            ", fileContractId='" + getFileContractId() + "'" +
            ", imgViewId='" + getImgViewId() + "'" +
            ", struts='" + getStruts() + "'" +
            ", productsDescription='" + getProductsDescription() + "'" +
            ", delFlag='" + isDelFlag() + "'" +
            ", pubDate='" + getPubDate() + "'" +
            ", dayRateCalculation='" + getDayRateCalculation() + "'" +
            ", prodFlg='" + getProdFlg() + "'" +
            ", extendAttribute='" + getExtendAttribute() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
