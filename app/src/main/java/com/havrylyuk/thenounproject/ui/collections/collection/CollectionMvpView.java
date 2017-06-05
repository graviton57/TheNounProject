package com.havrylyuk.thenounproject.ui.collections.collection;

import com.havrylyuk.thenounproject.data.remote.model.NounCollection;
import com.havrylyuk.thenounproject.ui.base.BaseMvpView;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public interface CollectionMvpView extends BaseMvpView {

    void showCollections(List<NounCollection> nounCollectionList);

    void showEmptyView();


}
