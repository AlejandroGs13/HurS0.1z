package com.example.alejandrogs.hurs01z;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.hurs.alejandrogs.hurs01z.BDHelper;
import com.hurs.alejandrogs.hurs01z.LocationFragment;
import com.hurs.alejandrogs.hurs01z.MainFragment;
import com.hurs.alejandrogs.hurs01z.PhotoFragment;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        TabLayout tabs;
        BDHelper db;
        static final int REQUEST_IMAGE_CAPTURE = 1;
        private String name = "";
        private FloatingActionButton fab;
        private int fabInt = 0;
        android.app.FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = Environment.getExternalStorageDirectory() + "/test.jpg";
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db= new BDHelper(this);
        /*
            Tabs
         */
        /*
        tabs = (TabLayout)findViewById(R.id.Tab);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.addTab(tabs.newTab().setText("Tad 1"));
        tabs.addTab(tabs.newTab().setText("Tad 2"));
        tabs.addTab(tabs.newTab().setText("Tad 3"));
        tabs.addTab(tabs.newTab().setText("Tad 4"));
        */
        /*
        Igualamos el floatingbutton y creramos su onclicklistener
         */

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (fabInt) {
                    case 0:
                        notes(view);
                        break;
                    case 1:
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,0);
                        break;
                    case 2:
                        snackbarMessage(view, "Esto es una prueba 2");
                        break;
                    case 3:
                        snackbarMessage(view, "Esto es una prueba 3");
                        break;
                    case 4:
                        snackbarMessage(view, "Esto es una prueba 4");
                        break;
                    case 7:

                        break;
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*
         * Actualizamos el fragment del content_main
         */
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.context_frame, new MainFragment()).commit();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
      //  android.app.FragmentManager fragmentManager;
        if (id == R.id.nav_note) {
            fabInt = 0;
            fab.setImageResource(R.drawable.ic_add_black_24dp);
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.context_frame, new MainFragment()).commit();
        } else if (id == R.id.nav_Photos) {
            fabInt = 1;
            fab.setImageResource(R.drawable.ic_menu_camera);
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.context_frame, new PhotoFragment()).commit();
        } else if (id == R.id.nav_videos) {
            fabInt = 2;
            fab.setImageResource(R.drawable.ic_video_call_black_24dp);
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.context_frame, new LocationFragment()).commit();
        } else if (id == R.id.nav_audios) {
            fabInt = 3;
            fab.setImageResource(R.drawable.ic_mic_none_black_24dp);
        } else if (id == R.id.nav_maps) {
            fabInt = 4;
            fab.setImageResource(R.drawable.ic_location_on_black_24dp);

        } else if (id == R.id.nav_share) {
            fabInt = 5;
        } else if (id == R.id.nav_cloud) {
            fabInt = 6;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void snackbarMessage(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    public void notes(View view){
        Intent Nota =new Intent(view.getContext().getApplicationContext(),NoteActivity.class);
        Nota.putExtra("Nota","");
        Nota.putExtra("ID","");
        Nota.putExtra("Edit","0");
        startActivity(Nota);
        finish();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }




}
