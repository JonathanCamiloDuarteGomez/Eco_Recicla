package com.example.eco_recicla.PickerFragment;

import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import com.example.eco_recicla.GestionDeReciclaje_AgregarSolicitudDeRecogida;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Usar la fecha actual como la fecha predeterminada en el selector
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Crear una nueva instancia de DatePickerDialog y devolverla
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

        // Establecer la fecha mínima en el DatePickerDialog a hoy
        c.add(Calendar.DAY_OF_MONTH, 1);
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());

        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        GestionDeReciclaje_AgregarSolicitudDeRecogida activity = (GestionDeReciclaje_AgregarSolicitudDeRecogida) getActivity();
        activity.processDatePickerResult(year, month, dayOfMonth);
    }
