package com.cg.fds.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.fds.entities.Login;

public class ICustomLoginRepositoryImpl implements ICustomLoginRepository{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public Login signIn(Login login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login signOut(Login login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login findUserByName(Login login) {
		// TODO Auto-generated method stub
		
		Session session = entityManager.unwrap(Session.class);
		// select * from AccountTable where cityName = 'dffd';
		String queryString = "from Login log where log.username=:login.username and log.password=:login.password";
		
		Query<Login> query = session.createQuery(queryString);
		query.setString("username", login.getUserName());
		query.setString("password", login.getPassword());
		
		
		Login  list = (Login) query.getSingleResult();
		
		// code to fetch data from DB using JPQL
		
		if(list != null)
		{
			return list;
		}
		else
		{
			throw new javax.persistence.NoResultException("User Records Not In The DB");
		}
	}

}
