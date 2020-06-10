package ar.com.maxwell.android_warehouse.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MeowAPI {
    @GET("meow")
    Call<Cat> getCatPicture();
}
