package com.example.igor.androidskilltest2.activities;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.igor.androidskilltest2.R;
import com.example.igor.androidskilltest2.models.User;

public class UserDetailActivity extends AppCompatActivity {

    public static Drawable tintDrawable(Drawable drawable, int tint) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, tint);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_ATOP);

        return drawable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        supportPostponeEnterTransition();

        TextView txtEdit = findViewById(R.id.txtEdit);
        setTintedCompoundDrawable(txtEdit, R.drawable.ic_edit_black_24dp, android.R.color.darker_gray);

        TextView textRemove = findViewById(R.id.txtRemove);
        setTintedCompoundDrawable(textRemove, R.drawable.ic_delete_black_24dp, android.R.color.darker_gray);

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

    private void setTintedCompoundDrawable(TextView textView, int drawableRes, int tintRes) {
        textView.setCompoundDrawablesWithIntrinsicBounds(
                tintDrawable(ContextCompat.getDrawable(UserDetailActivity.this, drawableRes),
                        ContextCompat.getColor(UserDetailActivity.this, tintRes)),  // Left
                null, // Top
                null, // Right
                null); //Bottom
    }
}
