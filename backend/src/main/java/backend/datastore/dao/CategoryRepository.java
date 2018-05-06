package backend.datastore.dao;

import backend.datastore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {

    Category getCategoriesByName(String name);
}
