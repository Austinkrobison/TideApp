package com.gmail.austinkrobison.tideapp;

public class RSSItem {
    private String date = null;
    private String time = null;
    private String day = null;
    private String predInFeet = null;
    private String highLow = null;

    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return date;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getTime(){
        return time;
    }
    public void setDay(String day){
        this.day = day;
    }
    public String getDay(){
        return day;
    }
    public void setPredInFeet(String pred){
        this.predInFeet = pred;
    }
    public String getPredInFeet(){
        return predInFeet+" ft.";
    }
    public void setHighLow(String HL){
        this.highLow = HL;
    }
    public String getHighLow(){
        if((highLow).equals("H")){
            return "High";
        }
        return "Low";
    }
    public String getNeatTopRow(){
        return this.getDate() +" "+ this.getDay();
    }
    public String getNeatBottomRow(){
        return this.getHighLow() + ": " + this.getTime();
    }

}
