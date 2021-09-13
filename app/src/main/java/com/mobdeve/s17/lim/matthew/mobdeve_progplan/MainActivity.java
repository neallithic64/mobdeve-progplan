package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.apimodels.LoginResponse;
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
	private Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		bundle = new Bundle();

		initOnClick();
		apiClient = new APIClient();
		 getPrograms();
//		postAdminReg("admin@gmail.com", "superAdmin", "admin");

	}

	@Override
	protected void onResume() {
		super.onResume();
		binding.etPassword.setText("");
		binding.etEmail.setText("");
	}

	@Override
	protected void onPause() {
		super.onPause();
		bundle.clear();
	}

	private void initOnClick() {
		binding.btnLogin.setOnClickListener(v -> {
			// TODO: login button...
			// TODO : user rationale (withdb)
			String email = binding.etEmail.getText().toString();
			String pass = binding.etPassword.getText().toString();
			loginUser(email, pass);
			// Intent gotoViewProgs = new Intent(MainActivity.this, ViewProgsActivity.class);
			// startActivity(gotoViewProgs);
			// finish();
		});

//		binding.btnRegister.setOnClickListener( v ->{
//			Intent gotoUserReg = new Intent(MainActivity.this,UserRegActivity.class);
//			startActivity(gotoUserReg);
//		});
	}

	private void getPrograms() {
		Call<List<Program>> call = apiClient.APIservice.getPrograms();
		call.enqueue(new Callback<List<Program>>() {
			@Override
			public void onResponse(Call<List<Program>> call, Response<List<Program>> response) {
				String msg = "";
				for (int i = 0; i < response.body().size(); i++)
					msg += response.body().get(i).getProgramId() + "\n";
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
					if (response.body() != null)
						Toast.makeText(MainActivity.this, response.body().string(), Toast.LENGTH_LONG).show();
					else Toast.makeText(MainActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
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

	private void loginUser(String email, String password) {
		Call<LoginResponse> call = apiClient.APIservice.postLogin(new User(email,"",password,""));
		call.enqueue(new Callback<LoginResponse>() {
			@Override
			public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
				try {
					if (response.body() != null) {
						bundle.putInt("userType", response.body().getUserType());
						bundle.putString("email", response.body().getEmail());
						bundle.putString("username", response.body().getUsername());
						if (response.body().getUserType() == 1) {
							// user
							bundle.putString("city", response.body().getEmail());
							bundle.putString("userType","user");

							Intent gotoViewProgs = new Intent(MainActivity.this, ViewProgsActivity.class);
							gotoViewProgs.putExtras(bundle);
							startActivity(gotoViewProgs);

							Toast.makeText(MainActivity.this, "Logged in: " + response.body().getEmail(), Toast.LENGTH_LONG).show();
							// intents
						} else {
							// admin
							// intents
							bundle.putString("userType","admin");

							Intent goToUserReg = new Intent(MainActivity.this,UserRegActivity.class);
							goToUserReg.putExtras(bundle);
							startActivity(goToUserReg);

							Toast.makeText(MainActivity.this, "Logged in: " + response.body().getEmail(), Toast.LENGTH_LONG).show();
						}

						Bundle newBundle = bundle;
					} else Toast.makeText(MainActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					Log.e("failedPostLogin", e.getMessage());
				}
			}
			@Override
			public void onFailure(Call<LoginResponse> call, Throwable t) {
				Log.e("failedPostLogin", t.getMessage());
			}
		});
	}

}
