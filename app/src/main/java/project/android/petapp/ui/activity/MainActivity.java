package project.android.petapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import project.android.petapp.R;
import project.android.petapp.ui.PetNavigation;
import project.android.petapp.ui.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity implements PetNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }

    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}