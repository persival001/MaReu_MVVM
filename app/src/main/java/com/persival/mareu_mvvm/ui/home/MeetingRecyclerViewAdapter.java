package com.persival.mareu_mvvm.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.persival.mareu_mvvm.databinding.ItemviewMeetingListBinding;
import com.persival.mareu_mvvm.model.Meeting;

import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private List<Meeting> meetings;
    private final EventsMeeting eventsMeeting;

    public MeetingRecyclerViewAdapter(EventsMeeting eventsMeeting) {
        this.eventsMeeting = eventsMeeting;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
        // Le warning est totalement justifié ici, à chaque simple ajout d'un Meeting dans la liste, tu "effaces" toute
        // la RecyclerView et tu reconstruis tout. Utilise plutôt un ListAdapter avec son DiffUtil, tu gagnes des super
        // animations en plus ^^
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tu peux utiliser le ViewBinding ici plutôt, c'est quand même plus court et pratique :)
        return new ViewHolder(
            ItemviewMeetingListBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
            )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);

        // Il est un peu plus commun de déléguer le bind de la data au ViewHolder. Comme ça si tu as plusieurs types de
        // ViewHolder, cette fonction ne devient pas un gigantesque bordel ^^
        holder.bind(meeting, eventsMeeting);
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Utilise "final" dès que tu peux, ça va t'obliger à bien coder ^^
        // N'hésite pas à demander à ton mentor pourquoi l'immutabilité c'est important :)
        private final ItemviewMeetingListBinding binding;

        public ViewHolder(@NonNull ItemviewMeetingListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(@NonNull Meeting meeting, EventsMeeting eventsMeeting) {

            binding.itemListName.setText(meeting.getMeetingItemDisplay());
            // setSelected à true mais jamais à false, je peux déjà te dire qu'il y a un bug là ^^
            binding.itemListMails.setSelected(true);
            binding.itemListMails.setText(meeting.getMails());
            binding.itemListRoomNumber.setText(meeting.getMeetingRoom());
            binding.itemListDeleteButton.setOnClickListener(v -> eventsMeeting.onDelete(meeting));
        }
    }
}