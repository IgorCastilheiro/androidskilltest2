package com.example.igor.androidskilltest2.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.igor.androidskilltest2.models.User;
import com.example.igor.androidskilltest2.repositories.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    public UserRepository userRepository;

    private LiveData<List<User>> userList;

    private User currentUser;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        userList = userRepository.getUserList();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public LiveData<List<User>> getUserList() {
        return userList;
    }

    public void insert(User user) {
        userRepository.insert(user);
    }
}
