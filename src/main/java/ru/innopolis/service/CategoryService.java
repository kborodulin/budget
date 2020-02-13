package ru.innopolis.service;

import ru.innopolis.domain.Category;

import java.util.List;

public interface CategoryService {
    void save(Category category);

    Category findById(Long id);

    void delete(Category category);

    List<Category> findAll();

    List<Category> findAllSort(Long categoryid);
}