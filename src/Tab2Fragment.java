package com.project.projectapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Tab2Fragment extends Fragment {

    String lang;
    private TextView txt, title, l;
    private ImageView pic;

    public Tab2Fragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //lang = this.getActivity().getIntent().getExtras().getString("lang");
        lang = getArguments().getString("lang");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_two, container, false);

        //lang = this.getActivity().getIntent().getExtras().getString("lang");

        //FragmentManager fragmentManager = getActivity().getSupportFragmentManager().beginTransaction().add(R.layout.fragment_one, container).commit();
        title = (TextView)view.findViewById(R.id.title2);
        txt = (TextView)view.findViewById(R.id.text2);
        pic = (ImageView)view.findViewById(R.id.contentView2);

        pic.setImageResource(R.drawable.desc2);

        if(lang.equals("en")){
            txt.setText(R.string.desc2);
            title.setText(R.string.title_howtoplay);
        }
        else if(lang.equals("mk")){
            txt.setText("По изборот на категоријата, еден играч го донесува уредот до челото и се обидува да го претпостави случајниот збор прикажан од избраната категорија со некои индиции дадени од другиот играч.");
            title.setText("Како Да Играш?");
        }

        return view;
    }
}
