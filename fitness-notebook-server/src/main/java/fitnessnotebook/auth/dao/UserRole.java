package fitnessnotebook.auth.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auth_user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@Column(length=20)
	private String name;

    public UserRole() {}

    public UserRole(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}