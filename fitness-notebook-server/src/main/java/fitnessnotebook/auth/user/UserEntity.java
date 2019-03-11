package fitnessnotebook.auth.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.ForeignKey;

@Entity
@Table(
    name = "auth_user",
    uniqueConstraints =  @UniqueConstraint(
        name = "uk_auth_user_username",
        columnNames = {
            "username",
        }
    )
)
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@Column(length=100)
	private String username;

	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id",
			foreignKey = @ForeignKey(name = "ROLE_ID_FK")
	)
	private UserRole role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}