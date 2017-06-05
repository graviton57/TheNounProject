package com.havrylyuk.thenounproject.data.remote.helper.error;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.support.annotation.NonNull;

import com.havrylyuk.thenounproject.R;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.HttpURLConnection;


import javax.inject.Inject;

import retrofit2.Response;
import timber.log.Timber;



/**
 * Created by Igor Havrylyuk on 19.05.2017
 */

public class ErrorHandlerHelper {

    private final Context context;

    @Inject
    public ErrorHandlerHelper(Context context) {
        this.context = context;
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public String getProperErrorMessage(Throwable throwable) {
        Throwable properException = getProperException(throwable);
        if (properException instanceof ServerException) {
            return context.getString(R.string.error_server);
        } else if (properException instanceof ServerNotAvailableException) {
            return context.getString(R.string.error_server_not_available);
        } else if (properException instanceof InternetConnectionException) {
            return context.getString(R.string.error_connection);
        } else if (properException instanceof NotFoundException) {
            return context.getString(R.string.error_not_found);
        } else if (properException instanceof UnauthorizedException) {
            return context.getString(R.string.error_unauthorized);
        } else if (properException instanceof NounResponseException) {
            return properException.getMessage();
            //return throwable.getMessage();
        } else {
            return String.format(context.getString(R.string.error_default), throwable.getMessage());
        }
    }

    private Throwable getProperException(Throwable throwable)  {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            Response response = httpException.response();
            String errorBody = null;
            try {
                String error = response.errorBody().string();
                Document htmlFile = Jsoup.parse(error, "ISO-8859-1");
                errorBody = htmlFile.body().text();
                Timber.d("response.errorBody()=%s, errorBody = %s", error, errorBody );
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (errorBody == null ) {
                return getThrowable(response.message(), response.code(), throwable);
            } else {
                 return new NounResponseException(errorBody);
            }
        } else if (throwable instanceof IOException) {
            return new InternetConnectionException();
        } else if (throwable instanceof NetworkErrorException) {
            return new InternetConnectionException();
        }
        return throwable;
    }

    @NonNull
    private Throwable getThrowable(String message, int code, Throwable throwable) {
        Throwable exception;
        switch (code) {
            case  HttpURLConnection.HTTP_NOT_FOUND:
                exception = new NotFoundException();
                break;
            case HttpURLConnection.HTTP_FORBIDDEN:
                exception = new UnauthorizedException();
                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                exception = new UncheckedException(message);
                break;
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                exception = new ServerNotAvailableException();
                break;
            case HttpURLConnection.HTTP_NOT_IMPLEMENTED:
            case HttpURLConnection.HTTP_BAD_GATEWAY:
            case HttpURLConnection.HTTP_UNAVAILABLE:
            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                exception = new ServerException(throwable);
                break;
            default:
                exception = new UncheckedException(message);
                break;
        }
        return exception;
    }
}
