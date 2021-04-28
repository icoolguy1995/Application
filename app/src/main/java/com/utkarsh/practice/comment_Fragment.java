package com.utkarsh.practice;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class comment_Fragment extends Fragment implements  View.OnClickListener {

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference messageBase;
    MessageAdapter messageAdapter;
    User user;
    List <Message> message;        // For this message class has created:

    RecyclerView recyclerViewMsg;

    EditText editMessage;
    ImageButton imageButtonSend;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container , savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_comment_, container, false);
        intit ();

        return  rootView;


       // return inflater.inflate(R.layout.fragment_comment_, container, false);
    }

    private void intit() {

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        user = new User();

        recyclerViewMsg = (RecyclerView) getView().findViewById(R.id.rvMessage);
        editMessage = (EditText) getView().findViewById(R.id.msgboard);
        imageButtonSend = (ImageButton) getView().findViewById(R.id.send);

        imageButtonSend.setOnClickListener(this);
        message = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onStart() {
        super.onStart();
        final FirebaseUser currentUser = auth.getCurrentUser();

        user.setUseremail(currentUser.getEmail());

    }
}