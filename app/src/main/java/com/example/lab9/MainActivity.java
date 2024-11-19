package com.example.lab9;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new DetailsFragment())
                .commit();


        // Find the Toolbar by its ID and set it as the app bar
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        // Optional: Customize the Toolbar (e.g., add a title)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Toolbar");
        }

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu layout
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.delete) {
            DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_container);

            if (detailsFragment != null) {
                detailsFragment.deleteSelectedMessage();
            } else {
                Toast.makeText(this, "No fragment found", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (id == R.id.aeroplanemode) {
            Toast.makeText(this, "Airplane mode on or off", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.about) {
            Toast.makeText(this, "Version 1.0, created by Amandeep Kaur", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Method to delete the selected chat message.
     * This method can be expanded to include fragment interaction or database updates.
     */
    private void deleteChatMessage() {
        // Assuming DetailsFragment is used to display chat messages
        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        if (detailsFragment != null) {
            detailsFragment.deleteSelectedMessage(); // Call fragment-specific deletion logic
        } else {
            Toast.makeText(this, "No message selected", Toast.LENGTH_SHORT).show();
        }
    }
}
