package fitnessnotebook.exercise.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Exercise extends BaseTypeEntity {

	@ManyToOne
	@JoinColumn(name = "equipment_id")
    private Equipment equipment;

	@OneToMany(
		mappedBy = "exercise",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<ExerciseMuscle> muscles = new ArrayList<>();

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public List<ExerciseMuscle> getMuscles() {
        return muscles;
    }

    public void setMuscles(List<ExerciseMuscle> muscles) {
        this.muscles = muscles;
    }

}