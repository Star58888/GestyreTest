package com.star.gestyretest;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1;
    LinearLayout linearLayout1, linearLayout3, linearLayout4;
    TextView textView1;
    GestureOverlayView gestureOverlay;
    GestureLibrary gestureLibrary;
    public void restore()
    {
        setContentView(R.layout.activity_main);
        findViews();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        findGestureLib();
    }


    void findViews() {
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        linearLayout3 = (LinearLayout) findViewById(R.id.linearLayout3);
        linearLayout4 = (LinearLayout) findViewById(R.id.linearLayout4);
        textView1 = (TextView)findViewById(R.id.textView1);
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        gestureOverlay = (GestureOverlayView)findViewById(R.id.gestureOverlay);
        gestureOverlay.addOnGesturePerformedListener(gestureListener);
        linearLayout1.setOnClickListener(helpListener);
    }

    void changeText() {
        int i = (int)(Math.random() * 255.0D);
        int j = (int)(Math.random() * 255.0D);
        int k = (int)(Math.random() * 255.0D);
        System.out.println("r =  " + i);
        textView1.setText("變更文字了");
        textView1.setTextColor(Color.rgb(i , j , k));
        textView1.setTextSize(30.0F);
    }
    void changeImage() {
        ImageView img = imageView1;
        int i = (int)(Math.random() * 17.0D);
        img.setImageResource(new int[]{R.drawable.backup , R.drawable.browser , R.drawable.calculator ,
                R.drawable.contacts , R.drawable.document
        ,R.drawable.map,R.drawable.maps,R.drawable.messages,R.drawable.music,R.drawable.note
        ,R.drawable.note1,R.drawable.phonebook,R.drawable.photos,R.drawable.setting,R.drawable.settings
        ,R.drawable.theme,R.drawable.wireless,}[i]);
        textView1.setText("變更圖片了");
    }
    void findGestureLib() {
        gestureLibrary = GestureLibraries.fromRawResource(this , R.raw.gestures);
        if (!gestureLibrary.load())
        {
            finish();
        }
    }

    GestureOverlayView.OnGesturePerformedListener  gestureListener = new GestureOverlayView.OnGesturePerformedListener() {
        @Override
        public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
            ArrayList<Prediction> list = gestureLibrary.recognize(gesture);
            System.out.println(list);
            if ((list.size() > 0) && ((list.get(0).score > 5.0D))) {
                String name = list.get(0).name;

                    if (!name.equals("toRight")) {

                        do {

                            if (name.equals("toLeft")) {

                                changeImage();
                                return;}


                            break;

                        } while (!list.equals("toRestore"));

                        restore();

                } changeText();

            }
        }

    };
    View.OnClickListener helpListener = new View.OnClickListener() {
        int clickCount;
        @Override
        public void onClick(View view) {
            clickCount += 1;
            if (clickCount == 1)
            {
                linearLayout3.setVisibility(View.VISIBLE);
            }
            if (clickCount == 2)
            {
                linearLayout4.setVisibility(View.VISIBLE);
            }
            if(clickCount ==3)
            {
                linearLayout1.setVisibility(View.GONE);
                clickCount = 0;
            }

        }
    };

}