package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityUserRegBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityViewProgsBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.APIClient;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.Admin;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.User;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegActivity extends AppCompatActivity{
	private ActivityUserRegBinding binding;
	//  private CitySpinnerAdapter cityadapter;
	private Bundle bundle;
	private APIClient apiClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityUserRegBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initOnClick();

		bundle = getIntent().getExtras();
		apiClient = new APIClient();
//      Initialize spinner adapter (will do it within CitySpinnerAdapter once db is set up)
		ArrayAdapter<CharSequence> cityadapter = ArrayAdapter.createFromResource(this,
				R.array.city_array, android.R.layout.simple_spinner_item);
		cityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		binding.spinnerCity.setAdapter(cityadapter);
	}

	private void initOnClick(){
		binding.btnRegisternewuser.setOnClickListener(v->{
//      TODO : Validate input and add to database
			try {
				if(bundle.getString("userType").equals("admin")) {
					if(validateInput()) {
						postUserReg(binding.etEmail.getText().toString(),binding.etUsername.getText().toString(),
								binding.etPassword.getText().toString(),binding.spinnerCity.getSelectedItem().toString());
					}
				} else
					Toast.makeText(UserRegActivity.this,"User does not have permission to add users", Toast.LENGTH_LONG).show();
			}catch (Exception e){
				Toast.makeText(UserRegActivity.this,"User is not logged in.\nReturn to previous screen.", Toast.LENGTH_LONG).show();
			}


//			Intent gotoMain = new Intent(UserRegActivity.this, MainActivity.class);
//
//
//			startActivity(gotoMain);
//			finish();
		});
	}

	/*
		Checks if the user has input the fields correctly under the following conditions
			- No fields are empty
			- Email is valid
			- Password and Confirm Password match
	*/
	private boolean validateInput(){

		if(binding.etEmail.getText().toString().equals("") ||
				binding.etUsername.getText().toString().equals("") ||
				binding.etPassword.getText().toString().equals("") ||
				binding.etConfirmpassword.getText().toString().equals("") ||
				binding.spinnerCity.getSelectedItem().toString().equals("Location/City")) {
			Toast.makeText(UserRegActivity.this,"One or more fields are empty", Toast.LENGTH_LONG).show();
			return false;
		} else if(!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.getText()).matches()){
			Toast.makeText(UserRegActivity.this,"Email is not valid",Toast.LENGTH_LONG).show();
			return false;
		} else if(!binding.etConfirmpassword.getText().toString().equals(binding.etPassword.getText().toString())) {
			Toast.makeText(UserRegActivity.this, "Passwords do not match",Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}

	private void postUserReg(String email, String user, String pass, String city) {
		User newUser = new User(email, user, pass, city);
		Call<ResponseBody> call = apiClient.APIservice.postUserReg(newUser);
		call.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				try {
					if (response.body() != null)
						if(response.code() == 200)
							Toast.makeText(UserRegActivity.this, "User successfully added", Toast.LENGTH_LONG).show();
						else
							Toast.makeText(UserRegActivity.this, response.body().string(), Toast.LENGTH_LONG).show();
					else Toast.makeText(UserRegActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					Log.e("failedUserReg", e.getMessage());
				}
			}
			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				Log.e("failedUserReg", t.getMessage());
			}
		});
	}
}