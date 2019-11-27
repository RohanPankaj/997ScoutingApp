package com.example.a997scoutingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";
    EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.edit_text);

    }
    public void save(View v){
        String text = mEditText.getText().toString();
        FileOutputStream fos = null;
        /*if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File FILE_NAME = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES),Environment.DIRECTORY_DOWNLOADS );

        }*/
        try {
            fos = openFileOutput(FILE_NAME,MODE_PRIVATE );

            fos.write(text.getBytes());

            mEditText.getText().clear();
            Toast.makeText( this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null){
                try{
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
    }
    public void load(View v){
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine())!= null){
                sb.append(text).append("\n");

            }
            mEditText.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fis != null ){
                try {
                    fis.close();

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }




}