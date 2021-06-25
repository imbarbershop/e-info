package com.example.e_info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.example.e_info.info.Info;

public class MainActivity extends AppCompatActivity {

    TextView nimview, namaview, hari, tanggal;
    TextView mk1,mk2,mk3,mk5,mk6,mk8,mk9,mk10;
    private String nimLogin;
    Button logout, tugas, info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nimview = (TextView)findViewById(R.id.nimview);
        namaview = (TextView)findViewById(R.id.namaview);
        logout = (Button)findViewById(R.id.logout);
        nimLogin = Login.sNim;
        nimview.setText(nimLogin);
        tugas = (Button)findViewById(R.id.btn_tugas);
        info = (Button)findViewById(R.id.btn_info);
        hari = (TextView)findViewById(R.id.hari);
        tanggal = (TextView)findViewById(R.id.tanggal);
        mk1 = (TextView)findViewById(R.id.mk1);
        mk2 = (TextView)findViewById(R.id.mk2);
        mk3 = (TextView)findViewById(R.id.mk3);
        mk5 = (TextView)findViewById(R.id.mk5);
        mk6 = (TextView)findViewById(R.id.mk6);
        mk8 = (TextView)findViewById(R.id.mk8);
        mk9 = (TextView)findViewById(R.id.mk9);
        mk10 = (TextView)findViewById(R.id.mk10);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        String[] splitDate = currentDate.split(",");

        hari.setText(splitDate[0].trim());
        tanggal.setText(splitDate[1].trim());


        if (splitDate[0].contains("Minggu")){
            mk1.setText("LIBUR");
            mk2.setText("LIBUR");

            mk3.setText("LIBUR");
            mk5.setText("LIBUR");
            mk6.setText("LIBUR");
            mk8.setText("LIBUR");
            mk9.setText("LIBUR");
            mk10.setText("LIBUR");

        } else if (splitDate[0].contains("Senin")) {
            mk1.setText(DataLogin.namaMk[0]);
            mk2.setText(DataLogin.namaMk[0]);

            mk3.setText(DataLogin.namaMk[1]);
            mk5.setText(DataLogin.namaMk[1]);
            mk6.setText(DataLogin.namaMk[1]);
            mk8.setText(DataLogin.namaMk[1]);
            mk9.setText(DataLogin.namaMk[1]);
            mk10.setText(DataLogin.namaMk[1]);

        } else if (splitDate[0].contains("Selasa")) {
            mk1.setText(DataLogin.namaMk[2]);
            mk2.setText(DataLogin.namaMk[2]);

            mk3.setText(DataLogin.namaMk[3]);
            mk5.setText(DataLogin.namaMk[3]);
            mk6.setText(DataLogin.namaMk[3]);
            mk8.setText(DataLogin.namaMk[3]);
            mk9.setText(DataLogin.namaMk[3]);
            mk10.setText(DataLogin.namaMk[3]);

        } else if (splitDate[0].contains("Rabu")) {
            mk1.setText(DataLogin.namaMk[4]);
            mk2.setText(DataLogin.namaMk[4]);

            mk3.setText(DataLogin.namaMk[5]);
            mk5.setText(DataLogin.namaMk[5]);
            mk6.setText(DataLogin.namaMk[5]);
            mk8.setText(DataLogin.namaMk[5]);
            mk9.setText(DataLogin.namaMk[5]);
            mk10.setText(DataLogin.namaMk[5]);

        } else if (splitDate[0].contains("Kamis")) {
            mk1.setText(DataLogin.namaMk[6]);
            mk2.setText(DataLogin.namaMk[7]);

            mk3.setText(DataLogin.namaMk[8]);
            mk5.setText(DataLogin.namaMk[8]);
            mk6.setText(DataLogin.namaMk[8]);
            mk8.setText(DataLogin.namaMk[8]);
            mk9.setText(DataLogin.namaMk[8]);
            mk10.setText(DataLogin.namaMk[8]);

        } else if (splitDate[0].contains("Jumat")) {
            mk1.setText(DataLogin.namaMk[9]);
            mk2.setText(DataLogin.namaMk[10]);

            mk3.setText(DataLogin.namaMk[10]);
            mk5.setText(DataLogin.namaMk[10]);
            mk6.setText(DataLogin.namaMk[10]);
            mk8.setText(DataLogin.namaMk[10]);
            mk9.setText(DataLogin.namaMk[10]);
            mk10.setText(DataLogin.namaMk[11]);

        } else if(splitDate[0].contains("Sabtu")){
            mk1.setText("LIBUR");
            mk2.setText("LIBUR");

            mk3.setText("LIBUR");
            mk5.setText("LIBUR");
            mk6.setText("LIBUR");
            mk8.setText("LIBUR");
            mk9.setText("LIBUR");
            mk10.setText("LIBUR");
        }
        else {
            Toast.makeText(getApplicationContext(), "Date & Time Error", Toast.LENGTH_SHORT).show();
        }

        for (int i=1; i<=30 ; i++){
            if (nimLogin.equals(DataLogin.nimTi1[i])){
            namaview.setText(DataLogin.namaTi1[i]);
            }
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MainActivity.this, Login.class);
                startActivity(login);
            }
        });

        tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tugasIntent = new Intent(MainActivity.this, Tugas.class);
                startActivity(tugasIntent);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoIntent = new Intent(MainActivity.this, Info.class);
                startActivity(infoIntent);
            }
        });
    }
}