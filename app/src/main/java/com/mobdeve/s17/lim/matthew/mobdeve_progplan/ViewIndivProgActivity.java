package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityViewIndivProgBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Outcome;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.ProgChecklist;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Resource;

import java.util.ArrayList;

public class ViewIndivProgActivity extends AppCompatActivity {
	private ActivityViewIndivProgBinding binding;
	private ArrayList<Outcome> outcomeArrayList;
	private ArrayList<Resource> resourceArrayList;
	private ArrayList<ProgChecklist> progChecklistArrayList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityViewIndivProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initOnClick();

		initOutcomes();
		loadOutcomes();
		initResources();
		loadResources();
		initializeProgressData();
		loadChecklist();
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
	private void initOutcomes(){
		outcomeArrayList = new ArrayList<>();
		outcomeArrayList.add(new Outcome("Outcome 1", "PI00001", 3000));
		outcomeArrayList.add(new Outcome("Outcome 2", "PI00001", 500));
		outcomeArrayList.add(new Outcome("Outcome 3", "PI00001", 1000));
	}

	private void initResources(){
		resourceArrayList = new ArrayList<>();
		resourceArrayList.add(new Resource("Resource 1","PI00001",1000));
		resourceArrayList.add(new Resource("Resource 2","PI00001",4500));
		resourceArrayList.add(new Resource("Resource 3","PI00001",3000));
		resourceArrayList.add(new Resource("Resource 4","PI00001",2000));
	}

	private void initializeProgressData(){
		progChecklistArrayList = new ArrayList<ProgChecklist>();
		progChecklistArrayList.add(new ProgChecklist("Sample Item 1","Prog000001",true));
		progChecklistArrayList.add(new ProgChecklist("Sample Item 2","Prog000001",true));
		progChecklistArrayList.add(new ProgChecklist("Sample Item 3","Prog000001",false));
		progChecklistArrayList.add(new ProgChecklist("Sample Item 4","Prog000001",false));
	}

	private void loadOutcomes(){
		int i = 0;

		while (i < outcomeArrayList.size())
		{
			TableRow newRow = new TableRow(this);
			TextView name = new TextView(this);
			TextView expectedval = new TextView(this);


			name.setText(outcomeArrayList.get(i).getOutcomeName());
			name.setBackground(getDrawable(R.drawable.border));
			name.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.7f));
//			name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			name.setTextColor(getColor(R.color.black));
			newRow.addView(name);

			expectedval.setText(Integer.toString(outcomeArrayList.get(i).getExpectedVal()));
			expectedval.setBackground(getDrawable(R.drawable.border));
			expectedval.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.3f));
//			expectedval.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			expectedval.setTextColor(getColor(R.color.black));
			newRow.addView(expectedval);

			binding.tlOutcomes.addView(newRow);

			i++;
		}
	}

	private void loadResources(){
		int i = 0;

		while (i < outcomeArrayList.size())
		{
			TableRow newRow = new TableRow(this);
			TextView name = new TextView(this);
			TextView expectedval = new TextView(this);


			name.setText(resourceArrayList.get(i).getResourceName());
			name.setBackground(getDrawable(R.drawable.border));
			name.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.7f));
//			name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			name.setTextColor(getColor(R.color.black));
			newRow.addView(name);

			expectedval.setText(Integer.toString(resourceArrayList.get(i).getExpectedAmt()));
			expectedval.setBackground(getDrawable(R.drawable.border));
			expectedval.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.3f));
//			expectedval.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			expectedval.setTextColor(getColor(R.color.black));
			newRow.addView(expectedval);

			binding.tlResources.addView(newRow);

			i++;
		}
	}
	private void loadChecklist(){
		int i = 0;

		while (i < outcomeArrayList.size())
		{
			TableRow newRow = new TableRow(this);
			TextView name = new TextView(this);
			TextView expectedval = new TextView(this);


			if(progChecklistArrayList.get(i).isChecked())
				name.setText("Done");
			else
				name.setText("In Progress");
			name.setBackground(getDrawable(R.drawable.border));
			name.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.2f));
//			name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			name.setTextColor(getColor(R.color.black));
			newRow.addView(name);

			expectedval.setText(progChecklistArrayList.get(i).getProgItem());
			expectedval.setBackground(getDrawable(R.drawable.border));
			expectedval.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.8f));
//			expectedval.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			expectedval.setTextColor(getColor(R.color.black));
			newRow.addView(expectedval);

			binding.tlProgress.addView(newRow);

			i++;
		}
	}
}