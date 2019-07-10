package com.example.homework222;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CityActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SharedPreferences mySharedPref;
    private static String INFO_CITY = "info_city";
    Spinner countriesSpinner;
    Spinner citiesSpinner;
    Spinner houseNumberSpinner;
    Button show_Address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        //инициируем все поля
        initView();
        //подключаем Toolbar
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CityActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        show_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result=String.format(getString(R.string.txt_city_result),countriesSpinner.getSelectedItem().toString(),citiesSpinner.getSelectedItem().toString(),houseNumberSpinner.getSelectedItem().toString());
                Toast.makeText(CityActivity.this, result, Toast.LENGTH_LONG).show();
                initPref(result);
            }
        });
    }
    private void initView(){
        toolbar = findViewById(R.id.my_toolbar);
        countriesSpinner=findViewById(R.id.countriesSpinner);
        citiesSpinner = findViewById(R.id.citiesSpinner);
        houseNumberSpinner = findViewById(R.id.houseNumberSpinner);
        show_Address = findViewById(R.id.show_Address);
        initSpinnerCountries();
        initHouseNumbersSpinner();
    }
    private void initSpinnerCountries(){
        String[] countries = getResources().getStringArray(R.array.countries);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countriesSpinner.setAdapter(adapter);

        //при изменении spinner получать текущее значение
        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] countries = getResources().getStringArray(R.array.countries);
                initSpinnerCities(countries[i]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void initHouseNumbersSpinner(){
        Integer[] numbers = new Integer[50];
        for (int i=1; i<numbers.length; i++){
            numbers[i-1]=i;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, numbers);
        houseNumberSpinner.setAdapter(adapter);
    }

    private void initSpinnerCities(String country){
        ArrayAdapter<CharSequence> adapter = null;
        switch (country){
            case "Россия":
                adapter= ArrayAdapter.createFromResource(this, R.array.r_cities, android.R.layout.simple_spinner_item);
                break;
            case "Украина":
                adapter= ArrayAdapter.createFromResource(this, R.array.u_cities, android.R.layout.simple_spinner_item);
                break;
            case "Белоруссия":
                adapter= ArrayAdapter.createFromResource(this, R.array.b_cities, android.R.layout.simple_spinner_item);
                break;
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citiesSpinner.setAdapter(adapter);
    }
    //создадим переменную, куда будем заносить значения выполненных задач
    public void initPref(String result){
        mySharedPref = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putString(INFO_CITY, result);
        editor.apply();
    }
}
