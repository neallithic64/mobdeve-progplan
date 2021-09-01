package com.mobdeve.s17.lim.matthew.mobdeve_progplan.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Program;

import java.util.ArrayList;

public class ProgramAdapter extends RecyclerView.Adapter {
	private ArrayList<Program> programArrayList;
	private Context context;
	private int position;

	public ProgramAdapter(ArrayList<Program> programArrayList, Context context) {
		this.programArrayList = programArrayList;
		this.context = context;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 0;
	}
}
