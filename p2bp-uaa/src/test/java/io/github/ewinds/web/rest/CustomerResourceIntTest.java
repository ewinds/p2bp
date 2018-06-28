package io.github.ewinds.web.rest;

import io.github.ewinds.UaaApp;
import io.github.ewinds.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the Customer REST controller.
 *
 * @see CustomerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UaaApp.class)
public class CustomerResourceIntTest {

    private MockMvc restMockMvc;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        CustomerResource customerResource = new CustomerResource(userRepository);
        restMockMvc = MockMvcBuilders
            .standaloneSetup(customerResource)
            .build();
    }

    /**
    * Test defaultAction
    */
    @Test
    public void testDefaultAction() throws Exception {
        restMockMvc.perform(get("/api/customer/default-action"))
            .andExpect(status().isOk());
    }

}
