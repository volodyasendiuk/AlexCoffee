package ua.com.alexcoffee.service.impl;

import ua.com.alexcoffee.dao.StatusDAO;
import ua.com.alexcoffee.entity.Status;
import ua.com.alexcoffee.enums.StatusEnum;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.DuplicateException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatusServiceImpl extends ItemServiceImpl<Status> implements StatusService {

    @Autowired
    private StatusDAO dao;

    @Override
    @Transactional
    public void add(StatusEnum title) throws WrongInformationException, DuplicateException {
        if (title == null) {
            throw new WrongInformationException("No status title!");
        }
        if (dao.get(title) != null) {
            throw new DuplicateException("Duplicate status with title  " + title + "!");
        }
        dao.add(new Status(title));
    }

    @Override
    @Transactional(readOnly = true)
    public Status get(StatusEnum title) throws WrongInformationException, BadRequestException {
        if (title == null) {
            throw new WrongInformationException("No status title!");
        }
        Status status = dao.get(title);
        if (status == null) {
            throw new BadRequestException("Can't find status by title " + title + "!");
        }
        return status;
    }

    @Override
    @Transactional(readOnly = true)
    public Status getDefault() throws BadRequestException {
        Status status = dao.getDefault();
        if (status == null) {
            throw new BadRequestException("Can't find default status!");
        }
        return status;
    }

    @Override
    @Transactional
    public void remove(StatusEnum title) throws WrongInformationException {
        if (title == null) {
            throw new WrongInformationException("No status title!");
        }
        dao.remove(title);
    }

    @Override
    public StatusDAO getDao() {
        return dao;
    }
}
