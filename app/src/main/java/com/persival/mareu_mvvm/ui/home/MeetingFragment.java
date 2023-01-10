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
import androidx.recyclerview.widget.RecyclerView;

import com.persival.mareu_mvvm.R;
import com.persival.mareu_mvvm.databinding.FragmentMeetingBinding;
import com.persival.mareu_mvvm.model.Meeting;
import com.persival.mareu_mvvm.repositories.MeetingRepository;
import com.persival.mareu_mvvm.ui.add.AddMeetingActivity;

import java.util.List;


public class MeetingFragment extends Fragment {

    private FragmentMeetingBinding binding;
    private MeetingViewModel meetingViewModel;
    private RecyclerView recyclerView;
    private final MeetingRecyclerViewAdapter meetingRecyclerViewAdapter = new MeetingRecyclerViewAdapter(new EventsMeeting() {
        @Override
        public void onDelete(Meeting meeting) {
            meetingViewModel.deleteMeeting(meeting);
        }
    });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        meetingViewModel = new ViewModelProvider(this).get(MeetingViewModel.class);
        initObserver();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentMeetingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        meetingViewModel.getMeetings(null, null);
        recyclerView = root.findViewById(R.id.listOfMeeting);

        initRecyclerView ();

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.floatingActionButton.setOnClickListener(view1 -> {
            Intent startAddMeetingActivity = new Intent(view1.getContext(), AddMeetingActivity.class);
            view1.getContext().startActivity(startAddMeetingActivity);
        });
    }

    private void initRecyclerView () {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(meetingRecyclerViewAdapter);
    }

    private void initObserver () {
        meetingViewModel.meetingsLiveData.observe(this, meetings -> {
            meetingRecyclerViewAdapter.setMeetings(meetings);
        });
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }
}