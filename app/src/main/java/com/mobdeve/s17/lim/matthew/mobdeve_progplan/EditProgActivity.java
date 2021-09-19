package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityEditProgBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.APIClient;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Feedback;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Program;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.ProgramJS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProgActivity extends AppCompatActivity {

	private ActivityEditProgBinding binding;
	private Bundle bundle;
	private Program program;
	private DatePickerDialog picker;
	private APIClient apiClient;

	private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityEditProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		bundle = new Bundle();
		bundle = getIntent().getExtras();
		program = (Program) bundle.getParcelable("program");
		
		binding.etDateRange1.setText(formatter.format(program.getStartDate()));
		binding.etDateRange2.setText(formatter.format(program.getEndDate()));
		binding.etLocation.setText(program.getStreet() + ", " + program.getCity());

		apiClient = new APIClient();
		initOnClick();
	}

	private void initOnClick(){

		binding.btnEditCancel.setOnClickListener( v -> {
			returnToViewProg();
		});

		binding.btnEditSave.setOnClickListener( v -> {
//      TODO : Save changes to database
			if(validateInput()){
				modifyProgramVal();
				editProgram();
			}
		});

		binding.ivGps.setOnClickListener(v->{
			Intent gotoMapActivity = new Intent(EditProgActivity.this, MapsActivity.class);
			startActivityForResult(gotoMapActivity, 800);
		});

		binding.etDateRange1.setOnClickListener(v->{
			final Calendar cldr = Calendar.getInstance();
			int day = cldr.get(Calendar.DAY_OF_MONTH);
			int month = cldr.get(Calendar.MONTH);
			int year = cldr.get(Calendar.YEAR);
			// date picker dialog
			picker = new DatePickerDialog(EditProgActivity.this,
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
			picker = new DatePickerDialog(EditProgActivity.this,
					new DatePickerDialog.OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
							binding.etDateRange2.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
						}
					}, year, month, day);
			picker.show();

		});
	}

	private void returnToViewProg(){
		Intent gotoViewIndivProg = new Intent(EditProgActivity.this,ViewIndivProgActivity.class);
		gotoViewIndivProg.putExtras(bundle);
		startActivity(gotoViewIndivProg);
		finish();
	}

	@Override
	public void onBackPressed() {
		returnToViewProg();
	}

	private boolean validateInput(){
		Date date1 = new Date(binding.etDateRange1.getText().toString());
		Date date2 = new Date(binding.etDateRange2.getText().toString());

		if (date1.before(new Date())){
			Toast.makeText(EditProgActivity.this,"Start Date should be before today",Toast.LENGTH_LONG).show();
			return false;
		}
		else if (date1.after(date2)){
			Toast.makeText(EditProgActivity.this,"End Date should be after Start Date", Toast.LENGTH_LONG).show();
			return false;
		}

		if(binding.etLocation.getText().toString().isEmpty() || binding.etLocation.getText().toString().indexOf(',') == -1) {
			Toast.makeText(EditProgActivity.this,"Location is invalid",Toast.LENGTH_LONG).show();
			return false;
		}

		return true;
	}

	private void modifyProgramVal(){
		int commaplacement = binding.etLocation.getText().toString().indexOf(',');

		program.setStartDate(new Date(binding.etDateRange1.getText().toString()));
		program.setEndDate(new Date(binding.etDateRange2.getText().toString()));
		program.setStreet(binding.etLocation.getText().toString().substring(0,commaplacement));
		program.setCity(binding.etLocation.getText().toString().substring(commaplacement + 2));

//		Toast.makeText(this, program.getStreet() + "\n" + program.getCity(), Toast.LENGTH_SHORT).show();
	}

	private void editProgram(){

		Call<ResponseBody> call = apiClient.APIservice.postEditProgram(program);
		call.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				try {
					if (response.body() != null) {
						Toast.makeText(EditProgActivity.this, response.body().string(), Toast.LENGTH_LONG).show();
						if (response.code() == 200)
							returnToViewProg();
					}
					else Toast.makeText(EditProgActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					Log.e("failedEditProg", e.getMessage());
				}
			}
			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				Log.e("failedEditProg", t.getMessage());
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Activity.RESULT_OK && requestCode == 800){
			Bundle locbundle = data.getExtras();
			binding.etLocation.setText(locbundle.getString("locationinput"));
		}
	}
}