package com.example.e_info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputTugas extends AppCompatActivity {
    private EditText etmk, etjudul, etdeskripsi, etdeadline;
    private Button kirim, back;
    private String mk, judul, deskripsi, masuk, deadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tugas);

        etmk = (EditText)findViewById(R.id.mk_input_tugas);
        etjudul = (EditText)findViewById(R.id.judul_input_tugas);
        etdeskripsi = (EditText)findViewById(R.id.deskripsi_input_tugas);
        etdeadline = (EditText)findViewById(R.id.deadline_input_tugas);
        kirim = (Button)findViewById(R.id.btn_kirim_tugas);
        back = (Button)findViewById(R.id.back_home);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        String[] splitDate = currentDate.split(", ");

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mk = etmk.getText().toString();
                judul = etjudul.getText().toString();
                deskripsi = etdeskripsi.getText().toString();
                masuk = splitDate[1].trim();
                deadline = etdeadline.getText().toString();

                if(mk.trim().equals("")){
                    etmk.setError("Wajib Diisi!");

                } else if(judul.trim().equals("")){
                    etjudul.setError("Wajib Diisi!");

                } else if(deskripsi.trim().equals("")){
                    etdeskripsi.setError("Wajib Diisi!");

                } else if(deadline.trim().equals("")){
                    etdeadline.setError("Wajib Diisi!");

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

    private void createData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(mk, judul, deskripsi, masuk, deadline);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(InputTugas.this, "Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                etmk.setText("");
                etjudul.setText("");
                etdeskripsi.setText("");
                etdeadline.setText("");
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(InputTugas.this, "Gagal Menghubungi Server |"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}