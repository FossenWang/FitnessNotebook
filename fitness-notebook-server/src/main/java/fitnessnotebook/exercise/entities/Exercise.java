package fitnessnotebook.exercise.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.type.TextType;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer id;

    @ColumnDefault("0")
    public Integer order;

    @Column(length=100)
	public String name;

    public TextType description;

	@ManyToOne
	@JoinColumn(name = "equipment_id")
    private Equipment equipment;

	@OneToMany(
		mappedBy = "exercise",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<ExerciseMuscle> muscles = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TextType getDescription() {
        return description;
    }

    public void setDescription(TextType description) {
        this.description = description;
    }

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