package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.FragmentFilterDialogBinding;


public class FilterDialogFragment extends DialogFragment {
	private FragmentFilterDialogBinding binding;
	public FilterDialogFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		binding = FragmentFilterDialogBinding.inflate(inflater, container, false);
		View view = binding.getRoot();
		return view;
	}
}