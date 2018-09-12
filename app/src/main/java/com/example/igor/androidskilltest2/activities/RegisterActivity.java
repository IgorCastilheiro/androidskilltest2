package com.example.igor.androidskilltest2.activities;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.example.igor.androidskilltest2.R;
import com.example.igor.androidskilltest2.models.User;
import com.example.igor.androidskilltest2.viewmodels.UserViewModel;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity {

    // UI references.
    private EditText txtEmailView;
    private EditText txtNameView;
    private EditText txtPasswordView;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Set up the login form.
        txtEmailView = findViewById(R.id.email);
        txtNameView = findViewById(R.id.name);

        txtPasswordView = findViewById(R.id.password);
        txtPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptRegister();
                return true;
            }
            return false;
        });

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(view -> attemptRegister());

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    /**
     * Attempts to register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptRegister() {

        // Reset errors.
        txtEmailView.setError(null);
        txtNameView.setError(null);
        txtPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = txtEmailView.getText().toString();
        String name = txtNameView.getText().toString();
        String password = txtPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            txtPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = txtPasswordView;
            cancel = true;
        }

        // Check for a valid name.
        if (TextUtils.isEmpty(name)) {
            txtNameView.setError(getString(R.string.error_field_required));
            focusView = txtNameView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            txtEmailView.setError(getString(R.string.error_field_required));
            focusView = txtEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            txtEmailView.setError(getString(R.string.error_invalid_email));
            focusView = txtEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            User user = new User(email, name, Base64.encodeToString(password.getBytes(), Base64.DEFAULT));
            userViewModel.insert(user);
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        super.onBackPressed();
        finish();
    }
}

