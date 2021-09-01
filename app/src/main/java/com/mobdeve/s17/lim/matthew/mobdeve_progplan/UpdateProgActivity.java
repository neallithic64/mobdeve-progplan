package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityUpdateProgBinding;

public class UpdateProgActivity extends AppCompatActivity {

	private ActivityUpdateProgBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityUpdateProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
//      TODO : Set up program progress item recycler adapter

		initOnCLick();
	}

	//    TODO: Set up Onclick Listeners
	private void initOnCLick(){
		binding.btnUpdateCancel.setOnClickListener(v->{
			Intent gotoViewIndivProg = new Intent(UpdateProgActivity.this,ViewIndivProgActivity.class);
			startActivity(gotoViewIndivProg);
			finish();
		});
		binding.btnUpdateSave.setOnClickListener(v->{
//        TODO: Save update of progressitems to database
			Intent gotoViewIndivProg = new Intent(UpdateProgActivity.this,ViewIndivProgActivity.class);
			startActivity(gotoViewIndivProg);
			finish();
		});
	}
}