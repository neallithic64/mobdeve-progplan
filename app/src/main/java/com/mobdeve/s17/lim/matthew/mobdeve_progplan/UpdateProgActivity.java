package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityUpdateProgBinding;

public class UpdateProgActivity extends AppCompatActivity {

    private ActivityUpdateProgBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateProgBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_update_prog);
//      TODO : Set up program progress item recycler adapter

        initOnCLick();
    }

//    TODO: Set up Onclick Listeners
    private void initOnCLick(){
        binding.btnUpdateCancel.setOnClickListener(v->{

        });
        binding.btnUpdateSave.setOnClickListener(v->{

        });
    }
}