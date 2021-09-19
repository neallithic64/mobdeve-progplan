package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityCreateProgBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.*;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Calendar;
import java.util.Date;

public class CreateProgActivity extends AppCompatActivity {
	private ActivityCreateProgBinding binding;
	private APIClient apiClient;
	private DatePickerDialog picker;
	private Bundle bundle;

	private Program	newProgram;
	private ArrayList<Outcome> outcomeArrayList;
	private ArrayList<Resource> resourceArrayList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityCreateProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		apiClient = new APIClient();
		bundle = new Bundle();
		bundle = getIntent().getExtras();

		initOnClick();
	}

	private void initOnClick() {
		binding.ivGps.setOnClickListener(v -> {
			// TODO: implement the modal for the maps
			Intent gotoMapActivity = new Intent(CreateProgActivity.this, MapsActivity.class);
			startActivityForResult(gotoMapActivity, 800);
		});

		binding.ivAddResource.setOnClickListener(v -> {
			// TODO: add table row
			TableRow newRow = new TableRow(this);
			EditText name, value;

			name = new EditText(this);
			value = new EditText(this);

			name.setBackground(getDrawable(R.drawable.border));
			name.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.7f));
//			name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			name.setTextColor(getColor(R.color.black));
			name.setHint("Resource Name");
			name.setTextSize(12);
			newRow.addView(name);

			value.setBackground(getDrawable(R.drawable.border));
			value.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.3f));
			value.setHint("Value");
			value.setTextSize(12);
			value.setTextColor(getColor(R.color.black));
			value.setInputType(InputType.TYPE_CLASS_NUMBER);
			// The specified child already has a parent. You must call removeView() on the child's parent first.
			 newRow.addView(value);

			binding.tlResources.addView(newRow);
			Log.d("TableLayout", "TableRow Count: " + binding.tlResources.getChildCount());
		});

		binding.ivAddOutcome.setOnClickListener(v -> {
			// TODO: add table row
			TableRow newRow = new TableRow(this);
			EditText name, value;

			name = new EditText(this);
			value = new EditText(this);

			name.setBackground(getDrawable(R.drawable.border));
			name.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.7f));
			name.setTextColor(getColor(R.color.black));
			name.setTextSize(12);
			name.setHint("Outcome Name");
			newRow.addView(name);

			value.setBackground(getDrawable(R.drawable.border));
			value.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.3f));
			value.setHint("Value");
			value.setTextColor(getColor(R.color.black));
			value.setTextSize(12);
			value.setInputType(InputType.TYPE_CLASS_NUMBER);
			// The specified child already has a parent. You must call removeView() on the child's parent first.
			newRow.addView(value);

			binding.tlOutcomes.addView(newRow);
		});

		binding.btnCreateSave.setOnClickListener(v -> {
			// TODO: Validate and Create HTTP request to server

//			Intent gotoViewProgs = new Intent(CreateProgActivity.this,ViewIndivProgActivity.class );
//			startActivity(gotoViewProgs);
//			finish();
			if(validateInput()){
				try{
					outcomeArrayList = new ArrayList<>();
					resourceArrayList = new ArrayList<>();
					String name;
					int val;

					if(binding.tlResources!= null)
					{
						for (int i = 1; i < binding.tlResources.getChildCount();i++){
							View resourceRow = binding.tlResources.getChildAt(i);
							if(resourceRow instanceof TableRow){

								View resourceData = ((TableRow) resourceRow).getChildAt(0);
								if (((EditText) resourceData).getText().toString().isEmpty())
									throw new Exception("Invalid Input found in Resources Table");
								name = ((EditText) resourceData).getText().toString();

								resourceData = ((TableRow) resourceRow).getChildAt(1);
								if(((EditText) resourceData).getText().toString().isEmpty() || Integer.parseInt(((EditText) resourceData).getText().toString()) <= 0)
									throw new Exception("Invalid Input found in Outcome Table");
								val = Integer.parseInt(((EditText) resourceData).getText().toString());

								resourceArrayList.add(new Resource(name,val));
							}
						}

					} else throw new Exception("No resource table found");

					if(binding.tlOutcomes!= null)
					{
						for (int i = 1; i < binding.tlOutcomes.getChildCount();i++){
							View outcomeRow = binding.tlOutcomes.getChildAt(i);
							if(outcomeRow instanceof TableRow){

								View outcomeData = ((TableRow) outcomeRow).getChildAt(0);
								if (((EditText) outcomeData).getText().toString().isEmpty())
									throw new Exception("Invalid Input found in Outcome Table");
								name = ((EditText) outcomeData).getText().toString();

								outcomeData = ((TableRow) outcomeRow).getChildAt(1);
								if(((EditText) outcomeData).getText().toString().isEmpty() || Integer.parseInt(((EditText) outcomeData).getText().toString()) <= 0)
									throw new Exception("Invalid Input found in Outcome Table");
								val = Integer.parseInt(((EditText) outcomeData).getText().toString());

								outcomeArrayList.add(new Outcome(name,val));
							}
						}
					} else throw new Exception("No outcome table found");

					int commaplacement = binding.etLocation.getText().toString().indexOf(',');

					postCreateProgram(binding.etProgName.getText().toString().trim(), new Date(binding.etDateRange1.getText().toString()),
							new Date(binding.etDateRange2.getText().toString()),binding.etLocation.getText().toString().substring(0,commaplacement),
							binding.etLocation.getText().toString().substring(commaplacement + 2));
				}catch (Exception e) {
					Toast.makeText(CreateProgActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}

		});

		binding.btnCreateCancel.setOnClickListener(v -> {
			// TODO: go back to page
			Intent gotoViewProgs = new Intent(CreateProgActivity.this, ViewProgsActivity.class);
			startActivity(gotoViewProgs);
			finish();
		});

		binding.etDateRange1.setOnClickListener(v->{
			final Calendar cldr = Calendar.getInstance();
			int day = cldr.get(Calendar.DAY_OF_MONTH);
			int month = cldr.get(Calendar.MONTH);
			int year = cldr.get(Calendar.YEAR);
			// date picker dialog
			picker = new DatePickerDialog(CreateProgActivity.this,
					new DatePickerDialog.OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
							binding.etDateRange1.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
						}
					}, year, month, day);
			picker.show();

		});

		binding.etDateRange2.setOnClickListener(v->{
			final Calendar cldr = Calendar.getInstance();
			int day = cldr.get(Calendar.DAY_OF_MONTH);
			int month = cldr.get(Calendar.MONTH);
			int year = cldr.get(Calendar.YEAR);
			// date picker dialog
			picker = new DatePickerDialog(CreateProgActivity.this,
					new DatePickerDialog.OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
							binding.etDateRange2.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
						}
					}, year, month, day);
			picker.show();

		});
	}

	private boolean validateInput(){
		if(binding.etProgName.getText().toString().isEmpty() || binding.etDateRange1.getText().toString().isEmpty() ||
				binding.etDateRange2.getText().toString().isEmpty() || binding.tlOutcomes.getChildCount() == 1 ||
				binding.tlResources.getChildCount() == 1) {
			Toast.makeText(CreateProgActivity.this,"One or more fields are empty", Toast.LENGTH_LONG).show();
			return false;
		}else if(binding.etLocation.getText().toString().isEmpty() || binding.etLocation.getText().toString().indexOf(',') == -1) {
			Toast.makeText(CreateProgActivity.this,"Location is invalid",Toast.LENGTH_LONG).show();
			return false;
		} else{
			Date date1 = new Date(binding.etDateRange1.getText().toString());
			Date date2 = new Date(binding.etDateRange2.getText().toString());

			if (date1.before(new Date())){
				Toast.makeText(CreateProgActivity.this,"Start Date should be before today",Toast.LENGTH_LONG).show();
				return false;
			}
			else if (date1.after(date2)){
				Toast.makeText(CreateProgActivity.this,"End Date should be after Start Date", Toast.LENGTH_LONG).show();
				return false;
			}

		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Activity.RESULT_OK && requestCode == 800){
			Bundle locbundle = data.getExtras();
			binding.etLocation.setText(locbundle.getString("locationinput"));
		}
	}

	private void postCreateProgram(String programTitle, Date startDate, Date endDate, String street, String city) {
		// TODO: get variables here
		Program newProgram = new Program("", bundle.getString("email"),programTitle,startDate,endDate,street,city,0, "In progress");

		ProgramJS programReq = new ProgramJS(newProgram, outcomeArrayList, resourceArrayList);
		Log.d("StartDate : " ,programReq.getProgram().getStartDate().toString());
		Log.d("EndDate : " ,programReq.getProgram().getEndDate().toString());

		Call<ResponseBody> call = apiClient.APIservice.postCreateProgram(programReq);
		call.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				try {
					if (response.body() != null) {
						Toast.makeText(CreateProgActivity.this, response.body().string(), Toast.LENGTH_LONG).show();
						if (response.code() == 200)
							finish();
					}
					else Toast.makeText(CreateProgActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					Log.e("failedUserReg", e.getMessage());
				}
			}
			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				Log.e("failedCreatePrograms", t.getMessage());
			}
		});
	}
}
