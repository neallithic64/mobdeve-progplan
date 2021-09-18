package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.icu.text.UFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.FragmentFilterDialogBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;


public class FilterDialogFragment extends DialogFragment {
	private FragmentFilterDialogBinding binding;
	private DatePickerDialog picker;
	public interface FilterDialogListener{
		void filterPrograms(Date startDate, Date endDate, String city);
	}

	public FilterDialogFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		binding = FragmentFilterDialogBinding.inflate(inflater, container, false);
		View view = binding.getRoot();

		ArrayAdapter<CharSequence> cityadapter = ArrayAdapter.createFromResource(getContext(),
				R.array.city_array, android.R.layout.simple_spinner_item);
		cityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		binding.spinnerCity.setAdapter(cityadapter);

		binding.btnFilterprograms.setOnClickListener(v -> {
			FilterDialogListener listener = (FilterDialogListener) getActivity();

			String city = binding.spinnerCity.getSelectedItem().toString();
			Date startDate;
			Date endDate;

			if(city.equals("Location/City"))
				city = "";

			if(binding.etDateRange1.getText().toString().isEmpty())
				startDate = new Date("01/01/1900");
			else startDate = new Date(binding.etDateRange1.getText().toString());

			if(binding.etDateRange2.getText().toString().isEmpty())
				endDate = new Date("12/30/9999");
			else endDate = new Date(binding.etDateRange2.getText().toString());

			listener.filterPrograms(startDate, endDate, city);

			dismiss();
		});

		binding.etDateRange1.setOnClickListener(v->{
			final Calendar cldr = Calendar.getInstance();
			int day = cldr.get(Calendar.DAY_OF_MONTH);
			int month = cldr.get(Calendar.MONTH);
			int year = cldr.get(Calendar.YEAR);
			// date picker dialog
			picker = new DatePickerDialog(getContext(),
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
			picker = new DatePickerDialog(getContext(),
					new DatePickerDialog.OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
							binding.etDateRange2.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
						}
					}, year, month, day);
			picker.show();

		});

		return view;
	}

}