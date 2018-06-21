package io.github.ewinds.web.rest;

import io.github.ewinds.UaaApp;

import io.github.ewinds.config.SecurityBeanOverrideConfiguration;

import io.github.ewinds.domain.UserExtra;
import io.github.ewinds.repository.UserExtraRepository;
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
import java.util.List;

import static io.github.ewinds.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserExtraResource REST controller.
 *
 * @see UserExtraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UaaApp.class)
public class UserExtraResourceIntTest {

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_NICKNAME = "AAAAAAAAAA";
    private static final String UPDATED_NICKNAME = "BBBBBBBBBB";

    private static final String DEFAULT_REAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REAL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ID_CARD = "AAAAAAAAAA";
    private static final String UPDATED_ID_CARD = "BBBBBBBBBB";

    private static final Long DEFAULT_POINTS = 1L;
    private static final Long UPDATED_POINTS = 2L;

    private static final String DEFAULT_REFERRAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REFERRAL_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ID_CARD_VERIFIED = false;
    private static final Boolean UPDATED_ID_CARD_VERIFIED = true;

    private static final Integer DEFAULT_ID_CARD_ERROR_TIMES = 1;
    private static final Integer UPDATED_ID_CARD_ERROR_TIMES = 2;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIVERSITY = "AAAAAAAAAA";
    private static final String UPDATED_UNIVERSITY = "BBBBBBBBBB";

    private static final String DEFAULT_DIPLOMA = "AAAAAAAAAA";
    private static final String UPDATED_DIPLOMA = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_INDUSTRY = "AAAAAAAAAA";
    private static final String UPDATED_INDUSTRY = "BBBBBBBBBB";

    private static final String DEFAULT_SCALE = "AAAAAAAAAA";
    private static final String UPDATED_SCALE = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_INCOME = "AAAAAAAAAA";
    private static final String UPDATED_INCOME = "BBBBBBBBBB";

    private static final String DEFAULT_RECOMMENDED_BY_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_RECOMMENDED_BY_PHONE = "BBBBBBBBBB";

    @Autowired
    private UserExtraRepository userExtraRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserExtraMockMvc;

    private UserExtra userExtra;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserExtraResource userExtraResource = new UserExtraResource(userExtraRepository);
        this.restUserExtraMockMvc = MockMvcBuilders.standaloneSetup(userExtraResource)
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
    public static UserExtra createEntity(EntityManager em) {
        UserExtra userExtra = new UserExtra()
            .phone(DEFAULT_PHONE)
            .nickname(DEFAULT_NICKNAME)
            .realName(DEFAULT_REAL_NAME)
            .idCard(DEFAULT_ID_CARD)
            .points(DEFAULT_POINTS)
            .referralCode(DEFAULT_REFERRAL_CODE)
            .idCardVerified(DEFAULT_ID_CARD_VERIFIED)
            .idCardErrorTimes(DEFAULT_ID_CARD_ERROR_TIMES)
            .comment(DEFAULT_COMMENT)
            .districtCode(DEFAULT_DISTRICT_CODE)
            .university(DEFAULT_UNIVERSITY)
            .diploma(DEFAULT_DIPLOMA)
            .company(DEFAULT_COMPANY)
            .industry(DEFAULT_INDUSTRY)
            .scale(DEFAULT_SCALE)
            .position(DEFAULT_POSITION)
            .income(DEFAULT_INCOME)
            .recommendedByPhone(DEFAULT_RECOMMENDED_BY_PHONE);
        return userExtra;
    }

    @Before
    public void initTest() {
        userExtra = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserExtra() throws Exception {
        int databaseSizeBeforeCreate = userExtraRepository.findAll().size();

        // Create the UserExtra
        restUserExtraMockMvc.perform(post("/api/user-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userExtra)))
            .andExpect(status().isCreated());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeCreate + 1);
        UserExtra testUserExtra = userExtraList.get(userExtraList.size() - 1);
        assertThat(testUserExtra.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testUserExtra.getNickname()).isEqualTo(DEFAULT_NICKNAME);
        assertThat(testUserExtra.getRealName()).isEqualTo(DEFAULT_REAL_NAME);
        assertThat(testUserExtra.getIdCard()).isEqualTo(DEFAULT_ID_CARD);
        assertThat(testUserExtra.getPoints()).isEqualTo(DEFAULT_POINTS);
        assertThat(testUserExtra.getReferralCode()).isEqualTo(DEFAULT_REFERRAL_CODE);
        assertThat(testUserExtra.isIdCardVerified()).isEqualTo(DEFAULT_ID_CARD_VERIFIED);
        assertThat(testUserExtra.getIdCardErrorTimes()).isEqualTo(DEFAULT_ID_CARD_ERROR_TIMES);
        assertThat(testUserExtra.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testUserExtra.getDistrictCode()).isEqualTo(DEFAULT_DISTRICT_CODE);
        assertThat(testUserExtra.getUniversity()).isEqualTo(DEFAULT_UNIVERSITY);
        assertThat(testUserExtra.getDiploma()).isEqualTo(DEFAULT_DIPLOMA);
        assertThat(testUserExtra.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testUserExtra.getIndustry()).isEqualTo(DEFAULT_INDUSTRY);
        assertThat(testUserExtra.getScale()).isEqualTo(DEFAULT_SCALE);
        assertThat(testUserExtra.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testUserExtra.getIncome()).isEqualTo(DEFAULT_INCOME);
        assertThat(testUserExtra.getRecommendedByPhone()).isEqualTo(DEFAULT_RECOMMENDED_BY_PHONE);
    }

    @Test
    @Transactional
    public void createUserExtraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userExtraRepository.findAll().size();

        // Create the UserExtra with an existing ID
        userExtra.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserExtraMockMvc.perform(post("/api/user-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userExtra)))
            .andExpect(status().isBadRequest());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserExtras() throws Exception {
        // Initialize the database
        userExtraRepository.saveAndFlush(userExtra);

        // Get all the userExtraList
        restUserExtraMockMvc.perform(get("/api/user-extras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userExtra.getId().intValue())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].nickname").value(hasItem(DEFAULT_NICKNAME.toString())))
            .andExpect(jsonPath("$.[*].realName").value(hasItem(DEFAULT_REAL_NAME.toString())))
            .andExpect(jsonPath("$.[*].idCard").value(hasItem(DEFAULT_ID_CARD.toString())))
            .andExpect(jsonPath("$.[*].points").value(hasItem(DEFAULT_POINTS.intValue())))
            .andExpect(jsonPath("$.[*].referralCode").value(hasItem(DEFAULT_REFERRAL_CODE.toString())))
            .andExpect(jsonPath("$.[*].idCardVerified").value(hasItem(DEFAULT_ID_CARD_VERIFIED.booleanValue())))
            .andExpect(jsonPath("$.[*].idCardErrorTimes").value(hasItem(DEFAULT_ID_CARD_ERROR_TIMES)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].districtCode").value(hasItem(DEFAULT_DISTRICT_CODE.toString())))
            .andExpect(jsonPath("$.[*].university").value(hasItem(DEFAULT_UNIVERSITY.toString())))
            .andExpect(jsonPath("$.[*].diploma").value(hasItem(DEFAULT_DIPLOMA.toString())))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].industry").value(hasItem(DEFAULT_INDUSTRY.toString())))
            .andExpect(jsonPath("$.[*].scale").value(hasItem(DEFAULT_SCALE.toString())))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION.toString())))
            .andExpect(jsonPath("$.[*].income").value(hasItem(DEFAULT_INCOME.toString())))
            .andExpect(jsonPath("$.[*].recommendedByPhone").value(hasItem(DEFAULT_RECOMMENDED_BY_PHONE.toString())));
    }

    @Test
    @Transactional
    public void getUserExtra() throws Exception {
        // Initialize the database
        userExtraRepository.saveAndFlush(userExtra);

        // Get the userExtra
        restUserExtraMockMvc.perform(get("/api/user-extras/{id}", userExtra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userExtra.getId().intValue()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.nickname").value(DEFAULT_NICKNAME.toString()))
            .andExpect(jsonPath("$.realName").value(DEFAULT_REAL_NAME.toString()))
            .andExpect(jsonPath("$.idCard").value(DEFAULT_ID_CARD.toString()))
            .andExpect(jsonPath("$.points").value(DEFAULT_POINTS.intValue()))
            .andExpect(jsonPath("$.referralCode").value(DEFAULT_REFERRAL_CODE.toString()))
            .andExpect(jsonPath("$.idCardVerified").value(DEFAULT_ID_CARD_VERIFIED.booleanValue()))
            .andExpect(jsonPath("$.idCardErrorTimes").value(DEFAULT_ID_CARD_ERROR_TIMES))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.districtCode").value(DEFAULT_DISTRICT_CODE.toString()))
            .andExpect(jsonPath("$.university").value(DEFAULT_UNIVERSITY.toString()))
            .andExpect(jsonPath("$.diploma").value(DEFAULT_DIPLOMA.toString()))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY.toString()))
            .andExpect(jsonPath("$.industry").value(DEFAULT_INDUSTRY.toString()))
            .andExpect(jsonPath("$.scale").value(DEFAULT_SCALE.toString()))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION.toString()))
            .andExpect(jsonPath("$.income").value(DEFAULT_INCOME.toString()))
            .andExpect(jsonPath("$.recommendedByPhone").value(DEFAULT_RECOMMENDED_BY_PHONE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserExtra() throws Exception {
        // Get the userExtra
        restUserExtraMockMvc.perform(get("/api/user-extras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserExtra() throws Exception {
        // Initialize the database
        userExtraRepository.saveAndFlush(userExtra);
        int databaseSizeBeforeUpdate = userExtraRepository.findAll().size();

        // Update the userExtra
        UserExtra updatedUserExtra = userExtraRepository.findOne(userExtra.getId());
        // Disconnect from session so that the updates on updatedUserExtra are not directly saved in db
        em.detach(updatedUserExtra);
        updatedUserExtra
            .phone(UPDATED_PHONE)
            .nickname(UPDATED_NICKNAME)
            .realName(UPDATED_REAL_NAME)
            .idCard(UPDATED_ID_CARD)
            .points(UPDATED_POINTS)
            .referralCode(UPDATED_REFERRAL_CODE)
            .idCardVerified(UPDATED_ID_CARD_VERIFIED)
            .idCardErrorTimes(UPDATED_ID_CARD_ERROR_TIMES)
            .comment(UPDATED_COMMENT)
            .districtCode(UPDATED_DISTRICT_CODE)
            .university(UPDATED_UNIVERSITY)
            .diploma(UPDATED_DIPLOMA)
            .company(UPDATED_COMPANY)
            .industry(UPDATED_INDUSTRY)
            .scale(UPDATED_SCALE)
            .position(UPDATED_POSITION)
            .income(UPDATED_INCOME)
            .recommendedByPhone(UPDATED_RECOMMENDED_BY_PHONE);

        restUserExtraMockMvc.perform(put("/api/user-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserExtra)))
            .andExpect(status().isOk());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeUpdate);
        UserExtra testUserExtra = userExtraList.get(userExtraList.size() - 1);
        assertThat(testUserExtra.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testUserExtra.getNickname()).isEqualTo(UPDATED_NICKNAME);
        assertThat(testUserExtra.getRealName()).isEqualTo(UPDATED_REAL_NAME);
        assertThat(testUserExtra.getIdCard()).isEqualTo(UPDATED_ID_CARD);
        assertThat(testUserExtra.getPoints()).isEqualTo(UPDATED_POINTS);
        assertThat(testUserExtra.getReferralCode()).isEqualTo(UPDATED_REFERRAL_CODE);
        assertThat(testUserExtra.isIdCardVerified()).isEqualTo(UPDATED_ID_CARD_VERIFIED);
        assertThat(testUserExtra.getIdCardErrorTimes()).isEqualTo(UPDATED_ID_CARD_ERROR_TIMES);
        assertThat(testUserExtra.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testUserExtra.getDistrictCode()).isEqualTo(UPDATED_DISTRICT_CODE);
        assertThat(testUserExtra.getUniversity()).isEqualTo(UPDATED_UNIVERSITY);
        assertThat(testUserExtra.getDiploma()).isEqualTo(UPDATED_DIPLOMA);
        assertThat(testUserExtra.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testUserExtra.getIndustry()).isEqualTo(UPDATED_INDUSTRY);
        assertThat(testUserExtra.getScale()).isEqualTo(UPDATED_SCALE);
        assertThat(testUserExtra.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testUserExtra.getIncome()).isEqualTo(UPDATED_INCOME);
        assertThat(testUserExtra.getRecommendedByPhone()).isEqualTo(UPDATED_RECOMMENDED_BY_PHONE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserExtra() throws Exception {
        int databaseSizeBeforeUpdate = userExtraRepository.findAll().size();

        // Create the UserExtra

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserExtraMockMvc.perform(put("/api/user-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userExtra)))
            .andExpect(status().isCreated());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserExtra() throws Exception {
        // Initialize the database
        userExtraRepository.saveAndFlush(userExtra);
        int databaseSizeBeforeDelete = userExtraRepository.findAll().size();

        // Get the userExtra
        restUserExtraMockMvc.perform(delete("/api/user-extras/{id}", userExtra.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserExtra.class);
        UserExtra userExtra1 = new UserExtra();
        userExtra1.setId(1L);
        UserExtra userExtra2 = new UserExtra();
        userExtra2.setId(userExtra1.getId());
        assertThat(userExtra1).isEqualTo(userExtra2);
        userExtra2.setId(2L);
        assertThat(userExtra1).isNotEqualTo(userExtra2);
        userExtra1.setId(null);
        assertThat(userExtra1).isNotEqualTo(userExtra2);
    }
}
