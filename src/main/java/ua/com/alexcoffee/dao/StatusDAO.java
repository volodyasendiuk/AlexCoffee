package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.enums.StatusEnum;

public interface StatusDAO extends DAO<Status> {

    void add(StatusEnum title);

    void remove(StatusEnum title);

    Status get(StatusEnum title);

    Status getDefault();
}
