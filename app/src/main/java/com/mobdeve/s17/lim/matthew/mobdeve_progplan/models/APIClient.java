package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class APIClient {
	public static final String BASE_URL = "https://covid-progplan.herokuapp.com/";
	public Retrofit retrofit;
	public Gson gson;
	public static APIEndpointInterface APIservice;

	public APIClient() {
		gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
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
	}

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
