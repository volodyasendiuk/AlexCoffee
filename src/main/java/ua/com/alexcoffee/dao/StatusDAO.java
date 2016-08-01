package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.entity.Status;
import ua.com.alexcoffee.enums.StatusEnum;

public interface StatusDAO extends Dao<Status> {

    void add(StatusEnum title);

    void remove(StatusEnum title);

    Status get(StatusEnum title);

    Status getDefault();
}
