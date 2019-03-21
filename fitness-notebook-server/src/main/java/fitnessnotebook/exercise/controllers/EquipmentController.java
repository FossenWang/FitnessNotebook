package fitnessnotebook.exercise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fitnessnotebook.Urls;
import fitnessnotebook.exercise.dao.Equipment;
import fitnessnotebook.exercise.dao.EquipmentService;

@RestController
public class EquipmentController{

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping(Urls.equipmentDetail)
    public Equipment getEquipment(@PathVariable("id") Integer id) {
        Equipment equipment = equipmentService.loadEquipmentById(id);
        return equipment;
    }
}