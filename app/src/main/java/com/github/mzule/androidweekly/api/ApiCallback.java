package com.github.mzule.androidweekly.api;

/**
 * Created by CaoDongping on 3/24/16.
 */
public interface ApiCallback<T> {

    void onSuccess(T data, boolean fromCache);

    void onFailure(Exception e);

}
