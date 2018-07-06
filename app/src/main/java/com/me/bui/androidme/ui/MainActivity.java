package com.me.bui.androidme.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.me.bui.androidme.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex;
    private int bodyIndex;
    private int legIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Position clicked : " + position, Toast.LENGTH_LONG).show();

        int bodyPartNumber = position/12;
        int listIndex = position - 12*bodyPartNumber;
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
