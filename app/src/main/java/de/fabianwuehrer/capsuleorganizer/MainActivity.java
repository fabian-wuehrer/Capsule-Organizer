package de.fabianwuehrer.capsuleorganizer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_CAPSULE_REQUEST = 1;

    private CapsuleViewModel capsuleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btnAddCapsule = findViewById(R.id.btn_add_capsule);
        btnAddCapsule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, AddCapsuleActivity.class);
                startActivityForResult(intent, ADD_CAPSULE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CapsuleAdapter adapter = new CapsuleAdapter();
        recyclerView.setAdapter(adapter);

        capsuleViewModel = ViewModelProviders.of(this).get(CapsuleViewModel.class);
        capsuleViewModel.getAllCapsules().observe(this, new Observer<List<Capsule>>() {
            @Override
            public void onChanged(List<Capsule> capsules) {
                adapter.submitList(capsules);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_CAPSULE_REQUEST && resultCode == RESULT_OK){
            String name = data.getStringExtra(AddCapsuleActivity.EXTRA_NAME);
            String description = data.getStringExtra(AddCapsuleActivity.EXTRA_DESCRIPTION);
            int count = data.getIntExtra(AddCapsuleActivity.EXTRA_COUNT, 1);

            Capsule capsule = new Capsule(name, description, count);
            capsuleViewModel.insert(capsule);

            Toast.makeText(this, "Capsule saved", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Capsule not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
