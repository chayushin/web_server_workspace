package com.sh.web.jsp;

import com.sh.web.item.domain.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet("/jspTest.do")
public class JspTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> names = Arrays.asList("홍길동", "신사임당", "이순신", "강감찬");
        List<Item> items = Arrays.asList(
                new Item(123, "삼성키보드", 20_000),
                new Item(435, "한성키보드", 35_000),
                new Item(888, "레오폴드키보드", 25_000)
        );
        Map<String, Object> map = Map.of(
                "name", "홍길동",
                "age", 33,
                "married", true
        );

        request.setAttribute("names", names);
        request.setAttribute("items", items);
        request.setAttribute("map", map);

        request.setAttribute("no1", 123.456);
        request.setAttribute("no2", 3_000_000);
        request.setAttribute("no3", .15);
        request.setAttribute("date", Date.valueOf("2023-07-24")); // java.sql.Date
        request.setAttribute("datetime", new java.util.Date());

        request.setAttribute("names", names);
        request.setAttribute("items", items);
        request.setAttribute("map", map);

        request.getRequestDispatcher("/WEB-INF/views/jspTest.jsp")
                .forward(request, response);
    }

}