package com.like.service;

import com.like.bean.Product;
import com.like.dao.ProductDao;

import java.sql.SQLException;
import java.util.List;

public class ProductService
{
    public List<Product> findAll() throws SQLException
    {
        ProductDao productDao = new ProductDao();
        return productDao.findAll();
    }
}
