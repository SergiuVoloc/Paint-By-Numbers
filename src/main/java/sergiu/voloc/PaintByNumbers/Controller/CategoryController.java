package sergiu.voloc.PaintByNumbers.Controller;

import sergiu.voloc.PaintByNumbers.Model.Category;
import sergiu.voloc.PaintByNumbers.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String product(Model model){
        Iterable<Category> o = categoryService.all();
        model.addAttribute("list", (ArrayList<Category>) o);
        return "/pages/homepage/index";
    }
    @GetMapping(value ="/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", categoryService.read(id));
        return "/pages/homepage/index";
    }
    @PostMapping()
    public String create(@RequestBody Category o){
        categoryService.create(o);
        return "/pages/homepage/index";
    }
    @PutMapping("/{id}")
    public String update(@PathVariable(value = "id") UUID id, @RequestBody Category o){
        categoryService.update(id, o);
        return "/pages/homepage/index";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") UUID id){
        categoryService.delete(id);
        return "/pages/homepage/index";
    }

}
