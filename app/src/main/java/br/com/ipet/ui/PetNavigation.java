package br.com.ipet.ui;

import android.support.v4.app.Fragment;

public interface PetNavigation {
    void navigateTo(Fragment fragment, boolean addToBackstack);
}
