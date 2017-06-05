package com.havrylyuk.thenounproject.injection.module;

import android.content.Context;

import com.havrylyuk.thenounproject.BuildConfig;
import com.havrylyuk.thenounproject.data.remote.ApiHelper;
import com.havrylyuk.thenounproject.data.remote.ApiInterface;
import com.havrylyuk.thenounproject.data.remote.AppApiHelper;
import com.havrylyuk.thenounproject.data.remote.helper.HeaderHelper;
import com.havrylyuk.thenounproject.data.remote.helper.error.ErrorHandlerHelper;
import com.havrylyuk.thenounproject.injection.qualifier.ApplicationContext;
import com.havrylyuk.thenounproject.injection.scope.TheNounProjectAppScope;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;
import timber.log.Timber;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

@Module(includes = { ContextModule.class, RxModule.class })
public class NetworkModule {

    @Provides
    @TheNounProjectAppScope
    public Interceptor httpInterceptor() {
        return new Interceptor() {
            @Override public Response intercept(Chain chain) throws IOException {
                // Request customization: add request headers
                Request.Builder requestBuilder = chain.request()
                        .newBuilder()
                        .method(chain.request().method(), chain.request().body())
                        .headers(HeaderHelper.getAppHeaders());
                return chain.proceed(requestBuilder.build());
            }
        };
    }

    @Provides
    @TheNounProjectAppScope
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override public void log(String message) {
                        Timber.i(message);
                    }
                });
        interceptor.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    @Provides
    @TheNounProjectAppScope
    public SigningInterceptor signingInterceptor(){
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(
                BuildConfig.NOUN_DEV_API_KEY,
                BuildConfig.NOUN_DEV_SECRET_API_KEY);
        return new SigningInterceptor(consumer);
    }

    @Provides
    @TheNounProjectAppScope
    public OkHttpClient okHttpClient(Interceptor interceptor) {

        return new OkHttpClient.Builder().addInterceptor(interceptor)
                .addInterceptor(signingInterceptor())
                .addInterceptor(loggingInterceptor())
                .build();
    }

    @Provides
    @TheNounProjectAppScope
    public Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(BuildConfig.NOUN_PROJECT_BASE_API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @TheNounProjectAppScope
    public ApiInterface apiServiceInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @TheNounProjectAppScope
    public ErrorHandlerHelper errorHandlerHelper(@ApplicationContext Context context) {
        return new ErrorHandlerHelper(context);
    }

    @Provides
    @TheNounProjectAppScope
    public ApiHelper apiHelper(ApiInterface apiInterface, ErrorHandlerHelper errorHandlerHelper) {
        return new AppApiHelper(apiInterface, errorHandlerHelper);
    }

}
