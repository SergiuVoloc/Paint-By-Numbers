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

    @GetMapping("/create")
    public  String addPage(Model model){
        return "pages/category/create";
    }

    @GetMapping()
    public String all(Model model){
        Iterable<Category> o = categoryService.all();
        model.addAttribute("list", (ArrayList<Category>) o);
        return "/pages/category/index";
    }

    @GetMapping(value ="/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", categoryService.read(id));
        return "pages/category/edit";
    }

    @PostMapping()
    public String create(@RequestParam String name){
        categoryService.create(name);
        return "redirect:/category";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", categoryService.read(id));
        return "pages/category/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable(value = "id") UUID id, @RequestParam String name){
        Category c = categoryService.read(id);
        c.setName(name);
        categoryService.update(id, c);
        return "redirect:/category";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        categoryService.delete(id);
        return "redirect:/category";
    }

}
