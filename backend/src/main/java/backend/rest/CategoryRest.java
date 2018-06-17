package backend.rest;


import backend.dto.CategoryInfo;
import backend.exception.AppException;
import backend.exception.IncorrectParamsException;
import backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/category")
public class CategoryRest {

    @Autowired
    private CategoryService service;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addCategory(@RequestBody CategoryInfo category){
        try {
            return ResponseEntity.ok(service.addCategory(category));
        } catch (IncorrectParamsException | AppException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @RequestMapping(value = "{categoryId}/{categoryName}/edit",
            method = RequestMethod.POST)
    public ResponseEntity editCategory(@PathVariable Long categoryId, @PathVariable String categoryName){
        try {
            service.editCategoryName(categoryId,categoryName);
        } catch (IncorrectParamsException e) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok().build();
    }
}
