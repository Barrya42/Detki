package com.example.derbenevsv.myapplication;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.derbenevsv.myapplication.api_1c.Responses.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthorizationFragment extends Fragment implements View.OnClickListener, Callback, TextWatcher
{
    Button btLogin;
    Button btCancel;
    EditText etPhone;
    EditText etPass;
    private String phone;
    private String pass;
    private Context context;
    private ProgressDialog progressDialog;
    private OnLoginListener onLoginLitener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

    public AuthorizationFragment()
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //View rootView = getView();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_authorization, container, false);
        btLogin = rootView.findViewById(R.id.btLogin);
        btCancel = rootView.findViewById(R.id.btCancel);
        etPhone = rootView.findViewById(R.id.etPhone);
        etPass = rootView.findViewById(R.id.etPassword);
        etPhone.addTextChangedListener(this);
        etPass.addTextChangedListener(this);
        btLogin.setOnClickListener(this);
        btCancel.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context = context;
        if (context instanceof OnLoginListener)
        {
            onLoginLitener = (OnLoginListener) context;
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }


    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.btLogin)
        {
            MainActivity.getApi()
                    .Login(phone, pass, this);

            progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Вход...");
            progressDialog.show();
        } else if (view.getId() == R.id.btCancel)
        {

        }
    }

    @Override
    public void onResponse(Call call, Response response)
    {
        if (response.isSuccessful())
        {
            LoginResponse loginResponse = (LoginResponse) response.body();
            MainActivity.getApi()
                    .SetSessionGuid(loginResponse.GetSessionGuid());
            progressDialog.dismiss();
            if (onLoginLitener != null)
            {
                onLoginLitener.OnLogin();
            }
        }
    }

    @Override
    public void onFailure(Call call, Throwable t)
    {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {

    }

    @Override
    public void afterTextChanged(Editable editable)
    {
        btLogin.setEnabled(etPass.length() > 0 && etPhone.length() > 0);
    }


    interface OnLoginListener
    {
        void OnLogin();
    }


}