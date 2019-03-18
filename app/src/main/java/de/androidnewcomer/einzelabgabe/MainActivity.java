package de.androidnewcomer.einzelabgabe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    EditText input;
    TextView serverAnswer;
    TextView sortiert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        input = findViewById(R.id.matrikelnummer);
        serverAnswer = findViewById(R.id.serverAntwort);
        sortiert = findViewById(R.id.sortiert);


    }


    public void sort(View view){
    String matrikelNummer = input.getText().toString();
    char[] sort = new char[matrikelNummer.length()];
    int buffer = 0;
    int buffer2 = 0;
    int countEven = 0;

        for (int i = 0; i < matrikelNummer.length(); i++) {                 // zählt wieviele geraden Zahlen vorhanden sind
            if(matrikelNummer.charAt(i) % 2 == 0){
                countEven++;
            }
        }

        char[] sortEven = new char[countEven];
        char[] sortUneven = new char[matrikelNummer.length() - countEven];




        for (int i = 0; i < sort.length; i++) {
            if(matrikelNummer.charAt(i) % 2 == 0){
                sortEven[buffer2] = matrikelNummer.charAt(i);
                buffer2++;
            }
        }
        buffer2 = 0;

        sortEven = bubblesort(sortEven);


        for (int i = 0; i < sort.length; i++) {
            if(matrikelNummer.charAt(i) % 2 != 0){
                sortUneven[buffer2] = matrikelNummer.charAt(i);
                buffer2++;
            }
        }
       sortUneven = bubblesort(sortUneven);

        for (int i = 0; i < sortEven.length; i++) {
            sort[i] = sortEven[i];
            buffer++;
        }

        for (int i = 0; i < sortUneven.length; i++) {
            sort[buffer] = sortUneven[i];
            buffer++;
        }


        sortiert.setText(Arrays.toString(sort));



    }

    public char[] bubblesort(char[] toSort){

        char buffer = ' ';

        boolean swapped;
        int n = toSort.length;


        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if(toSort[i] > toSort[i+1]){
                    buffer = toSort[i];
                    toSort[i] = toSort[i+1];
                    toSort[i+1] = buffer;
                    swapped = true;
                }
        }
        n -= 1;

        }while(swapped);






        return toSort;

    }



    public void sendToServer(View v) {
        String studentNumber = input.getText().toString();
        String sentAnswer;
        TCPClient tcpClient = new TCPClient();

        tcpClient.setStudentNumber(studentNumber);

        tcpClient.start(); //start Thread

        try {
            tcpClient.join(1000); //wait max. 10 millisec for the end of thread
            sentAnswer = tcpClient.getServerAnswer();


        } catch (InterruptedException e) {
            sentAnswer = "Could not reach port. Check your internet connection!";
        }

        serverAnswer.setText(sentAnswer);


    }


    }
