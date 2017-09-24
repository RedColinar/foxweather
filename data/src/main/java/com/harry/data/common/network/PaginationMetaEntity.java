package com.harry.data.common.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaginationMetaEntity {
    @Expose
    @SerializedName("totalCount")
    private Integer totalCount;

    @Expose
    @SerializedName("pageCount")
    private Integer pageCount;

    @Expose
    @SerializedName("currentPage")
    private Integer currentPage;

    @Expose
    @SerializedName("perPage")
    private Integer perPage;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
