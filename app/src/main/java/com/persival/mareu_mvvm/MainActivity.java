package com.persival.mareu_mvvm;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.persival.mareu_mvvm.databinding.ActivityMainBinding;
import com.persival.mareu_mvvm.ui.home.MeetingViewModel;
import com.persival.mareu_mvvm.ui.utils.SpinnerDialogFragment;
import com.persival.mareu_mvvm.ui.utils.ViewModelFactory;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityMainBinding binding;
    private MeetingViewModel meetingViewModel;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        initDatePicker();
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        meetingViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MeetingViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.date_filter) {
            openDatePicker(binding.getRoot());
            return true;
        }
        if (id == R.id.location_filter) {
            SpinnerDialogFragment.newInstance().show(getSupportFragmentManager(), null);
            return true;
        }

        if (id == R.id.no_filter) {
            meetingViewModel.getMeetings(null, null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Get date from calendar
     */
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, day, month, year) -> {
            month = month + 1;
            String date = year + " " + getMonthFormat(month) + " " + day;
            meetingViewModel.getMeetings("date", date);
        };

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, day, month, year);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
    }

    /**
     * Convert month number to String
     */
    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEV";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "AVR";
        if (month == 5)
            return "MAI";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AOU";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        return "JAN";
    }

    /**
     * Show the calendar
     */
    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
