package com.example.e_info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText nim, pass, userAdmin, passAdmin;
    Button btnLoginMhs, btnLoginAdmin, btnMhs, btnAdmin;
    RelativeLayout relAdmin, relMahasiswa;
    ProgressDialog progressDialog;
    public static String sNim, sUserAdmin;

    CheckBox cbAdmin, cbMahasiswa;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean saveLogin;
    String sNim1, sPass1, sUserAdmin1, sPassAdmin1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nim = (EditText)findViewById(R.id.nim);
        pass = (EditText)findViewById(R.id.pass_mhs);
        userAdmin = (EditText)findViewById(R.id.user_admin);
        passAdmin = (EditText)findViewById(R.id.pass_admin);
        btnLoginMhs = (Button)findViewById(R.id.btn_login_mhs);
        btnLoginAdmin = (Button)findViewById(R.id.btn_login_admin);
        btnMhs = (Button)findViewById(R.id.btn_mhs);
        btnAdmin = (Button)findViewById(R.id.btn_admin);
        relMahasiswa = (RelativeLayout)findViewById(R.id.rel_mahasiswa);
        relAdmin = (RelativeLayout)findViewById(R.id.rel_admin);

        progressDialog = new ProgressDialog(Login.this);

        relAdmin.setVisibility(View.GONE);

        cbAdmin = (CheckBox)findViewById(R.id.cb_admin);
        cbMahasiswa = (CheckBox)findViewById(R.id.cb_mhs);

        sharedPreferences = getSharedPreferences("loginref", MODE_PRIVATE);
        editor = sharedPreferences.edit();






        btnMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relMahasiswa.setVisibility(View.VISIBLE);
                relAdmin.setVisibility(View.GONE);
                nim.requestFocus();
            }
        });
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relMahasiswa.setVisibility(View.GONE);
                relAdmin.setVisibility(View.VISIBLE);
                userAdmin.requestFocus();
            }
        });
        btnLoginMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sNim = nim.getText().toString();
                String sPass = pass.getText().toString();
                loginSave();
                CheckLogin(sNim, sPass);
            }
        });
        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sUserAdmin = userAdmin.getText().toString();
                String sPassAdmin = passAdmin.getText().toString();
                loginSave();
                CheckLogin2(sUserAdmin, sPassAdmin);
            }
        });

        saveLogin = sharedPreferences.getBoolean("saveLogin", true);
        if(saveLogin==true){
            nim.setText(sharedPreferences.getString("nim",null));
            pass.setText(sharedPreferences.getString("pass",null));
            userAdmin.setText(sharedPreferences.getString("userAdmin",null));
            passAdmin.setText(sharedPreferences.getString("passAdmin",null));
        } else {
            Toast.makeText(Login.this, "Can't Save Login", Toast.LENGTH_SHORT).show();
        }
    }

    public void loginSave(){
        sNim1 = nim.getText().toString();
        sPass1 = pass.getText().toString();
        sUserAdmin1 = userAdmin.getText().toString();
        sPassAdmin1 = passAdmin.getText().toString();

        if(cbMahasiswa.isChecked()){
            editor.putBoolean("saveLogin",true);
            editor.putString("nim",sNim1);
            editor.putString("pass",sPass1);
            editor.commit();
        } else if(cbAdmin.isChecked()){
            editor.putBoolean("saveLogin",true);
            editor.putString("userAdmin",sUserAdmin1);
            editor.putString("passAdmin",sPassAdmin1);
            editor.commit();
        } else {
            Toast.makeText(Login.this, "Can't Save Login", Toast.LENGTH_SHORT).show();
        }
    }

    public void CheckLogin(final String nim, final String pass){
        if(checkNetworkConnection()){
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_MAHASISWA_LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if(resp.equals("[{\"status\":\"OK\"}]")) {
                                    Toast.makeText(getApplicationContext(), "Login Berhasil!", Toast.LENGTH_LONG).show();
                                    Intent dashboard = new Intent(Login.this, MainActivity.class);
                                    startActivity(dashboard);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Login Gagal!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Server Bermasalah!", Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("nim", nim);
                    params.put("pass", pass);
                    return params;
                }
            };

            VolleyConnection.getInstance(Login.this).addToRequestQueue(stringRequest);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
                }
            }, 2000);
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet!", Toast.LENGTH_SHORT).show();
        }
    }
    public void CheckLogin2(final String userAdmin, final String passAdmin){
        if(checkNetworkConnection()){
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_ADMIN_LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if(resp.equals("[{\"status\":\"OK\"}]")) {
                                    Toast.makeText(getApplicationContext(), "Login Berhasil!", Toast.LENGTH_LONG).show();
                                    Intent dash2 = new Intent(Login.this, MainActivity2.class);
                                    startActivity(dash2);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Login Gagal!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Server Bermasalah!", Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("user_admin", userAdmin);
                    params.put("pass_admin", passAdmin);
                    return params;
                }
            };

            VolleyConnection.getInstance(Login.this).addToRequestQueue(stringRequest);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
                }
            }, 2000);
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet!", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}