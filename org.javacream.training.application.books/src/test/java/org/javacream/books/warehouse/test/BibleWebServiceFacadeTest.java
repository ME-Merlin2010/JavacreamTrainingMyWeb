package org.javacream.books.warehouse.test;

import org.javacream.books.warehouse.bible.impl.BibleWebServiceFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")

public class BibleWebServiceFacadeTest {

	@Autowired private BibleWebServiceFacade bibleWebService;
	
	@Test public void testIt() {
		System.out.println(bibleWebService.getBibleWordsbyKeyWord("hell"));
	}
}
