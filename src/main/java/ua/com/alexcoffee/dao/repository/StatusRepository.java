package ua.com.alexcoffee.dao.repository;

import ua.com.alexcoffee.entity.Status;
import ua.com.alexcoffee.enums.StatusEnum;

public interface StatusRepository extends ItemRepository<Status, Long> {

    Status findByTitle(StatusEnum title);

    void deleteByTitle(StatusEnum title);
}
