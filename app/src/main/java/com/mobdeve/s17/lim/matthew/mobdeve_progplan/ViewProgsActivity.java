package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityViewProgsBinding;

public class ViewProgsActivity extends AppCompatActivity {
	private ActivityViewProgsBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityViewProgsBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
	}
}
