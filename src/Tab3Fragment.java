package com.project.projectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Tab3Fragment extends Fragment {

    String lang;
    private TextView txt, title, l;
    private ImageView pic;
    private Button cont;

    public Tab3Fragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //lang = this.getActivity().getIntent().getExtras().getString("lang");
        lang = getArguments().getString("lang");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_three, container, false);

        lang = this.getActivity().getIntent().getExtras().getString("lang");

        //FragmentManager fragmentManager = getActivity().getSupportFragmentManager().beginTransaction().add(R.layout.fragment_one, container).commit();
        title = (TextView)view.findViewById(R.id.title3);
        txt = (TextView)view.findViewById(R.id.text3);
        pic = (ImageView)view.findViewById(R.id.desc3);
        pic.setImageResource(R.drawable.desc3);
        cont = (Button)view.findViewById(R.id.cont);

        if(lang.equals("en")){
            txt.setText(R.string.desc3);
            title.setText(R.string.title_howtoplay);
            cont.setText("Continue");
        }
        else if(lang.equals("mk")){
            txt.setText("Играчите цртаат нова картичка со навалување на телефонот нагоре или надолу. Кога ќе заврши времето на игра, играчите можат да ги видат резултатите на екранот.");
            title.setText("Како Да Играш?");
            cont.setText("Продолжи");
        }

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(contIntent);

            }
        });

        return view;
    }
}