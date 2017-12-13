package org.javacream.hello.web.store;

import java.io.Serializable;

public class StoreBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String categorie; 
	private String item;
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}


}
