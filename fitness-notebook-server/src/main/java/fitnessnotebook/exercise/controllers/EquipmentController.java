package fitnessnotebook.exercise.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fitnessnotebook.Urls;
import fitnessnotebook.exercise.dao.Equipment;
import fitnessnotebook.exercise.dao.EquipmentService;
import fitnessnotebook.tools.PagingResult;

@RestController
public class EquipmentController{

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping(Urls.equipmentDetail)
    public Equipment getEquipment(@PathVariable("id") Integer id) {
        Equipment equipment = equipmentService.loadEquipmentById(id);
        return equipment;
    }

    @GetMapping(Urls.equipmentList)
    public PagingResult<Equipment> getEquipmentList(
        @RequestParam(name = "page", defaultValue = "1") Integer page,
        @RequestParam(name = "size", defaultValue = "10") Integer size) {
        PagingResult<Equipment> result = equipmentService.getEquipmentList(page, size);
        return result;
    }

    @PostMapping(Urls.createEquipment)
    @ResponseStatus(HttpStatus.CREATED)
    public Equipment createEquipment(
        @RequestParam(name = "number", required = false) Integer number,
        @RequestParam(name = "name") String name,
        @RequestParam(name = "description", required = false) String description) {
            Equipment equipment = equipmentService.createEquipment(name, description, number);
            return equipment;
        }
}