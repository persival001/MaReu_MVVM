package com.persival.mareu_mvvm.ui.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.persival.mareu_mvvm.repository.MeetingRepository;
import com.persival.mareu_mvvm.ui.add.AddMeetingViewModel;
import com.persival.mareu_mvvm.ui.home.MeetingViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    public ViewModelFactory(MeetingRepository repository) {
    }

    public static ViewModelFactory getInstance() {
        return FactoryHolder.factory;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MeetingViewModel.class)) {
            return (T) new MeetingViewModel();
        } else if (modelClass.isAssignableFrom(AddMeetingViewModel.class)) {
            return (T) new AddMeetingViewModel();
        }
        throw new IllegalArgumentException("Unknow ViewModel");
    }

    private static final class FactoryHolder {
        static final ViewModelFactory factory = new ViewModelFactory(new MeetingRepository());
    }
}
