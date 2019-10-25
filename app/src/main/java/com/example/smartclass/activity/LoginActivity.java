package com.example.smartclass.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartclass.R;
import com.example.smartclass.model.RestObj;
import com.example.smartclass.preference.LoginPref;
import com.example.smartclass.preference.PrefGuru;
import com.example.smartclass.remote.ApiUtils;
import com.example.smartclass.remote.RetrofitClient;
import com.example.smartclass.remote.UserService;
import com.google.gson.JsonObject;

import org.json.JSONObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    Button btnLogin;
    UserService userService;
    LoginPref pref;
    PrefGuru prefGuru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userService = RetrofitClient.getClient().create(UserService.class);
        pref = new LoginPref(this);
        prefGuru = new PrefGuru(this);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if (validateLogin(email, password)) {
                    doLogin(email, password);
                }
            }
        });
    }

    private boolean validateLogin(String email, String password) {
        if (email == null || email.trim().length() == 0) {
            Toast.makeText(this, "Email is Required", Toast.LENGTH_SHORT).show();
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is Required", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private boolean doLogin(final String email, final String password) {
        Call<ResponseBody> call = userService.login(email, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject object = new JSONObject(response.body().string());
                    int status = object.getInt("status");
                    if (status == 200){
                        JSONObject data = object.getJSONObject("data");
                        pref.setIdUser(data.getInt("id"));
                        pref.setName(data.getString("email"));
                        pref.setRole(data.getString("role"));
                        pref.setCreatedAt(data.getString("created_at"));

                        if (pref.getRole().equals("siswa")){

                            Intent intent = new Intent(LoginActivity.this, SiswaActivity.class);
                            startActivity(intent);

                        }else if (pref.getRole().equals("guru")){

                            Intent intent = new Intent(LoginActivity.this, GuruActivity.class);
                            startActivity(intent);
                        }

                    }else {
                        //pwnya salah

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            //cek koneksi
            }
        });

        return false;
    }
}
