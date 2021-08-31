package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityEvaluateProgBinding;

public class EvaluateProgActivity extends AppCompatActivity {
	private ActivityEvaluateProgBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityEvaluateProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initOnClick();
	}

	private void initOnClick(){
		binding.btnEvalCancel.setOnClickListener( v -> {
			Intent gotoViewIndivProg = new Intent(EvaluateProgActivity.this, ViewIndivProgActivity.class);
			startActivity(gotoViewIndivProg);
			finish();
		});

		binding.btnEvalSave.setOnClickListener( v -> {
//      TODO : Save changes to database
			Intent gotoViewIndivProg = new Intent(EvaluateProgActivity.this, ViewIndivProgActivity.class);
			startActivity(gotoViewIndivProg);
			finish();
		});
	}
}
