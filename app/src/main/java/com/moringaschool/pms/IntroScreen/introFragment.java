package com.moringaschool.pms.IntroScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moringaschool.pms.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link introFragment} factory method to
 * create an instance of this fragment.
 */
public class introFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private TextView backText, nextText;

    public introFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment introFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static introFragment newInstance(String param1, String param2) {
//        introFragment fragment = new introFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @BindView(R.id.textView3)
    TextView mTextView3;
    @BindView(R.id.textView4) TextView mTextView4;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        ButterKnife.bind(this, view);

        nextText = mTextView3;
        backText = mTextView4;
        // Inflate the layout for this fragment
        nextText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(introFragment.this)
                        .navigate(R.id.action_intro_to_welcomeFragment);
            }
        });
        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(introFragment.this)
                        .navigate(R.id.action_intro_to_info);
            }
        });
        return view;
    }
}