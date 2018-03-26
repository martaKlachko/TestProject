package dao;

import org.hibernate.Session;
import org.hibernate.query.*;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

/**
 * Created by User on 23.03.2018.
 */
@Entity
public class Department {
    @Id
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="headId")
    private Lector head;
    @ManyToMany(mappedBy = "departments")
    private Set<Lector> lectors;

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

    public Lector getHead() {
        return head;
    }

    public void setHead(Lector head) {
        this.head = head;
    }

    public Set<Lector> getLectors() {
        return lectors;
    }

    public void setLectors(Set<Lector> lectors) {
        this.lectors = lectors;
    }


}
