package com.Gruge.MyBackend.service;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface BasicService<T> {
    public abstract CollectionModel<EntityModel<T>> getAll();

    public abstract EntityModel<T> getOne(int id);

    public abstract EntityModel<T> add(T object);

}
