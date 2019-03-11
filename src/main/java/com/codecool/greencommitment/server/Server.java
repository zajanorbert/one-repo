package com.codecool.greencommitment.server;

import com.codecool.greencommitment.common.Measurement;
import com.codecool.greencommitment.common.Measurements;
import com.codecool.greencommitment.common.XMLwriter;
import org.w3c.dom.Document;

import javax.print.Doc;
import javax.sql.rowset.spi.XmlWriter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    private static int id = 1;

    public void startserver(String[] args) {

        try (var listener = new ServerSocket(59090)) {
            System.out.println("The date server is running...");
            while (true) {
                var socket = listener.accept();
                ClientHandler clientSock = new ClientHandler(socket, id);
                id++;

                // The background thread will handle each client separately
                new Thread(clientSock).start();
            }
        } catch (Exception e) {
        }
    }

    private static class ClientHandler implements Runnable {

        private final Socket clientSocket;
        private int clientID;

        public ClientHandler(Socket clientSocket, int clientID) {
            this.clientSocket = clientSocket;
            this.clientID = clientID;
        }

        @Override
        public void run() {
            try {
                //RECEIVE FROM CLIENT
                PrintStream send = new PrintStream(clientSocket.getOutputStream());
                send.println("Connected successfully to the server. Your client id is " + clientID + ". ");
                send.println(clientID);

                List<Measurement> msArrayList = new ArrayList<>();
                Scanner scanner = new Scanner(clientSocket.getInputStream());

                while (scanner.hasNext()) {
                    String rawXml = scanner.nextLine();
                    System.out.println("[Client #" + clientID + "] : " + rawXml);

                    String stripped = rawXml.replaceAll("<[^>]*>", ".");
                    String finalStripped = stripped.replace("..", "");

                    String rawLengths = scanner.nextLine();
                    String[] lengths = rawLengths.split(":");

                    int[] lengthInt = new int[3];
                    for (int i = 0; i < lengthInt.length; i++) {
                        lengthInt[i] = Integer.valueOf(lengths[i]);
                    }

                    String[] properties = new String[]{finalStripped.substring(0, lengthInt[0]),
                            finalStripped.substring(lengthInt[0], lengthInt[0] + lengthInt[1]),
                            finalStripped.substring(lengthInt[0] + lengthInt[1], lengthInt[0] + lengthInt[1] + lengthInt[2])};

                    Measurement ms = new Measurement(Long.valueOf(properties[0]), Integer.valueOf(properties[1]), properties[2]);

                    msArrayList.add(ms);

                }
                Measurements measurements = new Measurements(clientID, msArrayList);
                XMLwriter xmlWriter = new XMLwriter();
                xmlWriter.xmlWrite(measurements);
            } catch (Exception e) {
            }
        }
    }
}