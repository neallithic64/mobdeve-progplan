package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityCreateProgBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.*;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateProgActivity extends AppCompatActivity {
	private ActivityCreateProgBinding binding;
	private APIClient apiClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityCreateProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		apiClient = new APIClient();
		initOnClick();
	}

	private void initOnClick() {
		binding.ivGps.setOnClickListener(v -> {
			// TODO: implement the modal for the maps
			new MapDialogFragment().show(getSupportFragmentManager(), null);
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
			newRow.addView(name);

			value.setBackground(getDrawable(R.drawable.border));
			value.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.3f));
			value.setHint("Value");
			value.setTextColor(getColor(R.color.black));
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
			name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			name.setTextColor(getColor(R.color.black));
			name.setHint("Outcome Name");
			newRow.addView(name);

			value.setBackground(getDrawable(R.drawable.border));
			value.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.3f));
			value.setHint("Value");
			value.setTextColor(getColor(R.color.black));
			// The specified child already has a parent. You must call removeView() on the child's parent first.
			newRow.addView(value);

			binding.tlOutcomes.addView(newRow);
		});

		binding.btnCreateSave.setOnClickListener(v -> {
			// TODO: create HTTP request to server
			Intent gotoViewProgs = new Intent(CreateProgActivity.this,ViewIndivProgActivity.class );
			startActivity(gotoViewProgs);
			finish();
		});

		binding.btnCreateCancel.setOnClickListener(v -> {
			// TODO: go back to page
			Intent gotoViewProgs = new Intent(CreateProgActivity.this, ViewProgsActivity.class);
			startActivity(gotoViewProgs);
			finish();
		});
	}

	private void postCreateProgram() {
		// TODO: get variables here
		Program newProgram = new Program();
		ArrayList<Outcome> outcomes = new ArrayList<>();
		ArrayList<Resource> resources = new ArrayList<>();
		ProgramJS programReq = new ProgramJS(newProgram, outcomes, resources);
		Call<ResponseBody> call = apiClient.APIservice.postCreateProgram(programReq);
		call.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

			}
			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {

			}
		});
	}
}
