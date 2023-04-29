package com.example.waterreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.waterreminder.databinding.FragmentSecondBinding;
import com.example.waterreminder.extras.AlertReciever;
import com.example.waterreminder.extras.TimePickerFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.Calendar;

public class AlarmFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {

private FragmentSecondBinding binding;
    private TextView mTextView;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view =  inflater.inflate(R.layout.fragment_second, container, false);
//        FloatingActionButton buttonAddDrink = view.findViewById(R.id.button_add_note);
//        buttonAddDrink.setVisibility(View.GONE);

        Button buttonTimePicker = view.findViewById(R.id.button_time_picker);
        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
            }
        });
        Button buttonCancelAlarm = view.findViewById(R.id.button_cancel);
        buttonCancelAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });

        mTextView = view.findViewById(R.id.textview);
        return view;

    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND,0);

        updateTimeText(c);
        startAlarm(c);
    }

    private void updateTimeText(Calendar c){
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        mTextView.setText(timeText);
    }
    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getView().getContext(), AlertReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getView().getContext(),1,intent,0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }

    public  void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getView().getContext(), AlertReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getView().getContext(),1,intent,0);

        alarmManager.cancel(pendingIntent);
        mTextView.setText("Alarm canceled");

    }
}