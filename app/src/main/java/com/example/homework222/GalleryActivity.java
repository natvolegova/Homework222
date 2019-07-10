package com.example.homework222;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Random;

public class GalleryActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SharedPreferences mySharedPref;
    private static String INFO_GALLERY = "info_gallery";

    private Button btn_back;
    private Button btn_next;
    private TextView text_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        //инициируем все поля
        initView();
        initPref();
        //подключаем Toolbar
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GalleryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GalleryActivity.this, GalleryActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initView(){
        btn_back = findViewById(R.id.btn_back);
        btn_next = findViewById(R.id.btn_next);
        text_result = findViewById(R.id.text_result);
        Random r = new Random();
        int rand_value = r.nextInt(50);
        String result = "http://myfile.org/"+ Integer.toString(rand_value);
        text_result.setText(result);

    }
    //создадим переменную, куда будем заносить значения выполненных задач

    public void initPref(){
        mySharedPref = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putString(INFO_GALLERY, getString(R.string.txt_gallery_result));
        editor.apply();
    }
}
