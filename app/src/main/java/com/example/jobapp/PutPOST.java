package com.example.jobapp;

public class PutPOST {

    public String name;
    public String caption;
    String image;

    public PutPOST(){
    }

    public PutPOST(String name, String caption,String image){
        this.name = name;
        this.caption = caption;
        this.image = image;
    }

    public String getName(){

        return name;
    }

    public void setName(String name ){

        this.name = name;
    }

    public String getImage(){

        return  image;
    }

    public void setImage(String image ){

        this.image = image;
    }

    public String getCaption(){

        return caption;
    }

    public void setCaption(String caption ){

        this.caption = caption;
    }
}
