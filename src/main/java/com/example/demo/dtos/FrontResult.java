package com.example.demo.dtos;

/**
 * Represents OCR result for the front side of an ID document.
 */
public class FrontResult {
    private String id;
    private String idProb;
    private String name;
    private String nameProb;
    private String dateOfBirth;
    private String dateOfBirthProb;
    private String sex;
    private String sexProb;
    private String nationality;
    private String nationalityProb;
    private String home;
    private String homeProb;
    private String address;
    private String addressProb;
    private String dateOfExpiry;
    private String dateOfExpiryProb;
    private String overallScore;
    private String numberOfNameLines;
    private AddressEntities addressEntities;
    private String typeNew;
    private String type;

    // Nested class for address entities
    public static class AddressEntities {
        private String province;
        private String district;
        private String ward;
        private String street;

        // Getters and Setters
        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getWard() {
            return ward;
        }

        public void setWard(String ward) {
            this.ward = ward;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProb() {
        return idProb;
    }

    public void setIdProb(String idProb) {
        this.idProb = idProb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameProb() {
        return nameProb;
    }

    public void setNameProb(String nameProb) {
        this.nameProb = nameProb;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirthProb() {
        return dateOfBirthProb;
    }

    public void setDateOfBirthProb(String dateOfBirthProb) {
        this.dateOfBirthProb = dateOfBirthProb;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSexProb() {
        return sexProb;
    }

    public void setSexProb(String sexProb) {
        this.sexProb = sexProb;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalityProb() {
        return nationalityProb;
    }

    public void setNationalityProb(String nationalityProb) {
        this.nationalityProb = nationalityProb;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getHomeProb() {
        return homeProb;
    }

    public void setHomeProb(String homeProb) {
        this.homeProb = homeProb;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressProb() {
        return addressProb;
    }

    public void setAddressProb(String addressProb) {
        this.addressProb = addressProb;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public String getDateOfExpiryProb() {
        return dateOfExpiryProb;
    }

    public void setDateOfExpiryProb(String dateOfExpiryProb) {
        this.dateOfExpiryProb = dateOfExpiryProb;
    }

    public String getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(String overallScore) {
        this.overallScore = overallScore;
    }

    public String getNumberOfNameLines() {
        return numberOfNameLines;
    }

    public void setNumberOfNameLines(String numberOfNameLines) {
        this.numberOfNameLines = numberOfNameLines;
    }

    public AddressEntities getAddressEntities() {
        return addressEntities;
    }

    public void setAddressEntities(AddressEntities addressEntities) {
        this.addressEntities = addressEntities;
    }

    public String getTypeNew() {
        return typeNew;
    }

    public void setTypeNew(String typeNew) {
        this.typeNew = typeNew;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}