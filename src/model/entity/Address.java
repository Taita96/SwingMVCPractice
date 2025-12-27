package model.entity;

import java.time.LocalDateTime;

public class Address {

    private Integer idAddress;
    private String street;
    private String country;
    private String city;
    private User user;
    private String apartarment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Address(String street, String country, String city, String aparatament) {
        this.street = street;
        this.country = country;
        this.city = city;
        this.apartarment = aparatament;
    }

    public Address() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getApartarment() {
        return apartarment;
    }

    public void setApartarment(String apartarment) {
        this.apartarment = apartarment;
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
