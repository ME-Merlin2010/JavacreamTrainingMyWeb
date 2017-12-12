package org.javacream.books.warehouse.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
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

	@Autowired private StoreService storeService;
	@Autowired
	DataSource dataSource;

	@Autowired private JdbcTemplate jdbcTemplate;
	@Test
	public void testSpring() {
		TestActor.doTest(booksService);
	}

	@Test
	public void testDataSource() throws Exception {
		Connection con = dataSource.getConnection();
		Assert.assertNotNull(con);
		con.close();
	}

	@Test
	public void testJdbcTemplate() {
		Assert.assertNotNull(jdbcTemplate);
		String result = jdbcTemplate.execute(new StatementCallback<String>() {
			@Override
			public String doInStatement(Statement statement) throws SQLException, DataAccessException {
				statement.execute("drop table messages if exists");
				statement.execute("create table messages (message varchar(256))");
				statement.execute("insert into messages (message) values ('Hello')");
				return "OK";
			}
		});
		Assert.assertEquals("OK", result);
	}
	
	@Test public void testStoreService() {
		Assert.assertEquals(42, storeService.getStock("books", "ISBN1"));
		Assert.assertEquals(200, storeService.getStock("books", "ISBN3"));
		Assert.assertEquals(0, storeService.getStock("irgend", "was"));
	}
}
