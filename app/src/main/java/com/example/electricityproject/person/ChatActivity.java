package com.example.electricityproject.person;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.electricityproject.R;
import com.hyphenate.easeui.ui.EaseChatFragment;

public class ChatActivity extends AppCompatActivity {

    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_chat_activity);
        activityInstance = this;
        chatFragment = new EaseChatFragment();
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }
}
