package com.sh.mvc.member.controller;

import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {

    private MemberService  memberService = new MemberService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 인코딩 처리
        // 2. 사용자 입력값처리
        HttpSession session = req.getSession();
        Member loginMember = (Member)req.getSession().getAttribute("loginMember");
        String id = loginMember.getId();
        System.out.println(id);
        // 3. 업무로직
        int result = memberService.deleteMember(id);
        // 세션해제
        session.invalidate();

        // 새션 새로 생성. msg 속성 저장
        session = req.getSession();
        session.setAttribute("msg", "성공적으로 탈퇴했습니다. \n 다음에 더 좋은 서비스로 만나요");

        // 4. redirect
        resp.sendRedirect(req.getContextPath() + "/");
    }
}