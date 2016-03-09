package com.example.amadeusz.chef_cook;

import retrofit2.Call;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface Get {

    @GET("/getAll.php?type=Zdjecia&receptureName=muszleZLososiem")
    Call<ResponseBody> getStringJSON();

}
