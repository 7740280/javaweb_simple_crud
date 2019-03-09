package com.like.servlet;

import com.like.bean.Product;
import com.like.service.ProductService;
import com.like.utils.UUID;
import com.mysql.fabric.Response;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.DbUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");

        String method = request.getParameter("method");
        if ("findAll".equals(method)) {
            findAll(request, response);
        } else if ("addPro".equals(method)) {
            addPro(request, response);
        } else if ("add".equals(method)) {
            add(request, response);
        } else if ("udp".equals(method)) {
            update(request, response);
        } else if ("edit".equals(method)) {
            edit(request, response);
        } else if ("del".equals(method)) {
            del(request, response);
        } else if ("checkDel".equals(method)) {
            checkDel(request, response);
        } else if ("search".equals(method)) {
            search(request, response);
        }
    }

    public void findAll(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            ProductService ps       = new ProductService();
            List<Product>  products = ps.findAll();

            request.setAttribute("list", products);
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "商品查找错误");
            try {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void addPro(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            request.getRequestDispatcher("/add.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Product               product      = new Product();
            BeanUtils.populate(product, parameterMap);
            product.setPid(UUID.getUUID());
            product.setPdate(new Date().toLocaleString());
            ProductService productService = new ProductService();

            int count = productService.add(product);
            if (count != 0) {
                request.getRequestDispatcher("/product?method=findAll").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("msg", "添加商品失败");
            try {
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


    public void update(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            String         id             = request.getParameter("id");
            ProductService productService = new ProductService();
            Product        product        = productService.update(id);


            request.setAttribute("product", product);

            request.getRequestDispatcher("/update.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", "商品不存在");
            try {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Product               product      = new Product();
            BeanUtils.populate(product, parameterMap);

            ProductService productService = new ProductService();
            productService.edit(product);

            request.getRequestDispatcher("/product?method=findAll").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "更新失败");
            try {
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void del(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            String         id             = request.getParameter("id");
            ProductService productService = new ProductService();
            productService.del(id);
            request.getRequestDispatcher("/product?method=findAll").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "删除失败");
            try {
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void checkDel(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            String[]       ids = request.getParameterValues("id");
            ProductService ps  = new ProductService();
            ps.checkDel(ids);
            request.getRequestDispatcher("/product?method=findAll").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "删除错误");
        }
    }

    public void search(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            String pname = request.getParameter("pname");
            String pid   = request.getParameter("pid");

            ProductService ps = new ProductService();
            List<Product>  search = ps.search(pname, pid);
            request.setAttribute("list",search);
            try {
                request.getRequestDispatcher("list.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("msg","查询错误");
            try {
                request.getRequestDispatcher("/error.jsp").forward(request,response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


    }
}
