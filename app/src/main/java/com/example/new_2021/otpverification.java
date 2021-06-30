package com.example.new_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class otpverification extends AppCompatActivity
{
    private boolean ac = false;
    private FloatingActionButton btn1,btn3;
    private TextView text1,text3,text5;
    private EditText eText;
    private String name;
    private int year1, month1, day1, hour1, minute1, numberOfRooms = 0,numberOfDays = 0;
    private Calendar calendar;
    public void inputName(View view)
    {
        name = eText.getText().toString();
        eText.setFocusableInTouchMode(false);
        eText.clearFocus();
        hideKeyboard(view);
        eText.setFocusableInTouchMode(true);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(MainActivity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void roomType(View view)
    {
        Switch aSwitch = (Switch)findViewById(R.id.switch1);
        if(aSwitch.isChecked()) ac = true;
        else ac = false;
        //Toast.makeText(this,""+ ac ,Toast.LENGTH_LONG).show();
        updateCost();
    }

    public void showDatePickerDialog(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        year1 = year;
                        month1 = monthOfYear;
                        day1 = dayOfMonth;
                        text1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, year1, month1, day1);
        datePickerDialog.show();
    }

    public void showTimePickerDialog(View view) {
        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        hour1 = hourOfDay;
                        minute1 = minute;
                        text3.setText(hourOfDay + ":" + minute);
                    }
                }, hour1, minute1, true);
        timePickerDialog.show();
    }

    public void updateCost() {
        if(ac) {
            text5.setText(numberOfRooms*numberOfDays*800+" Rs");
        }
        else {
            text5.setText(numberOfRooms*numberOfDays*500+" Rs");
        }
    }

    public void submit(View view) {
        if(eText.getText().toString().isEmpty()) {
            Toast.makeText(this,"Please Enter A Valid Name!",Toast.LENGTH_SHORT).show();
        }
        else if (numberOfRooms == 0){
            Toast.makeText(this,"Please Select Number Of Rooms!",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"Booked",Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Please attach  payment reciept in Registration form", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        btn1 = (FloatingActionButton)findViewById(R.id.floatingActionButton3);
        btn3 = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        text1 = (TextView)findViewById(R.id.textView11);
        text3 = (TextView)findViewById(R.id.textView4);
        text5 = (TextView)findViewById(R.id.textView15);
        eText = (EditText)findViewById(R.id.editTextTextPersonName);

        Spinner mySpinner1 = (Spinner)findViewById(R.id.spinner2);
        mySpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_LONG).show();
                numberOfRooms = position;
                updateCost();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner mySpinner2 = (Spinner)findViewById(R.id.spinner);
        mySpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_LONG).show();
                numberOfDays = position+1;
                updateCost();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calendar = Calendar.getInstance();
        year1 = calendar.get(Calendar.YEAR);
        month1  = calendar.get(Calendar.MONTH);
        day1 = calendar.get(Calendar.DAY_OF_MONTH);
        hour1 = calendar.get(Calendar.HOUR_OF_DAY);
        minute1 = calendar.get(Calendar.MINUTE);
        text1.setText(day1 +"/" +(month1+1)+"/"+year1);
        text3.setText(hour1 + ":" + minute1);
        text5.setText("0 Rs");
    }
}