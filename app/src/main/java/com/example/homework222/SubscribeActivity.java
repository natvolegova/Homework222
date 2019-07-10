package com.example.homework222;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SubscribeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SharedPreferences mySharedPref;
    private static String INFO_SUBSCRIBE = "info_subscribe";
    private static String SUBSCRIBE_NAME = "subscribe_name";
    private static String SUBSCRIBE_EMAIL = "subscribe_email";

    private EditText subscribe_name;
    private EditText subscribe_email;
    private Button btn_ok;
    private Button btn_reset;
    private TextView txt_subsc_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        //инициируем все поля
        initView();
        //подключаем Toolbar
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(SubscribeActivity.this, MainActivity.class);
               startActivity(intent);
           }
        });

        //обрабатываем нажатия кнопок
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= String.valueOf(subscribe_name.getText());
                String email = String.valueOf(subscribe_email.getText());
                String result = String.format(getString(R.string.txt_subscr_result),name,email);
                if(!name.equals("") && !email.equals("")){
                    txt_subsc_result.setText(result);
                    initPref(result, name, email);
                }
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_subsc_result.setText("");
                subscribe_name.setText("");
                subscribe_email.setText("");
                initPref("","","");
            }
        });

    }
    private void initView(){
        toolbar = findViewById(R.id.my_toolbar);
        subscribe_name = findViewById(R.id.subscribe_name);
        subscribe_email = findViewById(R.id.subscribe_email);
        btn_ok = findViewById(R.id.btn_ok);
        btn_reset = findViewById(R.id.btn_reset);
        txt_subsc_result = findViewById(R.id.txt_subsc_result);

        //получаем сохраненные настройки для рассылки
        mySharedPref = getSharedPreferences("settings", MODE_PRIVATE);
        String name = mySharedPref.getString(SUBSCRIBE_NAME, "");
        String email = mySharedPref.getString(SUBSCRIBE_EMAIL, "");
        subscribe_name.setText(name);
        subscribe_email.setText(email);
    }
    //создадим переменную, куда будем заносить значения выполненных задач
    public void initPref(String result, String name, String email){
        mySharedPref = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putString(INFO_SUBSCRIBE, result);
        editor.putString(SUBSCRIBE_NAME, name);
        editor.putString(SUBSCRIBE_EMAIL, email);
        editor.apply();
    }
}
