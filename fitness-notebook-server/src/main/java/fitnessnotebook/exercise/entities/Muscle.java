package fitnessnotebook.exercise.entities;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.type.TextType;

@Entity
public class Muscle {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer id;

    @ColumnDefault("0")
    public Integer order;

    @Column(length=100)
	public String name;

    public TextType description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TextType getDescription() {
        return description;
    }

    public void setDescription(TextType description) {
        this.description = description;
    }

}