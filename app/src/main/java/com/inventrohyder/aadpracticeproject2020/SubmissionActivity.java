package com.inventrohyder.aadpracticeproject2020;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;
import com.inventrohyder.aadpracticeproject2020.ui.main.GadsApi;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SubmissionActivity extends AppCompatActivity {

    private GadsApi mGadsApi;

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        final Button submitButton = findViewById(R.id.submit_project);
        submitButton.setOnClickListener(view -> submitProject());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .build();

        mGadsApi = retrofit.create(GadsApi.class);


    }

    private void submitProject() {
        String firstName = getTextIn(R.id.first_name_text_input);

        String lastName = getTextIn(R.id.last_name_text_input);

        String email = getTextIn(R.id.email_text_input);

        String link = getTextIn(R.id.github_text_input);

        if (firstName.length() > 0 && lastName.length() > 0 && email.length() > 0 && link.length() > 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.sumbission_question)
                    .setTitle(R.string.submission_title)
                    .setPositiveButton(R.string.submit,
                            (dialog, id) -> SubmissionActivity.this.sendProjectDetails(firstName, lastName, email, link))
                    .setNegativeButton(R.string.cancel, (dialog, id) -> {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    });

            // Create the AlertDialog object
            builder.create();
            builder.show();

        }

    }

    private void sendProjectDetails(String firstName, String lastName, String email, String link) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                findViewById(R.id.custom_toast_container));

        TextView txtSuccessIndicator = layout.findViewById(R.id.success_indicator_text);

        ImageView imgSuccessIndicator = layout.findViewById(R.id.success_indicator);


        mGadsApi.submitProject(firstName, lastName, email, link).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Not Successful" + response.code());

                    txtSuccessIndicator.setText(R.string.submit_not_successful);
                    imgSuccessIndicator.setImageResource(R.drawable.ic_not_successful);

                    showCustomToast(layout);

                    return;
                }

                try {
                    Log.i(TAG, "onResponse: Successful" + Objects.requireNonNull(response.body()).toString());
                } catch (Exception e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }

                txtSuccessIndicator.setText(R.string.submit_successful);
                imgSuccessIndicator.setImageResource(R.drawable.ic_success);

                showCustomToast(layout);

                new Handler().postDelayed(SubmissionActivity.this::finish, Toast.LENGTH_LONG * 1000);

            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);

                txtSuccessIndicator.setText(R.string.submit_not_successful);
                imgSuccessIndicator.setImageResource(R.drawable.ic_not_successful);

                showCustomToast(layout);
            }
        });
    }

    private void showCustomToast(View layout) {
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    @SuppressLint("ClickableViewAccessibility")
    private String getTextIn(int id) {
        TextInputLayout textInputLayout = findViewById(id);

        String text = String.valueOf(
                Objects.requireNonNull(textInputLayout.getEditText()).getText()
        );

        if (text.length() == 0) {
            textInputLayout.setError("Input value needed");
        }

        textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInputLayout.setError(null);
                textInputLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        return text;
    }
}