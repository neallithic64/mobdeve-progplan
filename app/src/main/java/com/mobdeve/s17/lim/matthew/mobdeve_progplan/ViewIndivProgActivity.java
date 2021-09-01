package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityViewIndivProgBinding;

public class ViewIndivProgActivity extends AppCompatActivity {
	private ActivityViewIndivProgBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityViewIndivProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initOnClick();
	}

	private void initOnClick(){
		binding.btnEdit.setOnClickListener(v->{
			Intent gotoEditProg = new Intent(ViewIndivProgActivity.this,EditProgActivity.class);
			startActivity(gotoEditProg);
			finish();
		});
		binding.btnUpdate.setOnClickListener(v->{
			Intent gotoUpdateProg = new Intent(ViewIndivProgActivity.this,UpdateProgActivity.class);
			startActivity(gotoUpdateProg);
			finish();
		});
		binding.btnEvaluate.setOnClickListener(v->{
			Intent gotoEvaluateProg = new Intent(ViewIndivProgActivity.this,EvaluateProgActivity.class);
			startActivity(gotoEvaluateProg);
			finish();
		});
	}
}