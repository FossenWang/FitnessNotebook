package fitnessnotebook.exercise.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "fitness_exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ColumnDefault("0")  // order
    private Integer number;

    @Column(length=100, nullable = false)
	private String name;

    @Type(type = "text")
    private String description;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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