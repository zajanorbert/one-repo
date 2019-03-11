package com.codecool.greencommitment.common;

import com.codecool.greencommitment.client.Client;
import com.codecool.greencommitment.server.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args[0].equals("client")){
            Client client = new Client();
            client.startclient(args);
        } else if (args[0].equals("server")){
            Server server = new Server();
            server.startserver(args);
        }


        XMLread read = new XMLread();
        XMLwriter xmLwriter = new XMLwriter();
        Measurement measurement1 = new Measurement(100000,23,"celsius");
        Measurement measurement2 = new Measurement(1500000,32,"celsius");
        List<Measurement> measurementList = new ArrayList<>();
        measurementList.add(measurement1);
        measurementList.add(measurement2);
        Measurements measurements = new Measurements(12, measurementList);



        //xmLwriter.xmlWrite(measurements);
       // System.out.println(read.xmlParser("src/main/data/12.xml"));

    }
}
