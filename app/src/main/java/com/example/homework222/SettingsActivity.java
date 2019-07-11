package com.example.homework222;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SharedPreferences mySharedPref;
    private static String SETTING_NAME = "setting_name";
    private static String SETTING_EMAIL = "setting_email";

    private EditText setting_name;
    private EditText setting_email;
    private Button btn_ok;
    private Button btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //инициируем все поля
        initView();
        //обрабатываем нажатия кнопок
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= String.valueOf(setting_name.getText());
                String email = String.valueOf(setting_email.getText());
                if(!name.equals("") && !email.equals("")){
                    Toast.makeText(SettingsActivity.this, getString(R.string.txt_settings_result), Toast.LENGTH_LONG).show();
                    initPref(name, email);
                }
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setting_name.setText("");
                setting_email.setText("");
                initPref("","");
            }
        });
        //подключаем Toolbar
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initView(){
        toolbar = findViewById(R.id.my_toolbar);
        setting_name = findViewById(R.id.setting_name);
        setting_email = findViewById(R.id.setting_email);
        btn_ok = findViewById(R.id.btn_ok);
        btn_reset = findViewById(R.id.btn_reset);

        //получаем сохраненные настройки для рассылки
        mySharedPref = getSharedPreferences("settings", MODE_PRIVATE);
        String name = mySharedPref.getString(SETTING_NAME, "");
        String email = mySharedPref.getString(SETTING_EMAIL, "");
        if(!name.equals("")){
            setting_name.setText(name);
        }
        if(!email.equals("")){
            setting_email.setText(email);
        }
    }
    //создадим переменную, куда будем заносить значения выполненных задач
    public void initPref(String name, String email){
        mySharedPref = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putString(SETTING_NAME, name);
        editor.putString(SETTING_EMAIL, email);
        editor.apply();
    }

}
