package fitnessnotebook.auth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return org.springframework.security.core.userdetails.User
            .builder().passwordEncoder(passwordEncoder::encode)
            .username(user.getUsername()).password(user.getPassword()).build();
    }

}