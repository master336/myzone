package com.web.core.dao.base;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-3-16
 * Time: 下午6:12
 * To change this template use File | Settings | File Templates.
 */
public class Pagination
{
    private int pageNumber = 1;
    private int totalRows;
    private int pageSize = 15;

    public int getPageNumber()
    {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    public int getTotalRows()
    {
        return totalRows;
    }

    public void setTotalRows(int totalRows)
    {
        this.totalRows = totalRows;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

}
