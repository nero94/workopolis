package com.edvantis.workopolis.api;


import com.edvantis.workopolis.model.Direction;
import com.edvantis.workopolis.model.User;
import com.edvantis.workopolis.model.Vacancy;
import com.google.gson.JsonObject;

import org.apache.http.HttpStatus;

import java.security.Principal;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by vasyl.dmytriv on 9/8/2016.
 */
public interface ApiInterface {

    @GET("/api/vacancies")
    Call<List<Vacancy>> getVacancies(
            @Query("keyword") String keyword,
            @Query("direction") Direction direction,
            @Query("address") String location,
            @Query("salary") Integer salary,
            @Query("offset") Integer offset,
            @Query("maxResults") Integer maxResults);

    @GET("/api/vacancy/{id}")
    Call<Vacancy> getVacancy(@Path("id") int id);

    @POST("/api/login")
    Call<User> basicLogin();

    @GET("/api/logout")
    Call<HttpStatus> basicLogout();

    @GET("/api/apply")
    Call<HttpStatus> apply(@Query("id") Integer vacancyId);

    @GET("/api/check_apply")
    Call<Boolean> checkApply(@Query("id") Integer vacancyId);

}
