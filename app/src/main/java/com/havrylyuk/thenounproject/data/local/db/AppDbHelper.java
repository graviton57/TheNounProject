package com.havrylyuk.thenounproject.data.local.db;


import com.havrylyuk.thenounproject.data.local.db.model.DaoSession;
import com.havrylyuk.thenounproject.data.local.db.model.OrmCollection;
import com.havrylyuk.thenounproject.data.local.db.model.OrmCollectionDao;
import com.havrylyuk.thenounproject.data.local.db.model.OrmHistory;
import com.havrylyuk.thenounproject.data.local.db.model.OrmHistoryDao;
import com.havrylyuk.thenounproject.data.local.db.model.OrmIcon;
import com.havrylyuk.thenounproject.data.local.db.model.OrmIconDao;
import com.havrylyuk.thenounproject.data.local.db.model.SearchType;
import com.havrylyuk.thenounproject.data.remote.model.NounAuthor;
import com.havrylyuk.thenounproject.data.remote.model.NounCollection;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;

import org.greenrobot.greendao.query.QueryBuilder;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 *
 * Created by Igor Havrylyuk on 25.05.2017.
 */
public class AppDbHelper implements DbHelper {

    public static final int SEARCH_HISTORY_ITEM_LIMIT = 4;
    public static final String HISTORY_DATE_FORMAT = "yyyy-MM-dd HH:mm";

    private final DaoSession daoSession;

    @Inject
    public AppDbHelper(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Override
    public Boolean saveCollections(final List<NounCollection> collections) {
        daoSession.getOrmCollectionDao()
                .insertOrReplaceInTx(convertNounCollectionToOrm(collections));
        return true;
    }

    @Override
    public Observable<List<OrmCollection>> getAllCollections() {
        return Observable.fromCallable(new Callable<List<OrmCollection>>() {
            @Override public List<OrmCollection> call() throws Exception {
                return daoSession.getOrmCollectionDao()
                        .queryBuilder()
                        .orderDesc(OrmCollectionDao.Properties.Id)
                        .list();
            }
        });
    }

    @Override
    public Observable<List<NounCollection>> getCollectionsFromDb(final String term) {
        return Observable.fromCallable(new Callable<List<NounCollection>>() {
            @Override public List<NounCollection> call() throws Exception {
                QueryBuilder<OrmCollection> qb = daoSession.getOrmCollectionDao().queryBuilder();
                if (term != null) {
                    qb.where(OrmCollectionDao.Properties.Name.like(term));
                }
                return convertOrmToNounCollections(qb.list());
            }
        });

    }

    @Override
    public Observable<Boolean> deleteCollection() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override public Boolean call() throws Exception {
                daoSession.getOrmCollectionDao().deleteAll();
                return true;
            }
        });
    }

    @Override
    public Observable<OrmCollection> getCollectionById(final String id) {
        return Observable.fromCallable(new Callable<OrmCollection>() {
            @Override public OrmCollection call() throws Exception {
                return daoSession.getOrmCollectionDao()
                        .queryBuilder()
                        .where(OrmCollectionDao.Properties.Id.eq(id))
                        .limit(1)
                        .unique();
            }
        });
    }

    @Override
    public Observable<List<OrmCollection>> getCollectionsByTerm(final String term) {
        return Observable.fromCallable(new Callable<List<OrmCollection>>() {
            @Override
            public List<OrmCollection> call() throws Exception {
                return daoSession.getOrmCollectionDao()
                        .queryBuilder()
                        .where(OrmCollectionDao.Properties.Name.eq(term))
                        .list();
            }
        });
    }

    @Override
    public Observable<List<NounIcon>> getCollectionIcons(final String collectionId) {
        return Observable.fromCallable(new Callable<List<NounIcon>>() {
            @Override
            public List<NounIcon> call() throws Exception {
                List<OrmIcon> fromDb = daoSession.getOrmIconDao()
                        .queryBuilder()
                        .where(OrmIconDao.Properties.Id.eq(collectionId))
                        .list();
                return convertOrmIconToNoun(fromDb);
            }
        });
    }

    @Override
    public Boolean saveIconsToDb(final List<NounIcon> iconsList) {
        List<OrmIcon> list = convertNounIconToOrm(iconsList);
        daoSession.getOrmIconDao().insertOrReplaceInTx(list);
        return true;
    }

    @Override
    public Observable<List<NounIcon>> getIconsFromDb(final String term) {
        return Observable.fromCallable(new Callable<List<NounIcon>>() {
            @Override public List<NounIcon> call() throws Exception {
                QueryBuilder<OrmIcon> qb = daoSession.getOrmIconDao().queryBuilder();
                if (term != null) {
                    qb.where(OrmIconDao.Properties.Term.like(term));
                }
                return convertOrmIconToNoun(qb.list());
            }
        });
    }

    @Override
    public Observable<List<NounIcon>> getFavoriteIcons() {
        return Observable.fromCallable(new Callable<List<NounIcon>>() {
            @Override public List<NounIcon> call() throws Exception {
                List<OrmIcon> fromDb = daoSession.getOrmIconDao()
                        .queryBuilder()
                        .where(OrmIconDao.Properties.IsFavorite.eq(true))
                        .list();
                return convertOrmIconToNoun(fromDb);
            }
        });
    }

    @Override
    public Observable<OrmIcon> getIconById(final String id) {
        return Observable.fromCallable(new Callable<OrmIcon>() {
            @Override public OrmIcon call() throws Exception {
                return daoSession.getOrmIconDao()
                        .queryBuilder()
                        .where(OrmIconDao.Properties.Id.eq(id))
                        .limit(1)
                        .unique();
            }
        });
    }

    @Override
    public Observable<List<OrmIcon>> getIconByTerm(final String term) {
        return Observable.fromCallable(new Callable<List<OrmIcon>>() {
            @Override
            public List<OrmIcon> call() throws Exception {
                return daoSession.getOrmIconDao()
                        .queryBuilder()
                        .where(OrmIconDao.Properties.Term.eq(term))
                        .list();
            }
        });
    }

    @Override
    public Observable<Boolean> deleteIcons() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override public Boolean call() throws Exception {
                daoSession.getOrmIconDao().deleteAll();
                return true;
            }
        });
    }

    @Override
    public Observable<List<OrmHistory>> getSearchHistory() {
        return Observable.fromCallable(new Callable<List<OrmHistory>>() {
            @Override
            public List<OrmHistory> call() throws Exception {
                return daoSession.getOrmHistoryDao()
                        .queryBuilder()
                        .orderDesc(OrmHistoryDao.Properties.Date)
                        .limit(SEARCH_HISTORY_ITEM_LIMIT)
                        .list();
            }
        });
    }

    @Override
    public <T> Long saveSearchHistory(String query, T nounEntity) {
        OrmHistory ormHistory = convertNounToHistoryOrm(nounEntity);
        if (ormHistory != null){
            ormHistory.setQuery(query);
            return daoSession.getOrmHistoryDao().insertOrReplace(ormHistory);
        } else {
            return (long)0;
        }
    }

    @Override
    public Observable<Boolean> clearSearchHistory() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override public Boolean call() throws Exception {
                daoSession.getOrmHistoryDao().deleteAll();
                return true;
            }
        });
    }

    @Override
    public Long getIconsCount() {
        return daoSession.getOrmIconDao().count();
    }

    @Override
    public Long getCollectionsCount() {
        return daoSession.getOrmCollectionDao().count();
    }

    @Override
    public Long getHistoryCount() {
        return daoSession.getOrmHistoryDao().count();
    }

    public static List<OrmIcon> convertNounIconToOrm(List<NounIcon> remoteNounIcons){
        List<OrmIcon> localOrmIcons = new ArrayList<>();
        for (NounIcon nounIcon : remoteNounIcons) {
            OrmIcon ormIcon = new OrmIcon();
            ormIcon.setId(nounIcon.getId());
            ormIcon.setTerm(nounIcon.getTerm());
            ormIcon.setTermSlug(nounIcon.getTermSlug());
            ormIcon.setDateUploaded(nounIcon.getDateUploaded());
            ormIcon.setAttribution(nounIcon.getAttribution());
            ormIcon.setPreviewUrl(nounIcon.getPreviewUrl());
            localOrmIcons.add(ormIcon);
        }
        return localOrmIcons;
    }

    public static List<OrmCollection> convertNounCollectionToOrm(List<NounCollection> nouns){
        List<OrmCollection> orms = new ArrayList<>();
        for (NounCollection nounCol : nouns) {
            OrmCollection ormCol = new OrmCollection();
            ormCol.setId(nounCol.getId());
            ormCol.setName(nounCol.getName());
            ormCol.setPermalink(nounCol.getPermalink());
            ormCol.setSlug(nounCol.getSlug());
            if (nounCol.getAuthor() != null) {
                if (nounCol.getAuthor().getName() != null) {
                    ormCol.setAuthorName(nounCol.getAuthor().getName());
                }
                if (nounCol.getAuthor().getLocation() != null) {
                    ormCol.setAuthorLoc(nounCol.getAuthor().getLocation());
                }
            }
            ormCol.setDateCreated(nounCol.getDate_created());
            ormCol.setDescription(nounCol.getDescription());
            orms.add(ormCol);
        }
        return orms;
    }

    public static List<NounCollection> convertOrmToNounCollections(List<OrmCollection> collections){
        List<NounCollection> result = new ArrayList<>();
        if (!collections.isEmpty() ){
            for (OrmCollection collection : collections) {
                NounCollection noun = new NounCollection();
                noun.setId(collection.getId());
                noun.setDate_created(collection.getDateCreated());
                noun.setDescription(collection.getDescription());
                noun.setName(collection.getName());
                noun.setPermalink(collection.getPermalink());
                noun.setSlug(collection.getSlug());
                NounAuthor author = new NounAuthor();
                author.setLocation(collection.getAuthorLoc());
                author.setName(collection.getAuthorName());
                noun.setAuthor(author);
                result.add(noun);
            }
        }
        return result;
    }

    public static List<NounIcon> convertOrmIconToNoun(List<OrmIcon> ormIcons){
        List<NounIcon> nounIcons = new ArrayList<>();
        for (OrmIcon ormIcon : ormIcons) {
            NounIcon nounIcon = new NounIcon();
            nounIcon.setId(ormIcon.getId());
            nounIcon.setDateUploaded(ormIcon.getDateUploaded());
            nounIcon.setAttribution(ormIcon.getAttribution());
            nounIcon.setPreviewUrl(ormIcon.getPreviewUrl());
            nounIcon.setFavorite(ormIcon.getFavorite()); // todo check NullPointerException
            nounIcons.add(nounIcon);
        }
        return nounIcons;
    }

    private <T> OrmHistory convertNounToHistoryOrm(T nounEntity) {
        OrmHistory result = null;
        String queryDateTime = new DateTime().toString(HISTORY_DATE_FORMAT);
        if (nounEntity instanceof NounIcon){
            result = new OrmHistory();
            result.setId(((NounIcon)nounEntity).getId());
            result.setDate(queryDateTime);
            result.setPreviewUrl(((NounIcon)nounEntity).getPreviewUrl());
            result.setType(SearchType.ICONS);
        }
        if (nounEntity instanceof NounCollection){
            result = new OrmHistory();
            result.setId(((NounCollection)nounEntity).getId());
            result.setDate(queryDateTime);
            result.setType(SearchType.COLLECTIONS);
        }
        return result;
    }

}
