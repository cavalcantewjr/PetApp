package project.android.petapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.android.petapp.R;
import project.android.petapp.ui.PetNavigation;

public class LoginFragment extends android.support.v4.app.Fragment {

    @BindView(R.id.password_text_input)
    TextInputLayout passwordTextInput;

    @BindView(R.id.password_edit_text)
    TextInputEditText passwordEditText;

    @BindView(R.id.next_button)
    MaterialButton nextButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pet_login_fragment, container, false);

        ButterKnife.bind(this, view);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PetNavigation) getActivity()).navigateTo(new ProductListFragment(), false);
            }
        });
        return view;
    }

}
