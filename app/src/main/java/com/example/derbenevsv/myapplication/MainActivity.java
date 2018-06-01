package com.example.derbenevsv.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.derbenevsv.myapplication.api_1c.Api;
import com.example.derbenevsv.myapplication.api_1c.Responses.CheckSessionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AuthorizationFragment.OnLoginListener
{

    private static Api api;

    public static Api getApi()
    {
        return api;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(this);
        PreferenceHelper.Initialize(this);
        api = new Api();
        api.CheckSession(new Callback<CheckSessionResponse>()
        {
            @Override
            public void onResponse(Call<CheckSessionResponse> call, Response<CheckSessionResponse> response)
            {

                if (response.isSuccessful())
                {
                    CheckSessionResponse checkSessionResponse = response.body();


                    Snackbar.make(fab, "Не требуется авторизации.", Snackbar.LENGTH_LONG)
                            .show();
                } else
                {
                    OpenAuthorizationFragment();
                }
            }

            @Override
            public void onFailure(Call<CheckSessionResponse> call, Throwable t)
            {
//                Snackbar.make(fab, "Нужно авторизоваться.", Snackbar.LENGTH_LONG);
            }
        });

    }

    private void OpenAuthorizationFragment()
    {
        getFragmentManager().beginTransaction()
                //.addToBackStack(null)
                .replace(R.id.fragmentContainer, new AuthorizationFragment())
                .commit();

    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            getFragmentManager().popBackStack();

            if (getFragmentManager().getBackStackEntryCount() == 0)
            {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            // Handle the camera action
        } else if (id == R.id.nav_gallery)
        {

        } else if (id == R.id.nav_slideshow)
        {


        } else if (id == R.id.nav_manage)
        {
            getFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer, new PreferenceFragmentImpl())
                    .commit();

        } else if (id == R.id.nav_share)
        {

        } else if (id == R.id.nav_send)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void OnLogin()
    {
        Toast.makeText(this, "Успешно авторизован.", Toast.LENGTH_LONG)
                .show();

    }
}