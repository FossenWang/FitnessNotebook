package fitnessnotebook.fitness;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import fitnessnotebook.exercise.dao.Equipment;
import fitnessnotebook.exercise.dao.EquipmentService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class ExerciseTest {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private MockMvc mockMvc;

    private Integer equipmentId;

    @Before
    public void setup() {
        Equipment equipment = equipmentService.createEquipment("杠铃", null, 0);
        this.equipmentId = equipment.getId();
    }

    @Test
    public void testEquipment() throws Exception {
        MvcResult result;

        result = this.mockMvc
            .perform(get(String.format("/api/fitness/equipment/%d", this.equipmentId)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(this.equipmentId))
            .andExpect(jsonPath("$.number").value(0))
            .andExpect(jsonPath("$.name").value("杠铃"))
            .andReturn();

        System.out.println(result);
    }

}