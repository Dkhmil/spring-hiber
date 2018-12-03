package com.khmil.spring_booot.dao;

import com.khmil.spring_booot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryNameAndProductList_price(String name, double price);

    @Query(value = "from Category c where c.name =: name")
    Optional<Category> getAllBySomeCriteria(@Param("name") String name);

}
