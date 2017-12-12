package org.javacream.books.warehouse.test;

import java.sql.Connection;

import javax.sql.DataSource;

import org.javacream.books.warehouse.api.BooksService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto training@rainer-sawitzki.de
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class BooksServiceTest {

	@Autowired
	private BooksService booksService;

	@Autowired DataSource dataSource;
	@Test
	
	public void testSpring() {
		TestActor.doTest(booksService);
	}

	@Test public void testDataSource() throws Exception{
		Connection con = dataSource.getConnection();
		Assert.assertNotNull(con);
		con.close();
	}
	

}
