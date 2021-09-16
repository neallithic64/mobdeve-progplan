package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.Toast;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.adapters.ProgramAdapter;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.adapters.ProgressAdapter;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.apimodels.UpdateJS;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityUpdateProgBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.APIClient;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.ProgChecklist;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Program;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.ProgramJS;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProgActivity extends AppCompatActivity {

	private ActivityUpdateProgBinding binding;
	private ArrayList<ProgChecklist>	progChecklistArrayList;
	private ProgressAdapter	progressAdapter;
	private Program program;

	private Bundle bundle;

	private APIClient apiClient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityUpdateProgBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

//      TODO : Set up program progress item recycler adapter
		bundle = new Bundle();
		bundle = getIntent().getExtras();
		progChecklistArrayList = bundle.getParcelableArrayList("progchecklist");
		program = (Program) bundle.getParcelable("program");

		apiClient = new APIClient();

		initOnCLick();
	}

	@Override
	protected void onResume() {
		super.onResume();

		binding.tvProgName.setText(program.getProgramTitle());

		progressAdapter = new ProgressAdapter(progChecklistArrayList,getApplicationContext());
		binding.rvProgprogresslist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
		binding.rvProgprogresslist.setAdapter(progressAdapter);
	}

	//    TODO: Set up Onclick Listeners
	private void initOnCLick(){
		binding.btnUpdateCancel.setOnClickListener(v->{
			returnToViewProg();
		});
		binding.btnUpdateSave.setOnClickListener(v->{
//        TODO: Save update of progressitems to database
//			Intent gotoViewIndivProg = new Intent(UpdateProgActivity.this,ViewIndivProgActivity.class);
//			startActivity(gotoViewIndivProg);
//			finish();
			progChecklistArrayList = progressAdapter.getProgChecklistArrayList();
			String text= "";
			updateChecklist();

		});
	}

	private void returnToViewProg(){
		Intent gotoViewIndivProg = new Intent(UpdateProgActivity.this,ViewIndivProgActivity.class);
		gotoViewIndivProg.putExtras(bundle);
		startActivity(gotoViewIndivProg);
		finish();
	}

	@Override
	public void onBackPressed() {
		returnToViewProg();
	}

	private void updateChecklist(){
		Call<ResponseBody> call = apiClient.APIservice.postUpdateChecklist(progChecklistArrayList);
		call.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				try {
					Toast.makeText(UpdateProgActivity.this, response.body().string(), Toast.LENGTH_LONG).show();
					returnToViewProg();
				} catch (IOException e) {
					Log.e("failedUpdateProgram", e.getMessage());
				}
			}
			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				Log.e("failedUpdateProgram", t.getMessage());
			}
		});
	}
	private void initializeProgressData(){
		progChecklistArrayList = new ArrayList<ProgChecklist>();
		progChecklistArrayList.add(new ProgChecklist("Sample Item 1","Prog000001",true));
		progChecklistArrayList.add(new ProgChecklist("Sample Item 2","Prog000001",true));
		progChecklistArrayList.add(new ProgChecklist("Sample Item 3","Prog000001",false));
		progChecklistArrayList.add(new ProgChecklist("Sample Item 4","Prog000001",false));
	}
}