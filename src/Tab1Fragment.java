package com.project.projectapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Tab1Fragment extends Fragment {

    String lang;
    private TextView txt, title, l;
    private ImageView pic;

    public Tab1Fragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //lang = this.getActivity().getIntent().getExtras().getString("lang");
        lang = getArguments().getString("lang");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        //lang = this.getActivity().getIntent().getExtras().getString("lang");

        //FragmentManager fragmentManager = getActivity().getSupportFragmentManager().beginTransaction().add(R.layout.fragment_one, container).commit();
        title = (TextView)view.findViewById(R.id.title);
        txt = (TextView)view.findViewById(R.id.text);
        pic = (ImageView)view.findViewById(R.id.contentView);

        pic.setImageResource(R.drawable.desc1);

        if(lang.equals("en")){
            txt.setText(R.string.desc1);
            title.setText(R.string.title_howtoplay);
        }
        else if(lang.equals("mk")){
            txt.setText("Играчи, изберете од различни категории за играње на избраниот јазик.");
            title.setText("Како Да Играш?");
        }

        return view;
    }
}
