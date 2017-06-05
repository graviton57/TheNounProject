package com.havrylyuk.thenounproject.data.local.db;

import com.havrylyuk.thenounproject.data.local.db.model.OrmCollection;
import com.havrylyuk.thenounproject.data.local.db.model.OrmHistory;
import com.havrylyuk.thenounproject.data.local.db.model.OrmIcon;
import com.havrylyuk.thenounproject.data.remote.model.NounCollection;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;

import io.reactivex.Observable;
import java.util.List;

/**
 *
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public interface DbHelper {

    Observable<List<OrmCollection>> getAllCollections();

    Observable<List<NounCollection>> getCollectionsFromDb(String term);

    Observable<OrmCollection> getCollectionById(String id);

    Observable<List<OrmCollection>> getCollectionsByTerm(String term);

    Observable<List<NounIcon>> getCollectionIcons(String collectionId);

    Boolean saveCollections(List<NounCollection> collectionsList);

    Observable<Boolean> deleteCollection();

    Boolean saveIconsToDb(List<NounIcon> iconsList);

    Observable<List<NounIcon>> getIconsFromDb(String term);

    Observable<List<NounIcon>> getFavoriteIcons();

    Observable<OrmIcon> getIconById(String id);

    Observable<List<OrmIcon>> getIconByTerm(String term);

    Observable<Boolean> deleteIcons();

    Observable<List<OrmHistory>> getSearchHistory();

    <E> Long  saveSearchHistory(String query, E nounEntity);

    Observable<Boolean> clearSearchHistory();

    Long getIconsCount();

    Long getCollectionsCount();

    Long getHistoryCount();

}
