package com.example.anew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView weight;
    private TextView age;
    private TextView height_value;
    private AppCompatSeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        seekBar = findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(listener);
        height_value = findViewById(R.id.height_value);
    }

    private SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            height_value.setText(String.format("%d Cm",progress));
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    public void increase_age(View view) {
        if (Integer.parseInt(age.getText().toString()) > 0){
            int age_value = Integer.parseInt(age.getText().toString()) + 1;
            age.setText(String.valueOf(age_value));
        }
    }

    public void decrease_age(View view) {
        if (Integer.parseInt(age.getText().toString()) > 1){
            int age_value = Integer.parseInt(age.getText().toString()) - 1;
            age.setText(String.valueOf(age_value));
        }
    }

    public void increase_weight(View view) {
        if (Integer.parseInt(weight.getText().toString()) > 0){
            int weight_value = Integer.parseInt(weight.getText().toString()) + 1;
            weight.setText(String.valueOf(weight_value));
        }
    }

    public void decrease_weight(View view) {
        if (Integer.parseInt(weight.getText().toString()) > 1){
            int weight_value = Integer.parseInt(weight.getText().toString()) - 1;
            weight.setText(String.valueOf(weight_value));
        }
    }

    public void Show_results(View view) {
        int get_age = Integer.parseInt(age.getText().toString());
        int weight_value = Integer.parseInt(weight.getText().toString());
        double get_height = (double)seekBar.getProgress()/100;

        int bmi = weight_value / (int)(get_height * get_height);

        showBMI(bmi);
    }

    private void showBMI(int bmi) {

        if (bmi < 18.5){
            shoCustomDialog(R.drawable.underweight, "Underweight", "Use some healthy fats");
        }else if(bmi > 18.5 && bmi < 24.9) {
            shoCustomDialog(R.drawable.normal, "Normalweight", "You are healthy");
        }else{
            shoCustomDialog(R.drawable.over, "Overweight", "Do some exercise");
        }

    }

    private void shoCustomDialog(int Id, String title, String tips) {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View view = LayoutInflater.from(this).inflate(R.layout.custome_dialog, viewGroup, false);
        AppCompatButton ok = view.findViewById(R.id.ok);
        ImageView imageView = view.findViewById(R.id.image_view);
        TextView result_title = view.findViewById(R.id.result_title);
        TextView tip = view.findViewById(R.id.tips);
        imageView.setImageResource(Id);
        result_title.setText(title);
        tip.setText(tips);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }
}