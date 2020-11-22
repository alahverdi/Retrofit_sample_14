package ir.allahverdi.session14webservice.service;

import java.util.List;

import ir.allahverdi.session14webservice.entity.Product;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DivarApi {

    String BASE_CLASS = "https://run.mocky.io/";

    @GET("v3/788ee63c-5343-4281-89dd-e773d549efb4")
    Call<List<Product>> getAllProducts();
}
