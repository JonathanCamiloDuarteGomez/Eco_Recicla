package com.example.eco_recicla.PickerFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.eco_recicla.GestionDeReciclaje_AgregarSolicitudDeRecogida;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Usar la hora actual como la hora predeterminada en el selector
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Crear una nueva instancia de TimePickerDialog y devolverla
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Validar si la hora está dentro del rango permitido
        if (hourOfDay < 6 || hourOfDay > 20) {
            Toast.makeText(getActivity(), "Por favor selecciona una hora entre las 6 AM y las 8 PM", Toast.LENGTH_LONG).show();
            // Volver a mostrar el diálogo de selección de hora
            DialogFragment newFragment = new TimePickerFragment();
            newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
        } else {
            GestionDeReciclaje_AgregarSolicitudDeRecogida activity = (GestionDeReciclaje_AgregarSolicitudDeRecogida) getActivity();
            activity.processTimePickerResult(hourOfDay, minute);
        }
    }

