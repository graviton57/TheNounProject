package com.havrylyuk.thenounproject.data.remote.model.response;

/**
 * {
 *  "licenses_consumed": 3,
 *  "result": "success"
 * }
 * Created by Igor Havrylyuk on 19.05.2017.
 */

public class PublishResponse {

    private int licenses_consumed;
    private String result;

    public PublishResponse() {
    }

    public int getLicensesConsumed() {
        return licenses_consumed;
    }

    public void setLicensesConsumed(int licenses_consumed) {
        this.licenses_consumed = licenses_consumed;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
