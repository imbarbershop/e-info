package com.example.e_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import com.example.e_info.admininfo.Info;
import com.example.e_info.admintugas.Tugas;
import com.example.e_info.info.InputInfo;

public class MainActivity2 extends AppCompatActivity {

    TextView nimview, namaview, hari, tanggal;
    TextView mk1,mk2,mk3,mk5,mk6,mk8,mk9,mk10;
    private String userAdmin;
    Button logout, tugas, info, inputTugas, inputInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nimview = (TextView)findViewById(R.id.user_view);
        namaview = (TextView)findViewById(R.id.admin_view);
        logout = (Button)findViewById(R.id.logout);
        userAdmin = Login.sUserAdmin;
        nimview.setText(userAdmin);

        tugas = (Button)findViewById(R.id.btn_tugas);
        info = (Button)findViewById(R.id.btn_info);
        inputTugas = (Button)findViewById(R.id.btn_input_tugas);
        inputInfo = (Button)findViewById(R.id.btn_input_info);
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

        for (int i=1; i<=2 ; i++){
            if (userAdmin.equals(DataLogin.userAdmTi1[i])){
                namaview.setText(DataLogin.namaAdmTi1[i]);
            }
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MainActivity2.this, Login.class);
                startActivity(login);
            }
        });
        tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tugasIntent = new Intent(MainActivity2.this, Tugas.class);
                startActivity(tugasIntent);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoIntent = new Intent(MainActivity2.this, Info.class);
                startActivity(infoIntent);
            }
        });
        inputTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoIntent = new Intent(MainActivity2.this, InputTugas.class);
                startActivity(infoIntent);
            }
        });
        inputInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoIntent = new Intent(MainActivity2.this, InputInfo.class);
                startActivity(infoIntent);
            }
        });
    }
}