package jm.task.core.jdbc.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import org.hibernate.annotations.Cache;

@NamedEntityGraph(
        name = "WithCompanyAndCar",
        attributeNodes = {
                @NamedAttributeNode(value = "company", subgraph = "address"),
                @NamedAttributeNode(value = "car"),
        },
        subgraphs = {
        @NamedSubgraph(name = "address", attributeNodes = @NamedAttributeNode("address"))
}
)
@Entity
@Getter
@EqualsAndHashCode
@Setter
@Table(name = "\"user\"")
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
@Access(value = AccessType.FIELD)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String lastName;

    private Byte age;

    @Embedded
    private StudentAddress studentAddress;

    @OneToOne
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "car_id")
    private Car car;

    public User() {
    }

    public User(String name, String lastName, Byte age, StudentAddress studentAddress, Company company, Car car) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.studentAddress = studentAddress;
        this.company = company;
        this.car = car;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
