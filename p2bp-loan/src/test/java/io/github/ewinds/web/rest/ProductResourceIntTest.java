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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.ewinds.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.ewinds.domain.enumeration.ProductState;
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
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
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
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
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
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
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
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

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
