package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityEvaluateProgBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Outcome;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.ProgChecklist;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Resource;

import java.util.ArrayList;

public class EvaluateProgActivity extends AppCompatActivity {
	private ActivityEvaluateProgBinding binding;
	private ArrayList<Outcome> outcomeArrayList;
	private ArrayList<Resource> resourceArrayList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityEvaluateProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initOnClick();

		initOutcomes();
		initResources();
		loadOutcomes();
		loadResources();
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

	private void initOutcomes(){
		outcomeArrayList = new ArrayList<>();
		outcomeArrayList.add(new Outcome("Outcome 1", "PI00001", 3000));
		outcomeArrayList.add(new Outcome("Outcome 2", "PI00001", 500));
	}

	private void initResources(){
		resourceArrayList = new ArrayList<>();
		resourceArrayList.add(new Resource("Resource 1","PI00001",1000));
		resourceArrayList.add(new Resource("Resource 2","PI00001",4500));
	}

	private void loadOutcomes(){
		int i = 0;

		while (i < outcomeArrayList.size())
		{
			TableRow newRow = new TableRow(this);
			TextView name = new TextView(this);
			TextView expectedval = new TextView(this);
			EditText actualval = new EditText(this);


			name.setText(outcomeArrayList.get(i).getOutcomeName());
			name.setBackground(getDrawable(R.drawable.border));
			name.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.5f));
//			name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			name.setTextColor(getColor(R.color.black));
			newRow.addView(name);

			expectedval.setText(Integer.toString(outcomeArrayList.get(i).getExpectedVal()));
			expectedval.setBackground(getDrawable(R.drawable.border));
			expectedval.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.25f));
//			expectedval.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			expectedval.setTextColor(getColor(R.color.black));
			newRow.addView(expectedval);

			actualval.setBackground(getDrawable(R.drawable.border));
			actualval.setTextSize(12);
			actualval.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.25f));
			actualval.setHint("Actual Value");
			actualval.setTextColor(getColor(R.color.black));
			// The specified child already has a parent. You must call removeView() on the child's parent first.
			newRow.addView(actualval);

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
			EditText actualval = new EditText(this);

			name.setText(resourceArrayList.get(i).getResourceName());
			name.setBackground(getDrawable(R.drawable.border));
			name.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.5f));
//			name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			name.setTextColor(getColor(R.color.black));
			newRow.addView(name);

			expectedval.setText(Integer.toString(resourceArrayList.get(i).getExpectedAmt()));
			expectedval.setBackground(getDrawable(R.drawable.border));
			expectedval.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.25f));
//			expectedval.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			expectedval.setTextColor(getColor(R.color.black));
			newRow.addView(expectedval);

			actualval.setBackground(getDrawable(R.drawable.border));
			actualval.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.25f));
			actualval.setHint("Actual Value");
			actualval.setTextColor(getColor(R.color.black));
			// The specified child already has a parent. You must call removeView() on the child's parent first.
			newRow.addView(actualval);
			binding.tlResources.addView(newRow);

			i++;
		}
	}
}
