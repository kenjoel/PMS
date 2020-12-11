package com.moringaschool.pms.IntroScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.moringaschool.pms.MainActivity;
import com.moringaschool.pms.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class infoActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.textView3) TextView mNextBtn;
    @BindView(R.id.textView4) TextView mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

        mNextBtn.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mNextBtn) {
            Intent intent = new Intent(infoActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if (v == mBackBtn) {
            Intent intent = new Intent(infoActivity.this, IntroActivity.class);
            startActivity(intent);
        }
    }
}