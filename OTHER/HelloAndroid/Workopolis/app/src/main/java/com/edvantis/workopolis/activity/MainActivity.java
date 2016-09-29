package com.edvantis.workopolis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edvantis.workopolis.model.Direction;
import com.edvantis.workopolis.model.DirectionMap;
import com.edvantis.workopolis.model.Vacancy;
import com.edvantis.workopolis.api.ApiInterface;
import com.edvantis.workopolis.util.CustomList;
import com.edvantis.workopolis.workopolis.R;
import com.edvantis.workopolis.api.ServiceGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView prevPage;
    private TextView nextPage;
    private TextView pageCounter;

    private int size = 0;
    private int[] id;
    private String[] names;
    private String desc[];
    private String imgUrl[];

    private String keyword = null;
    private Direction direction = null;
    private String location = null;
    private Integer salary = null;
    private Integer maxResult = 5;
    private Integer offset = 0;

    private void dataInit(){

        Bundle b = getIntent().getExtras();
        if(b != null) {
            keyword = b.getString("keyword");
            direction = DirectionMap.getKeyFromValue(DirectionMap.directions, b.getString("direction"));
            location = b.getString("location");
            salary = b.getInt("salary");
        }

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ApiInterface gitHubService = ServiceGenerator.createService(ApiInterface.class);
        Call<List<Vacancy>> call = gitHubService.getVacancies(keyword, direction, location, salary, offset, maxResult);
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            vacancies = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        size = vacancies.size();

        id = new int[size];
        names = new String[size];
        desc = new String[size];
        imgUrl = new String[size];

        for (int i=0; i < size; i++){
            Vacancy temp = vacancies.get(i);
            id[i] = temp.getId();
            names[i] = temp.getTitle();
            desc[i] = temp.getDescription();
            imgUrl[i] = temp.getImgUrl();
       }

        CustomList customList = new CustomList(this, names, desc, imgUrl);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(customList);

        prevPage = (TextView) findViewById(R.id.viewPrevPage);
        nextPage = (TextView) findViewById(R.id.viewNextPage);
        pageCounter = (TextView) findViewById(R.id.viewPageCounter);

        pageCounter.setText("<"+offset/maxResult+">");

        if (vacancies.size() == 0){
            prevPage.performClick();
            Toast.makeText(getBaseContext(), "No results", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            dataInit();
        }
        catch (Exception e){
            Toast.makeText(getBaseContext(), "Wrong input data", Toast.LENGTH_LONG).show();

        }

        prevPage.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
               if (offset > 1) {
                   offset = offset - maxResult;
                   dataInit();
               }
            }
        });

        nextPage.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if (size >= maxResult) {
                    offset = offset + maxResult;
                    dataInit();
                }
                else Toast.makeText(getBaseContext(), "No results", Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentDescription = new Intent(getApplicationContext(), DescriptionActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", id[i]); //Your id
                intentDescription.putExtras(b); //Put your id to your next Intent
                startActivity(intentDescription);
                //Toast.makeText(getApplicationContext(),"You Clicked "+names[i],Toast.LENGTH_SHORT).show();
            }
        });

    }

}
