package com.havrylyuk.thenounproject.data.remote.model.response;

import com.havrylyuk.thenounproject.data.remote.model.NounLimitUsage;

/**
 * Created by Igor Havrylyuk on 19.05.2017.
 */

public class UsageResponse {

    private NounLimitUsage usage;
    private NounLimitUsage limits;

    public UsageResponse() {
    }

    public NounLimitUsage getUsage() {
        return usage;
    }

    public NounLimitUsage getLimits() {
        return limits;
    }

    public void setUsage(NounLimitUsage usage) {
        this.usage = usage;
    }

    public void setLimits(NounLimitUsage limits) {
        this.limits = limits;
    }
}
