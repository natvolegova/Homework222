package com.example.homework222;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.GregorianCalendar;

public class TaskActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SharedPreferences mySharedPref;
    private static String INFO_TASK = "info_task";
    private Button chooseStartDate;
    private Button chooseEndDate;
    private Button btnOK;
    private CalendarView startDateCalendar;
    private CalendarView endDateCalendar;
    private long startDate;
    private long endDate;
    private String startDateText;
    private String endDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        //инициируем все поля
        initView();
        //подключаем Toolbar
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //обрабатываем события показа календаря
        chooseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endDateCalendar.setVisibility(View.GONE);
                startDateCalendar.setVisibility(View.VISIBLE);
            }
        });
        chooseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDateCalendar.setVisibility(View.GONE);
                endDateCalendar.setVisibility(View.VISIBLE);
            }
        });
        //обрабатываем события выбора дат
        startDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                startDateText = i2+"."+i1 +"."+ i;
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.set(i,i1,i2);
                startDate = calendar.getTimeInMillis();
                chooseStartDate.setText(getString(R.string.btn_start)+"\n"+startDateText);
                startDateCalendar.setVisibility(View.GONE);
            }
        });
        endDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                endDateText = i2+"."+i1 +"."+ i;
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.set(i,i1,i2);
                endDate = calendar.getTimeInMillis();
                chooseEndDate.setText(getString(R.string.btn_end)+"\n"+endDateText);
                endDateCalendar.setVisibility(View.GONE);
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result="";
                if(startDate<endDate){
                    result=String.format(getString(R.string.txt_task_result),startDateText,endDateText );
                    initPref(result);
                }else{
                    result=getString(R.string.msg_error_task);
                }
                chooseStartDate.setText(getString(R.string.btn_start));
                chooseEndDate.setText(getString(R.string.btn_end));
                Toast.makeText(TaskActivity.this,result,Toast.LENGTH_LONG).show();
            }
        });
    }
    private void initView(){
        toolbar = findViewById(R.id.my_toolbar);
        chooseStartDate = findViewById(R.id.chooseStartDate);
        chooseEndDate = findViewById(R.id.chooseEndDate);
        btnOK = findViewById(R.id.btnOK);
        startDateCalendar = findViewById(R.id.startDateCalendar);
        endDateCalendar = findViewById(R.id.endDateCalendar);
        //скрываем поля
        startDateCalendar.setVisibility(View.GONE);
        endDateCalendar.setVisibility(View.GONE);
    }
    //создадим переменную, куда будем заносить значения выполненных задач
    public void initPref(String result){
        mySharedPref = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putString(INFO_TASK, result);
        editor.apply();
    }
}
