package com.web.web.service.base;

import com.web.core.dao.base.HibernateDAO;
import com.web.web.exception.GxwebException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

@Service
public abstract class BaseService<T> {

	protected HibernateDAO<T> hibernateDAO;

	public BaseService()
	{
		sethibernateDAO();
	}

	@PostConstruct
	protected abstract void sethibernateDAO();

	protected void throwException(String message){
		throw new GxwebException(message);
	}


	public  List<T> getList(){
		return hibernateDAO.findAll();
	}

	public T get(Serializable id){
		return hibernateDAO.getById(id);
	}
}
