package com.persival.mareu_mvvm.ui.utils;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.persival.mareu_mvvm.R;
import com.persival.mareu_mvvm.databinding.DialogFragmentSpinnerBinding;
import com.persival.mareu_mvvm.ui.home.MeetingViewModel;

public class SpinnerDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener{

    private DialogFragmentSpinnerBinding binding;
    private MeetingViewModel meetingViewModel;

    public static SpinnerDialogFragment newInstance() {
        return new SpinnerDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Je ne suis pas sur que tu aies besoin du builder puisque tu ne l'utilises pas et tu passes plutôt la View
        // entière avec le ViewBinding
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = DialogFragmentSpinnerBinding.inflate(getLayoutInflater());
        builder.setView(binding.getRoot()).create();
        meetingViewModel = new ViewModelProvider(requireActivity()).get(MeetingViewModel.class);

        initAutoCompletion();

        return binding.getRoot();
    }

    /**
     * Auto completion for list of rooms
     */
    private void initAutoCompletion() {
        // Pas besoin de capturer la variable ici, tu peux l'utiliser direct :)

        // Habitue toi à "casser" tes lignes trop longues dans ce style, on fera pareil en Kotlin
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            getContext(),
            R.array.roomNumber,
            android.R.layout.simple_spinner_item
        );
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
            // La vue décide d'elle même de se fermer, ça serait mieux de mettre ce comportement dans le ViewModel
            // pour le tester unitairement, non ?
            SpinnerDialogFragment.this.dismiss();
        });
    }
}
