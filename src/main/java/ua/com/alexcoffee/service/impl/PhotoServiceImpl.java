package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.com.alexcoffee.dao.PhotoDAO;
import ua.com.alexcoffee.entity.Photo;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.PhotoService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PhotoServiceImpl extends ItemServiceImpl<Photo> implements PhotoService {

    private static String path = "c:/Server/apache-tomcat-8.0.33/webapps/ROOT/resources/img/";

    @Autowired
    private PhotoDAO dao;

    @Override
    @Transactional(readOnly = true)
    public Photo get(String title) throws WrongInformationException, BadRequestException {
        if (title.isEmpty()) {
            throw new WrongInformationException("No photo title!");
        }
        Photo photo = dao.get(title);
        if (photo == null) {
            throw new BadRequestException("Can't find photo by title " + title + "!");
        }
        return photo;
    }
    @Override
    @Transactional
    public void remove(String title) throws WrongInformationException {
        if (title.isEmpty()) {
            throw new WrongInformationException("No photo title!");
        }
        dao.delete(title);
    }


    @Override
    @Transactional
    public void saveFile(MultipartFile photo) {
        if (photo != null && !photo.isEmpty()) {
            try (OutputStream stream = new FileOutputStream(path + photo.getOriginalFilename())) {
                stream.write(photo.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    public void deleteFile(String url) {
        File file = new File(path + url);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    @Override
    public PhotoDAO getDao() {
        return dao;
    }
}
