package org.javacream.books.warehouse.bible.impl;

import javax.annotation.PostConstruct;

import org.javacream.books.warehose.bible.BibleWebservice;
import org.javacream.books.warehose.bible.BibleWebserviceSoap;
import org.springframework.stereotype.Service;

@Service
public class BibleWebServiceFacade {

	private BibleWebserviceSoap delegate;

	@PostConstruct public void init() {
		BibleWebservice bibleWebserviceFactory = new BibleWebservice();
		delegate = bibleWebserviceFactory.getBibleWebserviceSoap();

	}
	
	public String getBibleWordsByBookTitleAndChapter(String arg0, int arg1) {
		return delegate.getBibleWordsByBookTitleAndChapter(arg0, arg1);
	}

	public String getBibleWordsByChapterAndVerse(String arg0, int arg1, int arg2) {
		return delegate.getBibleWordsByChapterAndVerse(arg0, arg1, arg2);
	}

	public String getBibleWordsbyKeyWord(String arg0) {
		return delegate.getBibleWordsbyKeyWord(arg0);
	}

	public String getBookTitles() {
		return delegate.getBookTitles();
	}
}
