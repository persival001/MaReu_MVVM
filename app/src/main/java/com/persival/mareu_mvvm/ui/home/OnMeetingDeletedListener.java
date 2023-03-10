package com.persival.mareu_mvvm.ui.home;

import com.persival.mareu_mvvm.model.Meeting;

public interface OnMeetingDeletedListener {

    /**
     * Listen the trash button for delete the selected item
     *
     * @param meetingId the meeting id
     */
    void onDelete(long meetingId);
}
