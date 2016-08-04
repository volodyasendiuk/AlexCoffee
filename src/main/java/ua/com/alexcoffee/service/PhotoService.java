package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.Photo;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService extends ItemService<Photo> {

    Photo get(String title);

    void remove(String title);

    void saveFile(MultipartFile photo);

    void deleteFile(String url);
}
