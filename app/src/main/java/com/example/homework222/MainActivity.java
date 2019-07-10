package com.example.homework222;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SharedPreferences mySharedPref;
    private TextView txt_main_info;
    private TextView txt_name;

    //объявим переменные
    private static String INFO_SUBSCRIBE = "info_subscribe";
    private static String INFO_SETTINGS = "info_settings";
    private static String INFO_GALLERY = "info_gallery";
    private static String INFO_PAY = "info_pay";
    private static String INFO_CITY = "info_city";
    private static String INFO_TASK = "info_task";
    private static String SETTING_NAME = "setting_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        initPref();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;
        switch(id){
            case R.id.action_settings:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                break;
                case R.id.action_open_subscribe:
                intent = new Intent(MainActivity.this, SubscribeActivity.class);
                break;
            case R.id.action_open_gallery:
                intent = new Intent(MainActivity.this, GalleryActivity.class);
                break;
            case R.id.action_open_pay:
                intent = new Intent(MainActivity.this, PayActivity.class);
                break;
            case R.id.action_open_city:
                intent = new Intent(MainActivity.this, CityActivity.class);
                break;
            case R.id.action_open_task:
                intent = new Intent(MainActivity.this, TaskActivity.class);
                break;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }


    //создадим переменную, куда будем заносить значения выполненных задач
    public void initPref(){
        mySharedPref = getSharedPreferences("settings", MODE_PRIVATE);
        txt_main_info=findViewById(R.id.txt_main_info);
        txt_name=findViewById(R.id.txt_name);
        String result="";

        //настройки
        String txtSettings = mySharedPref.getString(SETTING_NAME, "");
        if(txtSettings.equals("")){
            result+= getString(R.string.txt_settings_noresult);
        }
        result+="\n\n";


        //подписка
        String txtSubscribe = mySharedPref.getString(INFO_SUBSCRIBE, "");
        if(txtSubscribe.equals("")){
            result+= getString(R.string.txt_subscr_noresult);
        }else{
            result+= txtSubscribe;
        }
        result+="\n\n";
        //галерея
        String txtGallery = mySharedPref.getString(INFO_GALLERY, "");
        if(txtGallery.equals("")){
            result+=getString(R.string.txt_gallery_noresult);
        }else{
            result+=getString(R.string.txt_gallery_result);
        }
        result+="\n\n";

        //оплата
        String txtPay = mySharedPref.getString(INFO_PAY, "");
        if(txtPay.equals("")){
            result+= getString(R.string.txt_pay_noresult);
        }else{
            result+= txtPay;
        }
        result+="\n\n";

        //города-страны
        String txtCity = mySharedPref.getString(INFO_CITY, "");
        if(txtCity.equals("")){
            result+= getString(R.string.txt_city_noresult);
        }else{
            result+= txtCity;
        }
        result+="\n\n";

        //календарь задач
        String txtTask = mySharedPref.getString(INFO_TASK, "");
        if(txtTask.equals("")){
            result+= getString(R.string.txt_task_noresult);
        }else{
            result+= txtTask;
        }
        result+="\n\n";

        txt_main_info.setText(result);

        String txtname = mySharedPref.getString(SETTING_NAME, "");
        if(txtname.equals("")){
            txt_name.setVisibility(View.GONE);
        }else{
            String main_intro=String.format(getString(R.string.txt_hello),txtname);
            txt_name.setText(main_intro);
        }

    }
}
