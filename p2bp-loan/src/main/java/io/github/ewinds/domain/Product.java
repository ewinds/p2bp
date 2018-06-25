package io.github.ewinds.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import io.github.ewinds.domain.enumeration.ProductState;

import io.github.ewinds.domain.enumeration.InterestCalculationPeriod;

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

    @Column(name = "amount", precision=10, scale=2)
    private BigDecimal amount;

    @Column(name = "interest_rate", precision=10, scale=2)
    private BigDecimal interestRate;

    @Column(name = "valid_day")
    private Integer validDay;

    @Column(name = "publish_date")
    private Instant publishDate;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "success_date")
    private Instant successDate;

    @Column(name = "start_interest_date")
    private Instant startInterestDate;

    @Column(name = "repay_type")
    private String repayType;

    @Enumerated(EnumType.STRING)
    @Column(name = "period_type")
    private InterestCalculationPeriod periodType;

    @Column(name = "period")
    private Integer period;

    @Column(name = "repay_times")
    private Integer repayTimes;

    @Column(name = "manage_fee", precision=10, scale=2)
    private BigDecimal manageFee;

    @Column(name = "manage_fee_scale", precision=10, scale=2)
    private BigDecimal manageFeeScale;

    @Column(name = "part_flg")
    private String partFlg;

    @Column(name = "full_flg")
    private String fullFlg;

    @Column(name = "failed_flg")
    private String failedFlg;

    @Column(name = "amount_yes", precision=10, scale=2)
    private BigDecimal amountYes;

    @Column(name = "amount_wait", precision=10, scale=2)
    private BigDecimal amountWait;

    @Column(name = "amount_scale", precision=10, scale=2)
    private BigDecimal amountScale;

    @Column(name = "min_tender_amount", precision=10, scale=2)
    private BigDecimal minTenderAmount;

    @Column(name = "max_tender_amount", precision=10, scale=2)
    private BigDecimal maxTenderAmount;

    @Column(name = "tender_award_flg")
    private String tenderAwardFlg;

    @Column(name = "tender_failure_award_flg")
    private String tenderFailureAwardFlg;

    @Column(name = "tender_award_amount", precision=10, scale=2)
    private BigDecimal tenderAwardAmount;

    @Column(name = "tender_award_scale", precision=10, scale=2)
    private BigDecimal tenderAwardScale;

    @Column(name = "directional_pwd")
    private String directionalPwd;

    @Column(name = "second_flg")
    private String secondFlg;

    @Column(name = "duein_amount", precision=10, scale=2)
    private BigDecimal dueinAmount;

    @Column(name = "invest_total_amount", precision=10, scale=2)
    private BigDecimal investTotalAmount;

    @Column(name = "wander_flg")
    private String wanderFlg;

    @Column(name = "tender_auto_payment_flg")
    private String tenderAutoPaymentFlg;

    @Column(name = "part_amount", precision=10, scale=2)
    private BigDecimal partAmount;

    @Column(name = "max_part")
    private Integer maxPart;

    @Column(name = "tender_max_times")
    private Integer tenderMaxTimes;

    @Column(name = "prepayment_flg")
    private String prepaymentFlg;

    @Column(name = "prepayment_interest_min_days")
    private Integer prepaymentInterestMinDays;

    @Column(name = "prepayment_min_amount", precision=10, scale=2)
    private BigDecimal prepaymentMinAmount;

    @Column(name = "prepayment_max_times")
    private Integer prepaymentMaxTimes;

    @Column(name = "guarantee_type")
    private String guaranteeType;

    @Column(name = "guarantee_others")
    private String guaranteeOthers;

    @Column(name = "service_fee_type")
    private String serviceFeeType;

    @Column(name = "service_fee", precision=10, scale=2)
    private BigDecimal serviceFee;

    @Column(name = "parking_fee", precision=10, scale=2)
    private BigDecimal parkingFee;

    @Column(name = "upd_date")
    private Instant updDate;

    @Column(name = "rate_calculation_type")
    private String rateCalculationType;

    @Column(name = "full_date")
    private Instant fullDate;

    @Column(name = "novice_flg")
    private String noviceFlg;

    @Column(name = "rate_type")
    private String rateType;

    @Column(name = "rate_input_value", precision=10, scale=2)
    private BigDecimal rateInputValue;

    @Column(name = "last_replay_date")
    private Instant lastReplayDate;

    @Column(name = "transfer_can_flg")
    private String transferCanFlg;

    @Column(name = "transfer_froze_time")
    private Integer transferFrozeTime;

    @Column(name = "min_increasing_amount", precision=10, scale=2)
    private BigDecimal minIncreasingAmount;

    @Column(name = "ver")
    private String ver;

    @Column(name = "id_card_check_flg")
    private String idCardCheckFlg;

    @Column(name = "marriage_check_flg")
    private String marriageCheckFlg;

    @Column(name = "household_check_flg")
    private String householdCheckFlg;

    @Column(name = "credibility_check_flg")
    private String credibilityCheckFlg;

    @Column(name = "guarantee_check_flg")
    private String guaranteeCheckFlg;

    @Size(max = 1024)
    @Column(name = "purpose", length = 1024)
    private String purpose;

    @Column(name = "estate_check_flg")
    private String estateCheckFlg;

    @Column(name = "guarantee_id")
    private String guaranteeId;

    @OneToOne
    @JoinColumn(unique = true)
    private OriginEnterprise originEnterprise;

    @OneToOne
    @JoinColumn(unique = true)
    private OriginIndividual originIndividual;

    @ManyToOne
    private ProductSpec spec;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public Product amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public Product interestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getValidDay() {
        return validDay;
    }

    public Product validDay(Integer validDay) {
        this.validDay = validDay;
        return this;
    }

    public void setValidDay(Integer validDay) {
        this.validDay = validDay;
    }

    public Instant getPublishDate() {
        return publishDate;
    }

    public Product publishDate(Instant publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public void setPublishDate(Instant publishDate) {
        this.publishDate = publishDate;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Product startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Product endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Instant getSuccessDate() {
        return successDate;
    }

    public Product successDate(Instant successDate) {
        this.successDate = successDate;
        return this;
    }

    public void setSuccessDate(Instant successDate) {
        this.successDate = successDate;
    }

    public Instant getStartInterestDate() {
        return startInterestDate;
    }

    public Product startInterestDate(Instant startInterestDate) {
        this.startInterestDate = startInterestDate;
        return this;
    }

    public void setStartInterestDate(Instant startInterestDate) {
        this.startInterestDate = startInterestDate;
    }

    public String getRepayType() {
        return repayType;
    }

    public Product repayType(String repayType) {
        this.repayType = repayType;
        return this;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public InterestCalculationPeriod getPeriodType() {
        return periodType;
    }

    public Product periodType(InterestCalculationPeriod periodType) {
        this.periodType = periodType;
        return this;
    }

    public void setPeriodType(InterestCalculationPeriod periodType) {
        this.periodType = periodType;
    }

    public Integer getPeriod() {
        return period;
    }

    public Product period(Integer period) {
        this.period = period;
        return this;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getRepayTimes() {
        return repayTimes;
    }

    public Product repayTimes(Integer repayTimes) {
        this.repayTimes = repayTimes;
        return this;
    }

    public void setRepayTimes(Integer repayTimes) {
        this.repayTimes = repayTimes;
    }

    public BigDecimal getManageFee() {
        return manageFee;
    }

    public Product manageFee(BigDecimal manageFee) {
        this.manageFee = manageFee;
        return this;
    }

    public void setManageFee(BigDecimal manageFee) {
        this.manageFee = manageFee;
    }

    public BigDecimal getManageFeeScale() {
        return manageFeeScale;
    }

    public Product manageFeeScale(BigDecimal manageFeeScale) {
        this.manageFeeScale = manageFeeScale;
        return this;
    }

    public void setManageFeeScale(BigDecimal manageFeeScale) {
        this.manageFeeScale = manageFeeScale;
    }

    public String getPartFlg() {
        return partFlg;
    }

    public Product partFlg(String partFlg) {
        this.partFlg = partFlg;
        return this;
    }

    public void setPartFlg(String partFlg) {
        this.partFlg = partFlg;
    }

    public String getFullFlg() {
        return fullFlg;
    }

    public Product fullFlg(String fullFlg) {
        this.fullFlg = fullFlg;
        return this;
    }

    public void setFullFlg(String fullFlg) {
        this.fullFlg = fullFlg;
    }

    public String getFailedFlg() {
        return failedFlg;
    }

    public Product failedFlg(String failedFlg) {
        this.failedFlg = failedFlg;
        return this;
    }

    public void setFailedFlg(String failedFlg) {
        this.failedFlg = failedFlg;
    }

    public BigDecimal getAmountYes() {
        return amountYes;
    }

    public Product amountYes(BigDecimal amountYes) {
        this.amountYes = amountYes;
        return this;
    }

    public void setAmountYes(BigDecimal amountYes) {
        this.amountYes = amountYes;
    }

    public BigDecimal getAmountWait() {
        return amountWait;
    }

    public Product amountWait(BigDecimal amountWait) {
        this.amountWait = amountWait;
        return this;
    }

    public void setAmountWait(BigDecimal amountWait) {
        this.amountWait = amountWait;
    }

    public BigDecimal getAmountScale() {
        return amountScale;
    }

    public Product amountScale(BigDecimal amountScale) {
        this.amountScale = amountScale;
        return this;
    }

    public void setAmountScale(BigDecimal amountScale) {
        this.amountScale = amountScale;
    }

    public BigDecimal getMinTenderAmount() {
        return minTenderAmount;
    }

    public Product minTenderAmount(BigDecimal minTenderAmount) {
        this.minTenderAmount = minTenderAmount;
        return this;
    }

    public void setMinTenderAmount(BigDecimal minTenderAmount) {
        this.minTenderAmount = minTenderAmount;
    }

    public BigDecimal getMaxTenderAmount() {
        return maxTenderAmount;
    }

    public Product maxTenderAmount(BigDecimal maxTenderAmount) {
        this.maxTenderAmount = maxTenderAmount;
        return this;
    }

    public void setMaxTenderAmount(BigDecimal maxTenderAmount) {
        this.maxTenderAmount = maxTenderAmount;
    }

    public String getTenderAwardFlg() {
        return tenderAwardFlg;
    }

    public Product tenderAwardFlg(String tenderAwardFlg) {
        this.tenderAwardFlg = tenderAwardFlg;
        return this;
    }

    public void setTenderAwardFlg(String tenderAwardFlg) {
        this.tenderAwardFlg = tenderAwardFlg;
    }

    public String getTenderFailureAwardFlg() {
        return tenderFailureAwardFlg;
    }

    public Product tenderFailureAwardFlg(String tenderFailureAwardFlg) {
        this.tenderFailureAwardFlg = tenderFailureAwardFlg;
        return this;
    }

    public void setTenderFailureAwardFlg(String tenderFailureAwardFlg) {
        this.tenderFailureAwardFlg = tenderFailureAwardFlg;
    }

    public BigDecimal getTenderAwardAmount() {
        return tenderAwardAmount;
    }

    public Product tenderAwardAmount(BigDecimal tenderAwardAmount) {
        this.tenderAwardAmount = tenderAwardAmount;
        return this;
    }

    public void setTenderAwardAmount(BigDecimal tenderAwardAmount) {
        this.tenderAwardAmount = tenderAwardAmount;
    }

    public BigDecimal getTenderAwardScale() {
        return tenderAwardScale;
    }

    public Product tenderAwardScale(BigDecimal tenderAwardScale) {
        this.tenderAwardScale = tenderAwardScale;
        return this;
    }

    public void setTenderAwardScale(BigDecimal tenderAwardScale) {
        this.tenderAwardScale = tenderAwardScale;
    }

    public String getDirectionalPwd() {
        return directionalPwd;
    }

    public Product directionalPwd(String directionalPwd) {
        this.directionalPwd = directionalPwd;
        return this;
    }

    public void setDirectionalPwd(String directionalPwd) {
        this.directionalPwd = directionalPwd;
    }

    public String getSecondFlg() {
        return secondFlg;
    }

    public Product secondFlg(String secondFlg) {
        this.secondFlg = secondFlg;
        return this;
    }

    public void setSecondFlg(String secondFlg) {
        this.secondFlg = secondFlg;
    }

    public BigDecimal getDueinAmount() {
        return dueinAmount;
    }

    public Product dueinAmount(BigDecimal dueinAmount) {
        this.dueinAmount = dueinAmount;
        return this;
    }

    public void setDueinAmount(BigDecimal dueinAmount) {
        this.dueinAmount = dueinAmount;
    }

    public BigDecimal getInvestTotalAmount() {
        return investTotalAmount;
    }

    public Product investTotalAmount(BigDecimal investTotalAmount) {
        this.investTotalAmount = investTotalAmount;
        return this;
    }

    public void setInvestTotalAmount(BigDecimal investTotalAmount) {
        this.investTotalAmount = investTotalAmount;
    }

    public String getWanderFlg() {
        return wanderFlg;
    }

    public Product wanderFlg(String wanderFlg) {
        this.wanderFlg = wanderFlg;
        return this;
    }

    public void setWanderFlg(String wanderFlg) {
        this.wanderFlg = wanderFlg;
    }

    public String getTenderAutoPaymentFlg() {
        return tenderAutoPaymentFlg;
    }

    public Product tenderAutoPaymentFlg(String tenderAutoPaymentFlg) {
        this.tenderAutoPaymentFlg = tenderAutoPaymentFlg;
        return this;
    }

    public void setTenderAutoPaymentFlg(String tenderAutoPaymentFlg) {
        this.tenderAutoPaymentFlg = tenderAutoPaymentFlg;
    }

    public BigDecimal getPartAmount() {
        return partAmount;
    }

    public Product partAmount(BigDecimal partAmount) {
        this.partAmount = partAmount;
        return this;
    }

    public void setPartAmount(BigDecimal partAmount) {
        this.partAmount = partAmount;
    }

    public Integer getMaxPart() {
        return maxPart;
    }

    public Product maxPart(Integer maxPart) {
        this.maxPart = maxPart;
        return this;
    }

    public void setMaxPart(Integer maxPart) {
        this.maxPart = maxPart;
    }

    public Integer getTenderMaxTimes() {
        return tenderMaxTimes;
    }

    public Product tenderMaxTimes(Integer tenderMaxTimes) {
        this.tenderMaxTimes = tenderMaxTimes;
        return this;
    }

    public void setTenderMaxTimes(Integer tenderMaxTimes) {
        this.tenderMaxTimes = tenderMaxTimes;
    }

    public String getPrepaymentFlg() {
        return prepaymentFlg;
    }

    public Product prepaymentFlg(String prepaymentFlg) {
        this.prepaymentFlg = prepaymentFlg;
        return this;
    }

    public void setPrepaymentFlg(String prepaymentFlg) {
        this.prepaymentFlg = prepaymentFlg;
    }

    public Integer getPrepaymentInterestMinDays() {
        return prepaymentInterestMinDays;
    }

    public Product prepaymentInterestMinDays(Integer prepaymentInterestMinDays) {
        this.prepaymentInterestMinDays = prepaymentInterestMinDays;
        return this;
    }

    public void setPrepaymentInterestMinDays(Integer prepaymentInterestMinDays) {
        this.prepaymentInterestMinDays = prepaymentInterestMinDays;
    }

    public BigDecimal getPrepaymentMinAmount() {
        return prepaymentMinAmount;
    }

    public Product prepaymentMinAmount(BigDecimal prepaymentMinAmount) {
        this.prepaymentMinAmount = prepaymentMinAmount;
        return this;
    }

    public void setPrepaymentMinAmount(BigDecimal prepaymentMinAmount) {
        this.prepaymentMinAmount = prepaymentMinAmount;
    }

    public Integer getPrepaymentMaxTimes() {
        return prepaymentMaxTimes;
    }

    public Product prepaymentMaxTimes(Integer prepaymentMaxTimes) {
        this.prepaymentMaxTimes = prepaymentMaxTimes;
        return this;
    }

    public void setPrepaymentMaxTimes(Integer prepaymentMaxTimes) {
        this.prepaymentMaxTimes = prepaymentMaxTimes;
    }

    public String getGuaranteeType() {
        return guaranteeType;
    }

    public Product guaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType;
        return this;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public String getGuaranteeOthers() {
        return guaranteeOthers;
    }

    public Product guaranteeOthers(String guaranteeOthers) {
        this.guaranteeOthers = guaranteeOthers;
        return this;
    }

    public void setGuaranteeOthers(String guaranteeOthers) {
        this.guaranteeOthers = guaranteeOthers;
    }

    public String getServiceFeeType() {
        return serviceFeeType;
    }

    public Product serviceFeeType(String serviceFeeType) {
        this.serviceFeeType = serviceFeeType;
        return this;
    }

    public void setServiceFeeType(String serviceFeeType) {
        this.serviceFeeType = serviceFeeType;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public Product serviceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
        return this;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getParkingFee() {
        return parkingFee;
    }

    public Product parkingFee(BigDecimal parkingFee) {
        this.parkingFee = parkingFee;
        return this;
    }

    public void setParkingFee(BigDecimal parkingFee) {
        this.parkingFee = parkingFee;
    }

    public Instant getUpdDate() {
        return updDate;
    }

    public Product updDate(Instant updDate) {
        this.updDate = updDate;
        return this;
    }

    public void setUpdDate(Instant updDate) {
        this.updDate = updDate;
    }

    public String getRateCalculationType() {
        return rateCalculationType;
    }

    public Product rateCalculationType(String rateCalculationType) {
        this.rateCalculationType = rateCalculationType;
        return this;
    }

    public void setRateCalculationType(String rateCalculationType) {
        this.rateCalculationType = rateCalculationType;
    }

    public Instant getFullDate() {
        return fullDate;
    }

    public Product fullDate(Instant fullDate) {
        this.fullDate = fullDate;
        return this;
    }

    public void setFullDate(Instant fullDate) {
        this.fullDate = fullDate;
    }

    public String getNoviceFlg() {
        return noviceFlg;
    }

    public Product noviceFlg(String noviceFlg) {
        this.noviceFlg = noviceFlg;
        return this;
    }

    public void setNoviceFlg(String noviceFlg) {
        this.noviceFlg = noviceFlg;
    }

    public String getRateType() {
        return rateType;
    }

    public Product rateType(String rateType) {
        this.rateType = rateType;
        return this;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getRateInputValue() {
        return rateInputValue;
    }

    public Product rateInputValue(BigDecimal rateInputValue) {
        this.rateInputValue = rateInputValue;
        return this;
    }

    public void setRateInputValue(BigDecimal rateInputValue) {
        this.rateInputValue = rateInputValue;
    }

    public Instant getLastReplayDate() {
        return lastReplayDate;
    }

    public Product lastReplayDate(Instant lastReplayDate) {
        this.lastReplayDate = lastReplayDate;
        return this;
    }

    public void setLastReplayDate(Instant lastReplayDate) {
        this.lastReplayDate = lastReplayDate;
    }

    public String getTransferCanFlg() {
        return transferCanFlg;
    }

    public Product transferCanFlg(String transferCanFlg) {
        this.transferCanFlg = transferCanFlg;
        return this;
    }

    public void setTransferCanFlg(String transferCanFlg) {
        this.transferCanFlg = transferCanFlg;
    }

    public Integer getTransferFrozeTime() {
        return transferFrozeTime;
    }

    public Product transferFrozeTime(Integer transferFrozeTime) {
        this.transferFrozeTime = transferFrozeTime;
        return this;
    }

    public void setTransferFrozeTime(Integer transferFrozeTime) {
        this.transferFrozeTime = transferFrozeTime;
    }

    public BigDecimal getMinIncreasingAmount() {
        return minIncreasingAmount;
    }

    public Product minIncreasingAmount(BigDecimal minIncreasingAmount) {
        this.minIncreasingAmount = minIncreasingAmount;
        return this;
    }

    public void setMinIncreasingAmount(BigDecimal minIncreasingAmount) {
        this.minIncreasingAmount = minIncreasingAmount;
    }

    public String getVer() {
        return ver;
    }

    public Product ver(String ver) {
        this.ver = ver;
        return this;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getIdCardCheckFlg() {
        return idCardCheckFlg;
    }

    public Product idCardCheckFlg(String idCardCheckFlg) {
        this.idCardCheckFlg = idCardCheckFlg;
        return this;
    }

    public void setIdCardCheckFlg(String idCardCheckFlg) {
        this.idCardCheckFlg = idCardCheckFlg;
    }

    public String getMarriageCheckFlg() {
        return marriageCheckFlg;
    }

    public Product marriageCheckFlg(String marriageCheckFlg) {
        this.marriageCheckFlg = marriageCheckFlg;
        return this;
    }

    public void setMarriageCheckFlg(String marriageCheckFlg) {
        this.marriageCheckFlg = marriageCheckFlg;
    }

    public String getHouseholdCheckFlg() {
        return householdCheckFlg;
    }

    public Product householdCheckFlg(String householdCheckFlg) {
        this.householdCheckFlg = householdCheckFlg;
        return this;
    }

    public void setHouseholdCheckFlg(String householdCheckFlg) {
        this.householdCheckFlg = householdCheckFlg;
    }

    public String getCredibilityCheckFlg() {
        return credibilityCheckFlg;
    }

    public Product credibilityCheckFlg(String credibilityCheckFlg) {
        this.credibilityCheckFlg = credibilityCheckFlg;
        return this;
    }

    public void setCredibilityCheckFlg(String credibilityCheckFlg) {
        this.credibilityCheckFlg = credibilityCheckFlg;
    }

    public String getGuaranteeCheckFlg() {
        return guaranteeCheckFlg;
    }

    public Product guaranteeCheckFlg(String guaranteeCheckFlg) {
        this.guaranteeCheckFlg = guaranteeCheckFlg;
        return this;
    }

    public void setGuaranteeCheckFlg(String guaranteeCheckFlg) {
        this.guaranteeCheckFlg = guaranteeCheckFlg;
    }

    public String getPurpose() {
        return purpose;
    }

    public Product purpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getEstateCheckFlg() {
        return estateCheckFlg;
    }

    public Product estateCheckFlg(String estateCheckFlg) {
        this.estateCheckFlg = estateCheckFlg;
        return this;
    }

    public void setEstateCheckFlg(String estateCheckFlg) {
        this.estateCheckFlg = estateCheckFlg;
    }

    public String getGuaranteeId() {
        return guaranteeId;
    }

    public Product guaranteeId(String guaranteeId) {
        this.guaranteeId = guaranteeId;
        return this;
    }

    public void setGuaranteeId(String guaranteeId) {
        this.guaranteeId = guaranteeId;
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

    public ProductSpec getSpec() {
        return spec;
    }

    public Product spec(ProductSpec productSpec) {
        this.spec = productSpec;
        return this;
    }

    public void setSpec(ProductSpec productSpec) {
        this.spec = productSpec;
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
            ", amount=" + getAmount() +
            ", interestRate=" + getInterestRate() +
            ", validDay=" + getValidDay() +
            ", publishDate='" + getPublishDate() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", successDate='" + getSuccessDate() + "'" +
            ", startInterestDate='" + getStartInterestDate() + "'" +
            ", repayType='" + getRepayType() + "'" +
            ", periodType='" + getPeriodType() + "'" +
            ", period=" + getPeriod() +
            ", repayTimes=" + getRepayTimes() +
            ", manageFee=" + getManageFee() +
            ", manageFeeScale=" + getManageFeeScale() +
            ", partFlg='" + getPartFlg() + "'" +
            ", fullFlg='" + getFullFlg() + "'" +
            ", failedFlg='" + getFailedFlg() + "'" +
            ", amountYes=" + getAmountYes() +
            ", amountWait=" + getAmountWait() +
            ", amountScale=" + getAmountScale() +
            ", minTenderAmount=" + getMinTenderAmount() +
            ", maxTenderAmount=" + getMaxTenderAmount() +
            ", tenderAwardFlg='" + getTenderAwardFlg() + "'" +
            ", tenderFailureAwardFlg='" + getTenderFailureAwardFlg() + "'" +
            ", tenderAwardAmount=" + getTenderAwardAmount() +
            ", tenderAwardScale=" + getTenderAwardScale() +
            ", directionalPwd='" + getDirectionalPwd() + "'" +
            ", secondFlg='" + getSecondFlg() + "'" +
            ", dueinAmount=" + getDueinAmount() +
            ", investTotalAmount=" + getInvestTotalAmount() +
            ", wanderFlg='" + getWanderFlg() + "'" +
            ", tenderAutoPaymentFlg='" + getTenderAutoPaymentFlg() + "'" +
            ", partAmount=" + getPartAmount() +
            ", maxPart=" + getMaxPart() +
            ", tenderMaxTimes=" + getTenderMaxTimes() +
            ", prepaymentFlg='" + getPrepaymentFlg() + "'" +
            ", prepaymentInterestMinDays=" + getPrepaymentInterestMinDays() +
            ", prepaymentMinAmount=" + getPrepaymentMinAmount() +
            ", prepaymentMaxTimes=" + getPrepaymentMaxTimes() +
            ", guaranteeType='" + getGuaranteeType() + "'" +
            ", guaranteeOthers='" + getGuaranteeOthers() + "'" +
            ", serviceFeeType='" + getServiceFeeType() + "'" +
            ", serviceFee=" + getServiceFee() +
            ", parkingFee=" + getParkingFee() +
            ", updDate='" + getUpdDate() + "'" +
            ", rateCalculationType='" + getRateCalculationType() + "'" +
            ", fullDate='" + getFullDate() + "'" +
            ", noviceFlg='" + getNoviceFlg() + "'" +
            ", rateType='" + getRateType() + "'" +
            ", rateInputValue=" + getRateInputValue() +
            ", lastReplayDate='" + getLastReplayDate() + "'" +
            ", transferCanFlg='" + getTransferCanFlg() + "'" +
            ", transferFrozeTime=" + getTransferFrozeTime() +
            ", minIncreasingAmount=" + getMinIncreasingAmount() +
            ", ver='" + getVer() + "'" +
            ", idCardCheckFlg='" + getIdCardCheckFlg() + "'" +
            ", marriageCheckFlg='" + getMarriageCheckFlg() + "'" +
            ", householdCheckFlg='" + getHouseholdCheckFlg() + "'" +
            ", credibilityCheckFlg='" + getCredibilityCheckFlg() + "'" +
            ", guaranteeCheckFlg='" + getGuaranteeCheckFlg() + "'" +
            ", purpose='" + getPurpose() + "'" +
            ", estateCheckFlg='" + getEstateCheckFlg() + "'" +
            ", guaranteeId='" + getGuaranteeId() + "'" +
            "}";
    }
}
