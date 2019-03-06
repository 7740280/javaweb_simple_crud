package com.like.dao;

import com.like.bean.Product;
import com.like.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao
{
    public List<Product> findAll() throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "select * from product";
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
    }
}
