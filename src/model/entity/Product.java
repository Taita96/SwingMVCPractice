package model.entity;

import model.entity.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Product {

    private Integer idProduct;
    private String code;
    private double price;
    private String material;
    private String typeProduct;
    private LocalDate registerDay;
    private String size;
    private String brand;
    private boolean waterproof;
    private int weight;
    private String gadget;
    private String security;
    private boolean wheels;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(String code, double price, String material, String typeProduct, LocalDate registerDay ,String size, String brand, boolean waterproof, int weight, String gadget, String security, boolean wheels) {
        this.code = code;
        this.price = price;
        this.material = material;
        this.typeProduct = typeProduct;
        this.registerDay = registerDay;
        this.size = size;
        this.brand = brand;
        this.waterproof = waterproof;
        this.weight = weight;
        this.gadget = gadget;
        this.security = security;
        this.wheels = wheels;
    }

    public Product() {
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public boolean isWaterproof() {
        return waterproof;
    }

    public void setWaterproof(boolean waterproof) {
        this.waterproof = waterproof;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isWheels() {
        return wheels;
    }

    public void setWheels(boolean wheels) {
        this.wheels = wheels;
    }

    public LocalDate getRegisterDay() {
        return registerDay;
    }

    public void setRegisterDay(LocalDate registerDay) {
        this.registerDay = registerDay;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGadget() {
        return gadget;
    }

    public void setGadget(String gadget) {
        this.gadget = gadget;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", material='" + material + '\'' +
                ", typeProduct='" + typeProduct + '\'' +
                ", registerDay=" + registerDay +
                ", size='" + size + '\'' +
                ", brand='" + brand + '\'' +
                ", waterproof=" + waterproof +
                ", weight=" + weight +
                ", gadget='" + gadget + '\'' +
                ", security='" + security + '\'' +
                ", wheels=" + wheels +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
