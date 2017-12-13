package org.javacream.books.warehouse.test;

import org.javacream.books.warehose.bible.BibleWebservice;
import org.javacream.books.warehose.bible.BibleWebserviceSoap;
import org.junit.BeforeClass;
import org.junit.Test;

public class BibleServiceTest {
	
	@BeforeClass public static void initTest() {
		System.setProperty("http.proxyHost", "dsvbm");
		System.setProperty("http.proxyPort", "8080");
	}
	@Test public void testBibleService() {
		BibleWebservice bibleWebserviceFactory = new BibleWebservice();
		BibleWebserviceSoap bibleWebService = bibleWebserviceFactory.getBibleWebserviceSoap();
		System.out.println(bibleWebService.getBookTitles());
	}
}
