package project.android.petapp.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.android.petapp.R;
import project.android.petapp.ui.fragment.ConfiguracoesFragment;
import project.android.petapp.ui.fragment.ContatoFragment;
import project.android.petapp.ui.fragment.PerfilFragment;
import project.android.petapp.ui.fragment.QuemSomosFragment;
import project.android.petapp.ui.fragment.TabFragment;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayout)
    public DrawerLayout drawerLayout;
    @BindView(R.id.navView)
    public NavigationView navigationView;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, new TabFragment())
                .commit();

        navigationView.setCheckedItem(R.id.nav_item_home);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = selectDrawerItem(menuItem);

                if (menuItem.getItemId() != R.id.nav_item_avaliar) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.containerView, selectedFragment)
                            .commit();

                    if (menuItem.getItemId() != R.id.nav_item_avaliar) getSupportActionBar().setTitle(menuItem.getTitle());
                    navigationView.setCheckedItem(menuItem.getItemId());
                }

                drawerLayout.closeDrawers();

                return false;
            }
        });

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    public Fragment selectDrawerItem(MenuItem menuItem){
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_item_home: {
                fragment = new TabFragment();
                break;
            }
            case R.id.nav_item_perfil: {
                fragment = new PerfilFragment();
                break;
            }
            case R.id.nav_item_configuracoes: {
                fragment = new ConfiguracoesFragment();
                break;
            }
            case R.id.nav_item_avaliar: {
                launchPlayStore();
                break;
            }
            case R.id.nav_item_quem_somos: {
                fragment = new QuemSomosFragment();
                break;
            }
            case R.id.nav_item_contato: {
                fragment = new ContatoFragment();
                break;
            }
        }
        return fragment;
    }

    private void launchPlayStore() {
//        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Uri uri = Uri.parse("market://details?id=" + "com.trello");
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }
    }
}