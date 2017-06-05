package com.havrylyuk.thenounproject.events;


import com.havrylyuk.thenounproject.data.remote.model.NounCollection;

/**
 * Created by Igor Havrylyuk on 19.05.2017
 */

public class NounCollectionsEvent {

    private final NounCollection  nounCollection;

    public NounCollectionsEvent(NounCollection nounCollection) {
        this.nounCollection = nounCollection;
    }

    public NounCollection getNounCollection() {
        return nounCollection;
    }

}
