package com.prm392.restapi.model;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface TraineeService {
    String TRAINEE = "Trainee";
    @GET(TRAINEE)
    Call<List<Trainee>> getAllTrainees();

    @GET(TRAINEE + "/{id}")
    Call<Trainee> getTraineeById(@Path("id") Object id);

    @POST(TRAINEE)
    Call<Trainee> createTrainee(@Body Trainee trainee);

    @PUT(TRAINEE + "/{id}")
    Call<Trainee> updateTrainee(@Path("id") Object id, @Body Trainee trainee);

    @DELETE(TRAINEE + "/{id}")
    Call<Trainee> deleteTrainee(@Path("id") Object id);
}
