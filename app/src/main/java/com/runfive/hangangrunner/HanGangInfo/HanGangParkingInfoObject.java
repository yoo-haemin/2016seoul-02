package com.runfive.hangangrunner.HanGangInfo;

/**
 * Created by JunHo on 8/17/2016.
 */
public class HanGangParkingInfoObject {

    private String gigu;    // 한강 지구번호
    private double latitude;   // 위도
    private double longitude;   // 경도
    private String tel; // 전화번호
    private String fee; // 요금


    public HanGangParkingInfoObject(String gigu, double lng, double lat, String tel, String fee) {
        this.gigu = gigu;
        this.longitude = lng;
        this.latitude = lat;
        this.tel = tel;
        this.fee = fee;
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

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
