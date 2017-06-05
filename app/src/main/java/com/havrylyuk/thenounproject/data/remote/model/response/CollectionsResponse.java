package com.havrylyuk.thenounproject.data.remote.model.response;

import com.havrylyuk.thenounproject.data.remote.model.NounCollection;

import java.util.List;

/**
 *
 * Created by Igor Havrylyuk on 18.05.2017.
 */

public class CollectionsResponse {

    private List<NounCollection> collections;

    public CollectionsResponse() {
    }

    public List<NounCollection> getNounCollections() {
        return collections;
    }

    public void setCollections(List<NounCollection> collections) {
        this.collections = collections;
    }
}
