package ua.com.alexcoffee.dao.repository;

import ua.com.alexcoffee.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository<T extends Model, E extends Number> extends JpaRepository<T, E> {

}
