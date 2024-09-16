package com.weshopifyplatform.app.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="categories")//If you don't give a name it will take the class name as the table name but some db add extra string
						//So to keep it uniform accross all the dbs we can provide name in this annotations
@Data //Generate getter setter and hashcode and other stuff.
public class Categories implements Serializable{ //Serializable coz sometimes our database is in cloud so easy to transfer the data

	/**
	 * 
	 */
	private static final long serialVersionUID = -2857332108550225557L; //Use the Generated one
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String name;
	private String aliasName;
	private String imagePath;
	private boolean isEnabled;
	
	@ManyToOne
	private Categories parent;
	
}
