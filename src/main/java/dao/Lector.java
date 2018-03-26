package dao;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by User on 23.03.2018.
 */
@Entity
public class Lector {
    @Id
    private int id;
    private String name;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "lector_has_department",
            joinColumns = { @JoinColumn(name = "lector_id") },
            inverseJoinColumns = { @JoinColumn(name = "department_id") }
    )
    private Set<Department> departments;
    private int salary;
    private Degree degree;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }


}
