package com.mattpierce.mwp_datachart.objects;

/**
 * Created by mattpierce on 8/23/17.
 */

public class DatasetMapping {

    private String fieldForX;
    private String fieldForY;

    public DatasetMapping(String fieldForX, String fieldForY) {
        this.fieldForX = fieldForX;
        this.fieldForY = fieldForY;
    }

    public String getFieldForX() {
        return fieldForX;
    }

    public void setFieldForX(String fieldForX) {
        this.fieldForX = fieldForX;
    }

    public String getFieldForY() {
        return fieldForY;
    }

    public void setFieldForY(String fieldForY) {
        this.fieldForY = fieldForY;
    }
}
