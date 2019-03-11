package com.codecool.greencommitment.client;

import java.io.*;
import java.util.*;
import java.net.Socket;

public class Client {

    private String clientID;
    private long generateDataSeconds = 1;

    public void startclient(String[] args) throws IOException {

        var socket = new Socket(args[1], 59090);

        //RECEIVE FROM SERVER
        var in = new Scanner(socket.getInputStream());
        System.out.println("[Server] : " + in.nextLine());
        int clientID = Integer.valueOf(in.nextLine()); //Get ID generated from Server

        while (true) {
            //SEND TO SERVER
            int data = datagenerator.generateData(2);
            Date currentTime = new Date();
            long currentMilis = currentTime.getTime();
            String xmlData = generateMeasurment(currentMilis,data,"celsius");
            String argLengths = getArgLengths(currentMilis,data,"celsius");
            PrintStream msg = new PrintStream(socket.getOutputStream());
            msg.println(xmlData);
            msg.println(argLengths);
        }
    }

    private String generateMeasurment(long time, int value, String type){
        return "<measurement><time>"+time+"</time><value>"+value+"</value><type>"+type+"</type></measurement>";
    }

    private String getArgLengths(long time, int value, String type){
        return String.valueOf(time).length()+":"+String.valueOf(value).length()+":"+type.length();
    }
}
