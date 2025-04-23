package com.example.demo.dtos;

import java.util.List;

/**
 * Represents OCR result for the back side of an ID document.
 */
public class BackResult {
    private String features;
    private String featuresProb;
    private String issueDate;
    private String issueDateProb;
    private List<String> mrz;
    private String mrzProb;
    private String overallScore;
    private String issueLocation;
    private String issueLocationProb;
    private String typeNew;
    private String type;
    private MrzDetails mrzDetails;
    private String placeOfBirth;
    private String placeOfBirthProb;
    private String address;
    private String addressProb;
    private String dateOfExpiry;
    private String dateOfExpiryProb;

    // Nested class for MRZ details
    public static class MrzDetails {
        private String id;
        private String name;
        private String dateOfExpiry;
        private String dateOfBirth;
        private String nationality;
        private String sex;

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDateOfExpiry() {
            return dateOfExpiry;
        }

        public void setDateOfExpiry(String dateOfExpiry) {
            this.dateOfExpiry = dateOfExpiry;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

    // Getters and Setters
    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getFeaturesProb() {
        return featuresProb;
    }

    public void setFeaturesProb(String featuresProb) {
        this.featuresProb = featuresProb;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueDateProb() {
        return issueDateProb;
    }

    public void setIssueDateProb(String issueDateProb) {
        this.issueDateProb = issueDateProb;
    }

    public List<String> getMrz() {
        return mrz;
    }

    public void setMrz(List<String> mrz) {
        this.mrz = mrz;
    }

    public String getMrzProb() {
        return mrzProb;
    }

    public void setMrzProb(String mrzProb) {
        this.mrzProb = mrzProb;
    }

    public String getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(String overallScore) {
        this.overallScore = overallScore;
    }

    public String getIssueLocation() {
        return issueLocation;
    }

    public void setIssueLocation(String issueLocation) {
        this.issueLocation = issueLocation;
    }

    public String getIssueLocationProb() {
        return issueLocationProb;
    }

    public void setIssueLocationProb(String issueLocationProb) {
        this.issueLocationProb = issueLocationProb;
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

    public MrzDetails getMrzDetails() {
        return mrzDetails;
    }

    public void setMrzDetails(MrzDetails mrzDetails) {
        this.mrzDetails = mrzDetails;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPlaceOfBirthProb() {
        return placeOfBirthProb;
    }

    public void setPlaceOfBirthProb(String placeOfBirthProb) {
        this.placeOfBirthProb = placeOfBirthProb;
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
}