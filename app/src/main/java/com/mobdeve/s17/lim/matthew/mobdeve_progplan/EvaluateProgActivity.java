package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityEvaluateProgBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.APIClient;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Feedback;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Outcome;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.ProgChecklist;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Program;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Resource;

import java.util.ArrayList;
import java.util.Date;

public class EvaluateProgActivity extends AppCompatActivity {
	private ActivityEvaluateProgBinding binding;
	private ArrayList<Outcome> outcomeArrayList;
	private ArrayList<Resource> resourceArrayList;
	private Program	program;
	private Feedback feedback;

	private APIClient apiClient;
	private Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityEvaluateProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		bundle = new Bundle();
		bundle = getIntent().getExtras();

		program = (Program) bundle.getParcelable("program");
		outcomeArrayList = bundle.getParcelableArrayList("outcomes");
		resourceArrayList = bundle.getParcelableArrayList("resources");

		binding.tvProgName.setText(program.getProgramTitle());

		initOnClick();

		loadResources();
		loadOutcomes();
	}


	private void initOnClick(){
		binding.btnEvalCancel.setOnClickListener( v -> {
			returnToViewProg();
		});

		binding.btnEvalSave.setOnClickListener( v -> {
//      TODO : Save changes to database
//			Intent gotoViewIndivProg = new Intent(EvaluateProgActivity.this, ViewIndivProgActivity.class);
//			gotoViewIndivProg.putExtras(bundle);
//			startActivity(gotoViewIndivProg);
//			finish();
//			if(validateInput())

		});
	}

	private void returnToViewProg(){
		Intent gotoViewIndivProg = new Intent(EvaluateProgActivity.this,ViewIndivProgActivity.class);
		gotoViewIndivProg.putExtras(bundle);
		startActivity(gotoViewIndivProg);
		finish();
	}

	@Override
	public void onBackPressed() {
		returnToViewProg();
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
			actualval.setInputType(InputType.TYPE_CLASS_NUMBER);
			actualval.setTextColor(getColor(R.color.black));
			// The specified child already has a parent. You must call removeView() on the child's parent first.
			newRow.addView(actualval);

			binding.tlOutcomes.addView(newRow);

			i++;
		}
	}

	private void loadResources(){
		int i = 0;

		while (i < resourceArrayList.size())
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
			actualval.setTextSize(12);
			actualval.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.25f));
			actualval.setHint("Actual Value");
			actualval.setTextColor(getColor(R.color.black));
			actualval.setInputType(InputType.TYPE_CLASS_NUMBER);
			// The specified child already has a parent. You must call removeView() on the child's parent first.
			newRow.addView(actualval);
			binding.tlResources.addView(newRow);

			i++;
		}
	}


	private boolean validateInput(){
		int i = 0;
		if(binding.etComments.getText().toString().isEmpty()) {
			Toast.makeText(EvaluateProgActivity.this,"One or more fields are empty",Toast.LENGTH_LONG).show();
			return false;
		}else{
			try{
				String name;
				int val;

				if(binding.tlResources!= null)
				{
					for ( i = 1; i < binding.tlResources.getChildCount();i++){
						View resourceRow = binding.tlResources.getChildAt(i);
						if(resourceRow instanceof TableRow){

							View resourceData = ((TableRow) resourceRow).getChildAt(2);
							if (((EditText) resourceData).getText().toString().isEmpty() || Integer.parseInt(((EditText) resourceData).getText().toString()) <= 0)
								throw new Exception("Invalid Input found in Resources Table");
							val = Integer.parseInt(((EditText) resourceData).getText().toString());

							resourceArrayList.get(i-1).setActualAmt(val);
						}
					}

				} else throw new Exception("No resource table found");

				if(binding.tlOutcomes!= null)
				{
					for (i = 1; i < binding.tlOutcomes.getChildCount();i++){
						View outcomeRow = binding.tlOutcomes.getChildAt(i);
						if(outcomeRow instanceof TableRow){

							View outcomeData = ((TableRow) outcomeRow).getChildAt(2);

							if(((EditText) outcomeData).getText().toString().isEmpty() || Integer.parseInt(((EditText) outcomeData).getText().toString()) <= 0)
								throw new Exception("Invalid Input found in Outcome Table");
							val = Integer.parseInt(((EditText) outcomeData).getText().toString());

							outcomeArrayList.get(i-1).setActualVal(val);
						}
					}
				} else throw new Exception("No outcome table found");
			}catch (Exception e) {
				Toast.makeText(EvaluateProgActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
				return false;
			}
		}

		return true;
	}

	private void evaluateProgram(){
		feedback = new Feedback("",program.getProgramId(),binding.etComments.getText().toString());

	}
}
