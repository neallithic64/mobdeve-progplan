package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityUserRegBinding;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityViewProgsBinding;

public class UserRegActivity extends AppCompatActivity{
    private ActivityUserRegBinding binding;
//  private CitySpinnerAdapter cityadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserRegBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//      TODO Initialize Spinner with DB

//      Initialize spinner adapter (will do it within CitySpinnerAdapter once db is set up)
        ArrayAdapter<CharSequence> cityadapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        cityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCity.setAdapter(cityadapter);
    }

    private void initOnClick(){
        binding.btnRegisternewuser.setOnClickListener(v->{
//      TODO : Validate input and add to database

            Intent gotoMain = new Intent(UserRegActivity.this, MainActivity.class);


            startActivity(gotoMain);
            finish();
        });
    }
}