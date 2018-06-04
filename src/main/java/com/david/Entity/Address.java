package com.david.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * This class represents a address
 *
 * @author David Langen
 */
@Entity
@Table(name = "address")
public class Address {

    /**
     * The unique id for the student.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The zip of the address.
     */
    @NotBlank
    @Column(name = "zip")
    private String zip;

    /**
     * The town of the address.
     */
    @NotBlank
    @Column(name = "town")
    private String town;

    /**
     * The street of the address.
     */
    @NotBlank
    @Column(name = "street")
    private String street;

    /**
     * The house number of the address.
     */
    @NotBlank
    @Column(name = "house_number")
    private String houseNumber;

    /**
     * A standard constructor filling all properties of a address object.
     *
     * @param zip         The zip of the address
     * @param town        The town of the address
     * @param street      The street of the address
     * @param houseNumber The house number of the address
     */
    public Address(@NotBlank String zip, @NotBlank String town, @NotBlank String street, @NotBlank String houseNumber) {
        this.zip = zip;
        this.town = town;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    /**
     * A default constructor
     */
    public Address() {
    }

    /**
     * Gets the id of the address.
     *
     * @return The id of the address.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id of this address.
     *
     * @param id The id to be set on this address.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the zip of the address.
     *
     * @return the zip of the address.
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the zip of the address.
     *
     * @param zip the zip to be set.
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Gets the town of the address.
     *
     * @return the town of the address.
     */
    public String getTown() {
        return town;
    }

    /**
     * Sets the town of the address.
     *
     * @param town the town to be set.
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * Gets the street of the address.
     *
     * @return the street of the address.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Setst the street of the address.
     *
     * @param street the street to be set.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the house number of the address.
     *
     * @return the house number of the address.
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the house number of the address.
     *
     * @param houseNumber the house number to be set.
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Returns a formatted output about the address
     *
     * @return the formatted output
     */
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", zip='" + zip + '\'' +
                ", town='" + town + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
