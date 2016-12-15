package cl.adachersoft.infocountry;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cl.adachersoft.infocountry.network.GetCountry;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cristian on 07-12-2016.
 */

public class CountryInterceptor {

    private static final String BASE_URL = "https://restcountries-v1.p.mashape.com/";

    public GetCountry inteceptor() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request request = originalRequest.newBuilder()
                    /*Common headers*/
                        .header("X-Mashape-Key", "G91OKOicPjmshF8WTUFi6hEE3Crqp1yiS42jsnWyhWNAwOsHbn")
                        .build();

                Response response = chain.proceed(request);

            /*If the request fail then you get 3 retrys*/
                int retryCount = 0;
                while (!response.isSuccessful() && retryCount < 3) {
                    retryCount++;
                    response = chain.proceed(request);
                }

                return response;
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        GetCountry request = interceptor.create(GetCountry.class);

        return request;

    }
}

