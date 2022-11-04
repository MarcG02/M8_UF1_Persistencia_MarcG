package com.example.m8_uf1_persistencia_marcg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.idButton);

        try  {
            OutputStream os = openFileOutput("dades.txt", MODE_PRIVATE);
            String message = "Hola";
            os.write(message.getBytes());
            os.close();

            File dir = this.getFilesDir();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}