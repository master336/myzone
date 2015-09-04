package com.web.core.dao.base;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-3-16
 * Time: 下午6:11
 * To change this template use File | Settings | File Templates.
 */
public class HibernatePage<T> implements Serializable
{
    private static final long serialVersionUID = 1913490847094219152L;
    private List<T> elements;
    /*****---start---****/
    private int firstPage = 1;
    private int lastPageNumber;
    private int lastPage;
    private int nextPageNumber;
    private int previousPageNumber;
    private int thisPageFirstElementNumber;
    private int thisPageLastElementNumber;
    /*****---end---****/
    private int pageSize;
    private int pageNumber;
    private long totalElements = 0;

    public HibernatePage(String resultHql, Session session, boolean cacheable, Pagination pagination, Object... values)
    {
        this(resultHql, session, cacheable, pagination.getPageNumber(), pagination.getPageSize(), values);
    }
    /****
     * 传统的HQL方式写法BUG太多 ，新加一套SQL直接查询的分页方法
     * create by zhangdong 2015-07-23
     *多传递一个 type 只用于区分构造方法 暂无其他意义
     */
    public HibernatePage(int type,String resultSql, Session session, boolean cacheable, Pagination pagination, Object... values)
    {
        this.pageNumber = pagination.getPageNumber();
        this.pageSize = pagination.getPageSize();
        this.nextPageNumber = (pageNumber+1);
        this.previousPageNumber = (pageNumber<2)?1:pageNumber-1;
        Query resultQuery = session.createSQLQuery(resultSql);
       try
        {
            String countHql = "select count(*) from ( " +resultSql+") as _dual " ;
            Query countQuery = session.createSQLQuery(countHql);

            if (cacheable)
            {
                countQuery.setCacheable(true);
                resultQuery.setCacheable(true);
            }

            for (int i = 0; i < values.length; i++)
            {
                if(values[i]!=null){
                    countQuery.setParameter(i, values[i]);
                    resultQuery.setParameter(i, values[i]);
                }
            }
            try {
                this.totalElements = ((BigInteger) countQuery.uniqueResult()).longValue();
            }catch(Exception e){

            }
            this.lastPageNumber = getLastPageNumber();

            this.lastPage = getLastPageNumber();
            if (Integer.MAX_VALUE == this.pageNumber || this.pageNumber > getLastPageNumber())   //last page
            {
                this.pageNumber = getLastPageNumber();
                this.nextPageNumber = this.pageNumber;
            }

            if (this.pageNumber < 1)
            {
                this.pageNumber = 1;
            }
            if(this.nextPageNumber>this.totalElements){
                this.nextPageNumber = (int)this.totalElements;
            }
            if(this.previousPageNumber>this.totalElements){
                this.previousPageNumber = (int)this.totalElements;
            }
            elements = resultQuery.setFirstResult((this.pageNumber - 1) * this.pageSize).setMaxResults(this.pageSize).list();
        }
        catch (HibernateException e)
        {
            throw new RuntimeException(e);
        }
    }
    public HibernatePage(String resultHql, Session session, boolean cacheable, int pageNumber, int pageSize, Object... values)
    {
        Query resultQuery = session.createQuery(resultHql);
        this.pageNumber = pageNumber;
        this.nextPageNumber = ++pageNumber;
        this.pageSize = pageSize;
        this.previousPageNumber = (pageNumber-1<1)?1:pageNumber-1;

        try
        {
            String countHql = "select count(*) " + removeSelect(removeOrders(resultQuery.getQueryString()));
            Query countQuery = session.createQuery(countHql);

            if (cacheable)
            {
                countQuery.setCacheable(true);
                resultQuery.setCacheable(true);
            }

            for (int i = 0; i < values.length; i++)
            {
                countQuery.setParameter(i, values[i]);
                resultQuery.setParameter(i, values[i]);
            }

            this.totalElements = (Long) countQuery.uniqueResult();
            this.lastPageNumber = getLastPageNumber();

            this.lastPage = getLastPageNumber();
            if (Integer.MAX_VALUE == this.pageNumber || this.pageNumber > getLastPageNumber())   //last page
            {
                this.pageNumber = getLastPageNumber();
                this.nextPageNumber = pageNumber;
            }

            if (this.pageNumber < 1)
            {
                this.pageNumber = 1;
            }
            elements = resultQuery.setFirstResult((this.pageNumber - 1) * this.pageSize).setMaxResults(this.pageSize).list();
        }
        catch (HibernateException e)
        {
            throw new RuntimeException(e);
        }
    }

    public HibernatePage(Query resultQuery, Session session, boolean cacheable, int pageNumber, int pageSize, Object... values)
    {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;

        try
        {
            String countHql = "select count(*) " + removeSelect(removeOrders(resultQuery.getQueryString()));
            Query countQuery = session.createQuery(countHql);

            if (cacheable)
            {
                countQuery.setCacheable(true);
                resultQuery.setCacheable(true);
            }

            for (int i = 0; i < values.length; i++)
            {
                countQuery.setParameter(i, values[i]);
                resultQuery.setParameter(i, values[i]);
            }

            this.totalElements = (Long) countQuery.uniqueResult();

            if (Integer.MAX_VALUE == this.pageNumber || this.pageNumber > getLastPageNumber())   //last page
            {
                this.pageNumber = getLastPageNumber();
            }

            if (this.pageNumber < 1)
            {
                this.pageNumber = 1;
            }

            elements = resultQuery.setFirstResult((this.pageNumber - 1) * this.pageSize).setMaxResults(this.pageSize).list();
        }
        catch (HibernateException e)
        {
            throw new RuntimeException(e);
        }
    }

    public HibernatePage(Query resultQuery, Query countQuery,Session session, boolean cacheable, int pageNumber, int pageSize, Object... values)
    {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;

        try
        {
            //String countHql = "select count(*) " + removeSelect(removeOrders(resultQuery.getQueryString()));
            //Query countQuery = session.createQuery(countHql);
            if (cacheable)
            {
                countQuery.setCacheable(true);
                resultQuery.setCacheable(true);
            }

            for (int i = 0; i < values.length; i++)
            {
                countQuery.setParameter(i, values[i]);
                resultQuery.setParameter(i, values[i]);
            }

            this.totalElements = (Long) countQuery.uniqueResult();

            if (Integer.MAX_VALUE == this.pageNumber || this.pageNumber > getLastPageNumber())   //last page
            {
                this.pageNumber = getLastPageNumber();
            }

            if (this.pageNumber < 1)
            {
                this.pageNumber = 1;
            }

            elements = resultQuery.setFirstResult((this.pageNumber - 1) * this.pageSize).setMaxResults(this.pageSize).list();
        }
        catch (HibernateException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 构建HibernatePage对象，完成Hibernate的Query数据的分页处理
     *
     * @param resultQuery Hibernate的Query对象
     * @param total       统计总数
     * @param pageNumber  当前页编码，从1开始，如果传的值为Integer.MAX_VALUE表示获取最后一页。
     *                    如果你不知道最后一页编码，传Integer.MAX_VALUE即可。如果当前页超过总页数，也表示最后一页。
     *                    这两种情况将重新更改当前页的页码，为最后一页编码。
     * @param pageSize    每一页显示的条目数
     */
    public HibernatePage(Query resultQuery, long total, int pageNumber, int pageSize)
    {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;

        try
        {
            this.totalElements = total;

            if (Integer.MAX_VALUE == this.pageNumber || this.pageNumber > getLastPageNumber())   //last page
            {
                this.pageNumber = getLastPageNumber();
            }

            if (this.pageNumber < 1)
            {
                this.pageNumber = 1;
            }

            elements = resultQuery.setFirstResult((this.pageNumber - 1) * this.pageSize).setMaxResults(this.pageSize).list();
        }
        catch (HibernateException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 构建HibernatePage对象，完成Hibernate的分页处理
     *
     * @param elements   当前页面要显示的数据
     * @param total      所有页面数据总数
     * @param pageNumber 当前页编码，从1开始，如果传的值为Integer.MAX_VALUE表示获取最后一页。
     *                   如果你不知道最后一页编码，传Integer.MAX_VALUE即可。如果当前页超过总页数，也表示最后一页。
     *                   这两种情况将重新更改当前页的页码，为最后一页编码。
     * @param pageSize   每一页显示的条目数
     */
    public HibernatePage(List elements, long total, int pageNumber, int pageSize)
    {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = total;

        if (Integer.MAX_VALUE == this.pageNumber || this.pageNumber > getLastPageNumber())   //last page
        {
            this.pageNumber = getLastPageNumber();
        }

        if (this.pageNumber < 1)
        {
            this.pageNumber = 1;
        }

        this.elements = elements;
    }

    public boolean isFirstPage()
    {
        return getPageNumber() == 1;
    }

    public boolean isLastPage()
    {
        return getPageNumber() >= getLastPageNumber();
    }

    public boolean hasNextPage()
    {
        return getLastPageNumber() > getPageNumber();
    }

    public boolean hasPreviousPage()
    {
        return getPageNumber() > 1;
    }

    public int getLastPageNumber()
    {
        if(totalElements==0){
            return  0;
        }
        return (int)(totalElements % this.pageSize == 0 ? totalElements / this.pageSize : totalElements / this.pageSize + 1);
    }

    /**
     * 返回List类型数据
     *
     * @return List数据源
     */
    public List<T> getElements()
    {
        return elements;
    }
    /**
     * 重新封装结果
     *
     * @return List数据源
     */
    public List<T> setElements(List<T> elements){
        return this.elements = elements;
    }

    public long getTotalElements()
    {
        return totalElements;
    }

    public int getThisPageFirstElementNumber()
    {
        return (getPageNumber() - 1) * getPageSize() + 1;
    }

    public long getThisPageLastElementNumber()
    {
        int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
        return getTotalElements() < fullPage ? getTotalElements() : fullPage;
    }

    public int getNextPageNumber()
    {
        return getPageNumber() + 1;
    }

    public int getPreviousPageNumber()
    {
        return getPageNumber() - 1;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public int getPageNumber()
    {
        return pageNumber;
    }

    /*
     * 去除select 子句，未考虑union的情况
     *
     */
    private static String removeSelect(String hql)
    {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
        return hql.substring(beginPos);
    }

    /*
     * 去除orderby 子句
     */
    private static String removeOrders(String hql)
    {
        Assert.hasText(hql);
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();

        while (m.find())
        {
            m.appendReplacement(sb, "");
        }

        m.appendTail(sb);
        return sb.toString();
    }

}
