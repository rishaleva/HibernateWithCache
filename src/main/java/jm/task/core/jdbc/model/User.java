package jm.task.core.jdbc.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import org.hibernate.annotations.Cache;

@Entity
@Getter
@EqualsAndHashCode
@Table(name = "\"user\"")
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
@Access(value = AccessType.PROPERTY)
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



    public User() {
    }

    public User(String name, String lastName, Byte age, StudentAddress studentAddress) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.studentAddress = studentAddress;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public void setStudentAddress(StudentAddress studentAddress) {
        this.studentAddress = studentAddress;
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
