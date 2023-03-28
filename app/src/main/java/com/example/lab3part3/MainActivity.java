package com.example.lab3part3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_name;
    EditText et_pass;
    Button btn_register;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_username);
        et_pass = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.bt_login);
        btn_register = findViewById(R.id.bt_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel model = new UserModel(et_name.getText().toString(), et_pass.getText().toString());
                DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
                boolean success = helper.register(model);
                Toast.makeText(MainActivity.this, "Success: "+success, Toast.LENGTH_SHORT).show();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
                boolean access = helper.check(et_name.getText().toString(), et_pass.getText().toString());
                if (access){
                    Toast.makeText(MainActivity.this, "Access: Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Access: Denied", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}