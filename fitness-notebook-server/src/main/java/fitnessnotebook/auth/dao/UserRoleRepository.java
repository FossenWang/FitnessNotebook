package fitnessnotebook.auth.dao;

import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    String findByName(String name);
}