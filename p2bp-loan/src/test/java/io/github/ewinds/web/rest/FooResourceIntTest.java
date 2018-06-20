package io.github.ewinds.web.rest;

import io.github.ewinds.LoanApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the Foo REST controller.
 *
 * @see FooResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoanApp.class)
public class FooResourceIntTest {

    private MockMvc restMockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        FooResource fooResource = new FooResource();
        restMockMvc = MockMvcBuilders
            .standaloneSetup(fooResource)
            .build();
    }

    /**
    * Test list
    */
    @Test
    public void testList() throws Exception {
        restMockMvc.perform(get("/api/foo/list"))
            .andExpect(status().isOk());
    }

}
