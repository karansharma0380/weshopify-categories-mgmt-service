package com.weshopifyplatform.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.weshopifyplatform.app.beans.CategoriesBean;
import com.weshopifyplatform.app.entities.Categories;
import com.weshopifyplatform.app.repo.CategoriesRepository;


@Service //No extra capability just a denoting annotation
//After implementing this we have to unit testing
public class CategoriesServiceImpl implements CategoriesService {
	
	
	private CategoriesRepository categoriesRepostiory;
	
	private ModelMapper modelMapper;
	
	//We are using constructor injection instead of @Autowired Annotation. This will ensure that when all the dependencies are 
	// injected only then the CategoriesServiceImpl is instantiated. Makes testing easy as well. Now take help of this.
	public CategoriesServiceImpl(CategoriesRepository categoriesRepostiory, ModelMapper modelMapper) {
		this.categoriesRepostiory=categoriesRepostiory;
		this.modelMapper=modelMapper;
	}
	
	//We have CategoriesBean but the Repo will take entities, so we have to convert our beans to entities
	// We can use beanutils, Model Mapper(Verygood and nice one) etc

	@Override
	public CategoriesBean saveCategory(CategoriesBean categoriesBean) {
		Categories categoriesEntity = mapBeanToEntity(categoriesBean);
		if(categoriesBean.getParentCatId()!=0) {
			Categories parentCategory = categoriesRepostiory.findById(categoriesBean.getParentCatId()).get();
			categoriesEntity.setParent(parentCategory);
		}
		categoriesEntity = categoriesRepostiory.save(categoriesEntity);
		categoriesBean = mapEntityToBean(categoriesEntity);
		return categoriesBean;
	}

	@Override
	public CategoriesBean updateCategory(CategoriesBean categoriesBean) {
		//This is same as above difference is this input categoriesBean has id associated to it. 
		Categories categoriesEntity = mapBeanToEntity(categoriesBean);
		//So when we use this save method and the bean has id to it, then this save method will work as an update method
		//If no id then this will save the record. 
		categoriesEntity = categoriesRepostiory.save(categoriesEntity);
		categoriesBean = mapEntityToBean(categoriesEntity);
		return categoriesBean;
	}

	@Override
	public List<CategoriesBean> deleteCategory(int categoryId) {
		List<CategoriesBean> categoriesBeansList = null;
		categoriesRepostiory.deleteById(categoryId);
		List<Categories> dbCategories = categoriesRepostiory.findAll();
		if(!CollectionUtils.isEmpty(dbCategories)) {
			categoriesBeansList = dbCategories.stream()
					.map(category->mapEntityToBean(category))
					.collect(Collectors.toList());
		}
		return categoriesBeansList;
	}

	@Override
	public CategoriesBean getCategory(int categoryId) {
		return mapEntityToBean(categoriesRepostiory.findById(categoryId).get());
	}

	@Override
	public List<CategoriesBean> getAllCategories() {
		List<CategoriesBean> categoriesBeansList = null;
	
		List<Categories> dbCategories = categoriesRepostiory.findAll();
		if(!CollectionUtils.isEmpty(dbCategories)) {
			categoriesBeansList = dbCategories.stream()
					.map(category->mapEntityToBean(category))
					.collect(Collectors.toList());
		}
		return categoriesBeansList;
	}

	@Override
	public List<CategoriesBean> getAllCategoriesOfAParent(int parentCatId) {
		List<CategoriesBean> categoriesBeansList = null;
		
		List<Categories> dbCategories = categoriesRepostiory.findAllChildsOfAParent(parentCatId);
		if(!CollectionUtils.isEmpty(dbCategories)) {
			categoriesBeansList = dbCategories.stream()
					.map(category->mapEntityToBean(category))
					.collect(Collectors.toList());
		}
		return categoriesBeansList;
	}
	
	private Categories mapBeanToEntity(CategoriesBean categoriesBean) {
		Categories categoriesEntity = modelMapper.map(categoriesBean,Categories.class);
		return categoriesEntity;
	}
	
	private CategoriesBean mapEntityToBean(Categories categories) {
		CategoriesBean categoriesBean = modelMapper.map(categories, CategoriesBean.class);
		
		// Manually handle the parent category ID mapping
	    if (categories.getParent() != null) {
	        categoriesBean.setParentCatId(categories.getParent().getId());
	    }
	    
		return categoriesBean;
	}
}
