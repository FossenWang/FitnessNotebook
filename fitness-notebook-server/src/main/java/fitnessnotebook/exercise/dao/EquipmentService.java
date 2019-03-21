package fitnessnotebook.exercise.dao;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public Equipment loadEquipmentById(Integer id) throws EntityNotFoundException {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        if (equipment.isPresent()) {
            return equipment.get();
        } else {
            throw new EntityNotFoundException("Equipment not found.");
        }
    }

    public Equipment createEquipment(String name, String description, Integer number) {
        Equipment equipment = new Equipment();
        equipment.setName(name);
        if (description != null) {
            equipment.setDescription(description);
        }
        if (number != null) {
            equipment.setNumber(number);
        }
        equipment = equipmentRepository.save(equipment);
        return equipment;
    }
}