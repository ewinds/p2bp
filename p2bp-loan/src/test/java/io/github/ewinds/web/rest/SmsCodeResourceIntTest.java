package io.github.ewinds.web.rest;

import io.github.ewinds.LoanApp;

import io.github.ewinds.config.SecurityBeanOverrideConfiguration;

import io.github.ewinds.domain.SmsCode;
import io.github.ewinds.repository.SmsCodeRepository;
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
 * Test class for the SmsCodeResource REST controller.
 *
 * @see SmsCodeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LoanApp.class, SecurityBeanOverrideConfiguration.class})
public class SmsCodeResourceIntTest {

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_EXPIRED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXPIRED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private SmsCodeRepository smsCodeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSmsCodeMockMvc;

    private SmsCode smsCode;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SmsCodeResource smsCodeResource = new SmsCodeResource(smsCodeRepository);
        this.restSmsCodeMockMvc = MockMvcBuilders.standaloneSetup(smsCodeResource)
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
    public static SmsCode createEntity(EntityManager em) {
        SmsCode smsCode = new SmsCode()
            .phone(DEFAULT_PHONE)
            .code(DEFAULT_CODE)
            .expiredDate(DEFAULT_EXPIRED_DATE);
        return smsCode;
    }

    @Before
    public void initTest() {
        smsCode = createEntity(em);
    }

    @Test
    @Transactional
    public void createSmsCode() throws Exception {
        int databaseSizeBeforeCreate = smsCodeRepository.findAll().size();

        // Create the SmsCode
        restSmsCodeMockMvc.perform(post("/api/sms-codes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(smsCode)))
            .andExpect(status().isCreated());

        // Validate the SmsCode in the database
        List<SmsCode> smsCodeList = smsCodeRepository.findAll();
        assertThat(smsCodeList).hasSize(databaseSizeBeforeCreate + 1);
        SmsCode testSmsCode = smsCodeList.get(smsCodeList.size() - 1);
        assertThat(testSmsCode.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testSmsCode.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSmsCode.getExpiredDate()).isEqualTo(DEFAULT_EXPIRED_DATE);
        assertThat(testSmsCode.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSmsCode.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSmsCode.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testSmsCode.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createSmsCodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = smsCodeRepository.findAll().size();

        // Create the SmsCode with an existing ID
        smsCode.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSmsCodeMockMvc.perform(post("/api/sms-codes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(smsCode)))
            .andExpect(status().isBadRequest());

        // Validate the SmsCode in the database
        List<SmsCode> smsCodeList = smsCodeRepository.findAll();
        assertThat(smsCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSmsCodes() throws Exception {
        // Initialize the database
        smsCodeRepository.saveAndFlush(smsCode);

        // Get all the smsCodeList
        restSmsCodeMockMvc.perform(get("/api/sms-codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(smsCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].expiredDate").value(hasItem(DEFAULT_EXPIRED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getSmsCode() throws Exception {
        // Initialize the database
        smsCodeRepository.saveAndFlush(smsCode);

        // Get the smsCode
        restSmsCodeMockMvc.perform(get("/api/sms-codes/{id}", smsCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(smsCode.getId().intValue()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.expiredDate").value(DEFAULT_EXPIRED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSmsCode() throws Exception {
        // Get the smsCode
        restSmsCodeMockMvc.perform(get("/api/sms-codes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSmsCode() throws Exception {
        // Initialize the database
        smsCodeRepository.saveAndFlush(smsCode);
        int databaseSizeBeforeUpdate = smsCodeRepository.findAll().size();

        // Update the smsCode
        SmsCode updatedSmsCode = smsCodeRepository.findOne(smsCode.getId());
        // Disconnect from session so that the updates on updatedSmsCode are not directly saved in db
        em.detach(updatedSmsCode);
        updatedSmsCode
            .phone(UPDATED_PHONE)
            .code(UPDATED_CODE)
            .expiredDate(UPDATED_EXPIRED_DATE);

        restSmsCodeMockMvc.perform(put("/api/sms-codes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSmsCode)))
            .andExpect(status().isOk());

        // Validate the SmsCode in the database
        List<SmsCode> smsCodeList = smsCodeRepository.findAll();
        assertThat(smsCodeList).hasSize(databaseSizeBeforeUpdate);
        SmsCode testSmsCode = smsCodeList.get(smsCodeList.size() - 1);
        assertThat(testSmsCode.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testSmsCode.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSmsCode.getExpiredDate()).isEqualTo(UPDATED_EXPIRED_DATE);
        assertThat(testSmsCode.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSmsCode.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSmsCode.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testSmsCode.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingSmsCode() throws Exception {
        int databaseSizeBeforeUpdate = smsCodeRepository.findAll().size();

        // Create the SmsCode

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSmsCodeMockMvc.perform(put("/api/sms-codes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(smsCode)))
            .andExpect(status().isCreated());

        // Validate the SmsCode in the database
        List<SmsCode> smsCodeList = smsCodeRepository.findAll();
        assertThat(smsCodeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSmsCode() throws Exception {
        // Initialize the database
        smsCodeRepository.saveAndFlush(smsCode);
        int databaseSizeBeforeDelete = smsCodeRepository.findAll().size();

        // Get the smsCode
        restSmsCodeMockMvc.perform(delete("/api/sms-codes/{id}", smsCode.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SmsCode> smsCodeList = smsCodeRepository.findAll();
        assertThat(smsCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SmsCode.class);
        SmsCode smsCode1 = new SmsCode();
        smsCode1.setId(1L);
        SmsCode smsCode2 = new SmsCode();
        smsCode2.setId(smsCode1.getId());
        assertThat(smsCode1).isEqualTo(smsCode2);
        smsCode2.setId(2L);
        assertThat(smsCode1).isNotEqualTo(smsCode2);
        smsCode1.setId(null);
        assertThat(smsCode1).isNotEqualTo(smsCode2);
    }
}
