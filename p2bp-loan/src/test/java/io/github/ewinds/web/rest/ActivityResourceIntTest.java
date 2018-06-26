package io.github.ewinds.web.rest;

import io.github.ewinds.LoanApp;

import io.github.ewinds.config.SecurityBeanOverrideConfiguration;

import io.github.ewinds.domain.Activity;
import io.github.ewinds.repository.ActivityRepository;
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

import io.github.ewinds.domain.enumeration.ActivityType;
/**
 * Test class for the ActivityResource REST controller.
 *
 * @see ActivityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LoanApp.class, SecurityBeanOverrideConfiguration.class})
public class ActivityResourceIntTest {

    private static final String DEFAULT_ACTIVITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_BEGIN_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BEGIN_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ActivityType DEFAULT_ACTIVITY_TYPE = ActivityType.CASH;
    private static final ActivityType UPDATED_ACTIVITY_TYPE = ActivityType.INTEREST;

    private static final Integer DEFAULT_CASH_ID = 1;
    private static final Integer UPDATED_CASH_ID = 2;

    private static final BigDecimal DEFAULT_ACTIVITY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ACTIVITY_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EXTRA_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXTRA_RATE = new BigDecimal(2);

    private static final String DEFAULT_ACTIVITY_TAB = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY_TAB = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVITY_DESC = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY_DESC = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DEL_FLAG = false;
    private static final Boolean UPDATED_DEL_FLAG = true;

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restActivityMockMvc;

    private Activity activity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActivityResource activityResource = new ActivityResource(activityRepository);
        this.restActivityMockMvc = MockMvcBuilders.standaloneSetup(activityResource)
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
    public static Activity createEntity(EntityManager em) {
        Activity activity = new Activity()
            .activityName(DEFAULT_ACTIVITY_NAME)
            .beginTime(DEFAULT_BEGIN_TIME)
            .endTime(DEFAULT_END_TIME)
            .activityType(DEFAULT_ACTIVITY_TYPE)
            .cashId(DEFAULT_CASH_ID)
            .activityRate(DEFAULT_ACTIVITY_RATE)
            .extraRate(DEFAULT_EXTRA_RATE)
            .activityTab(DEFAULT_ACTIVITY_TAB)
            .activityDesc(DEFAULT_ACTIVITY_DESC)
            .delFlag(DEFAULT_DEL_FLAG)
            .imageUrl(DEFAULT_IMAGE_URL);
        return activity;
    }

    @Before
    public void initTest() {
        activity = createEntity(em);
    }

    @Test
    @Transactional
    public void createActivity() throws Exception {
        int databaseSizeBeforeCreate = activityRepository.findAll().size();

        // Create the Activity
        restActivityMockMvc.perform(post("/api/activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activity)))
            .andExpect(status().isCreated());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeCreate + 1);
        Activity testActivity = activityList.get(activityList.size() - 1);
        assertThat(testActivity.getActivityName()).isEqualTo(DEFAULT_ACTIVITY_NAME);
        assertThat(testActivity.getBeginTime()).isEqualTo(DEFAULT_BEGIN_TIME);
        assertThat(testActivity.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testActivity.getActivityType()).isEqualTo(DEFAULT_ACTIVITY_TYPE);
        assertThat(testActivity.getCashId()).isEqualTo(DEFAULT_CASH_ID);
        assertThat(testActivity.getActivityRate()).isEqualTo(DEFAULT_ACTIVITY_RATE);
        assertThat(testActivity.getExtraRate()).isEqualTo(DEFAULT_EXTRA_RATE);
        assertThat(testActivity.getActivityTab()).isEqualTo(DEFAULT_ACTIVITY_TAB);
        assertThat(testActivity.getActivityDesc()).isEqualTo(DEFAULT_ACTIVITY_DESC);
        assertThat(testActivity.isDelFlag()).isEqualTo(DEFAULT_DEL_FLAG);
        assertThat(testActivity.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
        assertThat(testActivity.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testActivity.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testActivity.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testActivity.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createActivityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = activityRepository.findAll().size();

        // Create the Activity with an existing ID
        activity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActivityMockMvc.perform(post("/api/activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activity)))
            .andExpect(status().isBadRequest());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllActivities() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get all the activityList
        restActivityMockMvc.perform(get("/api/activities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(activity.getId().intValue())))
            .andExpect(jsonPath("$.[*].activityName").value(hasItem(DEFAULT_ACTIVITY_NAME.toString())))
            .andExpect(jsonPath("$.[*].beginTime").value(hasItem(DEFAULT_BEGIN_TIME.toString())))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].activityType").value(hasItem(DEFAULT_ACTIVITY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].cashId").value(hasItem(DEFAULT_CASH_ID)))
            .andExpect(jsonPath("$.[*].activityRate").value(hasItem(DEFAULT_ACTIVITY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].extraRate").value(hasItem(DEFAULT_EXTRA_RATE.intValue())))
            .andExpect(jsonPath("$.[*].activityTab").value(hasItem(DEFAULT_ACTIVITY_TAB.toString())))
            .andExpect(jsonPath("$.[*].activityDesc").value(hasItem(DEFAULT_ACTIVITY_DESC.toString())))
            .andExpect(jsonPath("$.[*].delFlag").value(hasItem(DEFAULT_DEL_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getActivity() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);

        // Get the activity
        restActivityMockMvc.perform(get("/api/activities/{id}", activity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(activity.getId().intValue()))
            .andExpect(jsonPath("$.activityName").value(DEFAULT_ACTIVITY_NAME.toString()))
            .andExpect(jsonPath("$.beginTime").value(DEFAULT_BEGIN_TIME.toString()))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME.toString()))
            .andExpect(jsonPath("$.activityType").value(DEFAULT_ACTIVITY_TYPE.toString()))
            .andExpect(jsonPath("$.cashId").value(DEFAULT_CASH_ID))
            .andExpect(jsonPath("$.activityRate").value(DEFAULT_ACTIVITY_RATE.intValue()))
            .andExpect(jsonPath("$.extraRate").value(DEFAULT_EXTRA_RATE.intValue()))
            .andExpect(jsonPath("$.activityTab").value(DEFAULT_ACTIVITY_TAB.toString()))
            .andExpect(jsonPath("$.activityDesc").value(DEFAULT_ACTIVITY_DESC.toString()))
            .andExpect(jsonPath("$.delFlag").value(DEFAULT_DEL_FLAG.booleanValue()))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingActivity() throws Exception {
        // Get the activity
        restActivityMockMvc.perform(get("/api/activities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActivity() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);
        int databaseSizeBeforeUpdate = activityRepository.findAll().size();

        // Update the activity
        Activity updatedActivity = activityRepository.findOne(activity.getId());
        // Disconnect from session so that the updates on updatedActivity are not directly saved in db
        em.detach(updatedActivity);
        updatedActivity
            .activityName(UPDATED_ACTIVITY_NAME)
            .beginTime(UPDATED_BEGIN_TIME)
            .endTime(UPDATED_END_TIME)
            .activityType(UPDATED_ACTIVITY_TYPE)
            .cashId(UPDATED_CASH_ID)
            .activityRate(UPDATED_ACTIVITY_RATE)
            .extraRate(UPDATED_EXTRA_RATE)
            .activityTab(UPDATED_ACTIVITY_TAB)
            .activityDesc(UPDATED_ACTIVITY_DESC)
            .delFlag(UPDATED_DEL_FLAG)
            .imageUrl(UPDATED_IMAGE_URL);

        restActivityMockMvc.perform(put("/api/activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedActivity)))
            .andExpect(status().isOk());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeUpdate);
        Activity testActivity = activityList.get(activityList.size() - 1);
        assertThat(testActivity.getActivityName()).isEqualTo(UPDATED_ACTIVITY_NAME);
        assertThat(testActivity.getBeginTime()).isEqualTo(UPDATED_BEGIN_TIME);
        assertThat(testActivity.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testActivity.getActivityType()).isEqualTo(UPDATED_ACTIVITY_TYPE);
        assertThat(testActivity.getCashId()).isEqualTo(UPDATED_CASH_ID);
        assertThat(testActivity.getActivityRate()).isEqualTo(UPDATED_ACTIVITY_RATE);
        assertThat(testActivity.getExtraRate()).isEqualTo(UPDATED_EXTRA_RATE);
        assertThat(testActivity.getActivityTab()).isEqualTo(UPDATED_ACTIVITY_TAB);
        assertThat(testActivity.getActivityDesc()).isEqualTo(UPDATED_ACTIVITY_DESC);
        assertThat(testActivity.isDelFlag()).isEqualTo(UPDATED_DEL_FLAG);
        assertThat(testActivity.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testActivity.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testActivity.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testActivity.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testActivity.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingActivity() throws Exception {
        int databaseSizeBeforeUpdate = activityRepository.findAll().size();

        // Create the Activity

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restActivityMockMvc.perform(put("/api/activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activity)))
            .andExpect(status().isCreated());

        // Validate the Activity in the database
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteActivity() throws Exception {
        // Initialize the database
        activityRepository.saveAndFlush(activity);
        int databaseSizeBeforeDelete = activityRepository.findAll().size();

        // Get the activity
        restActivityMockMvc.perform(delete("/api/activities/{id}", activity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Activity> activityList = activityRepository.findAll();
        assertThat(activityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Activity.class);
        Activity activity1 = new Activity();
        activity1.setId(1L);
        Activity activity2 = new Activity();
        activity2.setId(activity1.getId());
        assertThat(activity1).isEqualTo(activity2);
        activity2.setId(2L);
        assertThat(activity1).isNotEqualTo(activity2);
        activity1.setId(null);
        assertThat(activity1).isNotEqualTo(activity2);
    }
}
