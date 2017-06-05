package com.havrylyuk.thenounproject.data.remote.helper.error;

/**
 * Created by Igor Havrylyuk on 19.05.2017
 */
public class ServerException extends Throwable {

    public ServerException(Throwable throwable) {
        super(throwable);
    }
}
