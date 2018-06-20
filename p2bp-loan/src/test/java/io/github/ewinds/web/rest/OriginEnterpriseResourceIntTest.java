package io.github.ewinds.web.rest;

import io.github.ewinds.LoanApp;

import io.github.ewinds.config.SecurityBeanOverrideConfiguration;

import io.github.ewinds.domain.OriginEnterprise;
import io.github.ewinds.repository.OriginEnterpriseRepository;
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

/**
 * Test class for the OriginEnterpriseResource REST controller.
 *
 * @see OriginEnterpriseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LoanApp.class, SecurityBeanOverrideConfiguration.class})
public class OriginEnterpriseResourceIntTest {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ABBR_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ABBR_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTERED_CAPITAL = "AAAAAAAAAA";
    private static final String UPDATED_REGISTERED_CAPITAL = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_NUM = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_INDUSTRY = "AAAAAAAAAA";
    private static final String UPDATED_INDUSTRY = "BBBBBBBBBB";

    private static final String DEFAULT_EARNING = "AAAAAAAAAA";
    private static final String UPDATED_EARNING = "BBBBBBBBBB";

    private static final String DEFAULT_SIMPLE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_SIMPLE_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_INTRODUCE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_INTRODUCE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_BUSINESS_CHECKED = false;
    private static final Boolean UPDATED_BUSINESS_CHECKED = true;

    private static final Boolean DEFAULT_LEGAL_CARD_CHECKED = false;
    private static final Boolean UPDATED_LEGAL_CARD_CHECKED = true;

    private static final Boolean DEFAULT_BONDING_CHECKED = false;
    private static final Boolean UPDATED_BONDING_CHECKED = true;

    private static final Boolean DEFAULT_PLATFORM_CHECKED = false;
    private static final Boolean UPDATED_PLATFORM_CHECKED = true;

    private static final Boolean DEFAULT_ADDRESS_CHECKED = false;
    private static final Boolean UPDATED_ADDRESS_CHECKED = true;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private OriginEnterpriseRepository originEnterpriseRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOriginEnterpriseMockMvc;

    private OriginEnterprise originEnterprise;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OriginEnterpriseResource originEnterpriseResource = new OriginEnterpriseResource(originEnterpriseRepository);
        this.restOriginEnterpriseMockMvc = MockMvcBuilders.standaloneSetup(originEnterpriseResource)
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
    public static OriginEnterprise createEntity(EntityManager em) {
        OriginEnterprise originEnterprise = new OriginEnterprise()
            .companyName(DEFAULT_COMPANY_NAME)
            .abbrCompanyName(DEFAULT_ABBR_COMPANY_NAME)
            .registeredCapital(DEFAULT_REGISTERED_CAPITAL)
            .legalPerson(DEFAULT_LEGAL_PERSON)
            .businessNum(DEFAULT_BUSINESS_NUM)
            .industry(DEFAULT_INDUSTRY)
            .earning(DEFAULT_EARNING)
            .simpleDesc(DEFAULT_SIMPLE_DESC)
            .companyIntroduce(DEFAULT_COMPANY_INTRODUCE)
            .businessChecked(DEFAULT_BUSINESS_CHECKED)
            .legalCardChecked(DEFAULT_LEGAL_CARD_CHECKED)
            .bondingChecked(DEFAULT_BONDING_CHECKED)
            .platformChecked(DEFAULT_PLATFORM_CHECKED)
            .addressChecked(DEFAULT_ADDRESS_CHECKED)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return originEnterprise;
    }

    @Before
    public void initTest() {
        originEnterprise = createEntity(em);
    }

    @Test
    @Transactional
    public void createOriginEnterprise() throws Exception {
        int databaseSizeBeforeCreate = originEnterpriseRepository.findAll().size();

        // Create the OriginEnterprise
        restOriginEnterpriseMockMvc.perform(post("/api/origin-enterprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(originEnterprise)))
            .andExpect(status().isCreated());

        // Validate the OriginEnterprise in the database
        List<OriginEnterprise> originEnterpriseList = originEnterpriseRepository.findAll();
        assertThat(originEnterpriseList).hasSize(databaseSizeBeforeCreate + 1);
        OriginEnterprise testOriginEnterprise = originEnterpriseList.get(originEnterpriseList.size() - 1);
        assertThat(testOriginEnterprise.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testOriginEnterprise.getAbbrCompanyName()).isEqualTo(DEFAULT_ABBR_COMPANY_NAME);
        assertThat(testOriginEnterprise.getRegisteredCapital()).isEqualTo(DEFAULT_REGISTERED_CAPITAL);
        assertThat(testOriginEnterprise.getLegalPerson()).isEqualTo(DEFAULT_LEGAL_PERSON);
        assertThat(testOriginEnterprise.getBusinessNum()).isEqualTo(DEFAULT_BUSINESS_NUM);
        assertThat(testOriginEnterprise.getIndustry()).isEqualTo(DEFAULT_INDUSTRY);
        assertThat(testOriginEnterprise.getEarning()).isEqualTo(DEFAULT_EARNING);
        assertThat(testOriginEnterprise.getSimpleDesc()).isEqualTo(DEFAULT_SIMPLE_DESC);
        assertThat(testOriginEnterprise.getCompanyIntroduce()).isEqualTo(DEFAULT_COMPANY_INTRODUCE);
        assertThat(testOriginEnterprise.isBusinessChecked()).isEqualTo(DEFAULT_BUSINESS_CHECKED);
        assertThat(testOriginEnterprise.isLegalCardChecked()).isEqualTo(DEFAULT_LEGAL_CARD_CHECKED);
        assertThat(testOriginEnterprise.isBondingChecked()).isEqualTo(DEFAULT_BONDING_CHECKED);
        assertThat(testOriginEnterprise.isPlatformChecked()).isEqualTo(DEFAULT_PLATFORM_CHECKED);
        assertThat(testOriginEnterprise.isAddressChecked()).isEqualTo(DEFAULT_ADDRESS_CHECKED);
        assertThat(testOriginEnterprise.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOriginEnterprise.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOriginEnterprise.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testOriginEnterprise.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createOriginEnterpriseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = originEnterpriseRepository.findAll().size();

        // Create the OriginEnterprise with an existing ID
        originEnterprise.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOriginEnterpriseMockMvc.perform(post("/api/origin-enterprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(originEnterprise)))
            .andExpect(status().isBadRequest());

        // Validate the OriginEnterprise in the database
        List<OriginEnterprise> originEnterpriseList = originEnterpriseRepository.findAll();
        assertThat(originEnterpriseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOriginEnterprises() throws Exception {
        // Initialize the database
        originEnterpriseRepository.saveAndFlush(originEnterprise);

        // Get all the originEnterpriseList
        restOriginEnterpriseMockMvc.perform(get("/api/origin-enterprises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(originEnterprise.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].abbrCompanyName").value(hasItem(DEFAULT_ABBR_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].registeredCapital").value(hasItem(DEFAULT_REGISTERED_CAPITAL.toString())))
            .andExpect(jsonPath("$.[*].legalPerson").value(hasItem(DEFAULT_LEGAL_PERSON.toString())))
            .andExpect(jsonPath("$.[*].businessNum").value(hasItem(DEFAULT_BUSINESS_NUM.toString())))
            .andExpect(jsonPath("$.[*].industry").value(hasItem(DEFAULT_INDUSTRY.toString())))
            .andExpect(jsonPath("$.[*].earning").value(hasItem(DEFAULT_EARNING.toString())))
            .andExpect(jsonPath("$.[*].simpleDesc").value(hasItem(DEFAULT_SIMPLE_DESC.toString())))
            .andExpect(jsonPath("$.[*].companyIntroduce").value(hasItem(DEFAULT_COMPANY_INTRODUCE.toString())))
            .andExpect(jsonPath("$.[*].businessChecked").value(hasItem(DEFAULT_BUSINESS_CHECKED.booleanValue())))
            .andExpect(jsonPath("$.[*].legalCardChecked").value(hasItem(DEFAULT_LEGAL_CARD_CHECKED.booleanValue())))
            .andExpect(jsonPath("$.[*].bondingChecked").value(hasItem(DEFAULT_BONDING_CHECKED.booleanValue())))
            .andExpect(jsonPath("$.[*].platformChecked").value(hasItem(DEFAULT_PLATFORM_CHECKED.booleanValue())))
            .andExpect(jsonPath("$.[*].addressChecked").value(hasItem(DEFAULT_ADDRESS_CHECKED.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getOriginEnterprise() throws Exception {
        // Initialize the database
        originEnterpriseRepository.saveAndFlush(originEnterprise);

        // Get the originEnterprise
        restOriginEnterpriseMockMvc.perform(get("/api/origin-enterprises/{id}", originEnterprise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(originEnterprise.getId().intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.abbrCompanyName").value(DEFAULT_ABBR_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.registeredCapital").value(DEFAULT_REGISTERED_CAPITAL.toString()))
            .andExpect(jsonPath("$.legalPerson").value(DEFAULT_LEGAL_PERSON.toString()))
            .andExpect(jsonPath("$.businessNum").value(DEFAULT_BUSINESS_NUM.toString()))
            .andExpect(jsonPath("$.industry").value(DEFAULT_INDUSTRY.toString()))
            .andExpect(jsonPath("$.earning").value(DEFAULT_EARNING.toString()))
            .andExpect(jsonPath("$.simpleDesc").value(DEFAULT_SIMPLE_DESC.toString()))
            .andExpect(jsonPath("$.companyIntroduce").value(DEFAULT_COMPANY_INTRODUCE.toString()))
            .andExpect(jsonPath("$.businessChecked").value(DEFAULT_BUSINESS_CHECKED.booleanValue()))
            .andExpect(jsonPath("$.legalCardChecked").value(DEFAULT_LEGAL_CARD_CHECKED.booleanValue()))
            .andExpect(jsonPath("$.bondingChecked").value(DEFAULT_BONDING_CHECKED.booleanValue()))
            .andExpect(jsonPath("$.platformChecked").value(DEFAULT_PLATFORM_CHECKED.booleanValue()))
            .andExpect(jsonPath("$.addressChecked").value(DEFAULT_ADDRESS_CHECKED.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOriginEnterprise() throws Exception {
        // Get the originEnterprise
        restOriginEnterpriseMockMvc.perform(get("/api/origin-enterprises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOriginEnterprise() throws Exception {
        // Initialize the database
        originEnterpriseRepository.saveAndFlush(originEnterprise);
        int databaseSizeBeforeUpdate = originEnterpriseRepository.findAll().size();

        // Update the originEnterprise
        OriginEnterprise updatedOriginEnterprise = originEnterpriseRepository.findOne(originEnterprise.getId());
        // Disconnect from session so that the updates on updatedOriginEnterprise are not directly saved in db
        em.detach(updatedOriginEnterprise);
        updatedOriginEnterprise
            .companyName(UPDATED_COMPANY_NAME)
            .abbrCompanyName(UPDATED_ABBR_COMPANY_NAME)
            .registeredCapital(UPDATED_REGISTERED_CAPITAL)
            .legalPerson(UPDATED_LEGAL_PERSON)
            .businessNum(UPDATED_BUSINESS_NUM)
            .industry(UPDATED_INDUSTRY)
            .earning(UPDATED_EARNING)
            .simpleDesc(UPDATED_SIMPLE_DESC)
            .companyIntroduce(UPDATED_COMPANY_INTRODUCE)
            .businessChecked(UPDATED_BUSINESS_CHECKED)
            .legalCardChecked(UPDATED_LEGAL_CARD_CHECKED)
            .bondingChecked(UPDATED_BONDING_CHECKED)
            .platformChecked(UPDATED_PLATFORM_CHECKED)
            .addressChecked(UPDATED_ADDRESS_CHECKED)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restOriginEnterpriseMockMvc.perform(put("/api/origin-enterprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOriginEnterprise)))
            .andExpect(status().isOk());

        // Validate the OriginEnterprise in the database
        List<OriginEnterprise> originEnterpriseList = originEnterpriseRepository.findAll();
        assertThat(originEnterpriseList).hasSize(databaseSizeBeforeUpdate);
        OriginEnterprise testOriginEnterprise = originEnterpriseList.get(originEnterpriseList.size() - 1);
        assertThat(testOriginEnterprise.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testOriginEnterprise.getAbbrCompanyName()).isEqualTo(UPDATED_ABBR_COMPANY_NAME);
        assertThat(testOriginEnterprise.getRegisteredCapital()).isEqualTo(UPDATED_REGISTERED_CAPITAL);
        assertThat(testOriginEnterprise.getLegalPerson()).isEqualTo(UPDATED_LEGAL_PERSON);
        assertThat(testOriginEnterprise.getBusinessNum()).isEqualTo(UPDATED_BUSINESS_NUM);
        assertThat(testOriginEnterprise.getIndustry()).isEqualTo(UPDATED_INDUSTRY);
        assertThat(testOriginEnterprise.getEarning()).isEqualTo(UPDATED_EARNING);
        assertThat(testOriginEnterprise.getSimpleDesc()).isEqualTo(UPDATED_SIMPLE_DESC);
        assertThat(testOriginEnterprise.getCompanyIntroduce()).isEqualTo(UPDATED_COMPANY_INTRODUCE);
        assertThat(testOriginEnterprise.isBusinessChecked()).isEqualTo(UPDATED_BUSINESS_CHECKED);
        assertThat(testOriginEnterprise.isLegalCardChecked()).isEqualTo(UPDATED_LEGAL_CARD_CHECKED);
        assertThat(testOriginEnterprise.isBondingChecked()).isEqualTo(UPDATED_BONDING_CHECKED);
        assertThat(testOriginEnterprise.isPlatformChecked()).isEqualTo(UPDATED_PLATFORM_CHECKED);
        assertThat(testOriginEnterprise.isAddressChecked()).isEqualTo(UPDATED_ADDRESS_CHECKED);
        assertThat(testOriginEnterprise.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOriginEnterprise.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOriginEnterprise.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testOriginEnterprise.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingOriginEnterprise() throws Exception {
        int databaseSizeBeforeUpdate = originEnterpriseRepository.findAll().size();

        // Create the OriginEnterprise

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOriginEnterpriseMockMvc.perform(put("/api/origin-enterprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(originEnterprise)))
            .andExpect(status().isCreated());

        // Validate the OriginEnterprise in the database
        List<OriginEnterprise> originEnterpriseList = originEnterpriseRepository.findAll();
        assertThat(originEnterpriseList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOriginEnterprise() throws Exception {
        // Initialize the database
        originEnterpriseRepository.saveAndFlush(originEnterprise);
        int databaseSizeBeforeDelete = originEnterpriseRepository.findAll().size();

        // Get the originEnterprise
        restOriginEnterpriseMockMvc.perform(delete("/api/origin-enterprises/{id}", originEnterprise.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OriginEnterprise> originEnterpriseList = originEnterpriseRepository.findAll();
        assertThat(originEnterpriseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OriginEnterprise.class);
        OriginEnterprise originEnterprise1 = new OriginEnterprise();
        originEnterprise1.setId(1L);
        OriginEnterprise originEnterprise2 = new OriginEnterprise();
        originEnterprise2.setId(originEnterprise1.getId());
        assertThat(originEnterprise1).isEqualTo(originEnterprise2);
        originEnterprise2.setId(2L);
        assertThat(originEnterprise1).isNotEqualTo(originEnterprise2);
        originEnterprise1.setId(null);
        assertThat(originEnterprise1).isNotEqualTo(originEnterprise2);
    }
}
