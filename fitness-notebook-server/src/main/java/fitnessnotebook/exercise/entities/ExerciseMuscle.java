package fitnessnotebook.exercise.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ExerciseMuscle {

	@Id
	@ManyToOne
	@JoinColumn(name = "exercise_id", nullable = false)
	private Exercise exercise;

	@Id
	@ManyToOne
	@JoinColumn(name = "muscle_id", nullable = false)
    private Muscle muscle;

    @Column(nullable = false)
    private Boolean main;
}