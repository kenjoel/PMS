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

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.textView1) TextView mNextBtn;
    @BindView(R.id.textView2) TextView mSkipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        mNextBtn.setOnClickListener(this);
        mSkipBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mNextBtn) {
            Intent intent = new Intent(IntroActivity.this, infoActivity.class);
            startActivity(intent);
        }

        if (view == mSkipBtn) {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}