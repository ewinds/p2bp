package io.github.ewinds.web.rest;

import io.github.ewinds.LoanApp;

import io.github.ewinds.config.SecurityBeanOverrideConfiguration;

import io.github.ewinds.domain.ProductInfo;
import io.github.ewinds.repository.ProductInfoRepository;
import io.github.ewinds.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.ewinds.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProductInfoResource REST controller.
 *
 * @see ProductInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LoanApp.class, SecurityBeanOverrideConfiguration.class})
public class ProductInfoResourceIntTest {

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTEREST_RATE = new BigDecimal(2);

    private static final Integer DEFAULT_VALID_DAY = 1;
    private static final Integer UPDATED_VALID_DAY = 2;

    private static final Instant DEFAULT_PUBLISH_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUBLISH_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SUCCESS_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUCCESS_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_START_INTEREST_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_INTEREST_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REPAY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_REPAY_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_INTEREST_RATE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INTEREST_RATE_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PERIOD = 1;
    private static final Integer UPDATED_PERIOD = 2;

    private static final Integer DEFAULT_REPAY_TIMES = 1;
    private static final Integer UPDATED_REPAY_TIMES = 2;

    private static final BigDecimal DEFAULT_MANAGE_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MANAGE_FEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MANAGE_FEE_SCALE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MANAGE_FEE_SCALE = new BigDecimal(2);

    private static final String DEFAULT_PART_FLG = "AAAAAAAAAA";
    private static final String UPDATED_PART_FLG = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_FLG = "AAAAAAAAAA";
    private static final String UPDATED_FULL_FLG = "BBBBBBBBBB";

    private static final String DEFAULT_FAILED_FLG = "AAAAAAAAAA";
    private static final String UPDATED_FAILED_FLG = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT_YES = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT_YES = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMOUNT_WAIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT_WAIT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMOUNT_SCALE = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT_SCALE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_TENDER_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_TENDER_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAX_TENDER_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAX_TENDER_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_TENDER_AWARD_FLG = "AAAAAAAAAA";
    private static final String UPDATED_TENDER_AWARD_FLG = "BBBBBBBBBB";

    private static final String DEFAULT_TENDER_FAILURE_AWARD_FLG = "AAAAAAAAAA";
    private static final String UPDATED_TENDER_FAILURE_AWARD_FLG = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TENDER_AWARD_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_TENDER_AWARD_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TENDER_AWARD_SCALE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TENDER_AWARD_SCALE = new BigDecimal(2);

    private static final String DEFAULT_DIRECTIONAL_PWD = "AAAAAAAAAA";
    private static final String UPDATED_DIRECTIONAL_PWD = "BBBBBBBBBB";

    private static final String DEFAULT_SECOND_FLG = "AAAAAAAAAA";
    private static final String UPDATED_SECOND_FLG = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DUEIN_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DUEIN_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INVEST_TOTAL_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_INVEST_TOTAL_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_WANDER_FLG = "AAAAAAAAAA";
    private static final String UPDATED_WANDER_FLG = "BBBBBBBBBB";

    private static final String DEFAULT_TENDER_AUTO_PAYMENT_FLG = "AAAAAAAAAA";
    private static final String UPDATED_TENDER_AUTO_PAYMENT_FLG = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PART_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PART_AMOUNT = new BigDecimal(2);

    private static final Integer DEFAULT_MAX_PART = 1;
    private static final Integer UPDATED_MAX_PART = 2;

    private static final Integer DEFAULT_TENDER_MAX_TIMES = 1;
    private static final Integer UPDATED_TENDER_MAX_TIMES = 2;

    private static final String DEFAULT_PREPAYMENT_FLG = "AAAAAAAAAA";
    private static final String UPDATED_PREPAYMENT_FLG = "BBBBBBBBBB";

    private static final Integer DEFAULT_PREPAYMENT_INTEREST_MIN_DAYS = 1;
    private static final Integer UPDATED_PREPAYMENT_INTEREST_MIN_DAYS = 2;

    private static final BigDecimal DEFAULT_PREPAYMENT_MIN_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PREPAYMENT_MIN_AMOUNT = new BigDecimal(2);

    private static final Integer DEFAULT_PREPAYMENT_MAX_TIMES = 1;
    private static final Integer UPDATED_PREPAYMENT_MAX_TIMES = 2;

    private static final String DEFAULT_GUARANTEE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_GUARANTEE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_GUARANTEE_OTHERS = "AAAAAAAAAA";
    private static final String UPDATED_GUARANTEE_OTHERS = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_FEE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_FEE_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SERVICE_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SERVICE_FEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PARKING_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PARKING_FEE = new BigDecimal(2);

    private static final Instant DEFAULT_UPD_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPD_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_RATE_CALCULATION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RATE_CALCULATION_TYPE = "BBBBBBBBBB";

    private static final Instant DEFAULT_FULL_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FULL_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NOVICE_FLG = "AAAAAAAAAA";
    private static final String UPDATED_NOVICE_FLG = "BBBBBBBBBB";

    private static final String DEFAULT_RATE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RATE_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_RATE_INPUT_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_RATE_INPUT_VALUE = new BigDecimal(2);

    private static final Instant DEFAULT_LAST_REPLAY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_REPLAY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TRANSFER_CAN_FLG = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_CAN_FLG = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANSFER_FROZE_TIME = 1;
    private static final Integer UPDATED_TRANSFER_FROZE_TIME = 2;

    private static final BigDecimal DEFAULT_MIN_INCREASING_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_INCREASING_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_VER = "AAAAAAAAAA";
    private static final String UPDATED_VER = "BBBBBBBBBB";

    private static final String DEFAULT_ID_CARD_CHECK_FLG = "AAAAAAAAAA";
    private static final String UPDATED_ID_CARD_CHECK_FLG = "BBBBBBBBBB";

    private static final String DEFAULT_MARRIAGE_CHECK_FLG = "AAAAAAAAAA";
    private static final String UPDATED_MARRIAGE_CHECK_FLG = "BBBBBBBBBB";

    private static final String DEFAULT_HOUSEHOLD_CHECK_FLG = "AAAAAAAAAA";
    private static final String UPDATED_HOUSEHOLD_CHECK_FLG = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIBILITY_CHECK_FLG = "AAAAAAAAAA";
    private static final String UPDATED_CREDIBILITY_CHECK_FLG = "BBBBBBBBBB";

    private static final String DEFAULT_GUARANTEE_CHECK_FLG = "AAAAAAAAAA";
    private static final String UPDATED_GUARANTEE_CHECK_FLG = "BBBBBBBBBB";

    private static final String DEFAULT_PURPOSE = "AAAAAAAAAA";
    private static final String UPDATED_PURPOSE = "BBBBBBBBBB";

    private static final String DEFAULT_ESTATE_CHECK_FLG = "AAAAAAAAAA";
    private static final String UPDATED_ESTATE_CHECK_FLG = "BBBBBBBBBB";

    private static final String DEFAULT_GUARANTEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_GUARANTEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductInfoMockMvc;

    private ProductInfo productInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductInfoResource productInfoResource = new ProductInfoResource(productInfoRepository);
        this.restProductInfoMockMvc = MockMvcBuilders.standaloneSetup(productInfoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductInfo createEntity(EntityManager em) {
        ProductInfo productInfo = new ProductInfo()
            .amount(DEFAULT_AMOUNT)
            .interestRate(DEFAULT_INTEREST_RATE)
            .validDay(DEFAULT_VALID_DAY)
            .publishDate(DEFAULT_PUBLISH_DATE)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .successDate(DEFAULT_SUCCESS_DATE)
            .startInterestDate(DEFAULT_START_INTEREST_DATE)
            .repayType(DEFAULT_REPAY_TYPE)
            .interestRateType(DEFAULT_INTEREST_RATE_TYPE)
            .period(DEFAULT_PERIOD)
            .repayTimes(DEFAULT_REPAY_TIMES)
            .manageFee(DEFAULT_MANAGE_FEE)
            .manageFeeScale(DEFAULT_MANAGE_FEE_SCALE)
            .partFlg(DEFAULT_PART_FLG)
            .fullFlg(DEFAULT_FULL_FLG)
            .failedFlg(DEFAULT_FAILED_FLG)
            .amountYes(DEFAULT_AMOUNT_YES)
            .amountWait(DEFAULT_AMOUNT_WAIT)
            .amountScale(DEFAULT_AMOUNT_SCALE)
            .minTenderAmount(DEFAULT_MIN_TENDER_AMOUNT)
            .maxTenderAmount(DEFAULT_MAX_TENDER_AMOUNT)
            .tenderAwardFlg(DEFAULT_TENDER_AWARD_FLG)
            .tenderFailureAwardFlg(DEFAULT_TENDER_FAILURE_AWARD_FLG)
            .tenderAwardAmount(DEFAULT_TENDER_AWARD_AMOUNT)
            .tenderAwardScale(DEFAULT_TENDER_AWARD_SCALE)
            .directionalPwd(DEFAULT_DIRECTIONAL_PWD)
            .secondFlg(DEFAULT_SECOND_FLG)
            .dueinAmount(DEFAULT_DUEIN_AMOUNT)
            .investTotalAmount(DEFAULT_INVEST_TOTAL_AMOUNT)
            .wanderFlg(DEFAULT_WANDER_FLG)
            .tenderAutoPaymentFlg(DEFAULT_TENDER_AUTO_PAYMENT_FLG)
            .partAmount(DEFAULT_PART_AMOUNT)
            .maxPart(DEFAULT_MAX_PART)
            .tenderMaxTimes(DEFAULT_TENDER_MAX_TIMES)
            .prepaymentFlg(DEFAULT_PREPAYMENT_FLG)
            .prepaymentInterestMinDays(DEFAULT_PREPAYMENT_INTEREST_MIN_DAYS)
            .prepaymentMinAmount(DEFAULT_PREPAYMENT_MIN_AMOUNT)
            .prepaymentMaxTimes(DEFAULT_PREPAYMENT_MAX_TIMES)
            .guaranteeType(DEFAULT_GUARANTEE_TYPE)
            .guaranteeOthers(DEFAULT_GUARANTEE_OTHERS)
            .serviceFeeType(DEFAULT_SERVICE_FEE_TYPE)
            .serviceFee(DEFAULT_SERVICE_FEE)
            .parkingFee(DEFAULT_PARKING_FEE)
            .updDate(DEFAULT_UPD_DATE)
            .rateCalculationType(DEFAULT_RATE_CALCULATION_TYPE)
            .fullDate(DEFAULT_FULL_DATE)
            .noviceFlg(DEFAULT_NOVICE_FLG)
            .rateType(DEFAULT_RATE_TYPE)
            .rateInputValue(DEFAULT_RATE_INPUT_VALUE)
            .lastReplayDate(DEFAULT_LAST_REPLAY_DATE)
            .transferCanFlg(DEFAULT_TRANSFER_CAN_FLG)
            .transferFrozeTime(DEFAULT_TRANSFER_FROZE_TIME)
            .minIncreasingAmount(DEFAULT_MIN_INCREASING_AMOUNT)
            .ver(DEFAULT_VER)
            .idCardCheckFlg(DEFAULT_ID_CARD_CHECK_FLG)
            .marriageCheckFlg(DEFAULT_MARRIAGE_CHECK_FLG)
            .householdCheckFlg(DEFAULT_HOUSEHOLD_CHECK_FLG)
            .credibilityCheckFlg(DEFAULT_CREDIBILITY_CHECK_FLG)
            .guaranteeCheckFlg(DEFAULT_GUARANTEE_CHECK_FLG)
            .purpose(DEFAULT_PURPOSE)
            .estateCheckFlg(DEFAULT_ESTATE_CHECK_FLG)
            .guaranteeId(DEFAULT_GUARANTEE_ID);
        return productInfo;
    }

    @Before
    public void initTest() {
        productInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductInfo() throws Exception {
        int databaseSizeBeforeCreate = productInfoRepository.findAll().size();

        // Create the ProductInfo
        restProductInfoMockMvc.perform(post("/api/product-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productInfo)))
            .andExpect(status().isCreated());

        // Validate the ProductInfo in the database
        List<ProductInfo> productInfoList = productInfoRepository.findAll();
        assertThat(productInfoList).hasSize(databaseSizeBeforeCreate + 1);
        ProductInfo testProductInfo = productInfoList.get(productInfoList.size() - 1);
        assertThat(testProductInfo.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testProductInfo.getInterestRate()).isEqualTo(DEFAULT_INTEREST_RATE);
        assertThat(testProductInfo.getValidDay()).isEqualTo(DEFAULT_VALID_DAY);
        assertThat(testProductInfo.getPublishDate()).isEqualTo(DEFAULT_PUBLISH_DATE);
        assertThat(testProductInfo.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testProductInfo.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testProductInfo.getSuccessDate()).isEqualTo(DEFAULT_SUCCESS_DATE);
        assertThat(testProductInfo.getStartInterestDate()).isEqualTo(DEFAULT_START_INTEREST_DATE);
        assertThat(testProductInfo.getRepayType()).isEqualTo(DEFAULT_REPAY_TYPE);
        assertThat(testProductInfo.getInterestRateType()).isEqualTo(DEFAULT_INTEREST_RATE_TYPE);
        assertThat(testProductInfo.getPeriod()).isEqualTo(DEFAULT_PERIOD);
        assertThat(testProductInfo.getRepayTimes()).isEqualTo(DEFAULT_REPAY_TIMES);
        assertThat(testProductInfo.getManageFee()).isEqualTo(DEFAULT_MANAGE_FEE);
        assertThat(testProductInfo.getManageFeeScale()).isEqualTo(DEFAULT_MANAGE_FEE_SCALE);
        assertThat(testProductInfo.getPartFlg()).isEqualTo(DEFAULT_PART_FLG);
        assertThat(testProductInfo.getFullFlg()).isEqualTo(DEFAULT_FULL_FLG);
        assertThat(testProductInfo.getFailedFlg()).isEqualTo(DEFAULT_FAILED_FLG);
        assertThat(testProductInfo.getAmountYes()).isEqualTo(DEFAULT_AMOUNT_YES);
        assertThat(testProductInfo.getAmountWait()).isEqualTo(DEFAULT_AMOUNT_WAIT);
        assertThat(testProductInfo.getAmountScale()).isEqualTo(DEFAULT_AMOUNT_SCALE);
        assertThat(testProductInfo.getMinTenderAmount()).isEqualTo(DEFAULT_MIN_TENDER_AMOUNT);
        assertThat(testProductInfo.getMaxTenderAmount()).isEqualTo(DEFAULT_MAX_TENDER_AMOUNT);
        assertThat(testProductInfo.getTenderAwardFlg()).isEqualTo(DEFAULT_TENDER_AWARD_FLG);
        assertThat(testProductInfo.getTenderFailureAwardFlg()).isEqualTo(DEFAULT_TENDER_FAILURE_AWARD_FLG);
        assertThat(testProductInfo.getTenderAwardAmount()).isEqualTo(DEFAULT_TENDER_AWARD_AMOUNT);
        assertThat(testProductInfo.getTenderAwardScale()).isEqualTo(DEFAULT_TENDER_AWARD_SCALE);
        assertThat(testProductInfo.getDirectionalPwd()).isEqualTo(DEFAULT_DIRECTIONAL_PWD);
        assertThat(testProductInfo.getSecondFlg()).isEqualTo(DEFAULT_SECOND_FLG);
        assertThat(testProductInfo.getDueinAmount()).isEqualTo(DEFAULT_DUEIN_AMOUNT);
        assertThat(testProductInfo.getInvestTotalAmount()).isEqualTo(DEFAULT_INVEST_TOTAL_AMOUNT);
        assertThat(testProductInfo.getWanderFlg()).isEqualTo(DEFAULT_WANDER_FLG);
        assertThat(testProductInfo.getTenderAutoPaymentFlg()).isEqualTo(DEFAULT_TENDER_AUTO_PAYMENT_FLG);
        assertThat(testProductInfo.getPartAmount()).isEqualTo(DEFAULT_PART_AMOUNT);
        assertThat(testProductInfo.getMaxPart()).isEqualTo(DEFAULT_MAX_PART);
        assertThat(testProductInfo.getTenderMaxTimes()).isEqualTo(DEFAULT_TENDER_MAX_TIMES);
        assertThat(testProductInfo.getPrepaymentFlg()).isEqualTo(DEFAULT_PREPAYMENT_FLG);
        assertThat(testProductInfo.getPrepaymentInterestMinDays()).isEqualTo(DEFAULT_PREPAYMENT_INTEREST_MIN_DAYS);
        assertThat(testProductInfo.getPrepaymentMinAmount()).isEqualTo(DEFAULT_PREPAYMENT_MIN_AMOUNT);
        assertThat(testProductInfo.getPrepaymentMaxTimes()).isEqualTo(DEFAULT_PREPAYMENT_MAX_TIMES);
        assertThat(testProductInfo.getGuaranteeType()).isEqualTo(DEFAULT_GUARANTEE_TYPE);
        assertThat(testProductInfo.getGuaranteeOthers()).isEqualTo(DEFAULT_GUARANTEE_OTHERS);
        assertThat(testProductInfo.getServiceFeeType()).isEqualTo(DEFAULT_SERVICE_FEE_TYPE);
        assertThat(testProductInfo.getServiceFee()).isEqualTo(DEFAULT_SERVICE_FEE);
        assertThat(testProductInfo.getParkingFee()).isEqualTo(DEFAULT_PARKING_FEE);
        assertThat(testProductInfo.getUpdDate()).isEqualTo(DEFAULT_UPD_DATE);
        assertThat(testProductInfo.getRateCalculationType()).isEqualTo(DEFAULT_RATE_CALCULATION_TYPE);
        assertThat(testProductInfo.getFullDate()).isEqualTo(DEFAULT_FULL_DATE);
        assertThat(testProductInfo.getNoviceFlg()).isEqualTo(DEFAULT_NOVICE_FLG);
        assertThat(testProductInfo.getRateType()).isEqualTo(DEFAULT_RATE_TYPE);
        assertThat(testProductInfo.getRateInputValue()).isEqualTo(DEFAULT_RATE_INPUT_VALUE);
        assertThat(testProductInfo.getLastReplayDate()).isEqualTo(DEFAULT_LAST_REPLAY_DATE);
        assertThat(testProductInfo.getTransferCanFlg()).isEqualTo(DEFAULT_TRANSFER_CAN_FLG);
        assertThat(testProductInfo.getTransferFrozeTime()).isEqualTo(DEFAULT_TRANSFER_FROZE_TIME);
        assertThat(testProductInfo.getMinIncreasingAmount()).isEqualTo(DEFAULT_MIN_INCREASING_AMOUNT);
        assertThat(testProductInfo.getVer()).isEqualTo(DEFAULT_VER);
        assertThat(testProductInfo.getIdCardCheckFlg()).isEqualTo(DEFAULT_ID_CARD_CHECK_FLG);
        assertThat(testProductInfo.getMarriageCheckFlg()).isEqualTo(DEFAULT_MARRIAGE_CHECK_FLG);
        assertThat(testProductInfo.getHouseholdCheckFlg()).isEqualTo(DEFAULT_HOUSEHOLD_CHECK_FLG);
        assertThat(testProductInfo.getCredibilityCheckFlg()).isEqualTo(DEFAULT_CREDIBILITY_CHECK_FLG);
        assertThat(testProductInfo.getGuaranteeCheckFlg()).isEqualTo(DEFAULT_GUARANTEE_CHECK_FLG);
        assertThat(testProductInfo.getPurpose()).isEqualTo(DEFAULT_PURPOSE);
        assertThat(testProductInfo.getEstateCheckFlg()).isEqualTo(DEFAULT_ESTATE_CHECK_FLG);
        assertThat(testProductInfo.getGuaranteeId()).isEqualTo(DEFAULT_GUARANTEE_ID);
        assertThat(testProductInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductInfo.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testProductInfo.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createProductInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productInfoRepository.findAll().size();

        // Create the ProductInfo with an existing ID
        productInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductInfoMockMvc.perform(post("/api/product-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productInfo)))
            .andExpect(status().isBadRequest());

        // Validate the ProductInfo in the database
        List<ProductInfo> productInfoList = productInfoRepository.findAll();
        assertThat(productInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProductInfos() throws Exception {
        // Initialize the database
        productInfoRepository.saveAndFlush(productInfo);

        // Get all the productInfoList
        restProductInfoMockMvc.perform(get("/api/product-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].interestRate").value(hasItem(DEFAULT_INTEREST_RATE.intValue())))
            .andExpect(jsonPath("$.[*].validDay").value(hasItem(DEFAULT_VALID_DAY)))
            .andExpect(jsonPath("$.[*].publishDate").value(hasItem(DEFAULT_PUBLISH_DATE.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].successDate").value(hasItem(DEFAULT_SUCCESS_DATE.toString())))
            .andExpect(jsonPath("$.[*].startInterestDate").value(hasItem(DEFAULT_START_INTEREST_DATE.toString())))
            .andExpect(jsonPath("$.[*].repayType").value(hasItem(DEFAULT_REPAY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].interestRateType").value(hasItem(DEFAULT_INTEREST_RATE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].period").value(hasItem(DEFAULT_PERIOD)))
            .andExpect(jsonPath("$.[*].repayTimes").value(hasItem(DEFAULT_REPAY_TIMES)))
            .andExpect(jsonPath("$.[*].manageFee").value(hasItem(DEFAULT_MANAGE_FEE.intValue())))
            .andExpect(jsonPath("$.[*].manageFeeScale").value(hasItem(DEFAULT_MANAGE_FEE_SCALE.intValue())))
            .andExpect(jsonPath("$.[*].partFlg").value(hasItem(DEFAULT_PART_FLG.toString())))
            .andExpect(jsonPath("$.[*].fullFlg").value(hasItem(DEFAULT_FULL_FLG.toString())))
            .andExpect(jsonPath("$.[*].failedFlg").value(hasItem(DEFAULT_FAILED_FLG.toString())))
            .andExpect(jsonPath("$.[*].amountYes").value(hasItem(DEFAULT_AMOUNT_YES.intValue())))
            .andExpect(jsonPath("$.[*].amountWait").value(hasItem(DEFAULT_AMOUNT_WAIT.intValue())))
            .andExpect(jsonPath("$.[*].amountScale").value(hasItem(DEFAULT_AMOUNT_SCALE.intValue())))
            .andExpect(jsonPath("$.[*].minTenderAmount").value(hasItem(DEFAULT_MIN_TENDER_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].maxTenderAmount").value(hasItem(DEFAULT_MAX_TENDER_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].tenderAwardFlg").value(hasItem(DEFAULT_TENDER_AWARD_FLG.toString())))
            .andExpect(jsonPath("$.[*].tenderFailureAwardFlg").value(hasItem(DEFAULT_TENDER_FAILURE_AWARD_FLG.toString())))
            .andExpect(jsonPath("$.[*].tenderAwardAmount").value(hasItem(DEFAULT_TENDER_AWARD_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].tenderAwardScale").value(hasItem(DEFAULT_TENDER_AWARD_SCALE.intValue())))
            .andExpect(jsonPath("$.[*].directionalPwd").value(hasItem(DEFAULT_DIRECTIONAL_PWD.toString())))
            .andExpect(jsonPath("$.[*].secondFlg").value(hasItem(DEFAULT_SECOND_FLG.toString())))
            .andExpect(jsonPath("$.[*].dueinAmount").value(hasItem(DEFAULT_DUEIN_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].investTotalAmount").value(hasItem(DEFAULT_INVEST_TOTAL_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].wanderFlg").value(hasItem(DEFAULT_WANDER_FLG.toString())))
            .andExpect(jsonPath("$.[*].tenderAutoPaymentFlg").value(hasItem(DEFAULT_TENDER_AUTO_PAYMENT_FLG.toString())))
            .andExpect(jsonPath("$.[*].partAmount").value(hasItem(DEFAULT_PART_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].maxPart").value(hasItem(DEFAULT_MAX_PART)))
            .andExpect(jsonPath("$.[*].tenderMaxTimes").value(hasItem(DEFAULT_TENDER_MAX_TIMES)))
            .andExpect(jsonPath("$.[*].prepaymentFlg").value(hasItem(DEFAULT_PREPAYMENT_FLG.toString())))
            .andExpect(jsonPath("$.[*].prepaymentInterestMinDays").value(hasItem(DEFAULT_PREPAYMENT_INTEREST_MIN_DAYS)))
            .andExpect(jsonPath("$.[*].prepaymentMinAmount").value(hasItem(DEFAULT_PREPAYMENT_MIN_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].prepaymentMaxTimes").value(hasItem(DEFAULT_PREPAYMENT_MAX_TIMES)))
            .andExpect(jsonPath("$.[*].guaranteeType").value(hasItem(DEFAULT_GUARANTEE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].guaranteeOthers").value(hasItem(DEFAULT_GUARANTEE_OTHERS.toString())))
            .andExpect(jsonPath("$.[*].serviceFeeType").value(hasItem(DEFAULT_SERVICE_FEE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].serviceFee").value(hasItem(DEFAULT_SERVICE_FEE.intValue())))
            .andExpect(jsonPath("$.[*].parkingFee").value(hasItem(DEFAULT_PARKING_FEE.intValue())))
            .andExpect(jsonPath("$.[*].updDate").value(hasItem(DEFAULT_UPD_DATE.toString())))
            .andExpect(jsonPath("$.[*].rateCalculationType").value(hasItem(DEFAULT_RATE_CALCULATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].fullDate").value(hasItem(DEFAULT_FULL_DATE.toString())))
            .andExpect(jsonPath("$.[*].noviceFlg").value(hasItem(DEFAULT_NOVICE_FLG.toString())))
            .andExpect(jsonPath("$.[*].rateType").value(hasItem(DEFAULT_RATE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].rateInputValue").value(hasItem(DEFAULT_RATE_INPUT_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].lastReplayDate").value(hasItem(DEFAULT_LAST_REPLAY_DATE.toString())))
            .andExpect(jsonPath("$.[*].transferCanFlg").value(hasItem(DEFAULT_TRANSFER_CAN_FLG.toString())))
            .andExpect(jsonPath("$.[*].transferFrozeTime").value(hasItem(DEFAULT_TRANSFER_FROZE_TIME)))
            .andExpect(jsonPath("$.[*].minIncreasingAmount").value(hasItem(DEFAULT_MIN_INCREASING_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].ver").value(hasItem(DEFAULT_VER.toString())))
            .andExpect(jsonPath("$.[*].idCardCheckFlg").value(hasItem(DEFAULT_ID_CARD_CHECK_FLG.toString())))
            .andExpect(jsonPath("$.[*].marriageCheckFlg").value(hasItem(DEFAULT_MARRIAGE_CHECK_FLG.toString())))
            .andExpect(jsonPath("$.[*].householdCheckFlg").value(hasItem(DEFAULT_HOUSEHOLD_CHECK_FLG.toString())))
            .andExpect(jsonPath("$.[*].credibilityCheckFlg").value(hasItem(DEFAULT_CREDIBILITY_CHECK_FLG.toString())))
            .andExpect(jsonPath("$.[*].guaranteeCheckFlg").value(hasItem(DEFAULT_GUARANTEE_CHECK_FLG.toString())))
            .andExpect(jsonPath("$.[*].purpose").value(hasItem(DEFAULT_PURPOSE.toString())))
            .andExpect(jsonPath("$.[*].estateCheckFlg").value(hasItem(DEFAULT_ESTATE_CHECK_FLG.toString())))
            .andExpect(jsonPath("$.[*].guaranteeId").value(hasItem(DEFAULT_GUARANTEE_ID.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getProductInfo() throws Exception {
        // Initialize the database
        productInfoRepository.saveAndFlush(productInfo);

        // Get the productInfo
        restProductInfoMockMvc.perform(get("/api/product-infos/{id}", productInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productInfo.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.interestRate").value(DEFAULT_INTEREST_RATE.intValue()))
            .andExpect(jsonPath("$.validDay").value(DEFAULT_VALID_DAY))
            .andExpect(jsonPath("$.publishDate").value(DEFAULT_PUBLISH_DATE.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.successDate").value(DEFAULT_SUCCESS_DATE.toString()))
            .andExpect(jsonPath("$.startInterestDate").value(DEFAULT_START_INTEREST_DATE.toString()))
            .andExpect(jsonPath("$.repayType").value(DEFAULT_REPAY_TYPE.toString()))
            .andExpect(jsonPath("$.interestRateType").value(DEFAULT_INTEREST_RATE_TYPE.toString()))
            .andExpect(jsonPath("$.period").value(DEFAULT_PERIOD))
            .andExpect(jsonPath("$.repayTimes").value(DEFAULT_REPAY_TIMES))
            .andExpect(jsonPath("$.manageFee").value(DEFAULT_MANAGE_FEE.intValue()))
            .andExpect(jsonPath("$.manageFeeScale").value(DEFAULT_MANAGE_FEE_SCALE.intValue()))
            .andExpect(jsonPath("$.partFlg").value(DEFAULT_PART_FLG.toString()))
            .andExpect(jsonPath("$.fullFlg").value(DEFAULT_FULL_FLG.toString()))
            .andExpect(jsonPath("$.failedFlg").value(DEFAULT_FAILED_FLG.toString()))
            .andExpect(jsonPath("$.amountYes").value(DEFAULT_AMOUNT_YES.intValue()))
            .andExpect(jsonPath("$.amountWait").value(DEFAULT_AMOUNT_WAIT.intValue()))
            .andExpect(jsonPath("$.amountScale").value(DEFAULT_AMOUNT_SCALE.intValue()))
            .andExpect(jsonPath("$.minTenderAmount").value(DEFAULT_MIN_TENDER_AMOUNT.intValue()))
            .andExpect(jsonPath("$.maxTenderAmount").value(DEFAULT_MAX_TENDER_AMOUNT.intValue()))
            .andExpect(jsonPath("$.tenderAwardFlg").value(DEFAULT_TENDER_AWARD_FLG.toString()))
            .andExpect(jsonPath("$.tenderFailureAwardFlg").value(DEFAULT_TENDER_FAILURE_AWARD_FLG.toString()))
            .andExpect(jsonPath("$.tenderAwardAmount").value(DEFAULT_TENDER_AWARD_AMOUNT.intValue()))
            .andExpect(jsonPath("$.tenderAwardScale").value(DEFAULT_TENDER_AWARD_SCALE.intValue()))
            .andExpect(jsonPath("$.directionalPwd").value(DEFAULT_DIRECTIONAL_PWD.toString()))
            .andExpect(jsonPath("$.secondFlg").value(DEFAULT_SECOND_FLG.toString()))
            .andExpect(jsonPath("$.dueinAmount").value(DEFAULT_DUEIN_AMOUNT.intValue()))
            .andExpect(jsonPath("$.investTotalAmount").value(DEFAULT_INVEST_TOTAL_AMOUNT.intValue()))
            .andExpect(jsonPath("$.wanderFlg").value(DEFAULT_WANDER_FLG.toString()))
            .andExpect(jsonPath("$.tenderAutoPaymentFlg").value(DEFAULT_TENDER_AUTO_PAYMENT_FLG.toString()))
            .andExpect(jsonPath("$.partAmount").value(DEFAULT_PART_AMOUNT.intValue()))
            .andExpect(jsonPath("$.maxPart").value(DEFAULT_MAX_PART))
            .andExpect(jsonPath("$.tenderMaxTimes").value(DEFAULT_TENDER_MAX_TIMES))
            .andExpect(jsonPath("$.prepaymentFlg").value(DEFAULT_PREPAYMENT_FLG.toString()))
            .andExpect(jsonPath("$.prepaymentInterestMinDays").value(DEFAULT_PREPAYMENT_INTEREST_MIN_DAYS))
            .andExpect(jsonPath("$.prepaymentMinAmount").value(DEFAULT_PREPAYMENT_MIN_AMOUNT.intValue()))
            .andExpect(jsonPath("$.prepaymentMaxTimes").value(DEFAULT_PREPAYMENT_MAX_TIMES))
            .andExpect(jsonPath("$.guaranteeType").value(DEFAULT_GUARANTEE_TYPE.toString()))
            .andExpect(jsonPath("$.guaranteeOthers").value(DEFAULT_GUARANTEE_OTHERS.toString()))
            .andExpect(jsonPath("$.serviceFeeType").value(DEFAULT_SERVICE_FEE_TYPE.toString()))
            .andExpect(jsonPath("$.serviceFee").value(DEFAULT_SERVICE_FEE.intValue()))
            .andExpect(jsonPath("$.parkingFee").value(DEFAULT_PARKING_FEE.intValue()))
            .andExpect(jsonPath("$.updDate").value(DEFAULT_UPD_DATE.toString()))
            .andExpect(jsonPath("$.rateCalculationType").value(DEFAULT_RATE_CALCULATION_TYPE.toString()))
            .andExpect(jsonPath("$.fullDate").value(DEFAULT_FULL_DATE.toString()))
            .andExpect(jsonPath("$.noviceFlg").value(DEFAULT_NOVICE_FLG.toString()))
            .andExpect(jsonPath("$.rateType").value(DEFAULT_RATE_TYPE.toString()))
            .andExpect(jsonPath("$.rateInputValue").value(DEFAULT_RATE_INPUT_VALUE.intValue()))
            .andExpect(jsonPath("$.lastReplayDate").value(DEFAULT_LAST_REPLAY_DATE.toString()))
            .andExpect(jsonPath("$.transferCanFlg").value(DEFAULT_TRANSFER_CAN_FLG.toString()))
            .andExpect(jsonPath("$.transferFrozeTime").value(DEFAULT_TRANSFER_FROZE_TIME))
            .andExpect(jsonPath("$.minIncreasingAmount").value(DEFAULT_MIN_INCREASING_AMOUNT.intValue()))
            .andExpect(jsonPath("$.ver").value(DEFAULT_VER.toString()))
            .andExpect(jsonPath("$.idCardCheckFlg").value(DEFAULT_ID_CARD_CHECK_FLG.toString()))
            .andExpect(jsonPath("$.marriageCheckFlg").value(DEFAULT_MARRIAGE_CHECK_FLG.toString()))
            .andExpect(jsonPath("$.householdCheckFlg").value(DEFAULT_HOUSEHOLD_CHECK_FLG.toString()))
            .andExpect(jsonPath("$.credibilityCheckFlg").value(DEFAULT_CREDIBILITY_CHECK_FLG.toString()))
            .andExpect(jsonPath("$.guaranteeCheckFlg").value(DEFAULT_GUARANTEE_CHECK_FLG.toString()))
            .andExpect(jsonPath("$.purpose").value(DEFAULT_PURPOSE.toString()))
            .andExpect(jsonPath("$.estateCheckFlg").value(DEFAULT_ESTATE_CHECK_FLG.toString()))
            .andExpect(jsonPath("$.guaranteeId").value(DEFAULT_GUARANTEE_ID.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProductInfo() throws Exception {
        // Get the productInfo
        restProductInfoMockMvc.perform(get("/api/product-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductInfo() throws Exception {
        // Initialize the database
        productInfoRepository.saveAndFlush(productInfo);
        int databaseSizeBeforeUpdate = productInfoRepository.findAll().size();

        // Update the productInfo
        ProductInfo updatedProductInfo = productInfoRepository.findOne(productInfo.getId());
        // Disconnect from session so that the updates on updatedProductInfo are not directly saved in db
        em.detach(updatedProductInfo);
        updatedProductInfo
            .amount(UPDATED_AMOUNT)
            .interestRate(UPDATED_INTEREST_RATE)
            .validDay(UPDATED_VALID_DAY)
            .publishDate(UPDATED_PUBLISH_DATE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .successDate(UPDATED_SUCCESS_DATE)
            .startInterestDate(UPDATED_START_INTEREST_DATE)
            .repayType(UPDATED_REPAY_TYPE)
            .interestRateType(UPDATED_INTEREST_RATE_TYPE)
            .period(UPDATED_PERIOD)
            .repayTimes(UPDATED_REPAY_TIMES)
            .manageFee(UPDATED_MANAGE_FEE)
            .manageFeeScale(UPDATED_MANAGE_FEE_SCALE)
            .partFlg(UPDATED_PART_FLG)
            .fullFlg(UPDATED_FULL_FLG)
            .failedFlg(UPDATED_FAILED_FLG)
            .amountYes(UPDATED_AMOUNT_YES)
            .amountWait(UPDATED_AMOUNT_WAIT)
            .amountScale(UPDATED_AMOUNT_SCALE)
            .minTenderAmount(UPDATED_MIN_TENDER_AMOUNT)
            .maxTenderAmount(UPDATED_MAX_TENDER_AMOUNT)
            .tenderAwardFlg(UPDATED_TENDER_AWARD_FLG)
            .tenderFailureAwardFlg(UPDATED_TENDER_FAILURE_AWARD_FLG)
            .tenderAwardAmount(UPDATED_TENDER_AWARD_AMOUNT)
            .tenderAwardScale(UPDATED_TENDER_AWARD_SCALE)
            .directionalPwd(UPDATED_DIRECTIONAL_PWD)
            .secondFlg(UPDATED_SECOND_FLG)
            .dueinAmount(UPDATED_DUEIN_AMOUNT)
            .investTotalAmount(UPDATED_INVEST_TOTAL_AMOUNT)
            .wanderFlg(UPDATED_WANDER_FLG)
            .tenderAutoPaymentFlg(UPDATED_TENDER_AUTO_PAYMENT_FLG)
            .partAmount(UPDATED_PART_AMOUNT)
            .maxPart(UPDATED_MAX_PART)
            .tenderMaxTimes(UPDATED_TENDER_MAX_TIMES)
            .prepaymentFlg(UPDATED_PREPAYMENT_FLG)
            .prepaymentInterestMinDays(UPDATED_PREPAYMENT_INTEREST_MIN_DAYS)
            .prepaymentMinAmount(UPDATED_PREPAYMENT_MIN_AMOUNT)
            .prepaymentMaxTimes(UPDATED_PREPAYMENT_MAX_TIMES)
            .guaranteeType(UPDATED_GUARANTEE_TYPE)
            .guaranteeOthers(UPDATED_GUARANTEE_OTHERS)
            .serviceFeeType(UPDATED_SERVICE_FEE_TYPE)
            .serviceFee(UPDATED_SERVICE_FEE)
            .parkingFee(UPDATED_PARKING_FEE)
            .updDate(UPDATED_UPD_DATE)
            .rateCalculationType(UPDATED_RATE_CALCULATION_TYPE)
            .fullDate(UPDATED_FULL_DATE)
            .noviceFlg(UPDATED_NOVICE_FLG)
            .rateType(UPDATED_RATE_TYPE)
            .rateInputValue(UPDATED_RATE_INPUT_VALUE)
            .lastReplayDate(UPDATED_LAST_REPLAY_DATE)
            .transferCanFlg(UPDATED_TRANSFER_CAN_FLG)
            .transferFrozeTime(UPDATED_TRANSFER_FROZE_TIME)
            .minIncreasingAmount(UPDATED_MIN_INCREASING_AMOUNT)
            .ver(UPDATED_VER)
            .idCardCheckFlg(UPDATED_ID_CARD_CHECK_FLG)
            .marriageCheckFlg(UPDATED_MARRIAGE_CHECK_FLG)
            .householdCheckFlg(UPDATED_HOUSEHOLD_CHECK_FLG)
            .credibilityCheckFlg(UPDATED_CREDIBILITY_CHECK_FLG)
            .guaranteeCheckFlg(UPDATED_GUARANTEE_CHECK_FLG)
            .purpose(UPDATED_PURPOSE)
            .estateCheckFlg(UPDATED_ESTATE_CHECK_FLG)
            .guaranteeId(UPDATED_GUARANTEE_ID);

        restProductInfoMockMvc.perform(put("/api/product-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductInfo)))
            .andExpect(status().isOk());

        // Validate the ProductInfo in the database
        List<ProductInfo> productInfoList = productInfoRepository.findAll();
        assertThat(productInfoList).hasSize(databaseSizeBeforeUpdate);
        ProductInfo testProductInfo = productInfoList.get(productInfoList.size() - 1);
        assertThat(testProductInfo.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testProductInfo.getInterestRate()).isEqualTo(UPDATED_INTEREST_RATE);
        assertThat(testProductInfo.getValidDay()).isEqualTo(UPDATED_VALID_DAY);
        assertThat(testProductInfo.getPublishDate()).isEqualTo(UPDATED_PUBLISH_DATE);
        assertThat(testProductInfo.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testProductInfo.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testProductInfo.getSuccessDate()).isEqualTo(UPDATED_SUCCESS_DATE);
        assertThat(testProductInfo.getStartInterestDate()).isEqualTo(UPDATED_START_INTEREST_DATE);
        assertThat(testProductInfo.getRepayType()).isEqualTo(UPDATED_REPAY_TYPE);
        assertThat(testProductInfo.getInterestRateType()).isEqualTo(UPDATED_INTEREST_RATE_TYPE);
        assertThat(testProductInfo.getPeriod()).isEqualTo(UPDATED_PERIOD);
        assertThat(testProductInfo.getRepayTimes()).isEqualTo(UPDATED_REPAY_TIMES);
        assertThat(testProductInfo.getManageFee()).isEqualTo(UPDATED_MANAGE_FEE);
        assertThat(testProductInfo.getManageFeeScale()).isEqualTo(UPDATED_MANAGE_FEE_SCALE);
        assertThat(testProductInfo.getPartFlg()).isEqualTo(UPDATED_PART_FLG);
        assertThat(testProductInfo.getFullFlg()).isEqualTo(UPDATED_FULL_FLG);
        assertThat(testProductInfo.getFailedFlg()).isEqualTo(UPDATED_FAILED_FLG);
        assertThat(testProductInfo.getAmountYes()).isEqualTo(UPDATED_AMOUNT_YES);
        assertThat(testProductInfo.getAmountWait()).isEqualTo(UPDATED_AMOUNT_WAIT);
        assertThat(testProductInfo.getAmountScale()).isEqualTo(UPDATED_AMOUNT_SCALE);
        assertThat(testProductInfo.getMinTenderAmount()).isEqualTo(UPDATED_MIN_TENDER_AMOUNT);
        assertThat(testProductInfo.getMaxTenderAmount()).isEqualTo(UPDATED_MAX_TENDER_AMOUNT);
        assertThat(testProductInfo.getTenderAwardFlg()).isEqualTo(UPDATED_TENDER_AWARD_FLG);
        assertThat(testProductInfo.getTenderFailureAwardFlg()).isEqualTo(UPDATED_TENDER_FAILURE_AWARD_FLG);
        assertThat(testProductInfo.getTenderAwardAmount()).isEqualTo(UPDATED_TENDER_AWARD_AMOUNT);
        assertThat(testProductInfo.getTenderAwardScale()).isEqualTo(UPDATED_TENDER_AWARD_SCALE);
        assertThat(testProductInfo.getDirectionalPwd()).isEqualTo(UPDATED_DIRECTIONAL_PWD);
        assertThat(testProductInfo.getSecondFlg()).isEqualTo(UPDATED_SECOND_FLG);
        assertThat(testProductInfo.getDueinAmount()).isEqualTo(UPDATED_DUEIN_AMOUNT);
        assertThat(testProductInfo.getInvestTotalAmount()).isEqualTo(UPDATED_INVEST_TOTAL_AMOUNT);
        assertThat(testProductInfo.getWanderFlg()).isEqualTo(UPDATED_WANDER_FLG);
        assertThat(testProductInfo.getTenderAutoPaymentFlg()).isEqualTo(UPDATED_TENDER_AUTO_PAYMENT_FLG);
        assertThat(testProductInfo.getPartAmount()).isEqualTo(UPDATED_PART_AMOUNT);
        assertThat(testProductInfo.getMaxPart()).isEqualTo(UPDATED_MAX_PART);
        assertThat(testProductInfo.getTenderMaxTimes()).isEqualTo(UPDATED_TENDER_MAX_TIMES);
        assertThat(testProductInfo.getPrepaymentFlg()).isEqualTo(UPDATED_PREPAYMENT_FLG);
        assertThat(testProductInfo.getPrepaymentInterestMinDays()).isEqualTo(UPDATED_PREPAYMENT_INTEREST_MIN_DAYS);
        assertThat(testProductInfo.getPrepaymentMinAmount()).isEqualTo(UPDATED_PREPAYMENT_MIN_AMOUNT);
        assertThat(testProductInfo.getPrepaymentMaxTimes()).isEqualTo(UPDATED_PREPAYMENT_MAX_TIMES);
        assertThat(testProductInfo.getGuaranteeType()).isEqualTo(UPDATED_GUARANTEE_TYPE);
        assertThat(testProductInfo.getGuaranteeOthers()).isEqualTo(UPDATED_GUARANTEE_OTHERS);
        assertThat(testProductInfo.getServiceFeeType()).isEqualTo(UPDATED_SERVICE_FEE_TYPE);
        assertThat(testProductInfo.getServiceFee()).isEqualTo(UPDATED_SERVICE_FEE);
        assertThat(testProductInfo.getParkingFee()).isEqualTo(UPDATED_PARKING_FEE);
        assertThat(testProductInfo.getUpdDate()).isEqualTo(UPDATED_UPD_DATE);
        assertThat(testProductInfo.getRateCalculationType()).isEqualTo(UPDATED_RATE_CALCULATION_TYPE);
        assertThat(testProductInfo.getFullDate()).isEqualTo(UPDATED_FULL_DATE);
        assertThat(testProductInfo.getNoviceFlg()).isEqualTo(UPDATED_NOVICE_FLG);
        assertThat(testProductInfo.getRateType()).isEqualTo(UPDATED_RATE_TYPE);
        assertThat(testProductInfo.getRateInputValue()).isEqualTo(UPDATED_RATE_INPUT_VALUE);
        assertThat(testProductInfo.getLastReplayDate()).isEqualTo(UPDATED_LAST_REPLAY_DATE);
        assertThat(testProductInfo.getTransferCanFlg()).isEqualTo(UPDATED_TRANSFER_CAN_FLG);
        assertThat(testProductInfo.getTransferFrozeTime()).isEqualTo(UPDATED_TRANSFER_FROZE_TIME);
        assertThat(testProductInfo.getMinIncreasingAmount()).isEqualTo(UPDATED_MIN_INCREASING_AMOUNT);
        assertThat(testProductInfo.getVer()).isEqualTo(UPDATED_VER);
        assertThat(testProductInfo.getIdCardCheckFlg()).isEqualTo(UPDATED_ID_CARD_CHECK_FLG);
        assertThat(testProductInfo.getMarriageCheckFlg()).isEqualTo(UPDATED_MARRIAGE_CHECK_FLG);
        assertThat(testProductInfo.getHouseholdCheckFlg()).isEqualTo(UPDATED_HOUSEHOLD_CHECK_FLG);
        assertThat(testProductInfo.getCredibilityCheckFlg()).isEqualTo(UPDATED_CREDIBILITY_CHECK_FLG);
        assertThat(testProductInfo.getGuaranteeCheckFlg()).isEqualTo(UPDATED_GUARANTEE_CHECK_FLG);
        assertThat(testProductInfo.getPurpose()).isEqualTo(UPDATED_PURPOSE);
        assertThat(testProductInfo.getEstateCheckFlg()).isEqualTo(UPDATED_ESTATE_CHECK_FLG);
        assertThat(testProductInfo.getGuaranteeId()).isEqualTo(UPDATED_GUARANTEE_ID);
        assertThat(testProductInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductInfo.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testProductInfo.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingProductInfo() throws Exception {
        int databaseSizeBeforeUpdate = productInfoRepository.findAll().size();

        // Create the ProductInfo

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProductInfoMockMvc.perform(put("/api/product-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productInfo)))
            .andExpect(status().isCreated());

        // Validate the ProductInfo in the database
        List<ProductInfo> productInfoList = productInfoRepository.findAll();
        assertThat(productInfoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProductInfo() throws Exception {
        // Initialize the database
        productInfoRepository.saveAndFlush(productInfo);
        int databaseSizeBeforeDelete = productInfoRepository.findAll().size();

        // Get the productInfo
        restProductInfoMockMvc.perform(delete("/api/product-infos/{id}", productInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProductInfo> productInfoList = productInfoRepository.findAll();
        assertThat(productInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductInfo.class);
        ProductInfo productInfo1 = new ProductInfo();
        productInfo1.setId(1L);
        ProductInfo productInfo2 = new ProductInfo();
        productInfo2.setId(productInfo1.getId());
        assertThat(productInfo1).isEqualTo(productInfo2);
        productInfo2.setId(2L);
        assertThat(productInfo1).isNotEqualTo(productInfo2);
        productInfo1.setId(null);
        assertThat(productInfo1).isNotEqualTo(productInfo2);
    }
}
