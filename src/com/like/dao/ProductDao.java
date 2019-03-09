package com.like.dao;

import com.like.bean.Product;
import com.like.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao
{
    public List<Product> findAll() throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "select * from product";
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
    }

    public int add(Product product) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "insert into product values(?,?,?,?,?,?,?)";

        return queryRunner.update(sql, product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(), "", product.getPdate(), product.getPdesc());
    }

    public Product update(String id) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "select * from product where pid = ?";
        return queryRunner.query(sql, new BeanHandler<Product>(Product.class), id);
    }

    public int edit(Product p) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "update product set pname = ? , market_price = ? , shop_price = ? , pdate = ? , pdesc = ?  where pid = ?";

        return queryRunner.update(sql, p.getPname(), p.getMarket_price(), p.getShop_price(), p.getPdate(), p.getPdesc(), p.getPid());
    }

    public int del(String id) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "delete from product where pid = ?";
        return queryRunner.update(sql, id);
    }

    public int checkDel(String[] ids) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        for (String id : ids) {
            String sql = "delete from product where pid = ?";
            queryRunner.update(sql, id);
        }

        return 1;
    }


    public List<Product> search(String pname, String pid) throws SQLException
    {
        QueryRunner qr  = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql = "select * from product where 1 = 1";

        List<String> params = new ArrayList<>();

        if (!"".equals(pname)) {
            sql += " and pname like ?";
            params.add("%" + pname + "%");
        }

        if (!"".equals(pid)) {
            sql += " and pid like ? ";
            params.add("%" + pid + "%");
        }

        return qr.query(sql, new BeanListHandler<Product>(Product.class), params.toArray());
    }
}
