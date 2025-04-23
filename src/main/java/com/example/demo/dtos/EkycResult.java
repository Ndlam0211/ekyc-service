package com.example.demo.dtos;

/**
 * Holds OCR results for both front and back of an ID document.
 */
public class EkycResult {
    private FrontResult frontIdData;
    private BackResult backIdData;

    public FrontResult getFrontIdData() {
        return frontIdData;
    }

    public void setFrontIdData(FrontResult frontIdData) {
        this.frontIdData = frontIdData;
    }

    public BackResult getBackIdData() {
        return backIdData;
    }

    public void setBackIdData(BackResult backIdData) {
        this.backIdData = backIdData;
    }
}