package com.example.igor.androidskilltest2.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.igor.androidskilltest2.database.AppRoomDatabase;
import com.example.igor.androidskilltest2.interfaces.UserDAO;
import com.example.igor.androidskilltest2.models.User;

import java.util.List;

public class UserRepository {

    private UserDAO userDAO;
    private LiveData<List<User>> userList;

    public UserRepository(Application application) {
        AppRoomDatabase roomDatabase = AppRoomDatabase.getDatabase(application);
        userDAO = roomDatabase.userDAO();
        userList = userDAO.getAllUsers();
    }

    public LiveData<List<User>> getUserList() {
        return userList;
    }

    public void insert(User user) {
        new insertAsyncTask(userDAO).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDAO asyncTaskDao;

        insertAsyncTask(UserDAO dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            this.asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
