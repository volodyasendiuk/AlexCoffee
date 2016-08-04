package ua.com.alexcoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.alexcoffee.dao.SaleDAO;
import ua.com.alexcoffee.dao.repository.SaleRepository;
import ua.com.alexcoffee.model.Sale;

@Repository
public class SaleDAOImpl extends DAOAbstractImpl<Sale> implements SaleDAO {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public SaleRepository getRepository() {
        return saleRepository;
    }
}
