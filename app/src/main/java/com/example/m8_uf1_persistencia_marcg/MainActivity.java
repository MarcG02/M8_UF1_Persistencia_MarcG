package com.example.m8_uf1_persistencia_marcg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        EditText inputText = (EditText) findViewById(R.id.TextPersonName);
        Button button = findViewById(R.id.idButton);

        try  {
            String FileName = "dades.txt";
            OutputStream os = openFileOutput(FileName, MODE_PRIVATE);
            String message = "Hola";
            os.write(message.getBytes());
            os.close();

            File dir = getFilesDir();
            String cDir = String.valueOf(dir) + File.separator +FileName;
            Log.v("msg", cDir);
            FileInputStream fileInS = new FileInputStream(cDir);

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String inputData = inputText.getText().toString();

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}