package com.havrylyuk.thenounproject.data.remote.helper.error;

import timber.log.Timber;

/**
 * Created by Igor Havrylyuk on 19.05.2017
 */
public class NounResponseException extends Throwable {

    public NounResponseException(String detailMessage) {
        super(detailMessage);
        Timber.d("NounResponseException=%s",detailMessage);
    }

}
