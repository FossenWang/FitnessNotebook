package fitnessnotebook.auth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

public class JpaUserManager implements UserDetailsManager {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username);
        if (userModel == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        UserDetails user = User.builder()
            .username(userModel.getUsername())
            .password(userModel.getPassword())
            .roles("USER").build();
        return user;
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public void createUser(UserDetails user) {
        UserModel userModel = new UserModel();
        userModel.setUsername(user.getUsername());
        userModel.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userModel);
    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

}