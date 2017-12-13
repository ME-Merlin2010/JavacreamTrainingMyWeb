package org.javacream.books.isbngenerator.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("sequence")
public class DatabaseIsbnGenerator implements IsbnGenerator{

	@PersistenceContext private EntityManager entityManager;
	
	@Value("${prefix}")private String prefix;
	@Value("${countryCode}")private String countryCode;

	@Override
	@Transactional
	public String next() {
		int actualKey = (int) entityManager.createNativeQuery("select key from isbnkeys").getSingleResult();
		actualKey++;
		Query insert = entityManager.createNativeQuery("update isbnkeys set key=:newKey");
		insert.setParameter("newKey", actualKey);
		insert.executeUpdate();
		return prefix + actualKey + countryCode;
	}
}
