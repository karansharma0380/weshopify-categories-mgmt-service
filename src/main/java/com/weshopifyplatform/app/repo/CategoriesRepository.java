/**
 * 
 */
package com.weshopifyplatform.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weshopifyplatform.app.entities.Categories;

/**
 * Our repository is ready and will provide us a lot of functionality(combined CRUD and Paging repo)
 */							//								<EntityName, primaryKey_datatype_of_the_entity>
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

	@Query(value = "SELECT * FROM categories WHERE parent_id = :parentCatId", nativeQuery = true)
	List<Categories> findAllChildsOfAParent(@Param("parentCatId") int catId);

}
