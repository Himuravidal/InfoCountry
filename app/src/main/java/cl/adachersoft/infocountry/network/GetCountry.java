package cl.adachersoft.infocountry.network;

import cl.adachersoft.infocountry.models.Indicator;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static android.R.attr.country;

/**
 * Created by cristian on 06-12-2016.
 */

public interface GetCountry {


    @GET("name/{country}")
    Call<Indicator[]> getCountry(@Path("country") String country);


}