package jm.task.core.jdbc.model;


import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(value = AccessType.FIELD)
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StudentAddress {

    private String city;

    private String state;

    private String country;

    public StudentAddress() {
    }
    public StudentAddress(String city, String state, String country) {
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public StudentAddress(String city) {
        this.city = city;
    }

    public String getCity() {

        System.out.println("~~~~~~~~getCity~~~~~~~~~");

        return city;

    }

    public void setCity(String city) {

        this.city = city;

    }

    public String getState() {

        System.out.println("~~~~~~~~getState~~~~~~~~~");

        return state;

    }

    public void setState(String state) {

        this.state = state;

    }

    public String getCountry() {

        System.out.println("~~~~~~~~getCountry~~~~~~~~~");

        return country;

    }

    public void setCountry(String country) {

        this.country = country;

    }

}