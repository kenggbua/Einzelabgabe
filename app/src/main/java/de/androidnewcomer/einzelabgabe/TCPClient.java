package de.androidnewcomer.einzelabgabe;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class TCPClient extends Thread {

    String studentNumber;
    String serverAnswer;

    @Override
    public void run() {

        try {
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);


            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outToServer.writeBytes(studentNumber + '\n');

            serverAnswer = inFromServer.readLine();

            clientSocket.close();


        } catch (IOException e) {
            serverAnswer = "Could not listen on port!";

        }
    }


    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }


    public String getServerAnswer() {
        return serverAnswer;
    }


}