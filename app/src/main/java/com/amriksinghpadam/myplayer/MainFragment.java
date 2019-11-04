package com.amriksinghpadam.myplayer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainFragment extends Fragment {
    private LinearLayout layout1,layout2;
    private Context context;
    private int tempCount=0;

    public MainFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        layout1 = view.findViewById(R.id.linearLayout1);
        layout2 = view.findViewById(R.id.linearLayout2);

        layout1.setOnClickListener  (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tempCount==0){
                    Intent intent = new Intent(context , PlayerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("section",1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    tempCount++;
                }
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tempCount==0) {
                    Intent intent = new Intent(context, PlayerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("section", 2);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    tempCount++;
                }
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        tempCount=0;
    }

}
