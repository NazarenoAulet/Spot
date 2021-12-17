package com.quasartec.spot.Views.destinations.otherDestinations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import com.quasartec.spot.R;
import com.quasartec.spot.Views.MainActivity;

public class LoginFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView createTV;
    EditText emailET;
    TextView forgotTV;
    AppCompatButton loginBT;
    private String mParam1;
    private String mParam2;
    EditText passET;

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity = (MainActivity) getActivity();
        MainActivity.loginPhase = true;
        ((MainActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((MainActivity) getActivity()).drawerLayout.setDrawerLockMode(1);
        this.emailET = (EditText) view.findViewById(R.id.email_login_et);
        this.passET = (EditText) view.findViewById(R.id.pass_login_et);
        this.forgotTV = (TextView) view.findViewById(R.id.login_forgotPass_tv);
        this.createTV = (TextView) view.findViewById(R.id.createaccount_login_tv);
        this.loginBT = (AppCompatButton) view.findViewById(R.id.loginBtn);
        this.forgotTV.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LoginFragment.this.lambda$onViewCreated$0$LoginFragment(view);
            }
        });
        this.createTV.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LoginFragment.this.lambda$onViewCreated$1$LoginFragment(view);
            }
        });
    }

    public /* synthetic */ void lambda$onViewCreated$0$LoginFragment(View v) {
        ((MainActivity) getActivity()).navController.navigate((int) R.id.action_loginFragment_to_recoverPasswordFragment);
    }

    public /* synthetic */ void lambda$onViewCreated$1$LoginFragment(View v) {
        ((MainActivity) getActivity()).navController.navigate((int) R.id.action_loginFragment_to_createAccountFragment);
    }
}
