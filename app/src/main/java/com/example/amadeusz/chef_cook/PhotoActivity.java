package com.example.amadeusz.chef_cook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class PhotoActivity extends Activity {

    private double lastX;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        final ViewFlipper viewFlipper = (ViewFlipper)findViewById(R.id.big_photo_flipper);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        ArrayList<View> images = new ArrayList<>();
        final ArrayList<Integer> biggers = intent.getIntegerArrayListExtra(("biggers"));
        Toast.makeText(getApplicationContext(), (position + 1) + " / " + biggers.size(), Toast.LENGTH_SHORT).show();
        int left = 1;
        int right = biggers.size() - 1;
        while(left < right) {
            if(left == position) {
                position = right;
            }
            else if(right == position) {
                position = left;
            }
            int tmp = biggers.get(left);
            biggers.set(left, biggers.get(right));
            biggers.set(right, tmp);
            left++;
            right--;
        }
        for(int i = 0; i < biggers.size(); i++) {
            images.add(new ImageView(getApplicationContext()));
            ((ImageView) images.get(images.size() - 1)).setImageResource(biggers.get(i));
            viewFlipper.addView(images.get(images.size()- 1));
        }
        viewFlipper.setDisplayedChild(position);
        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent touchevent) {
                switch (touchevent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = touchevent.getX();
                        return true;
                    case MotionEvent.ACTION_UP:
                        float currentX = touchevent.getX();
                        // Handling left to right screen swap.
                        if (lastX < currentX) {
                            // If there aren't any other children, just break.
                            if (viewFlipper.getDisplayedChild() == 0)
                                break;
                            // Next screen comes in from left.
                            viewFlipper.setInAnimation(getApplication(), R.anim.slide_in_from_left);

                            // Current screen goes out from right.
                            viewFlipper.setOutAnimation(getApplication(), R.anim.slide_out_to_right);

                            // Display next screen.
                            viewFlipper.showNext();
                        }
                        // Handling right to left screen swap.
                        if (lastX > currentX) {
                            // If there is a child (to the left), kust break.
                            if (viewFlipper.getDisplayedChild() == 1)
                                break;
                            // Next screen comes in from right.
                            viewFlipper.setInAnimation(getApplication(), R.anim.slide_in_from_right);
                            // Current screen goes out from left.
                            viewFlipper.setOutAnimation(getApplication(), R.anim.slide_out_to_left);
                            // Display previous screen.
                            viewFlipper.showPrevious();
                        }
                        viewFlipper.getOutAnimation().setAnimationListener(new Animation.AnimationListener() {
                            public void onAnimationStart(Animation animation) {
                            }

                            public void onAnimationRepeat(Animation animation) {
                            }

                            public void onAnimationEnd(Animation animation) {
                                if(viewFlipper.getDisplayedChild() == 0) {
                                    position = 0;
                                }
                                else {
                                    for(int i = 1; i < biggers.size(); i++) {
                                        if(i == viewFlipper.getDisplayedChild()) {
                                            position = biggers.size() - i;
                                        }
                                    }
                                }
                                Toast.makeText(getApplicationContext(), (position + 1) + " / " + biggers.size(), Toast.LENGTH_SHORT).show();
                            }
                        });
                }
                return true;
            }
        });

    }

}
