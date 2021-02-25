package com.moringaschool.pms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.pms.Authentication.LoginActivity;
import com.moringaschool.pms.IntroScreen.HostActivity;
import com.moringaschool.pms.Services.PMSApi;
import com.moringaschool.pms.Services.PMSClient;
import com.moringaschool.pms.adapter.ArticleAdapter;
import com.moringaschool.pms.model.ApiReturn;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageActivity extends AppCompatActivity {
    private static String TAG = "This is in HomePageActivity";
    private FirebaseAuth.AuthStateListener mAuthListener;

    @BindView(R.id.recyclerView) RecyclerView pmsViews;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.errorTextView) TextView errorTextView;

    private List<ApiReturn> articles;
    private ArticleAdapter pmsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ButterKnife.bind(this);


        setupFirebaseAuth();
        //This methods will be used later on when we need to reference the current user

//        setUserDetails();
//        getUserDetails();

        //Initialize and Assign Variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        return true;

                    case R.id.loans:
                        startActivity(new Intent(getApplicationContext(), loans.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(),user.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });

        //Receiving Response and callback method

        PMSApi receivedClient = PMSClient.getClient();
        Call<List<ApiReturn>> call = receivedClient.getArticles();

        call.enqueue(new Callback<List<ApiReturn>>() {
            @Override
            public void onResponse(Call<List<ApiReturn>> call, Response<List<ApiReturn>> response) {
                Log.i(TAG, "onResponse: response received successfully");

                hideProgressBar();
                if(response.isSuccessful()){
                    articles = response.body();
                    pmsAdapter =    new ArticleAdapter(HomePageActivity.this, articles);
                    pmsViews.setAdapter(pmsAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomePageActivity.this);

                    pmsViews.setLayoutManager(layoutManager);
                    pmsViews.setHasFixedSize(true);

                    showArticles();

                }else{
                    showUnsuccessfulMessage();
                }

            }

            @Override
            public void onFailure(Call<List<ApiReturn>> call, Throwable t) {
                Log.e(TAG, "onFailure: receiving response failed", t);
                hideProgressBar();
                showFailureMessage();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.optionsSignout:
                signOut();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkAuthenticationState();
    }

    private void checkAuthenticationState(){
        Log.d(TAG, "checkAuthenticationState: checking authentication state.");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            Log.d(TAG, "checkAuthenticationState: user is null, navigating back to login screen.");

            Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }else{
            Log.d(TAG, "checkAuthenticationState: user is authenticated.");
        }
    }


    private void signOut(){
        Log.d(TAG, "signOut: signing out");
        FirebaseAuth.getInstance().signOut();
    }


//    private void getUserDetails(){
//        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
//        String allUserDetails = "The current User has this as credentials: ";
//
//        String name = mUser.getDisplayName();
//        String email = mUser.getEmail();
//        Uri profilePhoto = mUser.getPhotoUrl();
//        String id = mUser.getUid();
////        String anything = profilePhoto.getHost();
//
//        Log.i(TAG, "getUserDetails: " + allUserDetails + "\n" + name + "\n" +  email + "\n" + profilePhoto + id);
//
//    }



//    private void setUserDetails(){
//        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
//        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
//                .setDisplayName("Ken Joel")
//                .setPhotoUri(Uri.parse("https://ibb.co/zJ2042w"))
//                .build();
//        mUser.updateProfile(userProfileChangeRequest);
//    }


    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: started.");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }

    private void showFailureMessage() {
        errorTextView.setText("Something went wrong.");
        errorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        errorTextView.setText("Something went wrong. Please try again later");
        errorTextView.setVisibility(View.VISIBLE);
    }

    private void showArticles() {
        pmsViews.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

}