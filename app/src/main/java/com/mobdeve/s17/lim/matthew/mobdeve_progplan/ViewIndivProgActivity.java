package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.apimodels.LoginResponse;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityViewIndivProgBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.APIClient;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Feedback;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Outcome;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.ProgChecklist;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Program;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.ProgramJS;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Resource;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewIndivProgActivity extends AppCompatActivity {
	private ActivityViewIndivProgBinding binding;
	private ArrayList<Outcome> outcomeArrayList;
	private ArrayList<Resource> resourceArrayList;
	private ArrayList<ProgChecklist> progChecklistArrayList;
	private Program	program;
	private Bundle bundle;
	private Feedback feedback;
	private APIClient apiClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityViewIndivProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initOnClick();

		bundle = new Bundle();
		bundle = getIntent().getExtras();
	}

	@Override
	protected void onResume() {
		super.onResume();
		program = (Program) bundle.getParcelable("program");
		getProgramDetails();
//		Toast.makeText(ViewIndivProgActivity.this,program.getProgramId(),Toast.LENGTH_LONG).show();
	}

	private void initOnClick(){
		binding.btnEdit.setOnClickListener(v->{
			Intent gotoEditProg = new Intent(ViewIndivProgActivity.this,EditProgActivity.class);
			bundle.putParcelable("program", program);
			gotoEditProg.putExtras(bundle);
			startActivity(gotoEditProg);
			finish();
		});
		binding.btnUpdate.setOnClickListener(v->{
			Intent gotoUpdateProg = new Intent(ViewIndivProgActivity.this,UpdateProgActivity.class);
			bundle.putParcelable("program", program);
			bundle.putParcelableArrayList("progchecklist", progChecklistArrayList);
			gotoUpdateProg.putExtras(bundle);
			startActivity(gotoUpdateProg);
			finish();
		});
		binding.btnEvaluate.setOnClickListener(v->{
			Intent gotoEvaluateProg = new Intent(ViewIndivProgActivity.this,EvaluateProgActivity.class);
			bundle.putParcelable("program", program);
			bundle.putParcelableArrayList("outcomes",outcomeArrayList);
			bundle.putParcelableArrayList("resources",resourceArrayList);
			gotoEvaluateProg.putExtras(bundle);
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
		int i;

		binding.tlOutcomes.removeViews(1, binding.tlOutcomes.getChildCount() - 1);

		i = 0;
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

		binding.tlResources.removeViews(1, binding.tlResources.getChildCount() - 1);

		while (i < resourceArrayList.size())
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

		binding.tlProgress.removeViews(1, binding.tlProgress.getChildCount() - 1);

		while (i < progChecklistArrayList.size())
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

	private void updateUI(){
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

		loadChecklist();
		loadResources();
		loadOutcomes();

		if(feedback != null){
			Toast.makeText(ViewIndivProgActivity.this,feedback.getComments(),Toast.LENGTH_SHORT).show();
			binding.btnEvaluate.setVisibility(View.INVISIBLE);
			binding.btnEdit.setVisibility(View.INVISIBLE);
			binding.tvComments.setVisibility(View.VISIBLE);
			binding.tvCommentsval.setVisibility(View.VISIBLE);
			binding.tvCommentsval.setText(feedback.getComments());
		}
		binding.tvProgName.setText(program.getProgramTitle());
		binding.tvDateRange.setText(formatter.format(program.getStartDate()) + " - " +
				formatter.format(program.getEndDate()));
		binding.tvLocation.setText(program.getStreet() + ", " + program.getCity());

	}
	private void getProgramDetails(){
		outcomeArrayList = new ArrayList<>();
		resourceArrayList = new ArrayList<>();
		progChecklistArrayList = new ArrayList<>();

		Log.d("Testing","Entered getProgramDetails");
		Call<ProgramJS> call = apiClient.APIservice.getProgramDetails(program.getProgramId());
		call.enqueue(new Callback<ProgramJS>() {
			@Override
			public void onResponse(Call<ProgramJS> call, Response<ProgramJS> response) {
				try {
					if (response.body() != null) {
						outcomeArrayList = (ArrayList<Outcome>) response.body().getOutcomes();
						resourceArrayList = (ArrayList<Resource>) response.body().getResources();
						progChecklistArrayList = (ArrayList<ProgChecklist>) response.body().getChecklist();
						program = (Program) response.body().getProgram();
//						feedback = (Feedback) response.body().getFeedback();

//						Toast.makeText(ViewIndivProgActivity.this,feedback.toString(),Toast.LENGTH_SHORT).show();
						updateUI();
//						Log.d("TestingGetProgDetails", response.body().toString());
					} else Toast.makeText(ViewIndivProgActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					Log.e("failedGetDetails", e.getMessage());
				}
			}
			@Override
			public void onFailure(Call<ProgramJS> call, Throwable t) {
				Log.e("failedGetDetails", t.getMessage());
			}
		});
	}
}