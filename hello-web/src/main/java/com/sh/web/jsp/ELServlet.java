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
import java.util.List;
import java.util.Map;

@WebServlet("/el.do")
public class ELServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "홍길동";
        int num = 123;
        List<String> hobbies = Arrays.asList("독서", "넷플릭스", "게임", "맛집투어");
        Map<String, Integer> bookMap = Map.of("MyJava", 15_000, "정신이 나가기전", 23_000, "Dr.zang's office", 30_000);

        // context객체 대입해야 EL에서 사용이 가능하다.
        req.setAttribute("name", name);
        req.setAttribute("num", num);
        req.setAttribute("hobbies", hobbies);
        req.setAttribute("bookMap", bookMap);

        HttpSession session = req.getSession();
        session.setAttribute("name", "신사임당");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/02_el.jsp");
        requestDispatcher.forward(req, resp);
    }
}
