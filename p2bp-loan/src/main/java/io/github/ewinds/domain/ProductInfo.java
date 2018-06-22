package io.github.ewinds.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import io.github.ewinds.domain.enumeration.InterestCalculationPeriod;

/**
 * A ProductInfo.
 */
@Entity
@Table(name = "product_info")
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @OneToOne
    @JoinColumn(unique = true)
    private Product product;

    @ManyToOne
    private ProductSpec spec;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public ProductInfo amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public ProductInfo interestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getValidDay() {
        return validDay;
    }

    public ProductInfo validDay(Integer validDay) {
        this.validDay = validDay;
        return this;
    }

    public void setValidDay(Integer validDay) {
        this.validDay = validDay;
    }

    public Instant getPublishDate() {
        return publishDate;
    }

    public ProductInfo publishDate(Instant publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public void setPublishDate(Instant publishDate) {
        this.publishDate = publishDate;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public ProductInfo startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public ProductInfo endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Instant getSuccessDate() {
        return successDate;
    }

    public ProductInfo successDate(Instant successDate) {
        this.successDate = successDate;
        return this;
    }

    public void setSuccessDate(Instant successDate) {
        this.successDate = successDate;
    }

    public Instant getStartInterestDate() {
        return startInterestDate;
    }

    public ProductInfo startInterestDate(Instant startInterestDate) {
        this.startInterestDate = startInterestDate;
        return this;
    }

    public void setStartInterestDate(Instant startInterestDate) {
        this.startInterestDate = startInterestDate;
    }

    public String getRepayType() {
        return repayType;
    }

    public ProductInfo repayType(String repayType) {
        this.repayType = repayType;
        return this;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public InterestCalculationPeriod getPeriodType() {
        return periodType;
    }

    public ProductInfo periodType(InterestCalculationPeriod periodType) {
        this.periodType = periodType;
        return this;
    }

    public void setPeriodType(InterestCalculationPeriod periodType) {
        this.periodType = periodType;
    }

    public Integer getPeriod() {
        return period;
    }

    public ProductInfo period(Integer period) {
        this.period = period;
        return this;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getRepayTimes() {
        return repayTimes;
    }

    public ProductInfo repayTimes(Integer repayTimes) {
        this.repayTimes = repayTimes;
        return this;
    }

    public void setRepayTimes(Integer repayTimes) {
        this.repayTimes = repayTimes;
    }

    public BigDecimal getManageFee() {
        return manageFee;
    }

    public ProductInfo manageFee(BigDecimal manageFee) {
        this.manageFee = manageFee;
        return this;
    }

    public void setManageFee(BigDecimal manageFee) {
        this.manageFee = manageFee;
    }

    public BigDecimal getManageFeeScale() {
        return manageFeeScale;
    }

    public ProductInfo manageFeeScale(BigDecimal manageFeeScale) {
        this.manageFeeScale = manageFeeScale;
        return this;
    }

    public void setManageFeeScale(BigDecimal manageFeeScale) {
        this.manageFeeScale = manageFeeScale;
    }

    public String getPartFlg() {
        return partFlg;
    }

    public ProductInfo partFlg(String partFlg) {
        this.partFlg = partFlg;
        return this;
    }

    public void setPartFlg(String partFlg) {
        this.partFlg = partFlg;
    }

    public String getFullFlg() {
        return fullFlg;
    }

    public ProductInfo fullFlg(String fullFlg) {
        this.fullFlg = fullFlg;
        return this;
    }

    public void setFullFlg(String fullFlg) {
        this.fullFlg = fullFlg;
    }

    public String getFailedFlg() {
        return failedFlg;
    }

    public ProductInfo failedFlg(String failedFlg) {
        this.failedFlg = failedFlg;
        return this;
    }

    public void setFailedFlg(String failedFlg) {
        this.failedFlg = failedFlg;
    }

    public BigDecimal getAmountYes() {
        return amountYes;
    }

    public ProductInfo amountYes(BigDecimal amountYes) {
        this.amountYes = amountYes;
        return this;
    }

    public void setAmountYes(BigDecimal amountYes) {
        this.amountYes = amountYes;
    }

    public BigDecimal getAmountWait() {
        return amountWait;
    }

    public ProductInfo amountWait(BigDecimal amountWait) {
        this.amountWait = amountWait;
        return this;
    }

    public void setAmountWait(BigDecimal amountWait) {
        this.amountWait = amountWait;
    }

    public BigDecimal getAmountScale() {
        return amountScale;
    }

    public ProductInfo amountScale(BigDecimal amountScale) {
        this.amountScale = amountScale;
        return this;
    }

    public void setAmountScale(BigDecimal amountScale) {
        this.amountScale = amountScale;
    }

    public BigDecimal getMinTenderAmount() {
        return minTenderAmount;
    }

    public ProductInfo minTenderAmount(BigDecimal minTenderAmount) {
        this.minTenderAmount = minTenderAmount;
        return this;
    }

    public void setMinTenderAmount(BigDecimal minTenderAmount) {
        this.minTenderAmount = minTenderAmount;
    }

    public BigDecimal getMaxTenderAmount() {
        return maxTenderAmount;
    }

    public ProductInfo maxTenderAmount(BigDecimal maxTenderAmount) {
        this.maxTenderAmount = maxTenderAmount;
        return this;
    }

    public void setMaxTenderAmount(BigDecimal maxTenderAmount) {
        this.maxTenderAmount = maxTenderAmount;
    }

    public String getTenderAwardFlg() {
        return tenderAwardFlg;
    }

    public ProductInfo tenderAwardFlg(String tenderAwardFlg) {
        this.tenderAwardFlg = tenderAwardFlg;
        return this;
    }

    public void setTenderAwardFlg(String tenderAwardFlg) {
        this.tenderAwardFlg = tenderAwardFlg;
    }

    public String getTenderFailureAwardFlg() {
        return tenderFailureAwardFlg;
    }

    public ProductInfo tenderFailureAwardFlg(String tenderFailureAwardFlg) {
        this.tenderFailureAwardFlg = tenderFailureAwardFlg;
        return this;
    }

    public void setTenderFailureAwardFlg(String tenderFailureAwardFlg) {
        this.tenderFailureAwardFlg = tenderFailureAwardFlg;
    }

    public BigDecimal getTenderAwardAmount() {
        return tenderAwardAmount;
    }

    public ProductInfo tenderAwardAmount(BigDecimal tenderAwardAmount) {
        this.tenderAwardAmount = tenderAwardAmount;
        return this;
    }

    public void setTenderAwardAmount(BigDecimal tenderAwardAmount) {
        this.tenderAwardAmount = tenderAwardAmount;
    }

    public BigDecimal getTenderAwardScale() {
        return tenderAwardScale;
    }

    public ProductInfo tenderAwardScale(BigDecimal tenderAwardScale) {
        this.tenderAwardScale = tenderAwardScale;
        return this;
    }

    public void setTenderAwardScale(BigDecimal tenderAwardScale) {
        this.tenderAwardScale = tenderAwardScale;
    }

    public String getDirectionalPwd() {
        return directionalPwd;
    }

    public ProductInfo directionalPwd(String directionalPwd) {
        this.directionalPwd = directionalPwd;
        return this;
    }

    public void setDirectionalPwd(String directionalPwd) {
        this.directionalPwd = directionalPwd;
    }

    public String getSecondFlg() {
        return secondFlg;
    }

    public ProductInfo secondFlg(String secondFlg) {
        this.secondFlg = secondFlg;
        return this;
    }

    public void setSecondFlg(String secondFlg) {
        this.secondFlg = secondFlg;
    }

    public BigDecimal getDueinAmount() {
        return dueinAmount;
    }

    public ProductInfo dueinAmount(BigDecimal dueinAmount) {
        this.dueinAmount = dueinAmount;
        return this;
    }

    public void setDueinAmount(BigDecimal dueinAmount) {
        this.dueinAmount = dueinAmount;
    }

    public BigDecimal getInvestTotalAmount() {
        return investTotalAmount;
    }

    public ProductInfo investTotalAmount(BigDecimal investTotalAmount) {
        this.investTotalAmount = investTotalAmount;
        return this;
    }

    public void setInvestTotalAmount(BigDecimal investTotalAmount) {
        this.investTotalAmount = investTotalAmount;
    }

    public String getWanderFlg() {
        return wanderFlg;
    }

    public ProductInfo wanderFlg(String wanderFlg) {
        this.wanderFlg = wanderFlg;
        return this;
    }

    public void setWanderFlg(String wanderFlg) {
        this.wanderFlg = wanderFlg;
    }

    public String getTenderAutoPaymentFlg() {
        return tenderAutoPaymentFlg;
    }

    public ProductInfo tenderAutoPaymentFlg(String tenderAutoPaymentFlg) {
        this.tenderAutoPaymentFlg = tenderAutoPaymentFlg;
        return this;
    }

    public void setTenderAutoPaymentFlg(String tenderAutoPaymentFlg) {
        this.tenderAutoPaymentFlg = tenderAutoPaymentFlg;
    }

    public BigDecimal getPartAmount() {
        return partAmount;
    }

    public ProductInfo partAmount(BigDecimal partAmount) {
        this.partAmount = partAmount;
        return this;
    }

    public void setPartAmount(BigDecimal partAmount) {
        this.partAmount = partAmount;
    }

    public Integer getMaxPart() {
        return maxPart;
    }

    public ProductInfo maxPart(Integer maxPart) {
        this.maxPart = maxPart;
        return this;
    }

    public void setMaxPart(Integer maxPart) {
        this.maxPart = maxPart;
    }

    public Integer getTenderMaxTimes() {
        return tenderMaxTimes;
    }

    public ProductInfo tenderMaxTimes(Integer tenderMaxTimes) {
        this.tenderMaxTimes = tenderMaxTimes;
        return this;
    }

    public void setTenderMaxTimes(Integer tenderMaxTimes) {
        this.tenderMaxTimes = tenderMaxTimes;
    }

    public String getPrepaymentFlg() {
        return prepaymentFlg;
    }

    public ProductInfo prepaymentFlg(String prepaymentFlg) {
        this.prepaymentFlg = prepaymentFlg;
        return this;
    }

    public void setPrepaymentFlg(String prepaymentFlg) {
        this.prepaymentFlg = prepaymentFlg;
    }

    public Integer getPrepaymentInterestMinDays() {
        return prepaymentInterestMinDays;
    }

    public ProductInfo prepaymentInterestMinDays(Integer prepaymentInterestMinDays) {
        this.prepaymentInterestMinDays = prepaymentInterestMinDays;
        return this;
    }

    public void setPrepaymentInterestMinDays(Integer prepaymentInterestMinDays) {
        this.prepaymentInterestMinDays = prepaymentInterestMinDays;
    }

    public BigDecimal getPrepaymentMinAmount() {
        return prepaymentMinAmount;
    }

    public ProductInfo prepaymentMinAmount(BigDecimal prepaymentMinAmount) {
        this.prepaymentMinAmount = prepaymentMinAmount;
        return this;
    }

    public void setPrepaymentMinAmount(BigDecimal prepaymentMinAmount) {
        this.prepaymentMinAmount = prepaymentMinAmount;
    }

    public Integer getPrepaymentMaxTimes() {
        return prepaymentMaxTimes;
    }

    public ProductInfo prepaymentMaxTimes(Integer prepaymentMaxTimes) {
        this.prepaymentMaxTimes = prepaymentMaxTimes;
        return this;
    }

    public void setPrepaymentMaxTimes(Integer prepaymentMaxTimes) {
        this.prepaymentMaxTimes = prepaymentMaxTimes;
    }

    public String getGuaranteeType() {
        return guaranteeType;
    }

    public ProductInfo guaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType;
        return this;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public String getGuaranteeOthers() {
        return guaranteeOthers;
    }

    public ProductInfo guaranteeOthers(String guaranteeOthers) {
        this.guaranteeOthers = guaranteeOthers;
        return this;
    }

    public void setGuaranteeOthers(String guaranteeOthers) {
        this.guaranteeOthers = guaranteeOthers;
    }

    public String getServiceFeeType() {
        return serviceFeeType;
    }

    public ProductInfo serviceFeeType(String serviceFeeType) {
        this.serviceFeeType = serviceFeeType;
        return this;
    }

    public void setServiceFeeType(String serviceFeeType) {
        this.serviceFeeType = serviceFeeType;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public ProductInfo serviceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
        return this;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getParkingFee() {
        return parkingFee;
    }

    public ProductInfo parkingFee(BigDecimal parkingFee) {
        this.parkingFee = parkingFee;
        return this;
    }

    public void setParkingFee(BigDecimal parkingFee) {
        this.parkingFee = parkingFee;
    }

    public Instant getUpdDate() {
        return updDate;
    }

    public ProductInfo updDate(Instant updDate) {
        this.updDate = updDate;
        return this;
    }

    public void setUpdDate(Instant updDate) {
        this.updDate = updDate;
    }

    public String getRateCalculationType() {
        return rateCalculationType;
    }

    public ProductInfo rateCalculationType(String rateCalculationType) {
        this.rateCalculationType = rateCalculationType;
        return this;
    }

    public void setRateCalculationType(String rateCalculationType) {
        this.rateCalculationType = rateCalculationType;
    }

    public Instant getFullDate() {
        return fullDate;
    }

    public ProductInfo fullDate(Instant fullDate) {
        this.fullDate = fullDate;
        return this;
    }

    public void setFullDate(Instant fullDate) {
        this.fullDate = fullDate;
    }

    public String getNoviceFlg() {
        return noviceFlg;
    }

    public ProductInfo noviceFlg(String noviceFlg) {
        this.noviceFlg = noviceFlg;
        return this;
    }

    public void setNoviceFlg(String noviceFlg) {
        this.noviceFlg = noviceFlg;
    }

    public String getRateType() {
        return rateType;
    }

    public ProductInfo rateType(String rateType) {
        this.rateType = rateType;
        return this;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getRateInputValue() {
        return rateInputValue;
    }

    public ProductInfo rateInputValue(BigDecimal rateInputValue) {
        this.rateInputValue = rateInputValue;
        return this;
    }

    public void setRateInputValue(BigDecimal rateInputValue) {
        this.rateInputValue = rateInputValue;
    }

    public Instant getLastReplayDate() {
        return lastReplayDate;
    }

    public ProductInfo lastReplayDate(Instant lastReplayDate) {
        this.lastReplayDate = lastReplayDate;
        return this;
    }

    public void setLastReplayDate(Instant lastReplayDate) {
        this.lastReplayDate = lastReplayDate;
    }

    public String getTransferCanFlg() {
        return transferCanFlg;
    }

    public ProductInfo transferCanFlg(String transferCanFlg) {
        this.transferCanFlg = transferCanFlg;
        return this;
    }

    public void setTransferCanFlg(String transferCanFlg) {
        this.transferCanFlg = transferCanFlg;
    }

    public Integer getTransferFrozeTime() {
        return transferFrozeTime;
    }

    public ProductInfo transferFrozeTime(Integer transferFrozeTime) {
        this.transferFrozeTime = transferFrozeTime;
        return this;
    }

    public void setTransferFrozeTime(Integer transferFrozeTime) {
        this.transferFrozeTime = transferFrozeTime;
    }

    public BigDecimal getMinIncreasingAmount() {
        return minIncreasingAmount;
    }

    public ProductInfo minIncreasingAmount(BigDecimal minIncreasingAmount) {
        this.minIncreasingAmount = minIncreasingAmount;
        return this;
    }

    public void setMinIncreasingAmount(BigDecimal minIncreasingAmount) {
        this.minIncreasingAmount = minIncreasingAmount;
    }

    public String getVer() {
        return ver;
    }

    public ProductInfo ver(String ver) {
        this.ver = ver;
        return this;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getIdCardCheckFlg() {
        return idCardCheckFlg;
    }

    public ProductInfo idCardCheckFlg(String idCardCheckFlg) {
        this.idCardCheckFlg = idCardCheckFlg;
        return this;
    }

    public void setIdCardCheckFlg(String idCardCheckFlg) {
        this.idCardCheckFlg = idCardCheckFlg;
    }

    public String getMarriageCheckFlg() {
        return marriageCheckFlg;
    }

    public ProductInfo marriageCheckFlg(String marriageCheckFlg) {
        this.marriageCheckFlg = marriageCheckFlg;
        return this;
    }

    public void setMarriageCheckFlg(String marriageCheckFlg) {
        this.marriageCheckFlg = marriageCheckFlg;
    }

    public String getHouseholdCheckFlg() {
        return householdCheckFlg;
    }

    public ProductInfo householdCheckFlg(String householdCheckFlg) {
        this.householdCheckFlg = householdCheckFlg;
        return this;
    }

    public void setHouseholdCheckFlg(String householdCheckFlg) {
        this.householdCheckFlg = householdCheckFlg;
    }

    public String getCredibilityCheckFlg() {
        return credibilityCheckFlg;
    }

    public ProductInfo credibilityCheckFlg(String credibilityCheckFlg) {
        this.credibilityCheckFlg = credibilityCheckFlg;
        return this;
    }

    public void setCredibilityCheckFlg(String credibilityCheckFlg) {
        this.credibilityCheckFlg = credibilityCheckFlg;
    }

    public String getGuaranteeCheckFlg() {
        return guaranteeCheckFlg;
    }

    public ProductInfo guaranteeCheckFlg(String guaranteeCheckFlg) {
        this.guaranteeCheckFlg = guaranteeCheckFlg;
        return this;
    }

    public void setGuaranteeCheckFlg(String guaranteeCheckFlg) {
        this.guaranteeCheckFlg = guaranteeCheckFlg;
    }

    public String getPurpose() {
        return purpose;
    }

    public ProductInfo purpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getEstateCheckFlg() {
        return estateCheckFlg;
    }

    public ProductInfo estateCheckFlg(String estateCheckFlg) {
        this.estateCheckFlg = estateCheckFlg;
        return this;
    }

    public void setEstateCheckFlg(String estateCheckFlg) {
        this.estateCheckFlg = estateCheckFlg;
    }

    public String getGuaranteeId() {
        return guaranteeId;
    }

    public ProductInfo guaranteeId(String guaranteeId) {
        this.guaranteeId = guaranteeId;
        return this;
    }

    public void setGuaranteeId(String guaranteeId) {
        this.guaranteeId = guaranteeId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ProductInfo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ProductInfo createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public ProductInfo lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public ProductInfo lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Product getProduct() {
        return product;
    }

    public ProductInfo product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductSpec getSpec() {
        return spec;
    }

    public ProductInfo spec(ProductSpec productSpec) {
        this.spec = productSpec;
        return this;
    }

    public void setSpec(ProductSpec productSpec) {
        this.spec = productSpec;
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
        ProductInfo productInfo = (ProductInfo) o;
        if (productInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
            "id=" + getId() +
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
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
