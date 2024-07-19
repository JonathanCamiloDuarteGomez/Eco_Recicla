package com.example.eco_recicla.PickerFragment;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.example.eco_recicla.GestionDeReciclaje_AgregarSolicitudDeRecogida;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use la hora actual como la hora predeterminada en el selector
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Crear una nueva instancia de TimePickerDialog y devolverla
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        GestionDeReciclaje_AgregarSolicitudDeRecogida activity = (GestionDeReciclaje_AgregarSolicitudDeRecogida) getActivity();
        activity.processTimePickerResult(hourOfDay, minute);
    }
}

