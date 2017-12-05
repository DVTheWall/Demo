package com.example.sanghani.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sanghani.demo.interfaces.ChangeCurrentFragment;

import java.lang.reflect.Field;

import fragment.HomeFragment;
import fragment.likesFragment;
import fragment.lllikesFragment;

public class SecondActivity extends AppCompatActivity implements ChangeCurrentFragment, FragmentManager.OnBackStackChangedListener {

    private Activity activity;
    private Toolbar toolbar;
    private String fragment_name;
    private ChangeCurrentFragment ChangeCurrentFragment;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        activity = SecondActivity.this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.second_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        setupNavigationView();


    }


    private void setupNavigationView() {

        if (bottomNavigationView != null) {

            // Select first menu item by default and show Fragment accordingly.
            Menu menu = bottomNavigationView.getMenu();
            selectFragment(menu.getItem(0));

            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item);
                            return false;
                        }
                    });
        }
    }

    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.action_home:
                // Action to perform when Home Menu item is selected.
             //   pushFragment(new HomeFragment());

                manageFragments(new HomeFragment(), getString(R.string.home));

                getSupportFragmentManager().addOnBackStackChangedListener(this);

                break;
            case R.id.action_likes:
                // Action to perform when Bag Menu item is selected.
                pushFragment(new likesFragment());
                break;
            case R.id.action_user:
                pushFragment(new likesFragment());

                break;
            case R.id.action_llikes:
                // Action to perform when Account Menu item is selected.
                pushFragment(new lllikesFragment());
                break;
        }
    }

    private void manageFragments(Fragment homeFragment, String tag) {

        fragment_name = homeFragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragment_popped = manager.popBackStackImmediate(fragment_name, 0);

        if (!fragment_popped) { //fragment not in back stack, create it.

            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.rootLayout, homeFragment, tag);
            // ft.show(fragmentName);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(fragment_name);
            ft.commit();

        }
    }

    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.rootLayout, fragment);
                ft.commit();
            }
        }
    }

    @Override
    public void onFragmentChangeListener(Fragment fragment, String tag) {

        manageFragments(fragment, tag);

    }

    @Override
    public void onFancyColorSelected(String title, String selectedColors) {

    }

    @Override
    public void onBackStackChanged() {

    }


    public static class BottomNavigationViewHelper {
        @SuppressLint("RestrictedApi")
        public static void disableShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    //noinspection RestrictedApi
                    item.setShiftingMode(false);
                    // set once again checked value, so view will be updated
                    //noinspection RestrictedApi
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BNVHelper", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e);
            }
        }
    }

    @Override
    public void onBackPressed() {

        /*if (getSupportFragmentManager().getBackStackEntryCount() == 1) {

            startActivity(new Intent(activity,MainActivity.class));
            finish();
//            overridePendingTransition(R.anim.left_in, R.anim.right_out);

        } else {
            startActivity(new Intent(activity,MainActivity.class));
//            super.onBackPressed();
//            overridePendingTransition(R.anim.left_in, R.anim.right_out);

        }*/

        startActivity(new Intent(activity,MainActivity.class));
        finish();

    }
}
