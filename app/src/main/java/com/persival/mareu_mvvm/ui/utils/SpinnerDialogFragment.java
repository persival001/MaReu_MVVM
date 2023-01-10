package com.persival.mareu_mvvm.ui.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.persival.mareu_mvvm.R;
import com.persival.mareu_mvvm.databinding.DialogFragmentSpinnerBinding;
import com.persival.mareu_mvvm.repositories.MeetingRepository;
import com.persival.mareu_mvvm.ui.home.EventsMeeting;
import com.persival.mareu_mvvm.ui.home.MeetingViewModel;

import java.util.List;
import java.util.Objects;

public class SpinnerDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener{

    private DialogFragmentSpinnerBinding binding;
    private MeetingViewModel meetingViewModel;
    private EventsMeeting eventsMeeting;

    public static SpinnerDialogFragment newInstance() {
        return new SpinnerDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = DialogFragmentSpinnerBinding.inflate(getLayoutInflater());
        builder.setView(binding.getRoot()).create();
        meetingViewModel = new ViewModelProvider(this).get(MeetingViewModel.class);

        initAutoCompletion();

        return binding.getRoot();
    }

    /**
     * Auto completion for list of rooms
     */
    private void initAutoCompletion() {
        Spinner spinner = binding.roomChoice;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.roomNumber, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    /**
     * Binding of Ok button
     * For choose a room to filter
     */
    private void onClickOkButton(String roomSelected) {
        String room = meetingViewModel.getRoomString(roomSelected);
        binding.okButton.setOnClickListener(v -> meetingViewModel.getMeetings("room", room));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String roomSelected = adapterView.getItemAtPosition(position).toString();
        onClickOkButton(roomSelected);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
