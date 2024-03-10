package com.lontsi.gestiondestock.interceptor;

import java.io.Serializable;

import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;

public class Intercepteur implements Interceptor,Serializable  {

	private static final long serialVersionUID = 165093654741233703L;

	@Override
	public boolean onLoad(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types)
			throws CallbackException {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------");
		return Interceptor.super.onLoad(entity, id, state, propertyNames, types);
	}
	
	@Override
	public void onDelete(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types)
			throws CallbackException {
		// TODO Auto-generated method stub
		System.out.println("---------------111111111--------------");
		Interceptor.super.onDelete(entity, id, state, propertyNames, types);
	}
	
}
