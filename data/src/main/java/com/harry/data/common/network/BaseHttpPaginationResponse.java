package com.harry.data.common.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 16-1-25.
 */
public class BaseHttpPaginationResponse extends BaseHttpResponse {
    @Expose
    @SerializedName("_links")
    private PaginationLinkEntity links;

    @Expose
    @SerializedName("_meta")
    private PaginationMetaEntity meta;

    public PaginationLinkEntity getLinks() {
        return links;
    }

    public void setLinks(PaginationLinkEntity links) {
        this.links = links;
    }

    public PaginationMetaEntity getMeta() {
        return meta;
    }

    public void setMeta(PaginationMetaEntity meta) {
        this.meta = meta;
    }
}
