package com.harry.data.common.network;

import com.google.gson.Gson;

/**
 * Base http request.
 */
public abstract class BaseHttpRequest {
    // region toString()

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // endregion
}
