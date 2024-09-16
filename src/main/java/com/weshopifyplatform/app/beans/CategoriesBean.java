package com.weshopifyplatform.app.beans;

import java.io.Serializable;

import lombok.Data;
/*
 * We have write Bean before writing the service layer, 
 * Bean is exactly same as the entity and only change is that it does not have any meta data(annotations that are specific to entitiess)
 */
@Data
public class CategoriesBean implements Serializable{

	private static final long serialVersionUID = -2857332108550225557L; //Use the Generated one

	private int id;

	private String name;
	private String aliasName;
	private String imagePath;
	private boolean enabled;
	
	private int parentCatId;
	
}
