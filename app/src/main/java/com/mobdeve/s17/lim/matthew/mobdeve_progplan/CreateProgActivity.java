package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityCreateProgBinding;

public class CreateProgActivity extends AppCompatActivity {
	private ActivityCreateProgBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityCreateProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initOnClick();
	}

	private void initOnClick() {
		binding.ivGps.setOnClickListener(v -> {
			// TODO: implement the modal for the maps
		});

		binding.ivAddResource.setOnClickListener(v -> {
			// TODO: add table row
		});

		binding.ivAddOutcome.setOnClickListener(v -> {
			// TODO: add table row
		});

		binding.btnCreateSave.setOnClickListener(v -> {
			// TODO: create HTTP request to server
		});

		binding.btnCreateCancel.setOnClickListener(v -> {
			// TODO: go back to page
		});
	}
}
