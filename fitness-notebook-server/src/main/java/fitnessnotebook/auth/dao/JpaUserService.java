package fitnessnotebook.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

import fitnessnotebook.auth.dao.AuthUser;

public class JpaUserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        UserDetails user = AuthUser.entityToAuthUser(userEntity);
        return user;
    }

    public void changePassword(String oldPassword, String newPassword) {

    }

    public void createUser(AuthUser user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRole(user.getRole());
        userRepository.save(userEntity);
    }

    public void deleteUser(String username) {

    }

    public void updateUser(UserDetails user) {

    }

    public boolean userExists(String username) {
        return false;
    }

}