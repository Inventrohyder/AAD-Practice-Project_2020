package com.inventrohyder.aadpracticeproject2020;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SubmissionActivity extends AppCompatActivity {

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
    }

    private void submitProject() {
        String firstName = getTextIn(R.id.first_name_text_input);

        String lastName = getTextIn(R.id.last_name_text_input);

        String email = getTextIn(R.id.email_text_input);

        String link = getTextIn(R.id.github_text_input);


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