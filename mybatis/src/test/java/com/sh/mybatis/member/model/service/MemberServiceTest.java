package com.sh.mybatis.member.model.service;

import com.sh.mybatis.member.model.entity.Gender;
import com.sh.mybatis.member.model.entity.Member;
import com.sh.mybatis.member.model.entity.Role;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        this.memberService = new MemberService();
    }

    @DisplayName("MemberService 객체는 null이 아니다.")
    @Test
    public void test1() {
        assertThat(memberService).isNotNull();
    }

    /**
     * mybatis는 ResultSet의 데이터를 vo클래스객체로 자동변환한다.
     * - varchar2/char <-> String
     * - number <-> int/double
     * - date <-> java.util.Date(기본값), java.time.LocalDate
     * - number(0|1) <-> boolean
     * - varchar2/char <-> Enum
     */
    @DisplayName("존재하는 회원이 정삭적으로 조회된다.")
    @Test
    public void test2() {
        Member member = memberService.findById("abcde");
        System.out.println(member);

        assertThat(member).isNotNull();

        assertThat(member.getId()).isNotNull();
        assertThat(member.getPassword()).isNotNull();
        assertThat(member.getName()).isNotNull();
        assertThat(member.getRole()).isNotNull();
    }

    @DisplayName("존재하는않는  회원이 NULL이 반환되어야 한다.")
    @Test
    public void test3() {
        Member member = memberService.findById("asdasdasdwqdwqdsafasdacascsacsacasc");
        assertThat(member).isNull();
    }

    @DisplayName("회원 전체 조회")
    @Test
    public void test4() {
        List<Member> members = memberService.findAll();
        assertThat(members)
                .isNotNull()
                .isNotEmpty();
        // Consumer타입 람다식 : 매개변수가 하나 있고, 리턴타입은 없음.
        members.forEach((member) -> {
            System.out.println(member);
            assertThat(member.getId()).isNotNull();
            assertThat(member.getPassword()).isNotNull();
            assertThat(member.getName()).isNotNull();
            assertThat(member.getRole()).isNotNull();
        });

    }

    @DisplayName("회원 이름 검색")
    @Test
    public void test5() {
        String keyword = "무개";
        List<Member> members = memberService.findByName(keyword);
        assertThat(members)
                .isNotNull()
                .isNotEmpty();

        members.forEach((member) -> assertThat(member.getName()).contains(keyword));
    }

    @DisplayName("성별 검색")
    @Test
    public void test6() {
        String gender = "M";
        List<Member> members = memberService.findByGender(gender);
        assertThat(members).isNotNull().isNotEmpty();
        members.forEach((member) -> {
            // Gender enum 객체의 실제값: name()
            assertThat(member.getGender().name()).isEqualTo(gender); // String
            assertThat(member.getGender()).isEqualTo(Gender.valueOf(gender));  // Enum 타입비교 String -> enum
        });
    }

    @Disabled
    @DisplayName("회원가입")
    @Test
    public void test7() {
        String id = "honggd";
        String password = "1234";
        String name = "홍길동";
        Member member =
                new Member(
                        id, password, name, Role.U, Gender.M, LocalDate.of(1999, 9, 9),
                        "honggd@naver.com", "01012341234", Arrays.asList("게임", "독서"), 0, null);
        int result = memberService.insertMember(member);
        assertThat(result).isEqualTo(1);

        Member member2 = memberService.findById(id);
        assertThat(member2).isNotNull();
        assertThat(member2.getId()).isEqualTo(id);
        assertThat(member2.getPassword()).isEqualTo(password);
        assertThat(member2.getName()).isEqualTo(name);

    }

    @DisplayName("회원가입시 오류 체크")
    @Test
    public void test8() {

        Member member =
                new Member(
                        "sinsa", null, "홍길동", Role.U, Gender.M, LocalDate.of(1999, 9, 9),
                        "honggd@naver.com", "01012341234", Arrays.asList("게임", "독서"), 0, null);

        Throwable th = catchThrowable(() -> {
            int result = memberService.insertMember(member);
        });
        assertThat(th).isInstanceOf(Exception.class);
    }

    @Disabled
    @DisplayName("회원정보 수정")
    @Test
    public void test9() {
        // given 주어진 상황 작성
        String id = "honggd";
        Member member = memberService.findById("honggd");

        // when 업무로직 작성
        String newName = member.getName() + "길동";
        Gender newGender = null;
        LocalDate newBirthday = LocalDate.of(2000, 1, 1);
        String newEamil = "honggd@gamil.com";
        String newPhone = "01011112222";

        member.setName(newName);
        member.setGender(newGender);
        member.setBirthday(newBirthday);
        member.setEmail(newEamil);
        member.setPhone(newPhone);

        int result = memberService.updateMember(member);
        assertThat(result).isGreaterThan(0);

        // then 검증코드
        Member member2 = memberService.findById(id);
        assertThat(member2.getName()).isEqualTo(newName);
        assertThat(member2.getGender()).isEqualTo(newGender);
        assertThat(member2.getBirthday()).isEqualTo(newBirthday);
        assertThat(member2.getEmail()).isEqualTo(newEamil);
        assertThat(member2.getPhone()).isEqualTo(newPhone);

    }

    @Disabled
    @DisplayName("회원 비밀번호 수정")
    @Test
    public void test10() {
        String id = "honggd";
        Member member = memberService.findById(id);
        String newPassword = "5678";
        member.setPassword(newPassword);

        int result = memberService.updateMemberPassword(member);
        assertThat(result).isGreaterThan(0);

        Member member2 = memberService.findById(id);
        assertThat(member2.getPassword()).isEqualTo(newPassword);
    }

    @Disabled
    @DisplayName("회원 권한 수정")
    @Test
    public void test11() {
        String id = "honggd";
        Member member = memberService.findById(id);
        Role newRole = Role.A;

        member.setRole(newRole);
        int result = memberService.updateMemberRole(member);
        assertThat(result).isGreaterThan(0);

        Member member2 = memberService.findById(id);
        assertThat(member2.getRole()).isEqualTo(newRole);
//        assertThat(member2.getRole().name()).isEqualTo(newRole.name()); // String으로 비교시
    }

    @DisplayName("회원 삭제")
    @Test
    public void test12() {
        String id = "honggd";
        Member member = memberService.findById(id);
        assertThat(member).isNotNull();

        int result = memberService.deleteMember(id);
        assertThat(result).isGreaterThan(0);

        Member member2 = memberService.findById(id);
        assertThat(member2).isNull();

    }
}