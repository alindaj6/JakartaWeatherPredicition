package id.co.imperio.jakartaweatherpredicition;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.imperio.jakartaweatherpredicition.dataModel.Model;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView) TextView textView;

    public static final String WEATHER_API = "http://api.openweathermap.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Calling the method that will fetch city
        getWeathers();

    }

    private void getWeathers(){
        //While the app fetched city we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WEATHER_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherAPI service = retrofit.create(WeatherAPI.class);

        Call<Model> call = service.getData();

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {

                List<id.co.imperio.jakartaweatherpredicition.dataModel.List> lists = response.body().getList();

                String detail = "";

                for (int i = 0; i < lists.size(); i++) {

                    String suhu = String.valueOf(lists.get(i).getMain().getTemp());
                    String description = lists.get(i).getWeather().get(0).getDescription();
                    String date = lists.get(i).getDtTxt();

                    detail += "Suhu = " + suhu + " Date = " + date + " Description = " + description + "\n";
                }

                textView.setText(detail);
                loading.dismiss();

                loading.dismiss();
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d("ERROR", t.getLocalizedMessage());
                loading.dismiss();
            }
        });

    }
}
