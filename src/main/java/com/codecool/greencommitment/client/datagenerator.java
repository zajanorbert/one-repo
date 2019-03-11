package com.codecool.greencommitment.client;

public class datagenerator {
    public static int generateData(int seconds){
        try{
            Thread.sleep(seconds*1000);
        }
        catch (Exception e){}
        double rndNumb = Math.random()*50+1;
        return (int)rndNumb;
    }
}
