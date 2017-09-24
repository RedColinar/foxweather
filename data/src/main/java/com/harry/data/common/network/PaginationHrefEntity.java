package com.harry.data.common.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 16-1-25.
 */
public class PaginationHrefEntity {
    @Expose
    @SerializedName("href")
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
