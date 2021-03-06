package com.me.bui.androidme.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.me.bui.androidme.R;
import com.me.bui.androidme.data.ImageAssets;

public class AndroiMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            // Head
            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setImageIds(ImageAssets.getHeads());
            headFragment.setListIndex(getIntent().getIntExtra("headIndex" , 0));

            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();

            // Body
            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(ImageAssets.getBodies());
            bodyFragment.setListIndex(getIntent().getIntExtra("bodyIndex" , 0));

            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            // Leg
            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(ImageAssets.getLegs());
            legFragment.setListIndex(getIntent().getIntExtra("legIndex" , 0));

            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();
        }
    }
}
