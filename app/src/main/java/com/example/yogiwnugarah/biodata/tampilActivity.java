package com.example.yogiwnugarah.biodata;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class tampilActivity extends AppCompatActivity {
    TextView nim, nama, alamat, telp, email;

    public static final String Nama="Nama";
    public static final String NIM="NIM";
    public static final String Alamat="Alamat";
    public static final String Telp= "Telp";
    public static final String Email="Email";
    public static final String uri = "uri";

    private ImageView imageView;

    Uri filePath;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imageView = (ImageView) findViewById(R.id.imageView);
        nim= (TextView) findViewById(R.id.txtnim);
        nama= (TextView) findViewById(R.id.txtnama);
        alamat= (TextView) findViewById(R.id.txtalamat);
        telp= (TextView) findViewById(R.id.txttelp);
        email= (TextView) findViewById(R.id.txtemail);

        nim.setText("NIM : "+getIntent().getExtras().getString(NIM));
        nama.setText("Nama : "+getIntent().getExtras().getString(Nama));
        alamat.setText("Alamat : "+getIntent().getExtras().getString(Alamat));
        telp.setText("Telp : "+getIntent().getExtras().getString(Telp));
        email.setText("Email : "+getIntent().getExtras().getString(Email));

        filePath = Uri.parse(getIntent().getExtras().getString(uri));
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
