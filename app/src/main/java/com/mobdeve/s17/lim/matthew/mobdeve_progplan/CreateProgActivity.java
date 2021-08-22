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
	}
}
