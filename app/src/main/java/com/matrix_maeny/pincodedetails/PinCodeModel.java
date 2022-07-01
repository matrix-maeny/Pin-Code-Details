package com.matrix_maeny.pincodedetails;

/*
"Name": "Ankireddipalli",
            "Description": "",
            "BranchType": "Branch Post Office",
            "DeliveryStatus": "Delivery",
            "Taluk": "Hyd",
            "Circle": "Hyd",
            "District": "Hyderabad",
            "Division": "Secunderabad",
            "Region": "Hyderabad City",
            "State": "Telangana",
            "Country": "India"
 */

public class PinCodeModel {
    private String villageName, description, branchType, deliveryStatus, taluk, circle, district, division, region, state, country;


    public PinCodeModel(String villageName, String description, String branchType, String deliveryStatus, String taluk, String circle, String district, String division, String region, String state, String country) {
        this.villageName = villageName;
        this.description = description;
        this.branchType = branchType;
        this.deliveryStatus = deliveryStatus;
        this.taluk = taluk;
        this.circle = circle;
        this.district = district;
        this.division = division;
        this.region = region;
        this.state = state;
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getTaluk() {
        return taluk;
    }

    public void setTaluk(String taluk) {
        this.taluk = taluk;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public String getDataString(){
        String dataString;
        dataString = "Village name: "+villageName+"\n\n"+"" +
                "Description: "+description+"\n\n"+
                "DeliveryStatus: "+deliveryStatus+"\n\n"+
                "Taluk: "+taluk+"\n\n"+
                "Circle: "+circle+"\n\n"+
                "District: "+district+"\n\n"+
                "Division: "+division+"\n\n"+
                "Region: "+region+"\n\n"+
                "State: "+state+"\n\n"+
                "Country: "+country;

        return dataString;
    }

    /*
    *  "Name": "Bogguladone",
            "Description": "",
            "BranchType": "Branch Post Office",
            "DeliveryStatus": "Delivery",
            "Taluk": "Nalgonda",
            "Circle": "Nalgonda",
            "District": "Nalgonda",
            "Division": "Nalgonda",
            "Region": "Hyderabad",
            "State": "Telangana",
            "Country": "India"
    * */
}
