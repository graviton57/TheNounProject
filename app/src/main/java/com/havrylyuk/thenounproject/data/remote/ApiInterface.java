package com.havrylyuk.thenounproject.data.remote;

import com.havrylyuk.thenounproject.data.remote.model.NounCollection;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.data.remote.model.NounPublishData;
import com.havrylyuk.thenounproject.data.remote.model.response.CollectionsResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.IconsResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.PublishResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.RecentUploadResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.UsageResponse;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * The Noun Project  Api
 * Created by Igor Havrylyuk on 19.05.2017
 */
public interface ApiInterface {

    /**
     * Collections
     * Request http://api.thenounproject.com/collections/?limit=5&offset=50&page=1
     * @param  options  Map<String, String>  of query parameters
     * options.put("limit", 50) limit (int) – maximum number of results
     * options.put("offset", 100) offset (int) – number of results to displace or skip over
     * options.put("page", 2) page (int) – number of results of limit length to displace or skip over
     * @return Return’s a list of all collections
     */
    @GET("collections")
    Observable<CollectionsResponse> getCollections(@QueryMap Map<String, String> options);

    /**
     * WARNING: Officially not documented!
     * Collections by term
     * Request http://api.thenounproject.com/collections/android
     * @param  term    – collections term
     * @return Returns a list of collection with term
     */
    @GET("collections/{term}")
    Observable<CollectionsResponse> getCollections(@Path("term") String term);

    /**
     * Collection
     * Request http://api.thenounproject.com/collection/4
     * @param  id    – collection id
     * @return Returns a single collection
     */
    @GET("collection/{id}")
    Observable<NounCollection> getCollection(@Path("id")  int  id);

    /**
     * Collection icons
     * Requests example http://api.thenounproject.com/collection/55/icons?limit=4
     * @param  id - collection id
     * @param  options  Map<String, String>  of query params
     * options.put("limit", 50), options.put("offset", 100), options.put("page", 2)
     * @return Returns a list of icons associated with a collection
     */
    @GET("/collection/{id}/icons")
    Observable<IconsResponse> getCollectionIcons(@Path("id") int id,
                                                 @QueryMap Map<String, String> options );

    /**
     * Collection
     * Request http://api.thenounproject.com/collection/national-park-service
     * @param  slug collection slug
     * @return Returns a single collection
     */
    @GET("/collection/{slug}")
    Observable<NounCollection> getCollection(@Path("slug") String slug);


    /**
     * Icon
     * Request http://api.thenounproject.com/icon/15
     * @param id – icon id
     * @return Returns a single icon
     */
    @GET("/icon/{id}")
    Observable<NounIcon> getIconById(@Path("id") int id);

    /**
     * Icon
     * Request http://api.thenounproject.com/icon/globe
     * @param term – icon term
     * @return Returns a single icon
     */
    @GET("/icon/{term}")
    Observable<NounIcon> getIconByTerm(@Path("term") String term);

    /**
     * Icons
     * Request http://api.thenounproject.com/icons/fish?limit_to_public_domain=1&limit=4
     * @param term – icon term
     * @param  options  Map<String, String> query params
     * limit_to_public_domain (int) – limit results to public domain icons only
     * options.put("limit", 50) options.put("offset", 100) options.put("page", 2)
     * @return Returns a list of icons
     */
    @GET("/icons/{term}")
    Observable<IconsResponse> getIcons(@Path("term") String term,
                                 @QueryMap Map<String, String> options);

    /**
     * Recent Uploads
     * Request http://api.thenounproject.com/icons/recent_uploads?limit=3&page=6
     * @param  options  Map<String, String>  of query params
     * options.put("limit", 50), options.put("offset", 100), options.put("page", 2)
     * @return Returns list of most recently uploaded icons
     */
    @GET("/icons/recent_uploads")
    Observable<RecentUploadResponse> getRecentUploadsIcons(@QueryMap Map<String, String> options);

    /**
     * Usage
     * Request http://api.thenounproject.com/oauth/usage
     * Returns current oauth usage and limits
     */
    @GET("/oauth/usage")
    Observable<UsageResponse> getOauthUsage();

    /**
     * User
     * Request http://api.thenounproject.com/user/1/collections/bicycle
     * @param userId - user id
     * @param slug - collection slug
     * @return  Returns a single collection associated with a user
     */
    @GET("/user/{user_id}/collections/{slug}")
    Observable<NounCollection> getUserCollection(
            @Path("user_id") int userId,
            @Path("slug")    String slug);

    /**
     * User
     * Request http://api.thenounproject.com/user/6/collections
     * @param userId - user id
     * @return  Returns a list of collections associated with a user
     */
    @GET("/user/{user_id}/collections")
    Observable<CollectionsResponse> getUserCollections(@Path("user_id") int userId);

    /**
     * User uploads
     * Request http://api.thenounproject.com/user/edward/uploads?limit=2
     * @param  username  username
     * @param  options  Map<String, String>
     * options.put("limit", 50) options.put("offset", 100) options.put("page", 2)
     * @return Returns a list of uploads associated with a user
     */
    @GET("/user/{username}/uploads")
    Observable<CollectionsResponse> getUserUploads(
            @Path("username") String username,
            @QueryMap Map<String, String> options);

    /**
     * Accepts icon ids for reporting icon usage
     * Request http://api.thenounproject.com/notify/publish
     * To test endpoint without reporting data, use /notify/publish?test=1
     * @param icons (JSON Parameters) – a string containing icon ids separated by commas
     * @return see PublishResponse
     */
    @POST("notify/publish")
    Observable<PublishResponse> publish(@Body NounPublishData icons);

}
