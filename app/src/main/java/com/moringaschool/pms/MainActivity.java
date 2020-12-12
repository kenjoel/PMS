package com.moringaschool.pms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.moringaschool.pms.IntroScreen.HomePageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.viewContent) Button mViewContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mViewContent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mViewContent) {
            Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
            startActivity(intent);
        }
    }

}