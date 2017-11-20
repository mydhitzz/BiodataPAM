package com.example.yogiwnugarah.biodata;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText NIM,Nama,Alamat,Telp,Email;
    Button button1,button2;
    private ImageView image;

    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);
        NIM= (EditText) findViewById(R.id.NIM);
        Nama= (EditText) findViewById(R.id.Nama);
        Alamat= (EditText) findViewById(R.id.Alamat);
        Telp= (EditText) findViewById(R.id.Telp);
        Email= (EditText) findViewById(R.id.Email);
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        image = (ImageView) findViewById(R.id.imageView);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    private void getImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Log.i("TAG","bitmap : "+bitmap);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v==button1){
            getImage();
        }
        if(v==button2){
            Intent i = new Intent(MainActivity.this, tampilActivity.class);
            i.putExtra(tampilActivity.Nama, Nama.getText().toString());
            i.putExtra(tampilActivity.Alamat, Alamat.getText().toString());
            i.putExtra(tampilActivity.Email, Email.getText().toString());
            i.putExtra(tampilActivity.NIM, NIM.getText().toString());
            i.putExtra(tampilActivity.Telp, Telp.getText().toString());
            i.putExtra(tampilActivity.uri, filePath.toString());
            Log.i("TAG","nama : "+Nama.getText().toString());
            startActivity(i);
        }
    }
}
