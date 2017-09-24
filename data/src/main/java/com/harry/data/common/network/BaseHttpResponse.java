package com.harry.data.common.network;

import com.google.gson.Gson;

/**
 * Base http response.
 */
public abstract class BaseHttpResponse {

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
