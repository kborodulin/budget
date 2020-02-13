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
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
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

    @Override
    public List<Category> findAllSort(Long categoryid) {
        Query query = em.createNativeQuery(
                "select categoryid, name from (\n" +
                        "select name, categoryid,\n" +
                        "\t\tcase \n" +
                        "\t\t\twhen categoryid = ?\n" +
                        "\t\t\tthen 1 \n" +
                        "\t\t\telse row_number() over() + 1 \n" +
                        "\t\tend rn \n" +
                        "from category\n" +
                        ") c order by rn"
        );
        query.setParameter(1, categoryid);
        List<Object[]> objects = query.getResultList();
        List<Category> categories = new ArrayList<>();
        for (Object[] obj : objects) {
            Category category = new Category();
            category.setCategoryid(((BigInteger) obj[0]).longValue());
            category.setName((String) obj[1]);
            categories.add(category);
        }
        return categories;
    }
}