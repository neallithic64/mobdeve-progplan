package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityEditProgBinding;

import java.text.DateFormat;
import java.util.Calendar;

public class EditProgActivity extends AppCompatActivity {

    private ActivityEditProgBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProgBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initOnClick();
    }

/*  TODO: Set up Onclick Listeners
    DatePicker Dialog is currently not opening

* */
    private void initOnClick(){
       /* binding.etDateRange1.setOnClickListener( v-> {
            DatePicker mDatePickerDialogFragment;
            mDatePickerDialogFragment = new com.mobdeve.s17.lim.matthew.mobdeve_progplan.DatePicker();
            mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
        });*/
        binding.btnEditCancel.setOnClickListener( v -> {
            Intent gotoViewIndivProg = new Intent(EditProgActivity.this, ViewIndivProgActivity.class);
            startActivity(gotoViewIndivProg);
            finish();
        });

        binding.btnEditSave.setOnClickListener( v -> {
//      TODO : Save changes to database
            Intent gotoViewIndivProg = new Intent(EditProgActivity.this, ViewIndivProgActivity.class);
            startActivity(gotoViewIndivProg);
            finish();
        });
    }

    /*public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
        binding.etDateRange1.setText(selectedDate);
    }*/
}