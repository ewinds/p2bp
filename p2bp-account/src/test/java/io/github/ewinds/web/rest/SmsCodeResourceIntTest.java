package io.github.ewinds.web.rest;

import io.github.ewinds.AccountApp;
import io.github.ewinds.config.SecurityBeanOverrideConfiguration;
import io.github.ewinds.domain.SmsCode;
import io.github.ewinds.repository.SmsCodeRepository;
import io.github.ewinds.service.SmsCodeService;
import io.github.ewinds.web.rest.errors.ExceptionTranslator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the SmsCodeResource REST controller.
 *
 * @see SmsCodeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountApp.class, SecurityBeanOverrideConfiguration.class})
public class SmsCodeResourceIntTest {

    private static final String DEFAULT_PHONE = "13555555555";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_EXPIRED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXPIRED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private SmsCodeRepository smsCodeRepository;

    @Autowired
    private SmsCodeService smsCodeService;

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
        final SmsCodeResource smsCodeResource = new SmsCodeResource(smsCodeService);
        this.restSmsCodeMockMvc = MockMvcBuilders.standaloneSetup(smsCodeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     * <p>
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
        assertThat(testSmsCode.getCode().length()).isEqualTo(6);
        assertThat(testSmsCode.getExpiredDate()).isAfter(Instant.now());
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
    public void verifySmsCode() throws Exception {
        // Initialize the database
        smsCode = smsCodeService.save(smsCode);

        int databaseSizeBeforeUpdate = smsCodeRepository.findAll().size();

        // Update the smsCode
        SmsCode updatedSmsCode = smsCodeRepository.findOne(smsCode.getId());

        restSmsCodeMockMvc.perform(post("/api/sms-codes:verify")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSmsCode)))
            .andExpect(status().isOk());

        // Validate the SmsCode in the database
        List<SmsCode> smsCodeList = smsCodeRepository.findAll();
        assertThat(smsCodeList).hasSize(databaseSizeBeforeUpdate);

        restSmsCodeMockMvc.perform(post("/api/sms-codes:verify")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(new SmsCode().phone(DEFAULT_PHONE))))
            .andExpect(status().isBadRequest());

        restSmsCodeMockMvc.perform(post("/api/sms-codes:verify")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(new SmsCode().phone(DEFAULT_PHONE).code(DEFAULT_CODE))))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void verifySmsCodeExpired() throws Exception {
        // Initialize the database
        smsCode.code("123456").expiredDate(Instant.now().minus(5, ChronoUnit.MINUTES));
        smsCode = smsCodeRepository.save(smsCode);

        int databaseSizeBeforeUpdate = smsCodeRepository.findAll().size();

        // Update the smsCode
        SmsCode updatedSmsCode = smsCodeRepository.findOne(smsCode.getId());

        restSmsCodeMockMvc.perform(post("/api/sms-codes:verify")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSmsCode)))
            .andExpect(status().isNotFound());

        // Validate the SmsCode in the database
        List<SmsCode> smsCodeList = smsCodeRepository.findAll();
        assertThat(smsCodeList).hasSize(databaseSizeBeforeUpdate);

        smsCode.expiredDate(Instant.now().minus(4, ChronoUnit.MINUTES));
        smsCodeRepository.save(smsCode);

        restSmsCodeMockMvc.perform(post("/api/sms-codes:verify")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSmsCode)))
            .andExpect(status().isOk());
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
