package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.adapters.ProgramAdapter;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.adapters.ProgressAdapter;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityUpdateProgBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.ProgChecklist;

import java.util.ArrayList;

public class UpdateProgActivity extends AppCompatActivity {

	private ActivityUpdateProgBinding binding;
	private ArrayList<ProgChecklist>	progChecklistArrayList;
	private ProgressAdapter	progressAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityUpdateProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
//      TODO : Set up program progress item recycler adapter

		initializeProgressData();
		progressAdapter = new ProgressAdapter(progChecklistArrayList,getApplicationContext());
		binding.rvProgprogresslist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
		binding.rvProgprogresslist.setAdapter(progressAdapter);

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

	private void initializeProgressData(){
		progChecklistArrayList = new ArrayList<ProgChecklist>();
		progChecklistArrayList.add(new ProgChecklist("Sample Item 1","Prog000001",true));
		progChecklistArrayList.add(new ProgChecklist("Sample Item 2","Prog000001",true));
		progChecklistArrayList.add(new ProgChecklist("Sample Item 3","Prog000001",false));
		progChecklistArrayList.add(new ProgChecklist("Sample Item 4","Prog000001",false));
	}
}