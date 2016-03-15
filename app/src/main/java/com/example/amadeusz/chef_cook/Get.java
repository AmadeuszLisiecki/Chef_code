package com.example.amadeusz.chef_cook;

import retrofit2.Call;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface Get {

    @GET("/getAll.php?type=ZdjeciaEtapow&receptureName=muszleZLososiem")
    Call<ResponseBody> getStepsPhotosForSalmoNudle();

    @GET("/getAll.php?type=Wideo&receptureName=muszleZLososiem")
    Call<ResponseBody> getWideoForSalmoNudle();

    @GET("/getAll.php?type=ZdjeciaPotraw&receptureName=muszleZLososiem")
    Call<ResponseBody> getPhotosForSalmoNudle();
}
