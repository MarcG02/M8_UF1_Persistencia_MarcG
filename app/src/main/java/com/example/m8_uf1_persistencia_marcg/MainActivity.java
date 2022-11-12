package com.example.m8_uf1_persistencia_marcg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView view = (TextView) findViewById(R.id.textView);
        EditText inputText = (EditText) findViewById(R.id.TextPersonName);
        Button button = findViewById(R.id.idButton);

        try  {
            String FileName = "dades.txt";
            FileInputStream fileInS = openFileInput(FileName);
            InputStreamReader fileInR = new InputStreamReader(fileInS);
            BufferedReader buffReader = new BufferedReader(fileInR);
            String line = buffReader.readLine();
            String text = "";

            while(line != null){
                text += line + "\n";

                line = buffReader.readLine();
            }

            view.setText(text);
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
    }
}