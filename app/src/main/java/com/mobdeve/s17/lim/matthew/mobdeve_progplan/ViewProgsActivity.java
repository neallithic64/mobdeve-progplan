package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.adapters.ProgramAdapter;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityViewProgsBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.APIClient;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Program;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProgsActivity extends AppCompatActivity implements FilterDialogFragment.FilterDialogListener {

	private ActivityViewProgsBinding 	binding;
	private ArrayList<Program>			programArrayList;
	private ProgramAdapter				programAdapter;
	private Bundle						bundle;

	private ProgramAdapter.ViewIndivProgListener listener;
	private int position;
	private Menu menu;

	private APIClient apiClient;

	private static final String CHANNEL_ID = "com.mobdeve.s17.lim.matthew.mobdeve_progplan.NOTIFICATION";
	private int notificationID = 10;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityViewProgsBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		apiClient = new APIClient();

		bundle = new Bundle();
		bundle = getIntent().getExtras();

		setOnClickListener();
//		initializeProgramData();
		programArrayList = new ArrayList<>();

		programAdapter = new ProgramAdapter(programArrayList,getApplicationContext(),listener);
		binding.rvProglist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
		binding.rvProglist.setAdapter(programAdapter);



		initOnClick();
	}

	@Override
	protected void onResume() {
		super.onResume();
		position = -1;
//		Toast.makeText(ViewProgsActivity.this,bundle.getString("email"),Toast.LENGTH_LONG).show();
		getPrograms();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.menu_filter:
				new FilterDialogFragment().show(getSupportFragmentManager(),null);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}


	private void initOnClick(){
		binding.fabAddProg.setOnClickListener(v->{
			Intent gotoCreateProg = new Intent(ViewProgsActivity.this,CreateProgActivity.class);
			gotoCreateProg.putExtras(bundle);
			startActivity(gotoCreateProg);
		});

//		TODO Create intent and binding for individual programs
		binding.rvProglist.setOnClickListener(view->{
			position = programAdapter.getSelected();
//			if(position != -1){
//				TODO: Modify code to save the program number to bundle
//				Code does not take into acccount different programs
				Intent gotoViewIndivProg = new Intent(ViewProgsActivity.this, ViewIndivProgActivity.class);
				startActivity(gotoViewIndivProg);
//			}
		});

	}

	private void initializeProgramData(){
		programArrayList = new ArrayList<>();
		programArrayList.add(new Program("PI000001","User01","Sample Program 1",
				new Date(),new Date(),"Sample Street Name", "Malabon City",10, "In Progress"));
		programArrayList.add(new Program("PI000002","User01","Sample Program 2",
				new Date(),new Date(),"Sample Street Name", "Quezon City",10, "In Progress"));
	}

	private void getPrograms(){
		programArrayList = new ArrayList<Program>();
		Call<List<Program>> call = apiClient.APIservice.getPrograms();
		call.enqueue(new Callback<List<Program>>() {
			@Override
			public void onResponse(Call<List<Program>> call, Response<List<Program>> response) {
				String msg = "";

				programArrayList = (ArrayList<Program>) response.body();
				programAdapter.changeDataSet(programArrayList);
				getNotifs();
//				Toast.makeText(ViewProgsActivity.this,Integer.toString(programArrayList.size()) , Toast.LENGTH_LONG).show();
			}
			@Override
			public void onFailure(Call<List<Program>> call, Throwable t) {
				Log.e("failedGetPrograms", t.getMessage());
			}
		});
	}
	private void setOnClickListener(){
		listener = new ProgramAdapter.ViewIndivProgListener() {
			@Override
			public void onClick(View v, int pos) {
				Intent intent = new Intent(getApplicationContext(), ViewIndivProgActivity.class);
				intent.putExtras(bundle);
				intent.putExtra("program", programArrayList.get(pos));
				startActivity(intent);
//				Toast.makeText(ViewProgsActivity.this,programArrayList.get(pos).getProgramId(),Toast.LENGTH_LONG).show();
			}
		};
	}

	/*
	* 	Filters programs based on the following parameters
	* 		- date of activity falls within the date range
	* 			-
	* 		- city matches the city of the program
	* 			- if city is empty, all programs will pass
	* */
	public void filterPrograms(Date startDate, Date endDate, String city){

		programArrayList = new ArrayList<Program>();
		Call<List<Program>> call = apiClient.APIservice.getPrograms();

		call.enqueue(new Callback<List<Program>>() {
			@Override
			public void onResponse(Call<List<Program>> call, Response<List<Program>> response) {
				String msg = "";

				programArrayList = (ArrayList<Program>) response.body();
				programAdapter.changeDataSet(programArrayList);

				for(int i = 0; i < programArrayList.size(); i++) {
					if (programArrayList.get(i).getCity().indexOf(city) == -1 ||
							!inDateRange(startDate,endDate,programArrayList.get(i).getStartDate(),
									programArrayList.get(i).getEndDate()))
					{
						programArrayList.remove(i);
						i--;
					}
				}
				programAdapter.changeDataSet(programArrayList);
				Toast.makeText(ViewProgsActivity.this, Integer.toString(programArrayList.size()), Toast.LENGTH_LONG).show();
			}
			@Override
			public void onFailure(Call<List<Program>> call, Throwable t) {
				Log.e("failedGetPrograms", t.getMessage());
			}
		});
	}

	public boolean inDateRange(Date startRange, Date endRange, Date startDate, Date endDate){
		return (startRange.before(endDate) || startRange.equals(endDate)) && (startDate.before(endRange) || startDate.equals(endRange));
	}

	public void getNotifs(){
		int i = 0;
		Date date = new Date();

		for(i = 0; i < programArrayList.size(); i++){
			if(programArrayList.get(i).getStatus().equals("Needs Evaluation"))
			{
//				Toast.makeText(getApplicationContext(),"implNotif: " + programArrayList.get(i).getProgramTitle(), Toast.LENGTH_LONG).show();
				evalNotif(programArrayList.get(i).getProgramTitle());

			}
		}

		for(i = 0; i < programArrayList.size(); i++){
			if (programArrayList.get(i).getStartDate().getMonth() == date.getMonth() &&
					programArrayList.get(i).getStartDate().getYear() == date.getYear() &&
					programArrayList.get(i).getStartDate().getDate() == date.getDate() + 1 &&
					!programArrayList.get(i).getStatus().equalsIgnoreCase("Complete") &&
					!programArrayList.get(i).getStatus().equalsIgnoreCase("Needs Evaluation"))
			{
//				Toast.makeText(getApplicationContext(),"implNotif: " + programArrayList.get(i).getProgramTitle(), Toast.LENGTH_LONG).show();
				implNotif(programArrayList.get(i).getProgramTitle());
			}
		}

	}

	private void implNotif(String programTitle){
		createNotificationChannel();
		createImplNotif(programTitle);
	}

	private void evalNotif(String programTitle){
		createNotificationChannel();
		createEvalNotif(programTitle);
	}
	private void createNotificationChannel() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			int importance = NotificationManager.IMPORTANCE_DEFAULT;
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "ProgPlan", importance);
			channel.setDescription("MOBDEVE ProgPlan");

			NotificationManager notificationManager = getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);

			Log.d("CreateNotifChannel","createNotificationChannel");
		}
	}

	public void createImplNotif(String programTitle){
		NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);

		builder.setSmallIcon(R.drawable.logosmall);
		builder.setContentTitle("You have an upcoming program!");
		builder.setContentText(programTitle + " starts tomorrow");
		builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logolarge));
		builder.setAutoCancel(true);

		NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
		notificationManager.notify(notificationID, builder.build());
		notificationID++;
		Log.d("ImplNotif","ImplementationNotif Created");
	}

	public void createEvalNotif(String programTitle){
		NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);

		builder.setSmallIcon(R.drawable.logosmall);
		builder.setContentTitle("You have a pending evaluation!");
		builder.setContentText(programTitle + " needs to be evaluated.");
		builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logolarge));
		builder.setAutoCancel(true);

		NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
		notificationManager.notify(notificationID, builder.build());
		notificationID++;
		Log.d("EvalNotif","EvaluationNotif Created");
	}
}
