package com.example.seng_group_app_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class activitynetwork2 extends AppCompatActivity {


    private EditText messageInput;
    private Button sendButton;
    private RecyclerView chatRecyclerView;
    private Activitynetwork.MessageAdapter messageAdapter;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitynetwork);

        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        btnHome = findViewById(R.id.btnHome);

        messageAdapter = new Activitynetwork.MessageAdapter(new ArrayList<String>());
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageInput.getText().toString();
                if (!message.isEmpty()) {
                    writeMessageToFile(message);
                    messageInput.setText("");
                    updateMessagesDisplay();
                } else {
                    Toast.makeText(activitynetwork2.this, "Please enter a message", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activitynetwork2.this, home2.class);
                startActivity(intent);
            }
        });
        updateMessagesDisplay();
    }

    private void updateMessagesDisplay() {
        List<String> messages = Arrays.asList(readMessagesFromFile().split("\n"));
        messageAdapter = new Activitynetwork.MessageAdapter(messages);
        chatRecyclerView.setAdapter(messageAdapter);
    }

    private void writeMessageToFile(String message) {
        try {
            FileOutputStream fos = openFileOutput("Chat.txt", MODE_PRIVATE | MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(message + "\n");
            outputStreamWriter.close();
        } catch (IOException e) {
            Toast.makeText(this, "Error writing message to file", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private String readMessagesFromFile() {
        StringBuilder messages = new StringBuilder();

        try {
            FileInputStream fis = openFileInput("Chat.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                messages.append(line).append("\n");
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return messages.toString();
    }

    public class MessageAdapter extends RecyclerView.Adapter<Activitynetwork.MessageAdapter.ChatMessageViewHolder> {
        private List<String> messages;

        public MessageAdapter(List<String> messages) {
            this.messages = messages;
        }

        @Override
        public Activitynetwork.MessageAdapter.@NonNull ChatMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new Activitynetwork.MessageAdapter.ChatMessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(Activitynetwork.MessageAdapter.@NonNull ChatMessageViewHolder holder, int position) {
            holder.messageText.setText(messages.get(position));
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

        class ChatMessageViewHolder extends RecyclerView.ViewHolder {
            TextView messageText;

            public ChatMessageViewHolder(@NonNull View itemView) {
                super(itemView);
                messageText = itemView.findViewById(android.R.id.text1);
            }
        }
    }
}