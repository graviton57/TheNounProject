package com.havrylyuk.thenounproject.events;

/**
 * Created by Igor Havrylyuk on 02.06.2017.
 */

public class SearchParamEvent {

    private final boolean isPublic;

    public SearchParamEvent(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public boolean isPublic() {
        return isPublic;
    }
}
