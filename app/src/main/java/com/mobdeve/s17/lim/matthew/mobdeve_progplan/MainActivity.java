package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityMainBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.*;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
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
		getPrograms();
		postAdminReg("sent@from.android", "SENT_WITH_ANDROID", "YAY");
	}
	
	private void initOnClick() {
		binding.btnLogin.setOnClickListener(v -> {
			// TODO: login button...
		});
	}

	private void getPrograms() {
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

	private void postAdminReg(String email, String user, String pass) {
		Admin newAdmin = new Admin(email, user, pass);
		Call<ResponseBody> call = apiClient.APIservice.postAdminReg(newAdmin);
		call.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				try {
					if (response.body() == null)
						Toast.makeText(MainActivity.this, response.body().string(), Toast.LENGTH_LONG).show();
					else
						Toast.makeText(MainActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					Log.e("failedPostAddAdmin", e.getMessage());
				}
			}
			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				Log.e("failedPostAddAdmin", t.getMessage());
			}
		});
	}

	private void postCreateProgram() {
		// Program newProg = new Program();
	}
}
