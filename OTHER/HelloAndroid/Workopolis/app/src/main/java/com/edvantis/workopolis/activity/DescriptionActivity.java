package com.edvantis.workopolis.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.edvantis.workopolis.model.CommunicationSkill;
import com.edvantis.workopolis.model.Direction;
import com.edvantis.workopolis.model.DirectionMap;
import com.edvantis.workopolis.model.TechnicalSkill;
import com.edvantis.workopolis.model.Vacancy;
import com.edvantis.workopolis.api.ApiInterface;
import com.edvantis.workopolis.workopolis.R;
import com.edvantis.workopolis.api.ServiceGenerator;

import org.apache.http.HttpStatus;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vasyl.dmytriv on 9/7/2016.
 */
public class DescriptionActivity extends AppCompatActivity{
    private boolean authenticated = false;

    private Integer vacancyId = null;
    private String title = "";
    private String description = "";
    private int salary;
    private String address = "";
    private Direction direction;
    private String responsibilities = "";
    private String technicalSkills = "";
    private String communicationSkills = "";
    private Boolean isApplied = null;

    private void dataInit(){

        Bundle b = getIntent().getExtras();
        int id = -1; // or other values
        if(b != null) {
            id = b.getInt("id");
            vacancyId = id;
        }
        try {
            ApiInterface apiSevice = ServiceGenerator.createService(ApiInterface.class);
            Call<Vacancy> call = apiSevice.getVacancy(id);
            Vacancy vacancy;
            vacancy = call.execute().body();
            title = vacancy.getTitle();
            description = vacancy.getDescription();
            salary = vacancy.getSalary();
            address = vacancy.getLocation();
            direction = vacancy.getDirection();
            responsibilities = vacancy.getResponsibilities();
            for(TechnicalSkill technicalSkill:vacancy.getTechnicalSkills()){
                technicalSkills += technicalSkill.toString();
            }
            for(CommunicationSkill communicationSkill:vacancy.getCommunicationSkills()){
                technicalSkills += communicationSkill.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            ApiInterface applyCheckSevice = ServiceGenerator.createService(ApiInterface.class);
            Call<Boolean> call = applyCheckSevice.checkApply(vacancyId);
            isApplied = call.execute().body();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_description);

        dataInit();

        TextView viewTitle = (TextView) findViewById(R.id.viewTitle);
        viewTitle.setText(title);
        TextView viewDirection = (TextView) findViewById(R.id.viewDirection);
        viewDirection.setText(DirectionMap.directions.get(direction));
        TextView viewSalary = (TextView) findViewById(R.id.viewSalary);
        viewSalary.setText(salary + "");
        TextView viewLocation = (TextView) findViewById(R.id.viewLocation);
        viewLocation.setText(address);
        TextView viewDescription = (TextView) findViewById(R.id.viewDescription);
        viewDescription.setText(description);
        TextView viewResponsibilities = (TextView) findViewById(R.id.viewResponsibilities);
        viewResponsibilities.setText(responsibilities);
        TextView viewSkills = (TextView) findViewById(R.id.viewSkills);
        viewSkills.setText(technicalSkills);
        TextView viewIfApplied = (TextView) findViewById(R.id.viewIfApplied);

        Button applyButton = (Button) findViewById(R.id.viewApplyButton);
        SharedPreferences userData = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        authenticated = userData.getBoolean("authenticated", authenticated);
        if (!authenticated || isApplied == null){
            applyButton.setVisibility(View.GONE);
            viewIfApplied.setVisibility(View.GONE);
        }
        else if (isApplied == true){
            applyButton.setVisibility(View.GONE);
        }else viewIfApplied.setVisibility(View.GONE);

        applyButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        try{
                            ApiInterface applySevice = ServiceGenerator.createService(ApiInterface.class);
                            Call<HttpStatus> call = applySevice.apply(vacancyId);
                            call.enqueue(new Callback<HttpStatus>() {
                                @Override
                                public void onResponse(Call<HttpStatus> call, Response<HttpStatus> response) {
                                    Toast.makeText(getBaseContext(), "Success", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFailure(Call<HttpStatus> call, Throwable t) {
                                    Toast.makeText(getBaseContext(), "Success", Toast.LENGTH_LONG).show();
                                }
                            });
                            recreate();
                        }
                        catch (Exception e){
                            Log.v("Apply error: ", e.toString());
                        }
                    }
                });

    }

}
