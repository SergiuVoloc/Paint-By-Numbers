package com.ecommerce.ecommerce.modules.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> all(){
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Category read(@PathVariable(value = "id") UUID id){
        return categoryRepository.findById(id).orElseThrow();
    }

    public Category create(String name){
        Category n = new Category(name);
        categoryRepository.save(n);
        return n;
    }
    public Category update(@PathVariable(value = "id") UUID id, Category o){
        Category c = categoryRepository.findById(id).orElseThrow();
        c.setName(o.getName());
        categoryRepository.save(c);
        return c;
    }

    public void delete(@PathVariable(value = "id") UUID id){
        categoryRepository.deleteById(id);
    }

    public Category getCategoryBySlug(String s){
        return categoryRepository.getCategoryBySlug(s);
    }
}
