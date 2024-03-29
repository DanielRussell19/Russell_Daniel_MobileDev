package com.example.russell_daniel_courseworkone.Models;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import java.util.List<S1707149>
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

//Daniel Russell S1707149
//Class used to define a generic datepicker dialog
public class CustomDatePicker extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getTargetFragment(), year, month, day);//Daniel Russell S1707149
    }

}
