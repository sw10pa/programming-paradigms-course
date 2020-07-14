package PPC.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Homepage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login").forward(req, resp);
    }

}
