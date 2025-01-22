package com.example.coinbase;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;


    private HomeFragment homeFragment;
    private TransactionFragment transactionFragment;
    private AccountFragment accountFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(!SharedPrefManager.getInstance(this).isLoggedIn())
//        {
//            finish();
//            startActivity(new Intent(this, LoginActivity.class));
//        }
            tabLayout = (TabLayout) findViewById(R.id.tabLayout);
            viewPager = (ViewPager) findViewById(R.id.viewPager);
            adapter = new ViewPagerAdapter(getSupportFragmentManager());

            //Fragments Here

            adapter.AddFragment(new HomeFragment(), "");
            adapter.AddFragment(new TransactionFragment(), "");
            adapter.AddFragment(new AccountFragment(), "");


            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);

            tabLayout.getTabAt(0).setIcon(R.drawable.home);
            tabLayout.getTabAt(1).setIcon(R.drawable.transaction);
            tabLayout.getTabAt(2).setIcon(R.drawable.face);

            //Remove Shadow From The Action Bar

            ActionBar actionBar = getSupportActionBar();
            actionBar.setElevation(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        return true;
    }
}
