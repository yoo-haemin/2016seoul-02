package com.runfive.hangangrunner.HanGangInfo;

/**
 * Created by JunHo on 2016-08-10.
 */
public class HanGangLawnInfoObject {

    private String gigu;    // 한강 지구번호
    private double latitude;   // 위도
    private double longitude;   // 경도

    public HanGangLawnInfoObject(String gigu, double lng, double lat) {
        this.gigu = gigu;
        this.longitude = lng;
        this.latitude = lat;
    }

    @Override
    public String toString() {
        return "지구명 : "+gigu + "경도 : " + longitude + "위도 : " + latitude + "\n";
        //return "지구명 : "+gigu + "x좌표 : " + xPosition +"\n";
    }

    public String getGigu() {
        return gigu;
    }

    public void setGigu(String gigu) {
        this.gigu = gigu;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
