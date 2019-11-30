package lk.haniman.rentalsystem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
public class RestApiTest {

    private MockMvc getMvc;

    @InjectMocks
    private Main main;

    @Before
    public void setUp() throws Exception{
        getMvc = MockMvcBuilders.standaloneSetup(main).build();

    }

    @Test
    public void testVehicleList() throws Exception{
        getMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("hehejeje"));
    }
}
