package com.example.m8_uf1_persistencia_marcg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputText = (EditText) findViewById(R.id.TextPersonName);
        Button button = findViewById(R.id.idButton);
        String FileName = "dades.txt";
        String text = "";

        /*This line contains the file*/
        String file = Arrays.toString(this.getFilesDir().list());

        /* if file isn't exists this condition generates a new file*/
        if (!file.contains("dades.txt")) {
            File ruta = new File(this.getFilesDir() + File.separator + FileName);

            try {
                OutputStream os = openFileOutput("dades.txt", MODE_PRIVATE);
                /*Succsessful text input when the file is created*/
                text = "File Created";
                Toast.makeText(MainActivity.this, "File was succsessfuly created", Toast.LENGTH_SHORT).show();

                os.write(text.getBytes());
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try  {
            FileInputStream fileInS = openFileInput(FileName);
            InputStreamReader fileInR = new InputStreamReader(fileInS);
            BufferedReader buffReader = new BufferedReader(fileInR);
            String line = buffReader.readLine();

            while(line != null){
                text += line + "\n";

                line = buffReader.readLine();
            }

            buffReader.close();
            fileInR.close();

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    String inputData = inputText.getText().toString();
                    try {
                        if (TextUtils.isEmpty(inputText.getText().toString())){
                            Toast.makeText(MainActivity.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            FileInputStream fileInS = openFileInput(FileName);
                            InputStreamReader fileInR = new InputStreamReader(fileInS);
                            BufferedReader buffReader = new BufferedReader(fileInR);
                            String line = buffReader.readLine();
                            String text = "";

                            while(line != null){
                                text += line + "\n";

                                line = buffReader.readLine();
                            }

                            buffReader.close();
                            fileInR.close();
                            OutputStream os = openFileOutput(FileName, MODE_PRIVATE);
                            text += inputData;
                            os.write(text.getBytes());

                            os.flush();
                            os.close();

                            updateView(FileName, text);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(MainActivity.this, "Text was written correctly", Toast.LENGTH_SHORT).show();
                    inputText.getText().clear();

                }

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        updateView(FileName, text);
    }
    public void updateView(String FileName, String text) {
        TextView view = (TextView) findViewById(R.id.textView2);
        view.setMovementMethod(new ScrollingMovementMethod());
        try {
            FileInputStream fileInS = openFileInput(FileName);
            InputStreamReader fileInR = new InputStreamReader(fileInS);
            BufferedReader buffReader = new BufferedReader(fileInR);
            String line = buffReader.readLine();
            text = "";

            while (line != null) {
                text += line + "\n";

                line = buffReader.readLine();
            }

            buffReader.close();
            fileInR.close();
            view.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}