package de.fabianwuehrer.capsuleorganizer;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_CAPSULE_REQUEST = 1;
    public static final int EDIT_CAPSULE_REQUEST = 2;


    private CapsuleViewModel capsuleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btnAddCapsule = findViewById(R.id.btn_add_capsule);
        btnAddCapsule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, AddEditCapsuleActivity.class);
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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                capsuleViewModel.delete(adapter.getCapsulesAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Capsule deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new CapsuleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Capsule capsule) {
                Intent intent = new Intent(MainActivity.this, AddEditCapsuleActivity.class);
                intent.putExtra(AddEditCapsuleActivity.EXTRA_ID, capsule.getId());
                intent.putExtra(AddEditCapsuleActivity.EXTRA_NAME, capsule.getName());
                intent.putExtra(AddEditCapsuleActivity.EXTRA_DESCRIPTION, capsule.getDescription());
                intent.putExtra(AddEditCapsuleActivity.EXTRA_COUNT, capsule.getCount());
                startActivityForResult(intent, EDIT_CAPSULE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_CAPSULE_REQUEST && resultCode == RESULT_OK){
            String name = data.getStringExtra(AddEditCapsuleActivity.EXTRA_NAME);
            String description = data.getStringExtra(AddEditCapsuleActivity.EXTRA_DESCRIPTION);
            int count = data.getIntExtra(AddEditCapsuleActivity.EXTRA_COUNT, 1);

            Capsule capsule = new Capsule(name, description, count);
            capsuleViewModel.insert(capsule);

            Toast.makeText(this, "Capsule saved", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == EDIT_CAPSULE_REQUEST && resultCode == RESULT_OK){
            int id =data.getIntExtra(AddEditCapsuleActivity.EXTRA_ID, -1);

            if (id==-1){
                Toast.makeText(this, "Capsule couldn't be saved", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = data.getStringExtra(AddEditCapsuleActivity.EXTRA_NAME);
            String description = data.getStringExtra(AddEditCapsuleActivity.EXTRA_DESCRIPTION);
            int count = data.getIntExtra(AddEditCapsuleActivity.EXTRA_COUNT, 1);

            Capsule capsule = new Capsule(name, description, count);
            capsule.setId(id);
            capsuleViewModel.update(capsule);

            Toast.makeText(this, "Capsule updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "canceled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
