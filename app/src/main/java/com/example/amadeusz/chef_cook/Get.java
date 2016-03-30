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

    @GET("/getAll.php?type=ZdjeciaEtapow&receptureName=kokosowaPotrawkaZAnanasem")
    Call<ResponseBody> getStepsPhotosCoconutStewWithPineapple();

    @GET("/getAll.php?type=ZdjeciaPotraw&receptureName=kokosowaPotrawkaZAnanasem")
    Call<ResponseBody> getPhotosCoconutStewWithPineapple();

    @GET("/getAll.php?type=ZdjeciaEtapow&receptureName=slodkiePampuchy")
    Call<ResponseBody> getStepsPhotosSweetPampuchy();

    @GET("/getAll.php?type=Wideo&receptureName=slodkiePampuchy")
    Call<ResponseBody> getWideoForSweetPampuchy();

    @GET("/getAll.php?type=ZdjeciaPotraw&receptureName=slodkiePampuchy")
    Call<ResponseBody> getPhotosSweetPampuchy();

    @GET("/getAll.php?type=ZdjeciaEtapow&receptureName=zupaPomidorowa")
    Call<ResponseBody> getStepsPhotosForTomatoSoup();

    @GET("/getAll.php?type=Wideo&receptureName=zupaPomidorowa")
    Call<ResponseBody> getWideoForTomatoSoup();

    @GET("/getAll.php?type=ZdjeciaPotraw&receptureName=zupaPomidorowa")
    Call<ResponseBody> getPhotosTomatoSoup();

}
