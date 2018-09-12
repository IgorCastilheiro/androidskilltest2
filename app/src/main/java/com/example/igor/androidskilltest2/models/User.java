package com.example.igor.androidskilltest2.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity
public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "email")
    @NonNull
    private String email;

    @ColumnInfo(name = "first_name")
    @NonNull
    private String firstName;

    @ColumnInfo(name = "password")
    @NonNull
    private String password;

    public User(@NonNull String email, @NonNull String firstName, @NonNull String password) {
        this.email = email;
        this.firstName = firstName;
        this.password = password;
    }

    protected User(Parcel in) {
        uid = in.readInt();
        email = in.readString();
        firstName = in.readString();
        password = in.readString();
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.uid);
        parcel.writeString(this.email);
        parcel.writeString(this.firstName);
        parcel.writeString(this.password);
    }
}
