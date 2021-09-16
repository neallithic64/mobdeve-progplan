package com.mobdeve.s17.lim.matthew.mobdeve_progplan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.R;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.ProgChecklist;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Program;

import java.util.ArrayList;

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder> {
	private ArrayList<ProgChecklist> progChecklistArrayList;
	private Context context;


	public ProgressAdapter(ArrayList<ProgChecklist> progChecklistArrayList, Context context) {
		this.progChecklistArrayList = progChecklistArrayList;
		this.context = context;
	}

	public int getItemCount(){return progChecklistArrayList.size();}

	public ArrayList<ProgChecklist> getProgChecklistArrayList(){
		return progChecklistArrayList;
	}

	@Override
	public ProgressAdapter.ProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.progress_data,parent,false);
		ProgressAdapter.ProgressViewHolder progressViewHolder = new ProgressAdapter.ProgressViewHolder(view);
		return progressViewHolder;
	}

	@Override
	public void onBindViewHolder(ProgressAdapter.ProgressViewHolder holder, int position) {
		holder.tv_progressitem.setText(progChecklistArrayList.get(position).getProgItem());
		holder.checkbox_checked.setChecked(progChecklistArrayList.get(position).isChecked());

		holder.checkbox_checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				//set your object's last status
				progChecklistArrayList.get(position).setChecked(isChecked);
			}
		});

	}

	protected class ProgressViewHolder extends RecyclerView.ViewHolder{
		TextView tv_progressitem;
		CheckBox checkbox_checked;

		public ProgressViewHolder(View view){
			super(view);
			tv_progressitem = view.findViewById(R.id.tv_progressitem);
			checkbox_checked = view.findViewById(R.id.checkbox_checked);
		}
	}
}
