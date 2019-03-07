package com.like.servlet;

import com.like.bean.Product;
import com.like.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String method = request.getParameter("method");
        System.out.println(method);
        if ("findAll".equals(method)) {
            findAll(request, response);
        } else if ("addPro".equals(method)) {
            System.out.println();
            addPro(request, response);
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
}
