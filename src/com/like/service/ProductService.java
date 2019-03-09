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

    public int add(Product product) throws SQLException
    {
        ProductDao productDao = new ProductDao();
        return productDao.add(product);
    }

    public Product update(String id) throws SQLException
    {
        ProductDao productDao = new ProductDao();
        return productDao.update(id);
    }

    public int edit(Product product) throws SQLException
    {
        ProductDao productDao = new ProductDao();
        return productDao.edit(product);
    }

    public int del(String id) throws SQLException
    {
        ProductDao productDao = new ProductDao();
        return productDao.del(id);
    }

    public int checkDel(String[] ids) throws SQLException
    {
        ProductDao pd = new ProductDao();
        return pd.checkDel(ids);
    }

    public List<Product> search(String pname, String pid) throws SQLException
    {
        ProductDao pd = new ProductDao();
        return pd.search(pname, pid);
    }
}
