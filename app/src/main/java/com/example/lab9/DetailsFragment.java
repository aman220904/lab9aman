package com.example.lab9;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DetailsFragment extends Fragment {

    private List<Message> messageList;
    private MessageAdapter adapter;
    private int selectedMessageIndex = -1; // To track the selected message

    public DetailsFragment() {
        super(R.layout.fragment_details);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.message_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize message list
        messageList = new ArrayList<>();
        messageList.add(new Message("Hi"));
        messageList.add(new Message("Hello"));
        messageList.add(new Message("How are you?"));

        // Set up the adapter
        adapter = new MessageAdapter(messageList, position -> {
            selectedMessageIndex = position; // Track selected message
        });
        recyclerView.setAdapter(adapter);
    }

    // Method to delete the selected message
    public void deleteSelectedMessage() {
        if (selectedMessageIndex >= 0 && selectedMessageIndex < messageList.size()) {
            messageList.remove(selectedMessageIndex);
            adapter.notifyItemRemoved(selectedMessageIndex);
            selectedMessageIndex = -1; // Reset selection
        } else {
            // No message selected
            Toast.makeText(getContext(), "No message selected", Toast.LENGTH_SHORT).show();
        }
    }
}
