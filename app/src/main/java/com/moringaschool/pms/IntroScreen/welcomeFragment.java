package com.moringaschool.pms.IntroScreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.moringaschool.pms.HomePageActivity;
import com.moringaschool.pms.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link welcomeFragment} factory method to
 * create an instance of this fragment.
 */
public class welcomeFragment extends Fragment {


    // TODO: Rename and change types of parameters
    private Button mViewContent;

    public welcomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
//    public static welcomeFragment newInstance(String param1, String param2) {
//        welcomeFragment fragment = new welcomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @BindView(R.id.viewContent) Button mButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        ButterKnife.bind(this, view);
        mViewContent = mButton;
        // Inflate the layout for this fragment
        mViewContent.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "Lonely" ;

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomePageActivity.class);
                Log.i(TAG, "onClick: Well we are clicking but");
                startActivity(intent);
            }
        });
        return view;
    }
}