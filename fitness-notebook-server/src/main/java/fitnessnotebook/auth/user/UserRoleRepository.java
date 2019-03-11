package fitnessnotebook.auth.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    String findByName(String name);
}