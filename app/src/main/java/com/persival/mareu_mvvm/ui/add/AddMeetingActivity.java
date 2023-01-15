package com.persival.mareu_mvvm.ui.add;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.persival.mareu_mvvm.R;
import com.persival.mareu_mvvm.databinding.ActivityAddMeetingBinding;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

// Beaucoup d'intelligence dans cette classe, presque tout le code est présent dans l'Activity, presque rien dans le
// ViewModel ! Il serait bon de passer la gestion des emails dans le ViewModel, par exemple
public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AddMeetingViewModel addMeetingViewModel;
    private DatePickerDialog datePickerDialog;
    private ActivityAddMeetingBinding binding;
    // Sépare bien les déclarations
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Attention cette ligne était inutile et ralentissait les perfs

        addMeetingViewModel = new ViewModelProvider(this).get(AddMeetingViewModel.class);

        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initAutoCompletion();
        initDatePicker();
        initBinding();

        addMeetingViewModel.getIsSaveButtonEnabledLiveData().observe(this, isSaveButtonEnabled ->
            binding.saveButton.setEnabled(isSaveButtonEnabled)
        );
        addMeetingViewModel.needToClose().observe(this, aVoid -> closeActivity());
    }

    /**
     * Binding all elements (set and get)
     * Bind participants for enabled the save button
     */
    private void initBinding() {
        binding.datePickerButton.setText(getTodayDate());
        binding.datePickerButton.setOnClickListener(view1 -> openDatePicker(binding.getRoot()));
        binding.startTimeButton.setOnClickListener(view1 -> popTimePickerStart(binding.getRoot()));

        bindParticipants(addMeetingViewModel, binding.participantsInput);

        participantEmailToChip();
        onSaveButtonClick();
    }

    /**
     * Create a Chip for new email participant and add it to participants list
     */
    // Attention à l'indentation, use et abuse du raccourci "Ctrl + Alt + L" pour reformater ton code automatiquement :)
    private void participantEmailToChip() {
        binding.emailOkButton.setOnClickListener(view -> {
            // Objects.requireNonNull est à bannir, cette fonction ne sert absolument à rien ! Va voir l'implémentation
            // avec Ctrl + Click gauche sur la fonction (requireNonNull ici). Si tu ne vois pas l'implémentation,
            // télécharge les souces de l'API 33 dans le SDK manager et recommence
            if (!Objects.requireNonNull(binding.participantsInput.getText()).toString().isEmpty() &&
                Patterns.EMAIL_ADDRESS.matcher(binding.participantsInput.getText().toString()).matches()) {
                Chip emailParticipant = new Chip(this);
                emailParticipant.setCloseIconVisible(true);
                emailParticipant.setText(binding.participantsInput.getText().toString());
                emailParticipant.setOnCloseIconClickListener(view1 -> {
                    binding.chips.removeView(emailParticipant);
                    addMeetingViewModel.getParticipants().remove(emailParticipant.getText().toString());
                });
                binding.chips.addView(emailParticipant);
                addMeetingViewModel.getParticipants().add(emailParticipant.getText().toString());
                binding.participantsLayout.setError(null);
                binding.participantsInput.setText("");
                binding.participantsInput.onEditorAction(EditorInfo.IME_ACTION_DONE);
            } else {
                // Utilise bien les Resources String pour permettre une traduction facile
                binding.participantsLayout.setError("Veuillez entrer une adresse mail valide");
            }
        });
    }

    /**
     * Verification of all entry of user on save button click
     */
    // Tu as déjà toute l'intelligence et même le nom de la fonction pour la déplacer dans le ViewModel :)
    private void onSaveButtonClick() {
        binding.saveButton.setOnClickListener(v -> {
            // Objects.requireNonNull est à bannir
            if (Objects.requireNonNull(binding.nameOfMeeting.getText()).toString().isEmpty()) {
                // Utilise bien les Resources String pour permettre une traduction facile
                Toast.makeText(this, "Merci de remplir le champs 'Objet de la réunion'", Toast.LENGTH_SHORT).show();

                // On évite les return à répétition, on préfèrera ici une suite de if / else (ou `when` en Kotlin plus
                // tard)
                return;
            }

            // Cette condition est cassée si on traduit l'application autrement qu'en Français, il va falloir trouver
            // mieux! ^^
            if (binding.startTimeButton.getText().toString().equals("Heure de la réunion")) {
                // Utilise bien les Resources String pour permettre une traduction facile
                Toast.makeText(this, "Merci de remplir le champs 'Heure de la réunion'", Toast.LENGTH_SHORT).show();
                return;
            }

            // Objects.requireNonNull est à bannir
            if (!Objects.requireNonNull(binding.participantsInput.getText()).toString().isEmpty() &&
                !Patterns.EMAIL_ADDRESS.matcher(binding.participantsInput.getText().toString()).matches()) {
                // Utilise bien les Resources String pour permettre une traduction facile
                Toast.makeText(this, "Veuillez entrer une adresse mail valide", Toast.LENGTH_SHORT).show();
                return;
            }

            if (addMeetingViewModel.getParticipants().size() < 2) {
                // Utilise bien les Resources String pour permettre une traduction facile
                Toast.makeText(this, "Veuillez entrer au moins 2 adresses mail", Toast.LENGTH_SHORT).show();
            } else {
                addMeetingViewModel.onAddButtonClicked(
                    // Objects.requireNonNull est à bannir
                    Objects.requireNonNull(binding.nameOfMeeting.getText()).toString(),
                    binding.datePickerButton.getText().toString(),
                    binding.startTimeButton.getText().toString(),
                    binding.roomChoiceAddMeeting.getSelectedItem().toString()
                );
            }
        });
    }

    private void closeActivity() {
        finish();
    }

    /**
     * Auto completion for list of rooms
     */
    private void initAutoCompletion() {
        Spinner spinner = findViewById(R.id.roomChoiceAddMeeting);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.roomNumber, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    /**
     * Get today date for calendar button
     */
    public CharSequence getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    /**
     * Get date from calendar
     */
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, day, month, year) -> {
            month = month + 1;
            String date = makeDateString(year, month, day);
            binding.datePickerButton.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, day, month, year);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    /**
     * Date in String version (month day year)
     */
    private String makeDateString(int day, int month, int year) {
        return day + " " + getMonthFormat(month) + " " + year;
    }

    /**
     * Convert month number to String
     */
    private String getMonthFormat(int month) {
        if (month == 1) {
            return "JAN";
        }
        if (month == 2) {
            return "FEV";
        }
        if (month == 3) {
            return "MAR";
        }
        if (month == 4) {
            return "AVR";
        }
        if (month == 5) {
            return "MAI";
        }
        if (month == 6) {
            return "JUN";
        }
        if (month == 7) {
            return "JUL";
        }
        if (month == 8) {
            return "AOU";
        }
        if (month == 9) {
            return "SEP";
        }
        if (month == 10) {
            return "OCT";
        }
        if (month == 11) {
            return "NOV";
        }
        if (month == 12) {
            return "DEC";
        }

        return "JAN";
    }

    /**
     * Show the calendar
     */
    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    /**
     * Show time picker for select the start hour meeting
     */
    public void popTimePickerStart(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
            hour = selectedHour;
            minute = selectedMinute;
            binding.startTimeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
        };

        int style = AlertDialog.THEME_HOLO_LIGHT;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Meeting Start Time");
        timePickerDialog.show();
    }

    /**
     * onItemSelected and onNothingSelected is for adapter view
     * of the spinner for select the position of the room place
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String text = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /**
     * Text watcher on e-mail participants for enabled or disabled
     * the save button
     */
    private void bindParticipants(AddMeetingViewModel viewModel, TextInputEditText participantsEditText) {
        participantsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.onParticipantsChanged(s.toString());
            }
        });
    }
}