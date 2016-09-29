package com.edvantis.workopolis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.edvantis.workopolis.workopolis.R;

/**
 * Created by vasyl.dmytriv on 9/12/2016.
 */
public class SearchActivity extends AppCompatActivity{

    Button search;
    EditText keyword;
    Spinner direction;
    EditText location;
    EditText salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Spinner spinner = (Spinner) findViewById(R.id.viewEditDirection);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.directions_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        search = (Button) findViewById(R.id.viewSearchButton);
        keyword = (EditText) findViewById(R.id.viewEditKeyword);
        direction = (Spinner) findViewById(R.id.viewEditDirection);
        location = (EditText) findViewById(R.id.viewEditLocation);
        salary = (EditText) findViewById(R.id.viewEditSalary);

        search.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        try{
                        Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                        Bundle b = new Bundle();
                        b.putString("keyword", keyword.getText().toString());
                        b.putString("direction", direction.getSelectedItem().toString());
                        b.putString("location", location.getText().toString());
                        b.putInt("salary", (salary.getText().length() != 0)?(Integer.valueOf(salary.getText().toString())):0);
                        intentMain.putExtras(b);
                        startActivity(intentMain);
                    }
                        catch (Exception e){
                            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();
                            Log.v("Search error: ", e.toString());
                        }
                    }
                });

    }

}
