package model.pojo;

import java.time.LocalDateTime;


public class Address {
    private int addressID;
    private String street;
    private String barangay;
    private String city;
    private String province;
    private String zipCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Address(int addressID, String street, String barangay, String city, String province, String zipCode, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.addressID = addressID;
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getAddressID() {
        return addressID;
    }

    public String getStreet() {
        return street;
    }

    public String getBarangay() {
        return barangay;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
