package com.project.projectapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class HowToPlayActivity extends FragmentActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_play_screen);

        /*DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));*/

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new TabAdapter(getSupportFragmentManager());

        // Add Fragments to adapter one by one
        adapter.addFragment(new Tab1Fragment(), "FRAG1");
        adapter.addFragment(new Tab2Fragment(), "FRAG2");
        adapter.addFragment(new Tab3Fragment(), "FRAG3");
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager, true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lang = extras.getString("lang");
        }

        Bundle data = new Bundle();
        data.putString("lang", lang);
        for(int i=0; i<3; i++){
            adapter.getItem(i).setArguments(data);
        }

        /*final Tab1Fragment first_fragment = new Tab1Fragment();
        data.putString("lang", lang);
        first_fragment.setArguments(data);
        final Tab2Fragment second_fragment = new Tab2Fragment();
        data.putString("lang", lang);
        second_fragment.setArguments(data);
        final Tab3Fragment third_fragment = new Tab3Fragment();
        data.putString("lang", lang);
        third_fragment.setArguments(data);*/

        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        /*adapter.addFragment(first_fragment, "Tab 1");
        adapter.addFragment(second_fragment, "Tab 2");
        adapter.addFragment(second_fragment, "Tab 3");

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

        /*adapter = new TabAdapter(getSupportFragmentManager()){
            @Override
            public Fragment getItem(int position){
                switch (position){
                    case 0 : return new Tab1Fragment();
                    case 1 : return new Tab2Fragment();
                    case 2 : return new Tab3Fragment();
                    default: return null;
                }
            }
            @Override
            public int getCount(){
                return 3;
            }
            @Override
            public void addFragment(Fragment fragment, String title) {
                title = title;
            }
        };*/

        //adapter.addFragment(new Tab1Fragment(), "Tab 1");
        //adapter.addFragment(new Tab2Fragment(), "Tab 2");

        /*if(viewPager.getCurrentItem() == 2){
            //SharedPreferences preferences = getSharedPreferences("launch", MODE_PRIVATE);
            //preferences.edit().putBoolean("activity_executed",true).apply();
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
            finish();
        }*/
    }
}
