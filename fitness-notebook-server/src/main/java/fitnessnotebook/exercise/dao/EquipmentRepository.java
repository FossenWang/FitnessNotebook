package fitnessnotebook.exercise.dao;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EquipmentRepository extends PagingAndSortingRepository<Equipment, Integer> {
}