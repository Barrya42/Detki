package com.example.derbenevsv.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.derbenevsv.myapplication.api_1c.Responses.LoginResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthorizationFragment extends Fragment implements View.OnClickListener, Callback, TextWatcher
{
    Button btLogin;
    Button btCancel;
    EditText etPhone;
    EditText etPass;
    CheckBox cbRememberMe;
    private String phone = "";
    private String pass = "";
    private Context context;
    //    private ProgressDialog progressDialog;
    private OnLoginListener onLoginLitener;
    private SnackBarShower snackBarShower;
    private ProgressDialogShower progressDialogShower;
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
        cbRememberMe = rootView.findViewById(R.id.cbRememberMe);

        etPhone.addTextChangedListener(this);
        etPass.addTextChangedListener(this);
        btLogin.setOnClickListener(this);
        btCancel.setOnClickListener(this);

        String storedPhone = PreferenceHelper.GetPhone();
        if (storedPhone != "")
        {
            etPhone.setText(storedPhone);
            phone = storedPhone;
        }

        LoginEnable();
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
        else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginListener");
        }
        if (context instanceof SnackBarShower)
        {
            snackBarShower = (SnackBarShower) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                    + " must implement snackBarShower");
        }
        if (context instanceof ProgressDialogShower)
        {
            progressDialogShower = (ProgressDialogShower) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                    + " must implement ProgressDialogShower");
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
        etPass.clearFocus();
        etPhone.clearFocus();
        if (view.getId() == R.id.btLogin)
        {
            MainActivity.getApi()
                    .Login(phone, pass, this);
            progressDialogShower.ShowProgressDialog("Вход...");
        }
        else if (view.getId() == R.id.btCancel)
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
            progressDialogShower.HideProgressDialog();
            onLoginLitener.OnLogin();
            if (cbRememberMe.isChecked())
            {
                PreferenceHelper.SetPhone(phone);
            }
        }
        else
        {
            String errorText = "Неизвестная ошибка.";
            try
            {
                errorText = response.errorBody()
                        .string();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            progressDialogShower.HideProgressDialog();
//            Toast.makeText(context, errorText, Toast.LENGTH_SHORT)
//                    .show();
            snackBarShower.ShowSnackBar(errorText);

        }
    }

    @Override
    public void onFailure(Call call, Throwable t)
    {
        try
        {
            throw t;
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
        }
    }


    private void LoginEnable()
    {
        btLogin.setEnabled(!phone.isEmpty() && !pass.isEmpty());
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
        pass = etPass.getText()
                .toString();
        phone = etPhone.getText()
                .toString();
        LoginEnable();
    }

    interface OnLoginListener
    {
        void OnLogin();
    }


}
