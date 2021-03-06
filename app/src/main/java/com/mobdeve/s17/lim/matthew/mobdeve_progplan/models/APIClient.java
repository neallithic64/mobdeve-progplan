package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.apimodels.LoginResponse;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.apimodels.UpdateJS;

import java.text.DateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class APIClient {
	public static final String BASE_URL = "https://covid-progplan.herokuapp.com/";
	public Retrofit retrofit;
	public Gson gson;
	public static APIEndpointInterface APIservice;

	public APIClient() {
		gson = new GsonBuilder()
				.setDateFormat(DateFormat.FULL)
				.setLenient()
				.create();
		retrofit = new Retrofit
				.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		APIservice = retrofit.create(APIEndpointInterface.class);
	}

	public interface APIEndpointInterface {
		@GET("/api/progp/")
		Call<ResponseBody> getTest();

		@GET("/api/progp/progs")
		Call<List<Program>> getPrograms();

		@GET("/api/progp/progs")
		Call<List<Program>> getFilterPrograms(@Query("city") String city);

		@POST("/api/progp/adminreg")
		Call<ResponseBody> postAdminReg(@Body Admin admin);

		@POST("/api/progp/login")
		Call<LoginResponse> postLogin(@Body User user);

		@POST("/api/progp/userreg")
		Call<ResponseBody> postUserReg(@Body User user);

		@POST("/api/progp/progadd")
		Call<ResponseBody> postCreateProgram(@Body ProgramJS program);

		@POST("/api/progp/progedit")
		Call<ResponseBody> postEditProgram(@Body Program program);

		@POST("/api/progp/progeval")
		Call<ResponseBody> postEvalProgram(@Body ProgramJS feedback);

		@POST("/api/progp/progchkl")
		Call<ResponseBody> postUpdateChecklist(@Body ArrayList<ProgChecklist> checklist);
		/* Template for query paths */
		@GET("/group/users")
		Call<List<User>> groupList(@Query("id") int groupId, String query);

		@GET("/api/progp/progdeets")
		Call<ProgramJS> getProgramDetails(@Query("id") String id);
	}

	/* This is NOT to be called anywhere in the application. This is only a template to
		use as a guide on how to write functions with RetroFit. Do not invoke this, this
		will not do anything useful!
	 */
	public void callGetTest() {
		Call<ResponseBody> call = APIservice.getTest();
		call.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				try {
					Log.i("callGetTest", response.body().string());
				} catch (IOException e) {
					Log.e("callGetTest", e.getMessage());
				}
			}
			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				Log.e("callGetTest", t.getMessage());
			}
		});
	}
}
