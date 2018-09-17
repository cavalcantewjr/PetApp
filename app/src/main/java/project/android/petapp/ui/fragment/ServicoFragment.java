package project.android.petapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import project.android.petapp.R;

public class ServicoFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servico, container, false);
//        View view = inflater.inflate(R.layout.fragment_produto, null);
        ButterKnife.bind(this, view);
        return view;
    }
}
