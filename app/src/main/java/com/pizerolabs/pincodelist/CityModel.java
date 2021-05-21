package com.pizerolabs.pincodelist;

public class CityModel {
    private int cityId, cityPincode;

    public CityModel() {
    }

    public CityModel(int cityId, int cityPincode) {
        this.cityId = cityId;
        this.cityPincode = cityPincode;
    }

    @Override
    public String toString() {
        return "CityModel{" +
                "cityId=" + cityId +
                ", cityPincode=" + cityPincode +
                '}';
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCityPincode() {
        return cityPincode;
    }

    public void setCityPincode(int cityPincode) {
        this.cityPincode = cityPincode;
    }
}

