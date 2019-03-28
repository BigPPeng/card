package com.card.common;

import java.util.List;

/**
 * Created by hongpeng.cui on 2019/3/27.
 */
public class Page<T> {
    private int pageSize;
    private int totalCount;
    private int pageNumber = 1;
    private int totalPages;
    private List<T> result;

    public Page(int pageSize, int totalCount) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPages = (int)Math.ceil((double)totalCount / (double)pageSize);
    }

    public boolean hasNextPage() {
        return this.pageNumber < this.totalPages;
    }

    public boolean hasPreviousPage() {
        return this.pageNumber > 1;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public int startNumber() {
        return this.pageSize * (this.pageNumber - 1);
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.totalPages = (int)Math.ceil((double)this.totalCount / (double)pageSize);
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public List<T> getResult() {
        return this.result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
