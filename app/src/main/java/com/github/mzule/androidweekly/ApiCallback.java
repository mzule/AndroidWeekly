package com.github.mzule.androidweekly;

/**
 * Created by CaoDongping on 3/24/16.
 */
public interface ApiCallback<T> {

    void onSuccess(T data);

    void onFailure(Exception e);

}
