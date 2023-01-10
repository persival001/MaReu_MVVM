package com.persival.mareu_mvvm.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.persival.mareu_mvvm.R;
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
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview_meeting_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);
        holder.mList.setText(meeting.getMeetingItemDisplay());
        holder.mListMails.setSelected(true);
        holder.mListMails.setText(meeting.getMails());
        holder.mRoomNumber.setText(meeting.getMeetingRoom());
        holder.mDeleteButton.setOnClickListener(v -> eventsMeeting.onDelete(meeting));
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mIcon;
        public TextView mList;
        public TextView mListMails;
        public TextView mRoomNumber;
        public ImageButton mDeleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.item_list_icon);
            mList = itemView.findViewById(R.id.item_list_name);
            mListMails = itemView.findViewById(R.id.item_list_mails);
            mRoomNumber = itemView.findViewById(R.id.item_list_room_number);
            mDeleteButton = itemView.findViewById(R.id.item_list_delete_button);
        }
    }
}