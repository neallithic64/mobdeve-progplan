package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.adapters.ProgramAdapter;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityViewProgsBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Program;

import java.util.ArrayList;
import java.util.Date;

public class ViewProgsActivity extends AppCompatActivity {

	private ActivityViewProgsBinding 	binding;
	private ArrayList<Program>			programArrayList;
	private ProgramAdapter				programAdapter;
	private int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityViewProgsBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		initializeProgramData();
		programAdapter = new ProgramAdapter(programArrayList,getApplicationContext());
		binding.rvProglist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
		binding.rvProglist.setAdapter(programAdapter);

		initOnClick();
	}

	@Override
	protected void onResume() {
		super.onResume();
		position = -1;
	}

	private void initOnClick(){
		binding.fabAddProg.setOnClickListener(v->{
			Intent gotoCreateProg = new Intent(ViewProgsActivity.this,CreateProgActivity.class);

			startActivity(gotoCreateProg);
		});

//		TODO Create intent and binding for individual programs
		binding.rvProglist.setOnClickListener(view->{
			position = programAdapter.getSelected();
//			if(position != -1){
//				TODO: Modify code to save the program number to bundle
//				Code does not take into acccount different programs
				Intent gotoViewIndivProg = new Intent(ViewProgsActivity.this, ViewIndivProgActivity.class);
				startActivity(gotoViewIndivProg);
//			}
		});
	}

	private void initializeProgramData(){
		programArrayList = new ArrayList<>();
		programArrayList.add(new Program("PI000001","User01","Sample Program 1",
				new Date(),new Date(),"Sample Street Name", "Malabon City",10, "In Progress"));
		programArrayList.add(new Program("PI000002","User01","Sample Program 2",
				new Date(),new Date(),"Sample Street Name", "Quezon City",10, "In Progress"));
	}
}
