package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityCreateProgBinding;

public class CreateProgActivity extends AppCompatActivity {
	private ActivityCreateProgBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityCreateProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initOnClick();
	}

	private void initOnClick() {
		binding.ivGps.setOnClickListener(v -> {
			// TODO: implement the modal for the maps
		});

		binding.ivAddResource.setOnClickListener(v -> {
			// TODO: add table row
			TableRow newRow = new TableRow(this);
			EditText name, value;
			name = value = new EditText(this);

			name.setBackground(getDrawable(R.drawable.border));
			name.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.7f));
			name.setTextColor(getColor(R.color.black));
			newRow.addView(name);

			value.setBackground(getDrawable(R.drawable.border));
			value.setLayoutParams(new TableRow.LayoutParams(
					0,
					TableRow.LayoutParams.MATCH_PARENT,
					0.3f));
			value.setTextColor(getColor(R.color.black));
			// The specified child already has a parent. You must call removeView() on the child's parent first.
			// newRow.addView(value);

			binding.tlResources.addView(newRow);
			Log.d("TableLayout", "TableRow Count: " + binding.tlResources.getChildCount());
		});

		binding.ivAddOutcome.setOnClickListener(v -> {
			// TODO: add table row
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
}
