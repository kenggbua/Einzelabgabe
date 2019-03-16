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

        for (int i = 0; i < matrikelNummer.length(); i++) {
            if(matrikelNummer.charAt(i) % 2 == 0){
                sort[buffer] = matrikelNummer.charAt(i);
                buffer++;
            }
        }

        for (int i = 0; i < matrikelNummer.length(); i++) {
            if(matrikelNummer.charAt(i) % 2 != 0){
                sort[buffer] = matrikelNummer.charAt(i);
                buffer++;
            }
        }

        sortiert.setText(Arrays.toString(sort));



    }



    public void sendToServer(View v) {
        String studentNumber = input.getText().toString();
        String sendedAnswer;
        TCPClient tcpClient = new TCPClient();

        tcpClient.setStudentNumber(studentNumber);

        tcpClient.start(); //start Thread

        try {
            tcpClient.join(100000); //wait max. 10 millisec for the end of thread
            sendedAnswer = tcpClient.getServerAnswer();


        } catch (InterruptedException e) {
            sendedAnswer = "Could not reach port. Check your internet connection!";
        }

        serverAnswer.setText(sendedAnswer);


    }


    }
