package project.android.petapp;

import android.support.v4.app.Fragment;

public interface PetNavigation {
    void navigateTo(Fragment fragment, boolean addToBackstack);
}
