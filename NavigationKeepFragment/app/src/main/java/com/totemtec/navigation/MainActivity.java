package com.totemtec.navigation;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupBottomNavigationBar();
    }

    private void setupBottomNavigationBar() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setItemIconTintList(null);
        // get fragment
        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // setup custom navigator
        KeepStateNavigator navigator = new KeepStateNavigator(this,
                navHostFragment.getChildFragmentManager(), R.id.nav_host_fragment);
        navController.getNavigatorProvider().addNavigator(navigator);

        // set navigation graph
        navController.setGraph(R.navigation.mobile_navigation);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

}