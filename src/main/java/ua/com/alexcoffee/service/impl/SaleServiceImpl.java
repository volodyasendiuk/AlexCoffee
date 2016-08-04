package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.alexcoffee.dao.SaleDAO;
import ua.com.alexcoffee.model.Sale;
import ua.com.alexcoffee.service.SaleService;

public class SaleServiceImpl extends ItemServiceImpl<Sale> implements SaleService {
    @Autowired
    private SaleDAO saleDAO;

    @Override
    public SaleDAO getDao() {
        return saleDAO;
    }
}
