package com.web.web.controller.base;

import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.web.web.vo.Res;

public class JSONOutputController {

	//JSON输出
	protected String json() {
		return json(null);
	}
	//JSON输出
	protected String json(Object obj) {
		return json(true,obj);
	}
	//JSON输出
	protected String json(boolean success, Object obj) {
		//返回JSON 过滤Hibernate关系中为null的HibernateProxy与PersistenceCollection 避免Exception
		return JSON.toJSONString(new Res(success ? Res.success : Res.fail, obj),new PropertyPreFilter() {
			public boolean apply(JSONSerializer arg0, Object source, String name) {
				if(source==null){
					return false;
				}
				if(source instanceof HibernateProxy&&((HibernateProxy) source).getHibernateLazyInitializer().isUninitialized()){
					return false;
				}else if (source instanceof PersistentCollection) {//实体关联集合一对多等
					PersistentCollection collection = (PersistentCollection) source;
					if (!collection.wasInitialized()) {
						return false;
					}
					Object val = collection.getValue();
					if (val == null) {
						return false;
					}
				}
				return true;
			}
			//禁止JSON 输出中循环引用检测$ref
		});//,SerializerFeature.DisableCircularReferenceDetect);
	}
}
