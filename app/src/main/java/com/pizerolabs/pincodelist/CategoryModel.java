package com.pizerolabs.pincodelist;

public class CategoryModel {
    private String cityName;
    private String cityZip;

    public CategoryModel() {
    }

    public CategoryModel(String cityName, String cityZip) {
        this.cityName = cityName;
        this.cityZip = cityZip;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityZip() {
        return cityZip;
    }

    public void setCityZip(String cityZip) {
        this.cityZip = cityZip;
    }


}
