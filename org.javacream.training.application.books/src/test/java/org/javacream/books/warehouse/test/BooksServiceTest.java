package org.javacream.books.warehouse.test;

import org.javacream.books.warehouse.api.BooksService;
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

	@Test
	
	public void testSpring() {
		TestActor.doTest(booksService);
	}
//	public void testBusinessObjects() {
//		MapBooksService mapBooksService = new MapBooksService();
//		RandomIsbnGenerator randomIsbnGenerator = new RandomIsbnGenerator();
//		randomIsbnGenerator.setCountryCode("-de");
//		mapBooksService.setIsbnGenerator(randomIsbnGenerator);
//		mapBooksService.setStoreService(new SimpleStoreService());
//		randomIsbnGenerator.setPrefix("TEST:");
//		
//		TestActor.doTest(mapBooksService);
//		
//	
//	}

	

}
