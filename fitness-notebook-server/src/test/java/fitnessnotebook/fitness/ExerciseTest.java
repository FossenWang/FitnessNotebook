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

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

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
        Equipment equipment = equipmentService.createEquipment("barbell", null, 0);
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
            .andExpect(jsonPath("$.name").value("barbell"))
            .andExpect(jsonPath("$.description").isEmpty())
            .andReturn();

        result = this.mockMvc
            .perform(get(String.format("/api/fitness/equipment/%d", 1000)))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.msg").value("Equipment not found."))
            .andReturn();

        assertEquals(404, result.getResponse().getStatus());

        result = this.mockMvc
            .perform(get("/api/fitness/equipment"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.pageInfo.page").value(1))
            .andExpect(jsonPath("$.pageInfo.size").value(10))
            .andExpect(jsonPath("$.pageInfo.total").value(1))
            .andExpect(jsonPath("$.result[0].id").value(this.equipmentId))
            .andExpect(jsonPath("$.result[0].number").value(0))
            .andExpect(jsonPath("$.result[0].name").value("barbell"))
            .andExpect(jsonPath("$.result[0].description").isEmpty())
            .andReturn();

        result = this.mockMvc
            .perform(post("/api/fitness/equipment").with(csrf()))
                // .param("name", "dumbbell")
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.msg").isNotEmpty())
            .andReturn();

        result = this.mockMvc
            .perform(post("/api/fitness/equipment")
                .param("name", "dumbbell")
                .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("dumbbell"))
                .andExpect(jsonPath("$.number").isEmpty())
                .andExpect(jsonPath("$.description").isEmpty())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}