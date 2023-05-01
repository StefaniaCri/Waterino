package com.example.waterreminder;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class FeedbackFragment extends Fragment {

    EditText nameData, emailData, feedbackData;
    Button save, details, emailbtn;
    Firebase firebase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        nameData = view.findViewById(R.id.name_feedback);
        emailData = view.findViewById(R.id.email_feedback);
        feedbackData = view.findViewById(R.id.feedback);

        save = view.findViewById(R.id.btn_save);
        details = view.findViewById(R.id.btn_details);
        emailbtn = view.findViewById(R.id.btn_email);
        Firebase.setAndroidContext(view.getContext());
        String uniquesID = Settings.Secure.getString(view.getContext().getContentResolver(),Settings.Secure.ANDROID_ID);
        firebase = new Firebase("https://waterreminder-53b2c-default-rtdb.europe-west1.firebasedatabase.app/Users" + uniquesID);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details.setEnabled(true);
                emailbtn.setEnabled(true);
                final String name = nameData.getText().toString();
                final String email = emailData.getText().toString();
                final String feedback = feedbackData.getText().toString();

                Firebase child_name = firebase.child("Name");
                child_name.setValue(name);
                if(name.isEmpty()){
                    nameData.setError("This is an required field!");
                    save.setEnabled(false);
                }else {
                    nameData.setError(null);
                    save.setEnabled(true);
                }

                Firebase child_email = firebase.child("Email");
                child_email.setValue(email);
                if(email.isEmpty()){
                    emailData.setError("This is an required field!");
                    save.setEnabled(false);
                }else {
                    emailData.setError(null);
                    save.setEnabled(true);
                }

                Firebase child_feedback = firebase.child("Feedback");
                child_feedback.setValue(feedback);
                if(feedback.isEmpty()){
                    emailData.setError("This is an required field!");
                    save.setEnabled(false);
                }else {
                    emailData.setError(null);
                    save.setEnabled(true);
                }
                Toast.makeText(view.getContext(), "Your data was saved!",Toast.LENGTH_SHORT).show();
                details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(view.getContext())
                                .setTitle("Sended details")
                                .setMessage("Name - " + name + "\n\n Email - " + email + "\n\nMessage - " + feedback)
                                .show();
                    }
                });
                emailbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String myemail = "stefania.cristea1@s.unibuc.ro";
                        Intent send = new Intent(Intent.ACTION_SEND);
                        send.putExtra(Intent.EXTRA_EMAIL,new String[]{myemail});
                        send.putExtra(Intent.EXTRA_SUBJECT,"Feeddback from " + name);
                        send.putExtra(Intent.EXTRA_TEXT,feedback +"\n\n Find at email "+ email);

                        send.setType("message/rfc822");
                        send.setPackage("com.google.android.gm");
                        startActivity(send);

                    }
                });
            }
        });


        return view;
    }
}