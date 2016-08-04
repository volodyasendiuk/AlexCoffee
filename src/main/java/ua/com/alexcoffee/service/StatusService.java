package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.enums.StatusEnum;

public interface StatusService extends ItemService<Status> {

    void add(StatusEnum title);

    Status get(StatusEnum title);

    Status getDefault();

    void remove(StatusEnum title);

}
