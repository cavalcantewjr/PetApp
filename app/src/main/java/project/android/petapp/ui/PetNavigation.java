package project.android.petapp.ui;

import android.support.v4.app.Fragment;

public interface PetNavigation {
    void navigateTo(Fragment fragment, boolean addToBackstack);
}
