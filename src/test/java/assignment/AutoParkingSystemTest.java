package assignment;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.assignment.controller.AutoParkingSystem;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=AutoParkingSystem.class)
@AutoConfigureMockMvc
public class AutoParkingSystemTest {
	@Autowired
	private MockMvc mockMvc;

	private final String URL = "/automatedParking/park/";
	
	private final Logger log = LoggerFactory.getLogger(AutoParkingSystemTest.class);

	@Test
	public void TestAutoParkingSystem() throws Exception {
		log.info("--------- Executing Test ---------");
		mockMvc.perform(MockMvcRequestBuilders.get(URL + "{id}", "5,5:RFLFRFLF")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().string("Current Position: (7,7)"));
		mockMvc.perform(MockMvcRequestBuilders.get(URL + "{id}", "6,6:FFLFFLFFLFF")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().string("Current Position: (6,6)"));
		mockMvc.perform(MockMvcRequestBuilders.get(URL + "{id}", "5,5:FLFLFFRFFF")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().string("Current Position: (4,1)"));
		log.info("--------- End Test --------- ");
	}
}
