package fitnessnotebook.auth.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

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

	public AuthUser(String username, String password, String... roles) {
        super(username, password, makeRoles(roles));
    }

	public AuthUser(Integer id, String username, String password, String... roles) {
        this(username, password, roles);
        this.id = id;
    }

    public static AuthUser entityToAuthUser(UserEntity userEntity) {
        return new AuthUser(
            userEntity.getId(),
            userEntity.getUsername(),
            userEntity.getPassword(),
            "USER");
    }

    public static AuthUser createAnonymousUser() {
        return new AuthUser(
            "anonymousUser",
            "anonymous",
            "ANONYMOUS");
    }

    public static AuthUser getCurrentUser() {
        Object principal = SecurityContextHolder
            .getContext().getAuthentication().getPrincipal();
        AuthUser user;
        if (principal instanceof AuthUser) {
            user = (AuthUser) principal;
        } else if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            user = new AuthUser(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());
        } else {
            user = createAnonymousUser();
        }
        return user;
    }

    private static List<GrantedAuthority> makeRoles(String... roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(
                roles.length);
        for (String role : roles) {
            Assert.isTrue(!role.startsWith("ROLE_"), role
                    + " cannot start with ROLE_ (it is automatically added)");
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return authorities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}