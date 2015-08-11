package com.example.jyoti.transition;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class Activity2 extends Activity {
    private ImageView imageView;
    public static final String EXTRA_ALBUM_ART_RESID = "EXTRA_ALBUM_ART_RESID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            //To enable window content transitions in your code instead, call the Window.requestFeature() method:
            getWindow().requestFeature(android.view.Window.FEATURE_CONTENT_TRANSITIONS);
            //Transition ts_enter = new Slide();  //Slide(); //Explode();
            Transition ts_exit = new Explode();  //Slide(); //Explode();

            //ts_enter.setDuration(2000);
            ts_exit.setDuration(2000);

            //getWindow().setEnterTransition(ts_enter);
            getWindow().setExitTransition(ts_exit);
        }
       setContentView(R.layout.activity_activity2);
        Intent intent=getIntent();
        String name=intent.getStringExtra("pos");
        int imageid=intent.getIntExtra(EXTRA_ALBUM_ART_RESID ,0);
        imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setTransitionName(name);  //set transition name
        imageView.setImageResource(imageid); //set image resource
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        //imageView.setDisplayType(DisplayType.FIT_IF_BIGGER);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
