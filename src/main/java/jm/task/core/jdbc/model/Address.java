package jm.task.core.jdbc.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;

    public Address() {
    }

    public Address(String city) {
        this.city = city;
    }

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
