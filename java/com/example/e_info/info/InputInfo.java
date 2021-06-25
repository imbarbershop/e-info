package com.example.e_info.info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_info.R;

import java.text.DateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputInfo extends AppCompatActivity {
    private EditText etmk, etjudul, etdeskripsi;
    private Button kirim, back;
    private String mk, judul, deskripsi, masuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_info);

        etmk = (EditText)findViewById(R.id.mk_input_info);
        etjudul = (EditText)findViewById(R.id.judul_input_info);
        etdeskripsi = (EditText)findViewById(R.id.deskripsi_input_info);
        kirim = (Button)findViewById(R.id.btn_kirim_info);
        back = (Button)findViewById(R.id.back_home);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mk = etmk.getText().toString();
                judul = etjudul.getText().toString();
                deskripsi = etdeskripsi.getText().toString();
                masuk = currentDate;

                if(mk.trim().equals("")){
                    etmk.setError("Wajib Diisi!");

                } else if(judul.trim().equals("")){
                    etjudul.setError("Wajib Diisi!");

                } else if(deskripsi.trim().equals("")){
                    etdeskripsi.setError("Wajib Diisi!");

                } else {
                    createData();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    public void createData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(mk, judul, deskripsi, masuk);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(InputInfo.this, "Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                etmk.setText("");
                etjudul.setText("");
                etdeskripsi.setText("");
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(InputInfo.this, "Gagal Menghubungi Server |"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}