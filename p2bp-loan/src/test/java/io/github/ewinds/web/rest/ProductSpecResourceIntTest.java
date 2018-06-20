package io.github.ewinds.web.rest;

import io.github.ewinds.LoanApp;

import io.github.ewinds.config.SecurityBeanOverrideConfiguration;

import io.github.ewinds.domain.ProductSpec;
import io.github.ewinds.repository.ProductSpecRepository;
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

import io.github.ewinds.domain.enumeration.ProductCategory;
/**
 * Test class for the ProductSpecResource REST controller.
 *
 * @see ProductSpecResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LoanApp.class, SecurityBeanOverrideConfiguration.class})
public class ProductSpecResourceIntTest {

    private static final ProductCategory DEFAULT_CATEGORY = ProductCategory.FINANCING;
    private static final ProductCategory UPDATED_CATEGORY = ProductCategory.DEBT;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_START_INTEREST_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_START_INTEREST_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_START_INTEREST_TIME = "AAAAAAAAAA";
    private static final String UPDATED_START_INTEREST_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_REPAY_TIME_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_REPAY_TIME_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PRINCIPAL_ASSIGN_ACCOUNTS = "AAAAAAAAAA";
    private static final String UPDATED_PRINCIPAL_ASSIGN_ACCOUNTS = "BBBBBBBBBB";

    private static final String DEFAULT_PRINCIPAL_SPECIAL_ACCOUNTS_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRINCIPAL_SPECIAL_ACCOUNTS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_INTEREST_ASSIGN_ACCOUNTS = "AAAAAAAAAA";
    private static final String UPDATED_INTEREST_ASSIGN_ACCOUNTS = "BBBBBBBBBB";

    private static final String DEFAULT_INTEREST_SPECIAL_ACCOUNTS_ID = "AAAAAAAAAA";
    private static final String UPDATED_INTEREST_SPECIAL_ACCOUNTS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BAIL = "AAAAAAAAAA";
    private static final String UPDATED_BAIL = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BAIL_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BAIL_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_PRODUCT_JOIN_COST = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_JOIN_COST = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_JOIN_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_JOIN_COST = new BigDecimal(2);

    private static final String DEFAULT_PRODUCT_EXIT_COST = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_EXIT_COST = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_EXIT_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXIT_COST = new BigDecimal(2);

    private static final String DEFAULT_FILE_CONTRACT_ID = "AAAAAAAAAA";
    private static final String UPDATED_FILE_CONTRACT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_IMG_VIEW_ID = "AAAAAAAAAA";
    private static final String UPDATED_IMG_VIEW_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STRUTS = "AAAAAAAAAA";
    private static final String UPDATED_STRUTS = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCTS_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCTS_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DEL_FLAG = false;
    private static final Boolean UPDATED_DEL_FLAG = true;

    private static final Instant DEFAULT_PUB_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUB_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DAY_RATE_CALCULATION = "AAAAAAAAAA";
    private static final String UPDATED_DAY_RATE_CALCULATION = "BBBBBBBBBB";

    private static final String DEFAULT_PROD_FLG = "AAAAAAAAAA";
    private static final String UPDATED_PROD_FLG = "BBBBBBBBBB";

    private static final String DEFAULT_EXTEND_ATTRIBUTE = "AAAAAAAAAA";
    private static final String UPDATED_EXTEND_ATTRIBUTE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ProductSpecRepository productSpecRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductSpecMockMvc;

    private ProductSpec productSpec;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductSpecResource productSpecResource = new ProductSpecResource(productSpecRepository);
        this.restProductSpecMockMvc = MockMvcBuilders.standaloneSetup(productSpecResource)
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
    public static ProductSpec createEntity(EntityManager em) {
        ProductSpec productSpec = new ProductSpec()
            .category(DEFAULT_CATEGORY)
            .name(DEFAULT_NAME)
            .startInterestType(DEFAULT_START_INTEREST_TYPE)
            .startInterestTime(DEFAULT_START_INTEREST_TIME)
            .repayTimeType(DEFAULT_REPAY_TIME_TYPE)
            .principalAssignAccounts(DEFAULT_PRINCIPAL_ASSIGN_ACCOUNTS)
            .principalSpecialAccountsId(DEFAULT_PRINCIPAL_SPECIAL_ACCOUNTS_ID)
            .interestAssignAccounts(DEFAULT_INTEREST_ASSIGN_ACCOUNTS)
            .interestSpecialAccountsId(DEFAULT_INTEREST_SPECIAL_ACCOUNTS_ID)
            .bail(DEFAULT_BAIL)
            .bailAmount(DEFAULT_BAIL_AMOUNT)
            .productJoinCost(DEFAULT_PRODUCT_JOIN_COST)
            .joinCost(DEFAULT_JOIN_COST)
            .productExitCost(DEFAULT_PRODUCT_EXIT_COST)
            .exitCost(DEFAULT_EXIT_COST)
            .fileContractId(DEFAULT_FILE_CONTRACT_ID)
            .imgViewId(DEFAULT_IMG_VIEW_ID)
            .struts(DEFAULT_STRUTS)
            .productsDescription(DEFAULT_PRODUCTS_DESCRIPTION)
            .delFlag(DEFAULT_DEL_FLAG)
            .pubDate(DEFAULT_PUB_DATE)
            .dayRateCalculation(DEFAULT_DAY_RATE_CALCULATION)
            .prodFlg(DEFAULT_PROD_FLG)
            .extendAttribute(DEFAULT_EXTEND_ATTRIBUTE);
        return productSpec;
    }

    @Before
    public void initTest() {
        productSpec = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductSpec() throws Exception {
        int databaseSizeBeforeCreate = productSpecRepository.findAll().size();

        // Create the ProductSpec
        restProductSpecMockMvc.perform(post("/api/product-specs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productSpec)))
            .andExpect(status().isCreated());

        // Validate the ProductSpec in the database
        List<ProductSpec> productSpecList = productSpecRepository.findAll();
        assertThat(productSpecList).hasSize(databaseSizeBeforeCreate + 1);
        ProductSpec testProductSpec = productSpecList.get(productSpecList.size() - 1);
        assertThat(testProductSpec.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testProductSpec.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProductSpec.getStartInterestType()).isEqualTo(DEFAULT_START_INTEREST_TYPE);
        assertThat(testProductSpec.getStartInterestTime()).isEqualTo(DEFAULT_START_INTEREST_TIME);
        assertThat(testProductSpec.getRepayTimeType()).isEqualTo(DEFAULT_REPAY_TIME_TYPE);
        assertThat(testProductSpec.getPrincipalAssignAccounts()).isEqualTo(DEFAULT_PRINCIPAL_ASSIGN_ACCOUNTS);
        assertThat(testProductSpec.getPrincipalSpecialAccountsId()).isEqualTo(DEFAULT_PRINCIPAL_SPECIAL_ACCOUNTS_ID);
        assertThat(testProductSpec.getInterestAssignAccounts()).isEqualTo(DEFAULT_INTEREST_ASSIGN_ACCOUNTS);
        assertThat(testProductSpec.getInterestSpecialAccountsId()).isEqualTo(DEFAULT_INTEREST_SPECIAL_ACCOUNTS_ID);
        assertThat(testProductSpec.getBail()).isEqualTo(DEFAULT_BAIL);
        assertThat(testProductSpec.getBailAmount()).isEqualTo(DEFAULT_BAIL_AMOUNT);
        assertThat(testProductSpec.getProductJoinCost()).isEqualTo(DEFAULT_PRODUCT_JOIN_COST);
        assertThat(testProductSpec.getJoinCost()).isEqualTo(DEFAULT_JOIN_COST);
        assertThat(testProductSpec.getProductExitCost()).isEqualTo(DEFAULT_PRODUCT_EXIT_COST);
        assertThat(testProductSpec.getExitCost()).isEqualTo(DEFAULT_EXIT_COST);
        assertThat(testProductSpec.getFileContractId()).isEqualTo(DEFAULT_FILE_CONTRACT_ID);
        assertThat(testProductSpec.getImgViewId()).isEqualTo(DEFAULT_IMG_VIEW_ID);
        assertThat(testProductSpec.getStruts()).isEqualTo(DEFAULT_STRUTS);
        assertThat(testProductSpec.getProductsDescription()).isEqualTo(DEFAULT_PRODUCTS_DESCRIPTION);
        assertThat(testProductSpec.isDelFlag()).isEqualTo(DEFAULT_DEL_FLAG);
        assertThat(testProductSpec.getPubDate()).isEqualTo(DEFAULT_PUB_DATE);
        assertThat(testProductSpec.getDayRateCalculation()).isEqualTo(DEFAULT_DAY_RATE_CALCULATION);
        assertThat(testProductSpec.getProdFlg()).isEqualTo(DEFAULT_PROD_FLG);
        assertThat(testProductSpec.getExtendAttribute()).isEqualTo(DEFAULT_EXTEND_ATTRIBUTE);
        assertThat(testProductSpec.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductSpec.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductSpec.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testProductSpec.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createProductSpecWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productSpecRepository.findAll().size();

        // Create the ProductSpec with an existing ID
        productSpec.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductSpecMockMvc.perform(post("/api/product-specs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productSpec)))
            .andExpect(status().isBadRequest());

        // Validate the ProductSpec in the database
        List<ProductSpec> productSpecList = productSpecRepository.findAll();
        assertThat(productSpecList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProductSpecs() throws Exception {
        // Initialize the database
        productSpecRepository.saveAndFlush(productSpec);

        // Get all the productSpecList
        restProductSpecMockMvc.perform(get("/api/product-specs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productSpec.getId().intValue())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].startInterestType").value(hasItem(DEFAULT_START_INTEREST_TYPE.toString())))
            .andExpect(jsonPath("$.[*].startInterestTime").value(hasItem(DEFAULT_START_INTEREST_TIME.toString())))
            .andExpect(jsonPath("$.[*].repayTimeType").value(hasItem(DEFAULT_REPAY_TIME_TYPE.toString())))
            .andExpect(jsonPath("$.[*].principalAssignAccounts").value(hasItem(DEFAULT_PRINCIPAL_ASSIGN_ACCOUNTS.toString())))
            .andExpect(jsonPath("$.[*].principalSpecialAccountsId").value(hasItem(DEFAULT_PRINCIPAL_SPECIAL_ACCOUNTS_ID.toString())))
            .andExpect(jsonPath("$.[*].interestAssignAccounts").value(hasItem(DEFAULT_INTEREST_ASSIGN_ACCOUNTS.toString())))
            .andExpect(jsonPath("$.[*].interestSpecialAccountsId").value(hasItem(DEFAULT_INTEREST_SPECIAL_ACCOUNTS_ID.toString())))
            .andExpect(jsonPath("$.[*].bail").value(hasItem(DEFAULT_BAIL.toString())))
            .andExpect(jsonPath("$.[*].bailAmount").value(hasItem(DEFAULT_BAIL_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].productJoinCost").value(hasItem(DEFAULT_PRODUCT_JOIN_COST.toString())))
            .andExpect(jsonPath("$.[*].joinCost").value(hasItem(DEFAULT_JOIN_COST.intValue())))
            .andExpect(jsonPath("$.[*].productExitCost").value(hasItem(DEFAULT_PRODUCT_EXIT_COST.toString())))
            .andExpect(jsonPath("$.[*].exitCost").value(hasItem(DEFAULT_EXIT_COST.intValue())))
            .andExpect(jsonPath("$.[*].fileContractId").value(hasItem(DEFAULT_FILE_CONTRACT_ID.toString())))
            .andExpect(jsonPath("$.[*].imgViewId").value(hasItem(DEFAULT_IMG_VIEW_ID.toString())))
            .andExpect(jsonPath("$.[*].struts").value(hasItem(DEFAULT_STRUTS.toString())))
            .andExpect(jsonPath("$.[*].productsDescription").value(hasItem(DEFAULT_PRODUCTS_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].delFlag").value(hasItem(DEFAULT_DEL_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].pubDate").value(hasItem(DEFAULT_PUB_DATE.toString())))
            .andExpect(jsonPath("$.[*].dayRateCalculation").value(hasItem(DEFAULT_DAY_RATE_CALCULATION.toString())))
            .andExpect(jsonPath("$.[*].prodFlg").value(hasItem(DEFAULT_PROD_FLG.toString())))
            .andExpect(jsonPath("$.[*].extendAttribute").value(hasItem(DEFAULT_EXTEND_ATTRIBUTE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getProductSpec() throws Exception {
        // Initialize the database
        productSpecRepository.saveAndFlush(productSpec);

        // Get the productSpec
        restProductSpecMockMvc.perform(get("/api/product-specs/{id}", productSpec.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productSpec.getId().intValue()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.startInterestType").value(DEFAULT_START_INTEREST_TYPE.toString()))
            .andExpect(jsonPath("$.startInterestTime").value(DEFAULT_START_INTEREST_TIME.toString()))
            .andExpect(jsonPath("$.repayTimeType").value(DEFAULT_REPAY_TIME_TYPE.toString()))
            .andExpect(jsonPath("$.principalAssignAccounts").value(DEFAULT_PRINCIPAL_ASSIGN_ACCOUNTS.toString()))
            .andExpect(jsonPath("$.principalSpecialAccountsId").value(DEFAULT_PRINCIPAL_SPECIAL_ACCOUNTS_ID.toString()))
            .andExpect(jsonPath("$.interestAssignAccounts").value(DEFAULT_INTEREST_ASSIGN_ACCOUNTS.toString()))
            .andExpect(jsonPath("$.interestSpecialAccountsId").value(DEFAULT_INTEREST_SPECIAL_ACCOUNTS_ID.toString()))
            .andExpect(jsonPath("$.bail").value(DEFAULT_BAIL.toString()))
            .andExpect(jsonPath("$.bailAmount").value(DEFAULT_BAIL_AMOUNT.intValue()))
            .andExpect(jsonPath("$.productJoinCost").value(DEFAULT_PRODUCT_JOIN_COST.toString()))
            .andExpect(jsonPath("$.joinCost").value(DEFAULT_JOIN_COST.intValue()))
            .andExpect(jsonPath("$.productExitCost").value(DEFAULT_PRODUCT_EXIT_COST.toString()))
            .andExpect(jsonPath("$.exitCost").value(DEFAULT_EXIT_COST.intValue()))
            .andExpect(jsonPath("$.fileContractId").value(DEFAULT_FILE_CONTRACT_ID.toString()))
            .andExpect(jsonPath("$.imgViewId").value(DEFAULT_IMG_VIEW_ID.toString()))
            .andExpect(jsonPath("$.struts").value(DEFAULT_STRUTS.toString()))
            .andExpect(jsonPath("$.productsDescription").value(DEFAULT_PRODUCTS_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.delFlag").value(DEFAULT_DEL_FLAG.booleanValue()))
            .andExpect(jsonPath("$.pubDate").value(DEFAULT_PUB_DATE.toString()))
            .andExpect(jsonPath("$.dayRateCalculation").value(DEFAULT_DAY_RATE_CALCULATION.toString()))
            .andExpect(jsonPath("$.prodFlg").value(DEFAULT_PROD_FLG.toString()))
            .andExpect(jsonPath("$.extendAttribute").value(DEFAULT_EXTEND_ATTRIBUTE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProductSpec() throws Exception {
        // Get the productSpec
        restProductSpecMockMvc.perform(get("/api/product-specs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductSpec() throws Exception {
        // Initialize the database
        productSpecRepository.saveAndFlush(productSpec);
        int databaseSizeBeforeUpdate = productSpecRepository.findAll().size();

        // Update the productSpec
        ProductSpec updatedProductSpec = productSpecRepository.findOne(productSpec.getId());
        // Disconnect from session so that the updates on updatedProductSpec are not directly saved in db
        em.detach(updatedProductSpec);
        updatedProductSpec
            .category(UPDATED_CATEGORY)
            .name(UPDATED_NAME)
            .startInterestType(UPDATED_START_INTEREST_TYPE)
            .startInterestTime(UPDATED_START_INTEREST_TIME)
            .repayTimeType(UPDATED_REPAY_TIME_TYPE)
            .principalAssignAccounts(UPDATED_PRINCIPAL_ASSIGN_ACCOUNTS)
            .principalSpecialAccountsId(UPDATED_PRINCIPAL_SPECIAL_ACCOUNTS_ID)
            .interestAssignAccounts(UPDATED_INTEREST_ASSIGN_ACCOUNTS)
            .interestSpecialAccountsId(UPDATED_INTEREST_SPECIAL_ACCOUNTS_ID)
            .bail(UPDATED_BAIL)
            .bailAmount(UPDATED_BAIL_AMOUNT)
            .productJoinCost(UPDATED_PRODUCT_JOIN_COST)
            .joinCost(UPDATED_JOIN_COST)
            .productExitCost(UPDATED_PRODUCT_EXIT_COST)
            .exitCost(UPDATED_EXIT_COST)
            .fileContractId(UPDATED_FILE_CONTRACT_ID)
            .imgViewId(UPDATED_IMG_VIEW_ID)
            .struts(UPDATED_STRUTS)
            .productsDescription(UPDATED_PRODUCTS_DESCRIPTION)
            .delFlag(UPDATED_DEL_FLAG)
            .pubDate(UPDATED_PUB_DATE)
            .dayRateCalculation(UPDATED_DAY_RATE_CALCULATION)
            .prodFlg(UPDATED_PROD_FLG)
            .extendAttribute(UPDATED_EXTEND_ATTRIBUTE);

        restProductSpecMockMvc.perform(put("/api/product-specs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductSpec)))
            .andExpect(status().isOk());

        // Validate the ProductSpec in the database
        List<ProductSpec> productSpecList = productSpecRepository.findAll();
        assertThat(productSpecList).hasSize(databaseSizeBeforeUpdate);
        ProductSpec testProductSpec = productSpecList.get(productSpecList.size() - 1);
        assertThat(testProductSpec.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testProductSpec.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProductSpec.getStartInterestType()).isEqualTo(UPDATED_START_INTEREST_TYPE);
        assertThat(testProductSpec.getStartInterestTime()).isEqualTo(UPDATED_START_INTEREST_TIME);
        assertThat(testProductSpec.getRepayTimeType()).isEqualTo(UPDATED_REPAY_TIME_TYPE);
        assertThat(testProductSpec.getPrincipalAssignAccounts()).isEqualTo(UPDATED_PRINCIPAL_ASSIGN_ACCOUNTS);
        assertThat(testProductSpec.getPrincipalSpecialAccountsId()).isEqualTo(UPDATED_PRINCIPAL_SPECIAL_ACCOUNTS_ID);
        assertThat(testProductSpec.getInterestAssignAccounts()).isEqualTo(UPDATED_INTEREST_ASSIGN_ACCOUNTS);
        assertThat(testProductSpec.getInterestSpecialAccountsId()).isEqualTo(UPDATED_INTEREST_SPECIAL_ACCOUNTS_ID);
        assertThat(testProductSpec.getBail()).isEqualTo(UPDATED_BAIL);
        assertThat(testProductSpec.getBailAmount()).isEqualTo(UPDATED_BAIL_AMOUNT);
        assertThat(testProductSpec.getProductJoinCost()).isEqualTo(UPDATED_PRODUCT_JOIN_COST);
        assertThat(testProductSpec.getJoinCost()).isEqualTo(UPDATED_JOIN_COST);
        assertThat(testProductSpec.getProductExitCost()).isEqualTo(UPDATED_PRODUCT_EXIT_COST);
        assertThat(testProductSpec.getExitCost()).isEqualTo(UPDATED_EXIT_COST);
        assertThat(testProductSpec.getFileContractId()).isEqualTo(UPDATED_FILE_CONTRACT_ID);
        assertThat(testProductSpec.getImgViewId()).isEqualTo(UPDATED_IMG_VIEW_ID);
        assertThat(testProductSpec.getStruts()).isEqualTo(UPDATED_STRUTS);
        assertThat(testProductSpec.getProductsDescription()).isEqualTo(UPDATED_PRODUCTS_DESCRIPTION);
        assertThat(testProductSpec.isDelFlag()).isEqualTo(UPDATED_DEL_FLAG);
        assertThat(testProductSpec.getPubDate()).isEqualTo(UPDATED_PUB_DATE);
        assertThat(testProductSpec.getDayRateCalculation()).isEqualTo(UPDATED_DAY_RATE_CALCULATION);
        assertThat(testProductSpec.getProdFlg()).isEqualTo(UPDATED_PROD_FLG);
        assertThat(testProductSpec.getExtendAttribute()).isEqualTo(UPDATED_EXTEND_ATTRIBUTE);
        assertThat(testProductSpec.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductSpec.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductSpec.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testProductSpec.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingProductSpec() throws Exception {
        int databaseSizeBeforeUpdate = productSpecRepository.findAll().size();

        // Create the ProductSpec

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProductSpecMockMvc.perform(put("/api/product-specs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productSpec)))
            .andExpect(status().isCreated());

        // Validate the ProductSpec in the database
        List<ProductSpec> productSpecList = productSpecRepository.findAll();
        assertThat(productSpecList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProductSpec() throws Exception {
        // Initialize the database
        productSpecRepository.saveAndFlush(productSpec);
        int databaseSizeBeforeDelete = productSpecRepository.findAll().size();

        // Get the productSpec
        restProductSpecMockMvc.perform(delete("/api/product-specs/{id}", productSpec.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProductSpec> productSpecList = productSpecRepository.findAll();
        assertThat(productSpecList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductSpec.class);
        ProductSpec productSpec1 = new ProductSpec();
        productSpec1.setId(1L);
        ProductSpec productSpec2 = new ProductSpec();
        productSpec2.setId(productSpec1.getId());
        assertThat(productSpec1).isEqualTo(productSpec2);
        productSpec2.setId(2L);
        assertThat(productSpec1).isNotEqualTo(productSpec2);
        productSpec1.setId(null);
        assertThat(productSpec1).isNotEqualTo(productSpec2);
    }
}
