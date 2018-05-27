package com.david.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "zip")
    private String zip;

    @NotBlank
    @Column(name = "town")
    private String town;

    @NotBlank
    @Column(name = "street")
    private String street;

    @NotBlank
    @Column(name = "house_number")
    private String houseNumber;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="address")
    private Student student;

    public Address(@NotBlank String zip, @NotBlank String town, @NotBlank String street, @NotBlank String houseNumber) {
        this.zip = zip;
        this.town = town;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Address() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
