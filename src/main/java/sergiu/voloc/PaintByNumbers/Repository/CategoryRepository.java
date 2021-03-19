package sergiu.voloc.PaintByNumbers.Repository;

import sergiu.voloc.PaintByNumbers.Model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {

    public Category getCategoryBySlug(String s);
}