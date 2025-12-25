package model.entity;

import java.time.LocalDateTime;

public class Address {

    private Integer idAddress;
    private String street;
    private String country;
    private String city;
    private String aparatament;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Address(Integer idAddress, String street, String country, String city, String aparatament, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idAddress = idAddress;
        this.street = street;
        this.country = country;
        this.city = city;
        this.aparatament = aparatament;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Address() {
    }

    public Integer getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Integer idAddress) {
        this.idAddress = idAddress;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAparatament() {
        return aparatament;
    }

    public void setAparatament(String aparatament) {
        this.aparatament = aparatament;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
