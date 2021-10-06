package com.example.it20020880;

public class Products {
    public Products(String pCatogory, String pDeliveryAvailable, String pDescription, String pFoodname, String pID, String pImage, String pPrice) {
        this.pCatogory = pCatogory;
        this.pDeliveryAvailable = pDeliveryAvailable;
        this.pDescription = pDescription;
        this.pFoodname = pFoodname;
        this.pID = pID;
        this.pImage = pImage;
        this.pPrice = pPrice;
    }

    String pCatogory;

    public String getpCatogory() {
        return pCatogory;
    }

    public void setpCatogory(String pCatogory) {
        this.pCatogory = pCatogory;
    }

    public String getpDeliveryAvailable() {
        return pDeliveryAvailable;
    }

    public void setpDeliveryAvailable(String pDeliveryAvailable) {
        this.pDeliveryAvailable = pDeliveryAvailable;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpFoodname() {
        return pFoodname;
    }

    public void setpFoodname(String pFoodname) {
        this.pFoodname = pFoodname;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    String pDeliveryAvailable;
    String pDescription;
    String pFoodname;
    String pID;
    String pImage;
    String pPrice;

    public Products(){}
}
