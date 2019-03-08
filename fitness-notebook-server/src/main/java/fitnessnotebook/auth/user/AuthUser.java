package fitnessnotebook.auth.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({
    "password", "accountNonExpired", "accountNonLocked",
    "authorities", "credentialsNonExpired", "enabled"})
public class AuthUser extends User {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private Integer id;

	public AuthUser(String username, String password,
        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, true, true, true, true, authorities);
    }

	public AuthUser(Integer id, String username, String password,
        Collection<? extends GrantedAuthority> authorities) {
        this(username, password, authorities);
        this.id = id;
    }

    public static AuthUser modelToAuthUser(UserModel userModel) {
        UserDetails userDetails = AuthUser.builder()
            .username(userModel.getUsername())
            .password(userModel.getPassword())
            .roles("USER").build();
        AuthUser user = new AuthUser(
            userModel.getId(),
            userDetails.getUsername(),
            userDetails.getPassword(),
            userDetails.getAuthorities());
        return user;
    }

    public static AuthUser createAnonymousUser() {
        UserDetails userDetails = AuthUser.builder()
            .username("anonymousUser")
            .password("anonymous")
            .roles("ANONYMOUS").build();
        AuthUser user = new AuthUser(
            userDetails.getUsername(),
            userDetails.getPassword(),
            userDetails.getAuthorities());
        return user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
}