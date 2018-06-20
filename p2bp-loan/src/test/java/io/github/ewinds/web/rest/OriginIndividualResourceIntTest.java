package io.github.ewinds.web.rest;

import io.github.ewinds.LoanApp;

import io.github.ewinds.config.SecurityBeanOverrideConfiguration;

import io.github.ewinds.domain.OriginIndividual;
import io.github.ewinds.repository.OriginIndividualRepository;
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
 * Test class for the OriginIndividualResource REST controller.
 *
 * @see OriginIndividualResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LoanApp.class, SecurityBeanOverrideConfiguration.class})
public class OriginIndividualResourceIntTest {

    private static final String DEFAULT_REAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REAL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ABBR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ABBR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ID_CARD = "AAAAAAAAAA";
    private static final String UPDATED_ID_CARD = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final Integer DEFAULT_GENDER = 1;
    private static final Integer UPDATED_GENDER = 2;

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final String DEFAULT_PURPOSE = "AAAAAAAAAA";
    private static final String UPDATED_PURPOSE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private OriginIndividualRepository originIndividualRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOriginIndividualMockMvc;

    private OriginIndividual originIndividual;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OriginIndividualResource originIndividualResource = new OriginIndividualResource(originIndividualRepository);
        this.restOriginIndividualMockMvc = MockMvcBuilders.standaloneSetup(originIndividualResource)
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
    public static OriginIndividual createEntity(EntityManager em) {
        OriginIndividual originIndividual = new OriginIndividual()
            .realName(DEFAULT_REAL_NAME)
            .abbrName(DEFAULT_ABBR_NAME)
            .idCard(DEFAULT_ID_CARD)
            .mobile(DEFAULT_MOBILE)
            .gender(DEFAULT_GENDER)
            .age(DEFAULT_AGE)
            .purpose(DEFAULT_PURPOSE);
        return originIndividual;
    }

    @Before
    public void initTest() {
        originIndividual = createEntity(em);
    }

    @Test
    @Transactional
    public void createOriginIndividual() throws Exception {
        int databaseSizeBeforeCreate = originIndividualRepository.findAll().size();

        // Create the OriginIndividual
        restOriginIndividualMockMvc.perform(post("/api/origin-individuals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(originIndividual)))
            .andExpect(status().isCreated());

        // Validate the OriginIndividual in the database
        List<OriginIndividual> originIndividualList = originIndividualRepository.findAll();
        assertThat(originIndividualList).hasSize(databaseSizeBeforeCreate + 1);
        OriginIndividual testOriginIndividual = originIndividualList.get(originIndividualList.size() - 1);
        assertThat(testOriginIndividual.getRealName()).isEqualTo(DEFAULT_REAL_NAME);
        assertThat(testOriginIndividual.getAbbrName()).isEqualTo(DEFAULT_ABBR_NAME);
        assertThat(testOriginIndividual.getIdCard()).isEqualTo(DEFAULT_ID_CARD);
        assertThat(testOriginIndividual.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testOriginIndividual.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testOriginIndividual.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testOriginIndividual.getPurpose()).isEqualTo(DEFAULT_PURPOSE);
        assertThat(testOriginIndividual.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOriginIndividual.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOriginIndividual.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testOriginIndividual.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createOriginIndividualWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = originIndividualRepository.findAll().size();

        // Create the OriginIndividual with an existing ID
        originIndividual.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOriginIndividualMockMvc.perform(post("/api/origin-individuals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(originIndividual)))
            .andExpect(status().isBadRequest());

        // Validate the OriginIndividual in the database
        List<OriginIndividual> originIndividualList = originIndividualRepository.findAll();
        assertThat(originIndividualList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOriginIndividuals() throws Exception {
        // Initialize the database
        originIndividualRepository.saveAndFlush(originIndividual);

        // Get all the originIndividualList
        restOriginIndividualMockMvc.perform(get("/api/origin-individuals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(originIndividual.getId().intValue())))
            .andExpect(jsonPath("$.[*].realName").value(hasItem(DEFAULT_REAL_NAME.toString())))
            .andExpect(jsonPath("$.[*].abbrName").value(hasItem(DEFAULT_ABBR_NAME.toString())))
            .andExpect(jsonPath("$.[*].idCard").value(hasItem(DEFAULT_ID_CARD.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].purpose").value(hasItem(DEFAULT_PURPOSE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getOriginIndividual() throws Exception {
        // Initialize the database
        originIndividualRepository.saveAndFlush(originIndividual);

        // Get the originIndividual
        restOriginIndividualMockMvc.perform(get("/api/origin-individuals/{id}", originIndividual.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(originIndividual.getId().intValue()))
            .andExpect(jsonPath("$.realName").value(DEFAULT_REAL_NAME.toString()))
            .andExpect(jsonPath("$.abbrName").value(DEFAULT_ABBR_NAME.toString()))
            .andExpect(jsonPath("$.idCard").value(DEFAULT_ID_CARD.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.purpose").value(DEFAULT_PURPOSE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOriginIndividual() throws Exception {
        // Get the originIndividual
        restOriginIndividualMockMvc.perform(get("/api/origin-individuals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOriginIndividual() throws Exception {
        // Initialize the database
        originIndividualRepository.saveAndFlush(originIndividual);
        int databaseSizeBeforeUpdate = originIndividualRepository.findAll().size();

        // Update the originIndividual
        OriginIndividual updatedOriginIndividual = originIndividualRepository.findOne(originIndividual.getId());
        // Disconnect from session so that the updates on updatedOriginIndividual are not directly saved in db
        em.detach(updatedOriginIndividual);
        updatedOriginIndividual
            .realName(UPDATED_REAL_NAME)
            .abbrName(UPDATED_ABBR_NAME)
            .idCard(UPDATED_ID_CARD)
            .mobile(UPDATED_MOBILE)
            .gender(UPDATED_GENDER)
            .age(UPDATED_AGE)
            .purpose(UPDATED_PURPOSE);

        restOriginIndividualMockMvc.perform(put("/api/origin-individuals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOriginIndividual)))
            .andExpect(status().isOk());

        // Validate the OriginIndividual in the database
        List<OriginIndividual> originIndividualList = originIndividualRepository.findAll();
        assertThat(originIndividualList).hasSize(databaseSizeBeforeUpdate);
        OriginIndividual testOriginIndividual = originIndividualList.get(originIndividualList.size() - 1);
        assertThat(testOriginIndividual.getRealName()).isEqualTo(UPDATED_REAL_NAME);
        assertThat(testOriginIndividual.getAbbrName()).isEqualTo(UPDATED_ABBR_NAME);
        assertThat(testOriginIndividual.getIdCard()).isEqualTo(UPDATED_ID_CARD);
        assertThat(testOriginIndividual.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testOriginIndividual.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testOriginIndividual.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testOriginIndividual.getPurpose()).isEqualTo(UPDATED_PURPOSE);
        assertThat(testOriginIndividual.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOriginIndividual.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOriginIndividual.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testOriginIndividual.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingOriginIndividual() throws Exception {
        int databaseSizeBeforeUpdate = originIndividualRepository.findAll().size();

        // Create the OriginIndividual

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOriginIndividualMockMvc.perform(put("/api/origin-individuals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(originIndividual)))
            .andExpect(status().isCreated());

        // Validate the OriginIndividual in the database
        List<OriginIndividual> originIndividualList = originIndividualRepository.findAll();
        assertThat(originIndividualList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOriginIndividual() throws Exception {
        // Initialize the database
        originIndividualRepository.saveAndFlush(originIndividual);
        int databaseSizeBeforeDelete = originIndividualRepository.findAll().size();

        // Get the originIndividual
        restOriginIndividualMockMvc.perform(delete("/api/origin-individuals/{id}", originIndividual.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OriginIndividual> originIndividualList = originIndividualRepository.findAll();
        assertThat(originIndividualList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OriginIndividual.class);
        OriginIndividual originIndividual1 = new OriginIndividual();
        originIndividual1.setId(1L);
        OriginIndividual originIndividual2 = new OriginIndividual();
        originIndividual2.setId(originIndividual1.getId());
        assertThat(originIndividual1).isEqualTo(originIndividual2);
        originIndividual2.setId(2L);
        assertThat(originIndividual1).isNotEqualTo(originIndividual2);
        originIndividual1.setId(null);
        assertThat(originIndividual1).isNotEqualTo(originIndividual2);
    }
}
