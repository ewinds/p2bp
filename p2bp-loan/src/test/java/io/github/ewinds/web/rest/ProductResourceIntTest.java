package io.github.ewinds.web.rest;

import io.github.ewinds.LoanApp;

import io.github.ewinds.config.SecurityBeanOverrideConfiguration;

import io.github.ewinds.domain.Product;
import io.github.ewinds.repository.ProductRepository;
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

import io.github.ewinds.domain.enumeration.ProductState;
import io.github.ewinds.domain.enumeration.InterestCalculationPeriod;
/**
 * Test class for the ProductResource REST controller.
 *
 * @see ProductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LoanApp.class, SecurityBeanOverrideConfiguration.class})
public class ProductResourceIntTest {

    private static final String DEFAULT_CONTRACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCTS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCTS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_HITS = 1;
    private static final Integer UPDATED_HITS = 2;

    private static final ProductState DEFAULT_STATE = ProductState.PENDING;
    private static final ProductState UPDATED_STATE = ProductState.CHECKED;

    private static final String DEFAULT_LITPIC_FILE_ID = "AAAAAAAAAA";
    private static final String UPDATED_LITPIC_FILE_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_CANCEL_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CANCEL_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CANCEL_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_CANCEL_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT_STATUS = "BBBBBBBBBB";

    private static final Integer DEFAULT_ASSIGNMENT = 1;
    private static final Integer UPDATED_ASSIGNMENT = 2;

    private static final Integer DEFAULT_ASSIGNMENT_ID = 1;
    private static final Integer UPDATED_ASSIGNMENT_ID = 2;

    private static final Integer DEFAULT_ORIGIN_INFO = 1;
    private static final Integer UPDATED_ORIGIN_INFO = 2;

    private static final String DEFAULT_FROZEN_REQUEST_NO = "AAAAAAAAAA";
    private static final String UPDATED_FROZEN_REQUEST_NO = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRODUCTS_GROUP_ID = 1;
    private static final Integer UPDATED_PRODUCTS_GROUP_ID = 2;

    private static final String DEFAULT_CONFINE = "AAAAAAAAAA";
    private static final String UPDATED_CONFINE = "BBBBBBBBBB";

    private static final Integer DEFAULT_AUDIT_INFO = 1;
    private static final Integer UPDATED_AUDIT_INFO = 2;

    private static final Integer DEFAULT_AUDIT_INFO_ID = 1;
    private static final Integer UPDATED_AUDIT_INFO_ID = 2;

    private static final Integer DEFAULT_AUTO_FINANCE_PUBLISH_VALID_TIME = 1;
    private static final Integer UPDATED_AUTO_FINANCE_PUBLISH_VALID_TIME = 2;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

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

    private static final InterestCalculationPeriod DEFAULT_PERIOD_TYPE = InterestCalculationPeriod.MONTH;
    private static final InterestCalculationPeriod UPDATED_PERIOD_TYPE = InterestCalculationPeriod.DAY;

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

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductMockMvc;

    private Product product;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductResource productResource = new ProductResource(productRepository);
        this.restProductMockMvc = MockMvcBuilders.standaloneSetup(productResource)
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
    public static Product createEntity(EntityManager em) {
        Product product = new Product()
            .contractNo(DEFAULT_CONTRACT_NO)
            .contractId(DEFAULT_CONTRACT_ID)
            .productsType(DEFAULT_PRODUCTS_TYPE)
            .title(DEFAULT_TITLE)
            .shortName(DEFAULT_SHORT_NAME)
            .hits(DEFAULT_HITS)
            .state(DEFAULT_STATE)
            .litpicFileId(DEFAULT_LITPIC_FILE_ID)
            .cancelTime(DEFAULT_CANCEL_TIME)
            .cancelRemark(DEFAULT_CANCEL_REMARK)
            .commentStatus(DEFAULT_COMMENT_STATUS)
            .assignment(DEFAULT_ASSIGNMENT)
            .assignmentId(DEFAULT_ASSIGNMENT_ID)
            .originInfo(DEFAULT_ORIGIN_INFO)
            .frozenRequestNo(DEFAULT_FROZEN_REQUEST_NO)
            .productsGroupId(DEFAULT_PRODUCTS_GROUP_ID)
            .confine(DEFAULT_CONFINE)
            .auditInfo(DEFAULT_AUDIT_INFO)
            .auditInfoId(DEFAULT_AUDIT_INFO_ID)
            .autoFinancePublishValidTime(DEFAULT_AUTO_FINANCE_PUBLISH_VALID_TIME)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .amount(DEFAULT_AMOUNT)
            .interestRate(DEFAULT_INTEREST_RATE)
            .validDay(DEFAULT_VALID_DAY)
            .publishDate(DEFAULT_PUBLISH_DATE)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .successDate(DEFAULT_SUCCESS_DATE)
            .startInterestDate(DEFAULT_START_INTEREST_DATE)
            .repayType(DEFAULT_REPAY_TYPE)
            .periodType(DEFAULT_PERIOD_TYPE)
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
        return product;
    }

    @Before
    public void initTest() {
        product = createEntity(em);
    }

    @Test
    @Transactional
    public void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getContractNo()).isEqualTo(DEFAULT_CONTRACT_NO);
        assertThat(testProduct.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testProduct.getProductsType()).isEqualTo(DEFAULT_PRODUCTS_TYPE);
        assertThat(testProduct.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testProduct.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testProduct.getHits()).isEqualTo(DEFAULT_HITS);
        assertThat(testProduct.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testProduct.getLitpicFileId()).isEqualTo(DEFAULT_LITPIC_FILE_ID);
        assertThat(testProduct.getCancelTime()).isEqualTo(DEFAULT_CANCEL_TIME);
        assertThat(testProduct.getCancelRemark()).isEqualTo(DEFAULT_CANCEL_REMARK);
        assertThat(testProduct.getCommentStatus()).isEqualTo(DEFAULT_COMMENT_STATUS);
        assertThat(testProduct.getAssignment()).isEqualTo(DEFAULT_ASSIGNMENT);
        assertThat(testProduct.getAssignmentId()).isEqualTo(DEFAULT_ASSIGNMENT_ID);
        assertThat(testProduct.getOriginInfo()).isEqualTo(DEFAULT_ORIGIN_INFO);
        assertThat(testProduct.getFrozenRequestNo()).isEqualTo(DEFAULT_FROZEN_REQUEST_NO);
        assertThat(testProduct.getProductsGroupId()).isEqualTo(DEFAULT_PRODUCTS_GROUP_ID);
        assertThat(testProduct.getConfine()).isEqualTo(DEFAULT_CONFINE);
        assertThat(testProduct.getAuditInfo()).isEqualTo(DEFAULT_AUDIT_INFO);
        assertThat(testProduct.getAuditInfoId()).isEqualTo(DEFAULT_AUDIT_INFO_ID);
        assertThat(testProduct.getAutoFinancePublishValidTime()).isEqualTo(DEFAULT_AUTO_FINANCE_PUBLISH_VALID_TIME);
        assertThat(testProduct.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProduct.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProduct.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testProduct.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testProduct.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testProduct.getInterestRate()).isEqualTo(DEFAULT_INTEREST_RATE);
        assertThat(testProduct.getValidDay()).isEqualTo(DEFAULT_VALID_DAY);
        assertThat(testProduct.getPublishDate()).isEqualTo(DEFAULT_PUBLISH_DATE);
        assertThat(testProduct.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testProduct.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testProduct.getSuccessDate()).isEqualTo(DEFAULT_SUCCESS_DATE);
        assertThat(testProduct.getStartInterestDate()).isEqualTo(DEFAULT_START_INTEREST_DATE);
        assertThat(testProduct.getRepayType()).isEqualTo(DEFAULT_REPAY_TYPE);
        assertThat(testProduct.getPeriodType()).isEqualTo(DEFAULT_PERIOD_TYPE);
        assertThat(testProduct.getPeriod()).isEqualTo(DEFAULT_PERIOD);
        assertThat(testProduct.getRepayTimes()).isEqualTo(DEFAULT_REPAY_TIMES);
        assertThat(testProduct.getManageFee()).isEqualTo(DEFAULT_MANAGE_FEE);
        assertThat(testProduct.getManageFeeScale()).isEqualTo(DEFAULT_MANAGE_FEE_SCALE);
        assertThat(testProduct.getPartFlg()).isEqualTo(DEFAULT_PART_FLG);
        assertThat(testProduct.getFullFlg()).isEqualTo(DEFAULT_FULL_FLG);
        assertThat(testProduct.getFailedFlg()).isEqualTo(DEFAULT_FAILED_FLG);
        assertThat(testProduct.getAmountYes()).isEqualTo(DEFAULT_AMOUNT_YES);
        assertThat(testProduct.getAmountWait()).isEqualTo(DEFAULT_AMOUNT_WAIT);
        assertThat(testProduct.getAmountScale()).isEqualTo(DEFAULT_AMOUNT_SCALE);
        assertThat(testProduct.getMinTenderAmount()).isEqualTo(DEFAULT_MIN_TENDER_AMOUNT);
        assertThat(testProduct.getMaxTenderAmount()).isEqualTo(DEFAULT_MAX_TENDER_AMOUNT);
        assertThat(testProduct.getTenderAwardFlg()).isEqualTo(DEFAULT_TENDER_AWARD_FLG);
        assertThat(testProduct.getTenderFailureAwardFlg()).isEqualTo(DEFAULT_TENDER_FAILURE_AWARD_FLG);
        assertThat(testProduct.getTenderAwardAmount()).isEqualTo(DEFAULT_TENDER_AWARD_AMOUNT);
        assertThat(testProduct.getTenderAwardScale()).isEqualTo(DEFAULT_TENDER_AWARD_SCALE);
        assertThat(testProduct.getDirectionalPwd()).isEqualTo(DEFAULT_DIRECTIONAL_PWD);
        assertThat(testProduct.getSecondFlg()).isEqualTo(DEFAULT_SECOND_FLG);
        assertThat(testProduct.getDueinAmount()).isEqualTo(DEFAULT_DUEIN_AMOUNT);
        assertThat(testProduct.getInvestTotalAmount()).isEqualTo(DEFAULT_INVEST_TOTAL_AMOUNT);
        assertThat(testProduct.getWanderFlg()).isEqualTo(DEFAULT_WANDER_FLG);
        assertThat(testProduct.getTenderAutoPaymentFlg()).isEqualTo(DEFAULT_TENDER_AUTO_PAYMENT_FLG);
        assertThat(testProduct.getPartAmount()).isEqualTo(DEFAULT_PART_AMOUNT);
        assertThat(testProduct.getMaxPart()).isEqualTo(DEFAULT_MAX_PART);
        assertThat(testProduct.getTenderMaxTimes()).isEqualTo(DEFAULT_TENDER_MAX_TIMES);
        assertThat(testProduct.getPrepaymentFlg()).isEqualTo(DEFAULT_PREPAYMENT_FLG);
        assertThat(testProduct.getPrepaymentInterestMinDays()).isEqualTo(DEFAULT_PREPAYMENT_INTEREST_MIN_DAYS);
        assertThat(testProduct.getPrepaymentMinAmount()).isEqualTo(DEFAULT_PREPAYMENT_MIN_AMOUNT);
        assertThat(testProduct.getPrepaymentMaxTimes()).isEqualTo(DEFAULT_PREPAYMENT_MAX_TIMES);
        assertThat(testProduct.getGuaranteeType()).isEqualTo(DEFAULT_GUARANTEE_TYPE);
        assertThat(testProduct.getGuaranteeOthers()).isEqualTo(DEFAULT_GUARANTEE_OTHERS);
        assertThat(testProduct.getServiceFeeType()).isEqualTo(DEFAULT_SERVICE_FEE_TYPE);
        assertThat(testProduct.getServiceFee()).isEqualTo(DEFAULT_SERVICE_FEE);
        assertThat(testProduct.getParkingFee()).isEqualTo(DEFAULT_PARKING_FEE);
        assertThat(testProduct.getUpdDate()).isEqualTo(DEFAULT_UPD_DATE);
        assertThat(testProduct.getRateCalculationType()).isEqualTo(DEFAULT_RATE_CALCULATION_TYPE);
        assertThat(testProduct.getFullDate()).isEqualTo(DEFAULT_FULL_DATE);
        assertThat(testProduct.getNoviceFlg()).isEqualTo(DEFAULT_NOVICE_FLG);
        assertThat(testProduct.getRateType()).isEqualTo(DEFAULT_RATE_TYPE);
        assertThat(testProduct.getRateInputValue()).isEqualTo(DEFAULT_RATE_INPUT_VALUE);
        assertThat(testProduct.getLastReplayDate()).isEqualTo(DEFAULT_LAST_REPLAY_DATE);
        assertThat(testProduct.getTransferCanFlg()).isEqualTo(DEFAULT_TRANSFER_CAN_FLG);
        assertThat(testProduct.getTransferFrozeTime()).isEqualTo(DEFAULT_TRANSFER_FROZE_TIME);
        assertThat(testProduct.getMinIncreasingAmount()).isEqualTo(DEFAULT_MIN_INCREASING_AMOUNT);
        assertThat(testProduct.getVer()).isEqualTo(DEFAULT_VER);
        assertThat(testProduct.getIdCardCheckFlg()).isEqualTo(DEFAULT_ID_CARD_CHECK_FLG);
        assertThat(testProduct.getMarriageCheckFlg()).isEqualTo(DEFAULT_MARRIAGE_CHECK_FLG);
        assertThat(testProduct.getHouseholdCheckFlg()).isEqualTo(DEFAULT_HOUSEHOLD_CHECK_FLG);
        assertThat(testProduct.getCredibilityCheckFlg()).isEqualTo(DEFAULT_CREDIBILITY_CHECK_FLG);
        assertThat(testProduct.getGuaranteeCheckFlg()).isEqualTo(DEFAULT_GUARANTEE_CHECK_FLG);
        assertThat(testProduct.getPurpose()).isEqualTo(DEFAULT_PURPOSE);
        assertThat(testProduct.getEstateCheckFlg()).isEqualTo(DEFAULT_ESTATE_CHECK_FLG);
        assertThat(testProduct.getGuaranteeId()).isEqualTo(DEFAULT_GUARANTEE_ID);
    }

    @Test
    @Transactional
    public void createProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product with an existing ID
        product.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList
        restProductMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractNo").value(hasItem(DEFAULT_CONTRACT_NO.toString())))
            .andExpect(jsonPath("$.[*].contractId").value(hasItem(DEFAULT_CONTRACT_ID.toString())))
            .andExpect(jsonPath("$.[*].productsType").value(hasItem(DEFAULT_PRODUCTS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].hits").value(hasItem(DEFAULT_HITS)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].litpicFileId").value(hasItem(DEFAULT_LITPIC_FILE_ID.toString())))
            .andExpect(jsonPath("$.[*].cancelTime").value(hasItem(DEFAULT_CANCEL_TIME.toString())))
            .andExpect(jsonPath("$.[*].cancelRemark").value(hasItem(DEFAULT_CANCEL_REMARK.toString())))
            .andExpect(jsonPath("$.[*].commentStatus").value(hasItem(DEFAULT_COMMENT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].assignment").value(hasItem(DEFAULT_ASSIGNMENT)))
            .andExpect(jsonPath("$.[*].assignmentId").value(hasItem(DEFAULT_ASSIGNMENT_ID)))
            .andExpect(jsonPath("$.[*].originInfo").value(hasItem(DEFAULT_ORIGIN_INFO)))
            .andExpect(jsonPath("$.[*].frozenRequestNo").value(hasItem(DEFAULT_FROZEN_REQUEST_NO.toString())))
            .andExpect(jsonPath("$.[*].productsGroupId").value(hasItem(DEFAULT_PRODUCTS_GROUP_ID)))
            .andExpect(jsonPath("$.[*].confine").value(hasItem(DEFAULT_CONFINE.toString())))
            .andExpect(jsonPath("$.[*].auditInfo").value(hasItem(DEFAULT_AUDIT_INFO)))
            .andExpect(jsonPath("$.[*].auditInfoId").value(hasItem(DEFAULT_AUDIT_INFO_ID)))
            .andExpect(jsonPath("$.[*].autoFinancePublishValidTime").value(hasItem(DEFAULT_AUTO_FINANCE_PUBLISH_VALID_TIME)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].interestRate").value(hasItem(DEFAULT_INTEREST_RATE.intValue())))
            .andExpect(jsonPath("$.[*].validDay").value(hasItem(DEFAULT_VALID_DAY)))
            .andExpect(jsonPath("$.[*].publishDate").value(hasItem(DEFAULT_PUBLISH_DATE.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].successDate").value(hasItem(DEFAULT_SUCCESS_DATE.toString())))
            .andExpect(jsonPath("$.[*].startInterestDate").value(hasItem(DEFAULT_START_INTEREST_DATE.toString())))
            .andExpect(jsonPath("$.[*].repayType").value(hasItem(DEFAULT_REPAY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].periodType").value(hasItem(DEFAULT_PERIOD_TYPE.toString())))
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
            .andExpect(jsonPath("$.[*].guaranteeId").value(hasItem(DEFAULT_GUARANTEE_ID.toString())));
    }

    @Test
    @Transactional
    public void getProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(product.getId().intValue()))
            .andExpect(jsonPath("$.contractNo").value(DEFAULT_CONTRACT_NO.toString()))
            .andExpect(jsonPath("$.contractId").value(DEFAULT_CONTRACT_ID.toString()))
            .andExpect(jsonPath("$.productsType").value(DEFAULT_PRODUCTS_TYPE.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.hits").value(DEFAULT_HITS))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.litpicFileId").value(DEFAULT_LITPIC_FILE_ID.toString()))
            .andExpect(jsonPath("$.cancelTime").value(DEFAULT_CANCEL_TIME.toString()))
            .andExpect(jsonPath("$.cancelRemark").value(DEFAULT_CANCEL_REMARK.toString()))
            .andExpect(jsonPath("$.commentStatus").value(DEFAULT_COMMENT_STATUS.toString()))
            .andExpect(jsonPath("$.assignment").value(DEFAULT_ASSIGNMENT))
            .andExpect(jsonPath("$.assignmentId").value(DEFAULT_ASSIGNMENT_ID))
            .andExpect(jsonPath("$.originInfo").value(DEFAULT_ORIGIN_INFO))
            .andExpect(jsonPath("$.frozenRequestNo").value(DEFAULT_FROZEN_REQUEST_NO.toString()))
            .andExpect(jsonPath("$.productsGroupId").value(DEFAULT_PRODUCTS_GROUP_ID))
            .andExpect(jsonPath("$.confine").value(DEFAULT_CONFINE.toString()))
            .andExpect(jsonPath("$.auditInfo").value(DEFAULT_AUDIT_INFO))
            .andExpect(jsonPath("$.auditInfoId").value(DEFAULT_AUDIT_INFO_ID))
            .andExpect(jsonPath("$.autoFinancePublishValidTime").value(DEFAULT_AUTO_FINANCE_PUBLISH_VALID_TIME))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.interestRate").value(DEFAULT_INTEREST_RATE.intValue()))
            .andExpect(jsonPath("$.validDay").value(DEFAULT_VALID_DAY))
            .andExpect(jsonPath("$.publishDate").value(DEFAULT_PUBLISH_DATE.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.successDate").value(DEFAULT_SUCCESS_DATE.toString()))
            .andExpect(jsonPath("$.startInterestDate").value(DEFAULT_START_INTEREST_DATE.toString()))
            .andExpect(jsonPath("$.repayType").value(DEFAULT_REPAY_TYPE.toString()))
            .andExpect(jsonPath("$.periodType").value(DEFAULT_PERIOD_TYPE.toString()))
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
            .andExpect(jsonPath("$.guaranteeId").value(DEFAULT_GUARANTEE_ID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        Product updatedProduct = productRepository.findOne(product.getId());
        // Disconnect from session so that the updates on updatedProduct are not directly saved in db
        em.detach(updatedProduct);
        updatedProduct
            .contractNo(UPDATED_CONTRACT_NO)
            .contractId(UPDATED_CONTRACT_ID)
            .productsType(UPDATED_PRODUCTS_TYPE)
            .title(UPDATED_TITLE)
            .shortName(UPDATED_SHORT_NAME)
            .hits(UPDATED_HITS)
            .state(UPDATED_STATE)
            .litpicFileId(UPDATED_LITPIC_FILE_ID)
            .cancelTime(UPDATED_CANCEL_TIME)
            .cancelRemark(UPDATED_CANCEL_REMARK)
            .commentStatus(UPDATED_COMMENT_STATUS)
            .assignment(UPDATED_ASSIGNMENT)
            .assignmentId(UPDATED_ASSIGNMENT_ID)
            .originInfo(UPDATED_ORIGIN_INFO)
            .frozenRequestNo(UPDATED_FROZEN_REQUEST_NO)
            .productsGroupId(UPDATED_PRODUCTS_GROUP_ID)
            .confine(UPDATED_CONFINE)
            .auditInfo(UPDATED_AUDIT_INFO)
            .auditInfoId(UPDATED_AUDIT_INFO_ID)
            .autoFinancePublishValidTime(UPDATED_AUTO_FINANCE_PUBLISH_VALID_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .amount(UPDATED_AMOUNT)
            .interestRate(UPDATED_INTEREST_RATE)
            .validDay(UPDATED_VALID_DAY)
            .publishDate(UPDATED_PUBLISH_DATE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .successDate(UPDATED_SUCCESS_DATE)
            .startInterestDate(UPDATED_START_INTEREST_DATE)
            .repayType(UPDATED_REPAY_TYPE)
            .periodType(UPDATED_PERIOD_TYPE)
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

        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProduct)))
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getContractNo()).isEqualTo(UPDATED_CONTRACT_NO);
        assertThat(testProduct.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testProduct.getProductsType()).isEqualTo(UPDATED_PRODUCTS_TYPE);
        assertThat(testProduct.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testProduct.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testProduct.getHits()).isEqualTo(UPDATED_HITS);
        assertThat(testProduct.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testProduct.getLitpicFileId()).isEqualTo(UPDATED_LITPIC_FILE_ID);
        assertThat(testProduct.getCancelTime()).isEqualTo(UPDATED_CANCEL_TIME);
        assertThat(testProduct.getCancelRemark()).isEqualTo(UPDATED_CANCEL_REMARK);
        assertThat(testProduct.getCommentStatus()).isEqualTo(UPDATED_COMMENT_STATUS);
        assertThat(testProduct.getAssignment()).isEqualTo(UPDATED_ASSIGNMENT);
        assertThat(testProduct.getAssignmentId()).isEqualTo(UPDATED_ASSIGNMENT_ID);
        assertThat(testProduct.getOriginInfo()).isEqualTo(UPDATED_ORIGIN_INFO);
        assertThat(testProduct.getFrozenRequestNo()).isEqualTo(UPDATED_FROZEN_REQUEST_NO);
        assertThat(testProduct.getProductsGroupId()).isEqualTo(UPDATED_PRODUCTS_GROUP_ID);
        assertThat(testProduct.getConfine()).isEqualTo(UPDATED_CONFINE);
        assertThat(testProduct.getAuditInfo()).isEqualTo(UPDATED_AUDIT_INFO);
        assertThat(testProduct.getAuditInfoId()).isEqualTo(UPDATED_AUDIT_INFO_ID);
        assertThat(testProduct.getAutoFinancePublishValidTime()).isEqualTo(UPDATED_AUTO_FINANCE_PUBLISH_VALID_TIME);
        assertThat(testProduct.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProduct.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProduct.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testProduct.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testProduct.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testProduct.getInterestRate()).isEqualTo(UPDATED_INTEREST_RATE);
        assertThat(testProduct.getValidDay()).isEqualTo(UPDATED_VALID_DAY);
        assertThat(testProduct.getPublishDate()).isEqualTo(UPDATED_PUBLISH_DATE);
        assertThat(testProduct.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testProduct.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testProduct.getSuccessDate()).isEqualTo(UPDATED_SUCCESS_DATE);
        assertThat(testProduct.getStartInterestDate()).isEqualTo(UPDATED_START_INTEREST_DATE);
        assertThat(testProduct.getRepayType()).isEqualTo(UPDATED_REPAY_TYPE);
        assertThat(testProduct.getPeriodType()).isEqualTo(UPDATED_PERIOD_TYPE);
        assertThat(testProduct.getPeriod()).isEqualTo(UPDATED_PERIOD);
        assertThat(testProduct.getRepayTimes()).isEqualTo(UPDATED_REPAY_TIMES);
        assertThat(testProduct.getManageFee()).isEqualTo(UPDATED_MANAGE_FEE);
        assertThat(testProduct.getManageFeeScale()).isEqualTo(UPDATED_MANAGE_FEE_SCALE);
        assertThat(testProduct.getPartFlg()).isEqualTo(UPDATED_PART_FLG);
        assertThat(testProduct.getFullFlg()).isEqualTo(UPDATED_FULL_FLG);
        assertThat(testProduct.getFailedFlg()).isEqualTo(UPDATED_FAILED_FLG);
        assertThat(testProduct.getAmountYes()).isEqualTo(UPDATED_AMOUNT_YES);
        assertThat(testProduct.getAmountWait()).isEqualTo(UPDATED_AMOUNT_WAIT);
        assertThat(testProduct.getAmountScale()).isEqualTo(UPDATED_AMOUNT_SCALE);
        assertThat(testProduct.getMinTenderAmount()).isEqualTo(UPDATED_MIN_TENDER_AMOUNT);
        assertThat(testProduct.getMaxTenderAmount()).isEqualTo(UPDATED_MAX_TENDER_AMOUNT);
        assertThat(testProduct.getTenderAwardFlg()).isEqualTo(UPDATED_TENDER_AWARD_FLG);
        assertThat(testProduct.getTenderFailureAwardFlg()).isEqualTo(UPDATED_TENDER_FAILURE_AWARD_FLG);
        assertThat(testProduct.getTenderAwardAmount()).isEqualTo(UPDATED_TENDER_AWARD_AMOUNT);
        assertThat(testProduct.getTenderAwardScale()).isEqualTo(UPDATED_TENDER_AWARD_SCALE);
        assertThat(testProduct.getDirectionalPwd()).isEqualTo(UPDATED_DIRECTIONAL_PWD);
        assertThat(testProduct.getSecondFlg()).isEqualTo(UPDATED_SECOND_FLG);
        assertThat(testProduct.getDueinAmount()).isEqualTo(UPDATED_DUEIN_AMOUNT);
        assertThat(testProduct.getInvestTotalAmount()).isEqualTo(UPDATED_INVEST_TOTAL_AMOUNT);
        assertThat(testProduct.getWanderFlg()).isEqualTo(UPDATED_WANDER_FLG);
        assertThat(testProduct.getTenderAutoPaymentFlg()).isEqualTo(UPDATED_TENDER_AUTO_PAYMENT_FLG);
        assertThat(testProduct.getPartAmount()).isEqualTo(UPDATED_PART_AMOUNT);
        assertThat(testProduct.getMaxPart()).isEqualTo(UPDATED_MAX_PART);
        assertThat(testProduct.getTenderMaxTimes()).isEqualTo(UPDATED_TENDER_MAX_TIMES);
        assertThat(testProduct.getPrepaymentFlg()).isEqualTo(UPDATED_PREPAYMENT_FLG);
        assertThat(testProduct.getPrepaymentInterestMinDays()).isEqualTo(UPDATED_PREPAYMENT_INTEREST_MIN_DAYS);
        assertThat(testProduct.getPrepaymentMinAmount()).isEqualTo(UPDATED_PREPAYMENT_MIN_AMOUNT);
        assertThat(testProduct.getPrepaymentMaxTimes()).isEqualTo(UPDATED_PREPAYMENT_MAX_TIMES);
        assertThat(testProduct.getGuaranteeType()).isEqualTo(UPDATED_GUARANTEE_TYPE);
        assertThat(testProduct.getGuaranteeOthers()).isEqualTo(UPDATED_GUARANTEE_OTHERS);
        assertThat(testProduct.getServiceFeeType()).isEqualTo(UPDATED_SERVICE_FEE_TYPE);
        assertThat(testProduct.getServiceFee()).isEqualTo(UPDATED_SERVICE_FEE);
        assertThat(testProduct.getParkingFee()).isEqualTo(UPDATED_PARKING_FEE);
        assertThat(testProduct.getUpdDate()).isEqualTo(UPDATED_UPD_DATE);
        assertThat(testProduct.getRateCalculationType()).isEqualTo(UPDATED_RATE_CALCULATION_TYPE);
        assertThat(testProduct.getFullDate()).isEqualTo(UPDATED_FULL_DATE);
        assertThat(testProduct.getNoviceFlg()).isEqualTo(UPDATED_NOVICE_FLG);
        assertThat(testProduct.getRateType()).isEqualTo(UPDATED_RATE_TYPE);
        assertThat(testProduct.getRateInputValue()).isEqualTo(UPDATED_RATE_INPUT_VALUE);
        assertThat(testProduct.getLastReplayDate()).isEqualTo(UPDATED_LAST_REPLAY_DATE);
        assertThat(testProduct.getTransferCanFlg()).isEqualTo(UPDATED_TRANSFER_CAN_FLG);
        assertThat(testProduct.getTransferFrozeTime()).isEqualTo(UPDATED_TRANSFER_FROZE_TIME);
        assertThat(testProduct.getMinIncreasingAmount()).isEqualTo(UPDATED_MIN_INCREASING_AMOUNT);
        assertThat(testProduct.getVer()).isEqualTo(UPDATED_VER);
        assertThat(testProduct.getIdCardCheckFlg()).isEqualTo(UPDATED_ID_CARD_CHECK_FLG);
        assertThat(testProduct.getMarriageCheckFlg()).isEqualTo(UPDATED_MARRIAGE_CHECK_FLG);
        assertThat(testProduct.getHouseholdCheckFlg()).isEqualTo(UPDATED_HOUSEHOLD_CHECK_FLG);
        assertThat(testProduct.getCredibilityCheckFlg()).isEqualTo(UPDATED_CREDIBILITY_CHECK_FLG);
        assertThat(testProduct.getGuaranteeCheckFlg()).isEqualTo(UPDATED_GUARANTEE_CHECK_FLG);
        assertThat(testProduct.getPurpose()).isEqualTo(UPDATED_PURPOSE);
        assertThat(testProduct.getEstateCheckFlg()).isEqualTo(UPDATED_ESTATE_CHECK_FLG);
        assertThat(testProduct.getGuaranteeId()).isEqualTo(UPDATED_GUARANTEE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Create the Product

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);
        int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Get the product
        restProductMockMvc.perform(delete("/api/products/{id}", product.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Product.class);
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(product1.getId());
        assertThat(product1).isEqualTo(product2);
        product2.setId(2L);
        assertThat(product1).isNotEqualTo(product2);
        product1.setId(null);
        assertThat(product1).isNotEqualTo(product2);
    }
}
