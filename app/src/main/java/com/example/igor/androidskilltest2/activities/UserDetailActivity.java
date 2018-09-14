package com.example.igor.androidskilltest2.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.igor.androidskilltest2.R;
import com.example.igor.androidskilltest2.models.User;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        supportPostponeEnterTransition();


        Bundle extras = getIntent().getExtras();
        User user;
        if (extras != null) {
            user = extras.getParcelable(MainActivity.EXTRA_USER_ITEM);

            ImageView imgAvatar = findViewById(R.id.imgAvatar);
            TextView txtName = findViewById(R.id.txtName);
            TextView txtEmail = findViewById(R.id.txtEmail);

            txtName.setText(user.getFirstName());
            txtEmail.setText(user.getEmail());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                String imageTransitionName = extras.getString(MainActivity.EXTRA_USER_IMAGE_TRANSITION_NAME);
                imgAvatar.setTransitionName(imageTransitionName);
                supportStartPostponedEnterTransition();
            }
        }

    }
}
