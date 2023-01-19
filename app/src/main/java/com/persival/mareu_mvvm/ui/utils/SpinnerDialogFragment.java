package com.persival.mareu_mvvm.ui.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.persival.mareu_mvvm.R;
import com.persival.mareu_mvvm.databinding.DialogFragmentSpinnerBinding;
import com.persival.mareu_mvvm.ui.home.MeetingViewModel;

public class SpinnerDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {

    private DialogFragmentSpinnerBinding binding;
    private MeetingViewModel meetingViewModel;

    public static SpinnerDialogFragment newInstance() {
        return new SpinnerDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogFragmentSpinnerBinding.inflate(getLayoutInflater());
        meetingViewModel = new ViewModelProvider(requireActivity()).get(MeetingViewModel.class);

        initAutoCompletion();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Auto completion for list of rooms
     */
    private void initAutoCompletion() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
            R.array.roomNumber, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.roomChoice.setAdapter(adapter);
        binding.roomChoice.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String roomSelected = adapterView.getItemAtPosition(position).toString();
        String room = meetingViewModel.getRoomString(roomSelected);
        onClickOkButton(room);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /**
     * Binding of Ok button
     * For choose a room to filter
     */
    private void onClickOkButton(String room) {
        binding.okButton.setOnClickListener(view -> {
            meetingViewModel.getMeetings("room", room);
            SpinnerDialogFragment.this.dismiss();
        });
    }
}
