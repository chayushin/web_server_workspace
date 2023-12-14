package com.sh.mvc.member.controller;

import com.sh.mvc.member.model.entity.Gender;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.entity.Role;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
    private MemberService memberService = new MemberService();



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // 1. 인코딩처리
//        req.setCharacterEncoding("utf-8");

        // 2. 사용자입력값 가져오기
        // id, password, name, birthday, email, phone, gender, hobby
//        String id = req.getParameter("id");
        Member loginMember = (Member)req.getSession().getAttribute("loginMember");
        String id = loginMember.getId();
        String name = req.getParameter("name");
        String _birthday = req.getParameter("birthday");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String _gender = req.getParameter("gender");
        String[] _hobby = req.getParameterValues("hobby");
        System.out.println(id + ", " + name + ", " + _birthday + ", " + email + ", " + phone + ", " + _gender + ", " + _hobby);


        // input:date는 text계열이라 작성하지 않아도 ""이 전송
        LocalDate birthday = _birthday != null && !"".equals(_birthday)?
                LocalDate.parse(_birthday, DateTimeFormatter.ISO_DATE) :
                null;
        Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
        List<String> hobby = _hobby != null ? Arrays.asList(_hobby) : null;


        // 3. 업무로직
        Member member = memberService.findById(id);
        System.out.println(member);
        member.setName(name);
        member.setBirthday(birthday);
        member.setEmail(email);
        member.setPhone(phone);
        member.setGender(gender);
        member.setHobby(hobby);


        int result = memberService.updateMember(member);
        // db정보가 성공적으로 수정되었다명, 해당내용으로 session속성 loginMember업데이트
        Member memberUpdated = memberService.findById(id);
        req.getSession().setAttribute("loginMember", memberUpdated);
//      4. redirect : /mvc/member/memberDetail
        resp.sendRedirect(req.getContextPath() + "/member/memberDetail");
    }
}