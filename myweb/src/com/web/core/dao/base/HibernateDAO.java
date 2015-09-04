package com.web.core.dao.base;

import com.web.web.vo.Pages;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.transform.ResultTransformer;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: Edmund Date: 15-3-16 Time: 下午6:07 To change
 * this template use File | Settings | File Templates.
 */
@SuppressWarnings({"unchecked"})
public abstract class HibernateDAO<T> extends HibernateDaoSupport {
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * 返回此Dao所管理的Entity类型
     */
    protected Class<T> poClass;

    public HibernateDAO() {
        poClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    protected Session getSession() {
        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            // 异步事务提交容易产生session关闭的情况 这里做一个检查
            if (!session.isOpen()) {
                session =  getHibernateTemplate().getSessionFactory().openSession();
            }
            session.setFlushMode(FlushMode.AUTO);
            return session;
        } catch (Exception e) {
            return  getHibernateTemplate().getSessionFactory().openSession();
        }
    }
    public T getById(Serializable id) {
        return (T) getHibernateTemplate().get(poClass, id);
    }

    public void delete(T po) {
        getHibernateTemplate().delete(po);
    }

    public void deleteById(Serializable id) {
        Object o = getById(id);

        if (o != null) {
            getHibernateTemplate().delete(o);
        }
    }
    public List<T> findAll() {
        // noinspection unchecked
        return (List<T>) getHibernateTemplate().find("from " + poClass.getName() + " obj");
    }

    public void save(T po) {
        getHibernateTemplate().merge(po);
    }

    public T saveReturn(T po) {
        return getHibernateTemplate().merge(po);
    }

    public void create(T po) {
        getHibernateTemplate().save(po);
    }

    public void update(T po) {
        getHibernateTemplate().merge(po);
    }

    public HibernatePage<T> doQuery(String hql, boolean cacheable, int curPage, int pageSize, Object... values) {
        return new HibernatePage<T>(hql, getSessionFactory().getCurrentSession(), cacheable, curPage, pageSize, values);
    }

    public HibernatePage<T> doQuery(String hql, boolean cacheable, Pagination pagination, Object... values) {
        return new HibernatePage<T>(hql, getSessionFactory().getCurrentSession(), cacheable, pagination, values);
    }

    /****
     * 传统的HQL方式写法BUG太多 ，新加一套SQL直接查询的分页方法 create by zhangdong 2015-07-23
     */
    public HibernatePage<T> doSQLQuery(String sql, boolean cacheable, Pagination pagination, Object... values) {
        return new HibernatePage<T>(1, sql, getSessionFactory().getCurrentSession(), cacheable, pagination, values);
    }

    public T doQueryUnique(final String hql, final Object... values) {
        return (T) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                query.setCacheable(true);

                for (int i = 0; i < values.length; i++) {
                    query.setParameter(i, values[i]);
                }

                return query.uniqueResult();
            }
        });

    }

    public T doQueryFirst(final String hql, final Object... values) {
        return (T) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                query.setCacheable(true);

                for (int i = 0; i < values.length; i++) {
                    query.setParameter(i, values[i]);
                }

                List list = query.list();

                if (list.size() > 0) {
                    return (T) list.get(0);
                } else {
                    return null;
                }
            }
        });
    }

    public void executeHsql(final String hql, final Object... values) {
        this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);

                for (int i = 0; i < values.length; i++) {
                    query.setParameter(i, values[i]);
                }
                query.executeUpdate();
                return null;
            }
        });
    }

    public List<T> doQueryList(final String hql, final boolean cacheable, final Object... values) {
        return (List<T>) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                query.setCacheable(cacheable);

                for (int i = 0; i < values.length; i++) {
                    query.setParameter(i, values[i]);
                }

                return query.list();
            }
        });
    }

    public String getFromHql() {
        return "from " + poClass;
    }

    public long doQueryCount(final String hql, final Object... values) {
        return (Long) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);

                for (int i = 0; i < values.length; i++) {
                    query.setParameter(i, values[i]);
                }

                Long count = (Long) query.uniqueResult();

                if (count == null) {
                    return 0;
                } else {
                    return count;
                }
            }
        });
    }

    public List<T> doQueryLimitList(final String hql,
                                    final boolean cacheable,
                                    final int dataNum,
                                    final Object... values) {
        return (List<T>) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                query.setCacheable(cacheable);

                for (int i = 0; i < values.length; i++) {
                    query.setParameter(i, values[i]);
                }

                query.setFirstResult(0);
                query.setMaxResults(dataNum);
                return query.list();
            }
        });
    }

    /**
     * sql封装map
     * 
     * @param sql
     * @return
     */
    public HashMap<String, String> getMapBySql(final String sql) {
        return (HashMap<String, String>) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                HashMap<String, String> r = new HashMap<String, String>();
                List list = session.createSQLQuery(sql).list();
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        Object[] os = (Object[]) list.get(i);
                        r.put(String.valueOf(os[0]), String.valueOf(os[1]));
                    }
                }
                return r;
            }
        });

        /*
         * try {
         * 
         * HashMap<String,String> hashMap =
         * (HashMap)this.getHibernateTemplate().createSQLQuery(
         * "select role_Id, role_Desc  from Sys_Roles"
         * ).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
         * 
         * return hashMap; } catch (RuntimeException re) {
         * 
         * throw re; }
         */
    }

    /**
     * 分页
     * 
     * @param hql
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     */
    public List doQueryListByHql(final String hql, final int pageNo, final int pageSize, final Object... values) {
        return (List) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                query.setFirstResult(pageNo);
                query.setMaxResults(pageSize);
                if (null != values) {
                    for (int i = 0; i < values.length; i++) {
                        query.setParameter(i, values[i]);
                    }
                }
                return query.list();
            }
        });
    }

    public <TK> List<TK> doQueryRecoredBySql(final Class<TK> resultClz, String sql, Object... params) {
        SQLQuery sqlQuery = this.currentSession().createSQLQuery(sql);
        sqlQuery.setReadOnly(true);
        sqlQuery.setResultTransformer(new ResultTransformer() {

            private static final long serialVersionUID = 1L;

            @Override
            public Object transformTuple(Object[] values, String[] columns) {

                Map<String, Object> map = new HashMap<String, Object>(1);
                int i = 0;
                for (String column : columns) {
                    map.put(column, values[i++]);
                }
                String voString = JSON.toJSONString(map);

                return JSON.parseObject(voString, resultClz);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public List transformList(List list) {
                return list;
            }
        });

        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                sqlQuery.setParameter(i, params[i]);
            }
        }

        return sqlQuery.list();
    }
    /**
     * 执行原生的SQL语句
     */
    public List executeSQL(String sql, Object... objects) {
        SQLQuery query = getSession().createSQLQuery(sql);
        if (null != objects && objects.length > 0) {
            for (int i = 0; i < objects.length; i++) {
                query.setParameter(i, objects[i]);
            }
        }

        return query.list();
    }

    /**
     * 执行自定义HQL语句
     */
    public List executeHQl(String hql, Object... objects) {
        Query query = getSession().createQuery(hql);
        for (int i = 0; objects != null && i < objects.length && objects[i] != null; i++) {
            query.setParameter(i, objects[i]);
        }
        return query.list();
    }

    /**
     *使用HQL 查询分页
     *
     * @param hql
     *            查询语句
     * @param objects
     *            参数
     */
    public Pages getPageByHQL(String hql, int pageNo, int pageSize,Object... objects) {
        /** 查询所有记录 */
        List allquery = executeHQl(hql, objects);
        List returnList = new ArrayList();
        /*** 获取所有记录 */
        int totalCount = 0;
        /*** 处理页容量 */
        if (pageSize < 1) {
            pageSize = 10;
        }
        /** 处理页码 */
        if (pageNo < 1) {
            pageNo = 1;
        }
        /** 处理记录总数及分页 */
        if (allquery == null || allquery.size() < 1) {
            totalCount = 0;
        } else {
            /** 处理分页数据 */
            totalCount = allquery.size();
            int totalPageCount = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
            pageNo = pageNo > totalPageCount ? totalPageCount : pageNo;
            int startCountNo = (pageNo - 1) * pageSize;
            int endCountNo = (pageNo) * pageSize - 1;
            if (endCountNo > (totalCount - 1)) {
                endCountNo = totalCount - 1;
            }
            for (int i = startCountNo; i <= endCountNo; i++) {
                returnList.add(allquery.get(i));
            }

        }
        /** 返回查询结果 */
        return new Pages(pageNo, pageSize, totalCount, returnList);
    }

}
