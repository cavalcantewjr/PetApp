package project.android.petapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.android.petapp.R;
import project.android.petapp.ui.fragment.ServicoFragment;
import project.android.petapp.ui.fragment.TabFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayout)
    public DrawerLayout drawerLayout;
    @BindView(R.id.navView)
    public NavigationView navigationView;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.nav_item_inbox: {
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.containerView, new ServicoFragment()).commit();
                    }
                    case R.id.nav_item_draft: {
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.containerView, new ServicoFragment()).commit();
                    }
                    case R.id.nav_item_sent: {
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.containerView, new ServicoFragment()).commit();
                    }
                }
                return false;
            }
        });

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }
}