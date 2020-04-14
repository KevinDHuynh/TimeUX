package edu.wit.mobileapp.TimeUX.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.wit.mobileapp.TimeUX.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public String fullname;
    public String username;

    @BindView(R.id.dateText) TextView dateText;
    @BindView(R.id.button) Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);


        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        ButterKnife.bind(this,root);

        Intent lastIntent = getActivity().getIntent();
        fullname = lastIntent.getStringExtra("fullname");
        username = lastIntent.getStringExtra("username");

        Intent intent  = new Intent(getActivity(), ClockHours.class);
        intent.putExtra("fullname",fullname);
        intent.putExtra("username",username);
        button.setOnClickListener(view -> startActivity(intent));


        return root;
    }
}