package backend.converters;

import backend.datastore.entities.Category;
import backend.dto.CategoryInfo;

public class CategoryConverter {


    public static CategoryInfo toTransactionInfo(Category t) {
        return CategoryInfo.builder()
                .id(t.getId())
                .name(t.getName())
                .build();
    }

    public static Category toCategory(CategoryInfo categoryInfo) {
        return Category.builder()
                .id(categoryInfo.getId())
                .name(categoryInfo.getName())
                .build();
    }
}
