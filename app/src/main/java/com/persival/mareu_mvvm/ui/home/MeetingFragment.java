package com.persival.mareu_mvvm.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.persival.mareu_mvvm.databinding.FragmentMeetingBinding;
import com.persival.mareu_mvvm.model.Meeting;
import com.persival.mareu_mvvm.ui.add.AddMeetingActivity;

public class MeetingFragment extends Fragment {

    private FragmentMeetingBinding binding;
    private MeetingViewModel meetingViewModel;

    private final MeetingRecyclerViewAdapter meetingRecyclerViewAdapter = new MeetingRecyclerViewAdapter(
        new EventsMeeting() {
            @Override
            public void onDelete(Meeting meeting) {
                meetingViewModel.deleteMeeting(meeting);
            }
        }
    );

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Très dangereux et très mauvaise pratique le "Shared ViewModel". Utilise plutôt `new ViewModelProvider(this)`
        meetingViewModel = new ViewModelProvider(requireActivity()).get(MeetingViewModel.class);
        meetingViewModel.meetingsLiveData.observe(this, meetings -> {
            meetingRecyclerViewAdapter.setMeetings(meetings);
        });
    }

    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        binding = FragmentMeetingBinding.inflate(inflater, container, false);
        meetingViewModel.getMeetings(null, null);

        initRecyclerView(binding);

        return binding.getRoot();
    }

    public void initRecyclerView(FragmentMeetingBinding binding) {
        // findViewById est à bannir lorsqu'on utilise le ViewBinding :)
        binding.listOfMeeting.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.listOfMeeting.addItemDecoration(new DividerItemDecoration(
            requireContext(),
            DividerItemDecoration.VERTICAL
        ));
        binding.listOfMeeting.setAdapter(meetingRecyclerViewAdapter);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.floatingActionButton.setOnClickListener(v -> {
            // Normalement, seules les Activity peuvent s'appeler entre elles, ou on utilise un objet commun genre
            // "Navigator" pour gérer la navigation au sein de l'application
            Intent startAddMeetingActivity = new Intent(v.getContext(), AddMeetingActivity.class);
            v.getContext().startActivity(startAddMeetingActivity);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}