package com.sh.web.jsp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/jstl.do")
public class JstlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "hong gildong";
        int num = (int)(Math.random() * 100) + 1; // 1 ~ 100 사이의 난수
        List<String> hobbies = Arrays.asList("독서", "넷플릭스", "게임", "맛집투어");

        // context객체 대입해야 EL에서 사용이 가능하다.
        req.setAttribute("name", name);
        req.setAttribute("num", num);
        req.setAttribute("hobbies", hobbies);

        req.setAttribute("no1", 12345);
        req.setAttribute("no2", 123.45);
        req.setAttribute("today", new Date()); // java.util.Date 사용

        HttpSession session = req.getSession();
        session.setAttribute("name", "신사임당");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/03_jstl.jsp");
        requestDispatcher.forward(req, resp);
    }
}
