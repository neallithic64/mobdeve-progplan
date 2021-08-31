package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityMainBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.APIClient;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Program;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;
	private APIClient apiClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initOnClick();
		apiClient = new APIClient();
		getProgs();
	}
	
	private void initOnClick() {
		binding.btnLogin.setOnClickListener(v -> {
			// TODO: login button...
		});
	}

	private void getProgs() {
		Call<List<Program>> call = apiClient.APIservice.getPrograms();
		call.enqueue(new Callback<List<Program>>() {
			@Override
			public void onResponse(Call<List<Program>> call, Response<List<Program>> response) {
				String msg = "";
				for (int i = 0; i < response.body().size(); i++) msg += response.body().get(i).getProgramId() + " ";
				Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
			}
			@Override
			public void onFailure(Call<List<Program>> call, Throwable t) {
				Log.e("failedGetPrograms", t.getMessage());
			}
		});
	}
}
