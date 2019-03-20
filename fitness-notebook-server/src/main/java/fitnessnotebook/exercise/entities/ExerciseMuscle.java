package fitnessnotebook.exercise.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ExerciseMuscle implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exercise_id", nullable = false)
	private Exercise exercise;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "muscle_id", nullable = false)
    private Muscle muscle;

    @Column(nullable = false)
    private Boolean main;

	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		ExerciseMuscle exerciseMuscle = (ExerciseMuscle) o;
		return Objects.equals( exercise, exerciseMuscle.exercise ) &&
				Objects.equals( muscle, exerciseMuscle.muscle );
	}

	@Override
	public int hashCode() {
		return Objects.hash( exercise, muscle );
	}
}