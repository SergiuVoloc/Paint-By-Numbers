package sergiu.voloc.PaintByNumbers.Service;

import org.springframework.web.bind.annotation.PathVariable;
import sergiu.voloc.PaintByNumbers.Model.Product;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    Iterable<Product> all();

    Product read(UUID id);

    Product create(String name, Float price, String description);

    Product update(UUID id, String name, Float price, String description);

    void delete(@PathVariable(value = "id") UUID id);

    // this method implements Search field by product name
    Iterable<Product> searchByName(String productName);

    // Predictive Search
    List<String> search(String keyword);
}
