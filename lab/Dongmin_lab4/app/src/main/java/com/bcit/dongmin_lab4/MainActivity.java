package com.bcit.dongmin_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        ImageView painting = findViewById(R.id.imageView_main_painting);
        painting.setOnLongClickListener(myOnLongClickListener);

        Switch lightSwitch = findViewById(R.id.switch_main_light);
        lightSwitch.setOnCheckedChangeListener(myOnCheckedChangeListener);
        lightSwitch.setChecked(true);

        SetupSpinner();
        Spinner spinner = findViewById(R.id.spinner_main_paintings);
        spinner.setOnItemSelectedListener(myOnItemSelectedListener);
    }

    void SetupSpinner()
    {
        Spinner spinner = findViewById(R.id.spinner_main_paintings);
        List<String> categories = new ArrayList<>();
        categories.add(0, "Mona Lisa");
        categories.add("American Gothic");
        categories.add("The Scream");
        categories.add("Van Gogh's Self Portrait");
        categories.add("Girl with a Pearl Earring");

        ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrAdapter);
    }

    private AdapterView.OnItemSelectedListener myOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            ImageView painting = findViewById(R.id.imageView_main_painting);
            switch (item) {
                case "Mona Lisa":
                    painting.setImageResource(R.drawable.main_monalisa_xx);
                    break;
                case "American Gothic":
                    painting.setImageResource(R.drawable.main_american_xx);
                    break;
                case "The Scream":
                    painting.setImageResource(R.drawable.main_scream_xx);
                    break;
                case "Van Gogh's Self Portrait":
                    painting.setImageResource(R.drawable.main_gogh_xx);
                    break;
                default:
                    painting.setImageResource(R.drawable.main_pearl_xx);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            return;
        }
    };

    private View.OnLongClickListener myOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Toast toast = Toast.makeText(getApplicationContext(), "Do not touch the artworks!", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
    };

    private CompoundButton.OnCheckedChangeListener myOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            ImageView imageView = findViewById(R.id.imageView_main_painting);
            View root = imageView.getRootView();
            if (isChecked) {
                imageView.clearColorFilter();
                buttonView.setTextColor(Color.BLACK);
                root.setBackgroundColor(Color.WHITE);
            } else {
                imageView.setColorFilter(Color.BLACK);
                buttonView.setTextColor(Color.WHITE);
                root.setBackgroundColor(Color.BLACK);
            }
        }
    };

}