package com.weshopifyplatform.app.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weshopifyplatform.app.beans.CategoriesBean;
import com.weshopifyplatform.app.service.CategoriesService;

import lombok.extern.slf4j.Slf4j;

@RestController //This is combination of @Controller annotation and @ReponseBody(we added in the return type of out method)
@Slf4j
@RequestMapping(path="/api")
public class CategoryResource {
	
	private CategoriesService categoriesService;
	
	public CategoryResource(CategoriesService categoriesService) {
		this.categoriesService=categoriesService;
	}
	
	@PostMapping(path = {"/categories"})
	public ResponseEntity<CategoriesBean> createCategory(@RequestBody CategoriesBean categoriesBean) {
		return new ResponseEntity<CategoriesBean>(categoriesService.saveCategory(categoriesBean),HttpStatus.CREATED);
	}
	
	@PutMapping(path = {"/categories"})
	public ResponseEntity<CategoriesBean> updateCategory(@RequestBody CategoriesBean categoriesBean) {
		return new ResponseEntity<CategoriesBean>(categoriesService.updateCategory(categoriesBean),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = {"/categories/{catId}"})
	public ResponseEntity<List<CategoriesBean>> deleteCategory(@PathVariable("catId") int catId) {
		return new ResponseEntity<List<CategoriesBean>>(categoriesService.deleteCategory(catId),HttpStatus.OK);
	}
	
	@GetMapping(path = {"/categories/{catId}"})
	public ResponseEntity<CategoriesBean> getCategory(@PathVariable("catId") int catId) {
		return new ResponseEntity<CategoriesBean>(categoriesService.getCategory(catId),HttpStatus.OK);
	}
	
	@GetMapping(path = {"/categories"})
	public ResponseEntity<List<CategoriesBean>> getAllCategory() {
		return new ResponseEntity<List<CategoriesBean>>(categoriesService.getAllCategories(),HttpStatus.OK);
	}
	
	@GetMapping(path = {"/categories/childs/{parentId}"})
	public ResponseEntity<List<CategoriesBean>> getAllChildCategory(@PathVariable("parentId") int parentId) {
		return new ResponseEntity<List<CategoriesBean>>(categoriesService.getAllCategoriesOfAParent(parentId),HttpStatus.OK);
	}
}
