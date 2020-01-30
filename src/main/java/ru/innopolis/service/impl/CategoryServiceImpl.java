package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Category;
import ru.innopolis.repository.CategoryRepository;
import ru.innopolis.service.CategoryService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void save(Category category) {
        log.info("saved Category to DB {}", category);
        categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        log.info("find category by ID {}", id);
        return categoryRepository.findById(id).get();
    }

    @Override
    public void delete(Category category) {
        log.info("delete category from DB {}", category);
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> findAll() {
        log.info("find all category");
        return categoryRepository.findAll();
    }
}