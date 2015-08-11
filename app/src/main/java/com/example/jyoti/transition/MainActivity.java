package com.example.jyoti.transition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jyoti.transition.adapter.CustomList;


public class MainActivity extends Activity {
    private ImageView imageView,imageView2;
    ListView list;
    String[] web = {
            "Facebook",
            "Gmail",
            "Twitter",
            "Youtube",
    } ;
    Integer[] imageId = {
            R.drawable.fb,
            R.drawable.gmail,
            R.drawable.twitter,
            R.drawable.youtube,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            //To enable window content transitions in your code instead, call the Window.requestFeature() method:
            MainActivity.this.getWindow().requestFeature(android.view.Window.FEATURE_CONTENT_TRANSITIONS);
            //Transition ts_enter = new Slide();  //Slide(); //Explode();
            Transition ts_exit = new Explode();

            //ts_enter.setDuration(2000);
            ts_exit.setDuration(2000);
        /*
        If you have set an enter transition for the second activity,
        the transition is also activated when the activity starts.
        */
            //getWindow().setEnterTransition(ts_enter);
            getWindow().setExitTransition(ts_exit);
        }
       setContentView(R.layout.activity_main_listview);
        //setContentView(R.layout.shared_layout);
        CustomList adapter = new
                CustomList(MainActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
               imageView=(ImageView)view.findViewById(R.id.img);
                int clickedimageid=imageId[position];
                imageView.setTransitionName("position");
                if (Build.VERSION.SDK_INT >= 21) {
                    Intent intent = new Intent(MainActivity.this, Activity2.class);
                    intent.putExtra("pos", "position");
                    intent.putExtra(Activity2.EXTRA_ALBUM_ART_RESID,clickedimageid);
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                                    imageView,   // The view which starts the transition
                                    "position"    // The transitionName of the view we’re transitioning to
                            );
                    startActivity( intent, options.toBundle());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
