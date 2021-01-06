package com.nalazoocare.des3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;

import com.nalazoocare.des3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding b;

    private String tem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_main);

        b.inBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String encodeTx = b.edit.getText().toString();
                try {
                    tem = DES3.encode(encodeTx);

                    b.showtx.setText(tem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        b.outBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.showtx.setText(DES3.decode(tem));
            }
        });
    }
}
