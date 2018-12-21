package de.fabianwuehrer.capsuleorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditCapsuleActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "de.fabianwuehrer.capsuleorganizer.EXTRA_ID";

    public static final String EXTRA_NAME =
            "de.fabianwuehrer.capsuleorganizer.EXTRA_NAME";

    public static final String EXTRA_DESCRIPTION =
            "de.fabianwuehrer.capsuleorganizer.EXTRA_DESCRIPTION";

    public static final String EXTRA_COUNT =
            "de.fabianwuehrer.capsuleorganizer.EXTRA_COUNT";

    private EditText editTextName;
    private EditText editTextDescription;
    private NumberPicker numberPickerCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_capsule);

        editTextName = findViewById(R.id.edit_text_name);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerCount = findViewById(R.id.number_picker_count);

        numberPickerCount.setMinValue(0);
        numberPickerCount.setMaxValue(99);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Capsule");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerCount.setValue(intent.getIntExtra(EXTRA_COUNT, 1));
        }
        else {
            setTitle("Add Capsule");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_capsule_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_capsule:
                saveCapsule();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveCapsule(){
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();
        int count = numberPickerCount.getValue();

        if (name.trim().isEmpty()){
            Toast.makeText(this, "Please insert a name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent ();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_COUNT, count);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
