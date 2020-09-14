package co.com.ceiba.mobile.pruebadeingreso.base.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.BuildConfig;
import co.com.ceiba.mobile.pruebadeingreso.remote.UserService;
import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Component ID: NetworkModule.java <br>
 * Description: Provides ReST API Dagger dependencies, having a single instance (singleton) for handling API calls. <br>
 * Author: <a href="mailto:lauragomez.lg247@gmail.com">Laura Gómez</a>
 * <p/>
 * Revision Change
 * <table>
 * <tr>
 * <th>Author</th><th>Date</th><th>Version</th><th>Change-Description</th>
 * </tr>
 * <tr>
 * <td>Laura Gómez</td><td>9/12/2020</td><td>1.0</td><td>Initial</td>
 * </tr>
 * </table>
 */
@Module(includes = {ViewModelModule.class})
public class NetworkModule {

    @Provides
    @Singleton
    UserService providesUserService() {
        return obtainRetrofit().create(UserService.class);
    }

    private Retrofit obtainRetrofit() {
        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder().build();
                    request = chain
                            .request()
                            .newBuilder()
                            .url(url)
                            .build();

                    return chain.proceed(request);
                })
                .build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }
}
