package jm.task.core.jdbc.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "company")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;;

    public Company() {

    }
    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private List<Address> address;

    public Company(String name, List<Address> address) {
        this.name = name;
        this.address = address;
    }
}
