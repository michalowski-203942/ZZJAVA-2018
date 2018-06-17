package backend.service;

import backend.converters.CategoryConverter;
import backend.datastore.dao.CategoryRepository;
import backend.datastore.dao.TransactionRepository;
import backend.datastore.dao.UserRepository;
import backend.datastore.entities.Category;
import backend.dto.CategoryInfo;
import backend.exception.AppException;
import backend.exception.IncorrectParamsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
public class CategoryService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;


    public long addCategory(CategoryInfo categoryInfo) throws IncorrectParamsException, AppException {
        Category category = CategoryConverter.toCategory(categoryInfo);
        try {
            categoryRepository.saveAndFlush(category);
            return category.getId();
        } catch (ConstraintViolationException e) {
            throw new IncorrectParamsException("Incorrect transaction data", e);
        }
    }


    public void editCategoryName(Long transactionId, String name) throws  IncorrectParamsException {
        Category catgory = categoryRepository.getOne(transactionId);
        catgory.setName(name);

        try {
            categoryRepository.saveAndFlush(catgory);

        } catch (ConstraintViolationException e) {
            throw new IncorrectParamsException("Incorrect transaction data", e);
        }
    }
}
