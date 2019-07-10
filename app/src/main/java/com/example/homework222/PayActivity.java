package com.example.homework222;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PayActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SharedPreferences mySharedPref;
    private static String INFO_PAY = "info_pay";
    private Button btn_add;
    private EditText inputMoney;
    private EditText inputInfo;
    private CheckBox bankCardChkBx;
    private CheckBox mobilePhoneChkBx;
    private CheckBox cashAddressChkBx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        //инициируем все поля
        initView();

        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    switch(compoundButton.getId()) {
                        case R.id.bankCardChkBx:
                            resetCheckBoxes();
                            bankCardChkBx.setChecked(true);
                            inputInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
                            break;
                        case R.id.mobilePhoneChkBx:
                            resetCheckBoxes();
                            mobilePhoneChkBx.setChecked(true);
                            inputInfo.setInputType(InputType.TYPE_CLASS_PHONE);
                            break;
                        case R.id.cashAddressChkBx:
                            resetCheckBoxes();
                            cashAddressChkBx.setChecked(true);
                            inputInfo.setInputType(InputType.TYPE_CLASS_TEXT);
                            break;
                    }
                }
            }
        };

        bankCardChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mobilePhoneChkBx.setOnCheckedChangeListener(checkedChangeListener);
        cashAddressChkBx.setOnCheckedChangeListener(checkedChangeListener);

        //обрабатываем нажатие кнопки
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value= String.valueOf(inputMoney.getText());
                String info = String.valueOf(inputInfo.getText());
                if(!value.equals("") && !info.equals("")) {
                    String result = String.format(getString(R.string.txt_pay_result), value, info);
                    Toast.makeText(PayActivity.this, result, Toast.LENGTH_LONG).show();
                    initPref(result);
                }
            }
        });

        //подключаем Toolbar
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initView(){
        btn_add = findViewById(R.id.btn_add);
        inputMoney = findViewById(R.id.inputMoney);
        inputInfo = findViewById(R.id.inputInfo);
        bankCardChkBx = findViewById(R.id.bankCardChkBx);
        mobilePhoneChkBx = findViewById(R.id.mobilePhoneChkBx);
        cashAddressChkBx = findViewById(R.id.cashAddressChkBx);
    }
    private void resetCheckBoxes(){
        bankCardChkBx.setChecked(false);
        mobilePhoneChkBx.setChecked(false);
        cashAddressChkBx.setChecked(false);
    }
    private void initPref(String result){
        mySharedPref = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putString(INFO_PAY, result);
        editor.apply();
    }
}
