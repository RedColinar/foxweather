package com.harry.data.common.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaginationLinkEntity {
    @Expose
    @SerializedName("self")
    PaginationHrefEntity self;

    @Expose
    @SerializedName("next")
    PaginationHrefEntity next;

    @Expose
    @SerializedName("last")
    PaginationHrefEntity last;

    public PaginationHrefEntity getLast() {
        return last;
    }

    public void setLast(PaginationHrefEntity last) {
        this.last = last;
    }

    public PaginationHrefEntity getNext() {
        return next;
    }

    public void setNext(PaginationHrefEntity next) {
        this.next = next;
    }

    public PaginationHrefEntity getSelf() {
        return self;
    }

    public void setSelf(PaginationHrefEntity self) {
        this.self = self;
    }
}
