package cl.adachersoft.infocountry;

import android.os.AsyncTask;
import android.provider.SyncStateContract;

import java.io.IOException;

import cl.adachersoft.infocountry.models.Indicator;
import cl.adachersoft.infocountry.network.GetCountry;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by cristian on 07-12-2016.
 */

public class BackgroundData extends AsyncTask<String, Void, Indicator> {

    @Override
    protected Indicator doInBackground(String... params) {
        GetCountry getCountry = new CountryInterceptor().inteceptor();

        Call<Indicator[]> call = getCountry.getCountry(params[0]);

        try {
            Response<Indicator[]> response = call.execute();
            Indicator indicator = response.body()[0];
            return indicator;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}



