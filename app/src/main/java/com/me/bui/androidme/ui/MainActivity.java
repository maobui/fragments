package com.me.bui.androidme.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.me.bui.androidme.R;
import com.me.bui.androidme.data.ImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true;

            Button nextButton = findViewById(R.id.next_btn);
            nextButton.setVisibility(View.GONE);

            GridView gridView = findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            if (savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Head
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setImageIds(ImageAssets.getHeads());

                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                // Body
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(ImageAssets.getBodies());

                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                // Leg
                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds(ImageAssets.getLegs());

                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Position clicked : " + position, Toast.LENGTH_LONG).show();

        int bodyPartNumber = position/12;
        int listIndex = position - 12*bodyPartNumber;

        if(mTwoPane) {
            BodyPartFragment fragment = new BodyPartFragment();
            switch (bodyPartNumber) {
                case 0:
                    fragment.setImageIds(ImageAssets.getHeads());
                    fragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, fragment)
                            .commit();
                    break;
                case 1:
                    fragment.setImageIds(ImageAssets.getBodies());
                    fragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, fragment)
                            .commit();
                    break;
                case 2:
                    fragment.setImageIds(ImageAssets.getLegs());
                    fragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, fragment)
                            .commit();
                    break;
            }
        }

        switch (bodyPartNumber) {
            case 0 :
                headIndex = listIndex;
                break;
            case 1 :
                bodyIndex = listIndex;
                break;
            case 2 :
                legIndex = listIndex;
                break;
            default: break;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("headIndex", headIndex);
        bundle.putInt("bodyIndex", bodyIndex);
        bundle.putInt("legIndex", legIndex);

        final Intent intent = new Intent(this, AndroiMeActivity.class);
        intent.putExtras(bundle);

        Button nextButton = findViewById(R.id.next_btn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
