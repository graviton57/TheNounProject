package com.havrylyuk.thenounproject;

import com.havrylyuk.thenounproject.data.local.db.AppDbHelper;
import com.havrylyuk.thenounproject.data.local.db.model.OrmIcon;
import com.havrylyuk.thenounproject.data.remote.model.NounCollection;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.data.remote.model.NounLimitUsage;
import com.havrylyuk.thenounproject.data.remote.model.response.CollectionsResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.IconsResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.RecentUploadResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.UsageResponse;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import static com.havrylyuk.thenounproject.data.local.db.AppDbHelper.HISTORY_DATE_FORMAT;

/**
 * Created by Igor Havrylyuk on 30.05.2017.
 */

public class TestModels {


    public static IconsResponse getIconsResponseModel(int iconsListSize) {
        List<NounIcon> list = new ArrayList<>();
        String generatedAt = newGeneratedAt();
        for (int i = 0; i < iconsListSize; i++) {
            list.add(newIcon());
        }
        IconsResponse responseModel = new IconsResponse();
        responseModel.setGeneratedAt(generatedAt);
        responseModel.setIcons(list);
        return responseModel;
    }

    public static RecentUploadResponse getRecentUploadIconsResponse(int iconsListSize) {
        List<NounIcon> list = new ArrayList<>();
        String generatedAt = newGeneratedAt();
        for (int i = 0; i < iconsListSize; i++) {
            list.add(newIcon());
        }
        RecentUploadResponse responseModel = new RecentUploadResponse();
        responseModel.setGeneratedAt(generatedAt);
        responseModel.setRecentUploads(list);
        return responseModel;
    }

    public static IconsResponse getCollectionIconsResponse(int iconsListSize) {
        List<NounIcon> list = new ArrayList<>();
        String generatedAt = newGeneratedAt();
        for (int i = 0; i < iconsListSize; i++) {
            list.add(newIcon());
        }
        IconsResponse responseModel = new IconsResponse();
        responseModel.setGeneratedAt(generatedAt);
        responseModel.setIcons(list);
        return responseModel;
    }

    public static CollectionsResponse getCollectionsResponse(int iconsListSize) {
        List<NounCollection> list = new ArrayList<>();
        for (int i = 0; i < iconsListSize; i++) {
            list.add(newCollection());
        }
        CollectionsResponse responseModel = new CollectionsResponse();
        responseModel.setCollections(list);
        return responseModel;
    }

    public static UsageResponse getOauthUsage() {
        UsageResponse responseModel = new UsageResponse();
        responseModel.setLimits(newUsageLimit());
        responseModel.setUsage(newUsageLimit());
        return responseModel;
    }

    private static NounLimitUsage newUsageLimit() {
        NounLimitUsage nounLimitUsage = new NounLimitUsage();
        nounLimitUsage.setDaily(0);
        nounLimitUsage.setMonthly(0);
        nounLimitUsage.setHourly(0);
        return nounLimitUsage;
    }

    //Database

    public static List<NounIcon> getDbIcons(int iconsListSize) {
        List<OrmIcon> list = new ArrayList<>();
        for (int i = 0; i < iconsListSize; i++) {
            list.add(newIconModel(i + 1));
        }
        return AppDbHelper.convertOrmIconToNoun(list);
    }

    public static NounIcon newNounIconModel(){
        NounIcon result = newIcon();
        result.setId("1");
        result.setAttribution("Beauty Salon from The Noun Project");
        result.setPreviewUrl("https://d30y9cdsu7xlg0.cloudfront.net/png/15-200.png");
        result.setDateUploaded(new DateTime().toString(HISTORY_DATE_FORMAT));
        return result;
    }

    public static OrmIcon newIconModel(int id) {
        OrmIcon ormIcon = new OrmIcon();
        ormIcon.setId(String.valueOf(id));
        ormIcon.setAttribution("Beauty Salon from The Noun Project");
        ormIcon.setDateUploaded(new DateTime().toString(HISTORY_DATE_FORMAT));
        ormIcon.setPreviewUrl("https://d30y9cdsu7xlg0.cloudfront.net/png/15-200.png");
        return ormIcon;
    }

    public static List<NounCollection> getDbCollections(int iconsListSize) {
        List<NounCollection> list = new ArrayList<>();
        for (int i = 0; i < iconsListSize; i++) {
            list.add(newCollectionModel(i+1));
        }
        return list;
    }

    public static NounCollection newCollectionModel(int id) {
        NounCollection nounCollection = new NounCollection();
        nounCollection.setId(String.valueOf(id));
        nounCollection.setDate_created("");
        nounCollection.setDescription("");
        return nounCollection;
    }


    public static NounIcon newIcon() {
        return new NounIcon();
    }

    public static NounCollection newCollection() {
        return new NounCollection();
    }

    public static String newGeneratedAt() {
        return "Tue, 30 May 2017 06:38:38 GMT";
    }
}
