package com.weshopifyplatform.app.service;

import java.util.List;

import com.weshopifyplatform.app.beans.CategoriesBean;

public interface CategoriesService {

	CategoriesBean saveCategory(CategoriesBean categoriesBean); // The it is taking is w/o id and the one return is with id
	CategoriesBean updateCategory(CategoriesBean categoriesBean); //It has bean with id coz that's how we will update it.
	List<CategoriesBean> deleteCategory(int categoryId); // after deleting we are getting the rest of the categories
	CategoriesBean getCategory(int categoryId);
	List<CategoriesBean> getAllCategories();
	List<CategoriesBean> getAllCategoriesOfAParent(int parentCatId);

	
}
