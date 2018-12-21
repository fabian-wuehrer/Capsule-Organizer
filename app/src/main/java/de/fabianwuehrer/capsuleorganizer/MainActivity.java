package de.fabianwuehrer.capsuleorganizer;

import android.os.Bundle;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private CapsuleViewModel capsuleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        capsuleViewModel = ViewModelProviders.of(this).get(CapsuleViewModel.class);
        capsuleViewModel.getAllCapsules().observe(this, new Observer<List<Capsule>>() {
            @Override
            public void onChanged(List<Capsule> capsules) {

            }
        });
    }
}
