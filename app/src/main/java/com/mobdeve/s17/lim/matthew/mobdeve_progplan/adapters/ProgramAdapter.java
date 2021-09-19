package com.mobdeve.s17.lim.matthew.mobdeve_progplan.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.R;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.ViewIndivProgActivity;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.ViewProgsActivity;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Program;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder> {
	private ArrayList<Program> programArrayList;
	private Context context;
	private int selected;
	private ViewIndivProgListener listener;

	public ProgramAdapter(ArrayList<Program> programArrayList, Context context, ViewIndivProgListener listener) {
		this.programArrayList = programArrayList;
		this.context = context;
		this.listener = listener;
	}

	public int getItemCount(){return programArrayList.size();}
	public int getSelected(){return selected;}
	@Override
	public ProgramAdapter.ProgramViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.program_data,parent,false);
		ProgramViewHolder programViewHolder = new ProgramViewHolder(view);
		return programViewHolder;
	}

	public void changeDataSet(ArrayList<Program> programArrayList){
		this.programArrayList.clear();
		this.programArrayList.addAll(programArrayList);
		notifyDataSetChanged();
	}

	@Override
	public void onBindViewHolder(ProgramAdapter.ProgramViewHolder holder, int position) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//		holder.iv_proglist_progress.setImageResource();
		holder.tv_proglist_name.setText(programArrayList.get(position).getProgramTitle());
		holder.tv_proglist_location.setText(programArrayList.get(position).getCity());
		holder.tv_proglist_dates.setText(formatter.format(programArrayList.get(position).getStartDate()) + " - " +
				formatter.format(programArrayList.get(position).getEndDate()));

		holder.pc_progress.clearChart();
		float complete = (float) programArrayList.get(position).getProgress();
		float incomplete = 100f - complete;
		holder.pc_progress.addPieSlice(new PieModel("Complete", complete, Color.parseColor("#028A0F")));
		holder.pc_progress.addPieSlice(new PieModel("Incomplete", incomplete, Color.parseColor("#BEBEBE")));
		holder.pc_progress.setInnerValueString(String.format("%.0f", complete) + "%");
		holder.pc_progress.setCurrentItem(1);

//		holder.btn_proglist_view.setOnClickListener(v->{
//			selected = position;
//		});
//		holder.program_cardView.setOnClickListener(v->{
//			selected = position;
//		});
	}

	protected class ProgramViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
		TextView 		tv_proglist_name;
		TextView 		tv_proglist_location;
		TextView 		tv_proglist_dates;
		CardView		program_cardView;
		PieChart		pc_progress;

		public ProgramViewHolder(View view){
			super(view);
			pc_progress = view.findViewById(R.id.pc_progresschart);
			tv_proglist_name = view.findViewById(R.id.tv_proglist_name);
			tv_proglist_location = view.findViewById(R.id.tv_proglist_location);
			tv_proglist_dates = view.findViewById(R.id.tv_proglist_dates);
			program_cardView = view.findViewById(R.id.program_cardview);
			view.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			listener.onClick(v, getAdapterPosition());
		}
	}

	public interface ViewIndivProgListener {
		void onClick(View v, int pos);
	}
}
