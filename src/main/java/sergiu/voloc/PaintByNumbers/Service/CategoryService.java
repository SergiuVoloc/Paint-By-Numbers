package sergiu.voloc.PaintByNumbers.Service;

import sergiu.voloc.PaintByNumbers.Model.Category;
import sergiu.voloc.PaintByNumbers.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> all(){
        return categoryRepository.findAll();
    }

    public Category read(@PathVariable(value = "id") UUID id){
        return categoryRepository.findById(id).orElseThrow();
    }

    public Category create(@RequestBody Category o){
        Category n = new Category();
        n.setName(o.getName());
        categoryRepository.save(n);
        return n;
    }
    public Category update(@PathVariable(value = "id") UUID id, @RequestBody Category o){
        Category c = categoryRepository.findById(id).orElseThrow();
        c.setName(o.getName());
        categoryRepository.save(c);
        return c;
    }

    public void delete(@PathVariable(value = "id") UUID id){
        categoryRepository.deleteById(id);
    }

}
