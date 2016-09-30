package id.co.imperio.jakartaweatherpredicition;


import id.co.imperio.jakartaweatherpredicition.dataModel.Model;
import retrofit2.http.GET;
import retrofit2.Call;


/**
 * Created by Comp on 28/09/2016.
 */

public interface WeatherAPI {
    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */
    @GET("data/2.5/forecast/?q=Jakarta&mode=json&units=metric&appid=1d15d4c7b20a945a81703a49e2ca9c8f")
    Call<Model> getData();

}
