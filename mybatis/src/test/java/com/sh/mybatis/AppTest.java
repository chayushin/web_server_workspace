package com.sh.mybatis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * JUnit 테스트 구성요소
 * 1. fixture method : 매테스트 전후작업
 *  - fixture 테스트대상 또는 테스트에 필요한 객체
 *  - 모든 테스트는 독립적으로 진행(매번 새로운 테스트객체를 생성후 진행)
 *  - @BeforeAll, @AfterAll (static)
 *  - @BeforeEach, @AfterEach
 *
 * 2. 단정문 (assertion)
 *  - 코드실행결과는 이것이다~ 단정
 *  - assertThat(...)
 *  - assertNotNull(...)
 *  - ...
 * 3. TestRunner : 테스트주체
 *
 */
public class AppTest {
    App app;

    // fixture메소드
    @BeforeAll
    public static void beforeAll(){
        System.out.println("beforeAll");
    }
    @AfterAll
    public static void afterAll(){
        System.out.println("afterAll");
    }
    @BeforeEach
    public void beforeEach(){
        System.out.println("beforeEach");
        this.app = new App();
    }
    @AfterEach
    public void afterEach(){
        System.out.println("afterEach");
    }



    @DisplayName("App#sum메소드 - 두수의 합을 반환하는지 테스트")
    @Test
    public void test1() {
        System.out.println("(test1)");
        int result = app.sum(10, 20);
        assertThat(result).isEqualTo(30);

        result = app.sum(30, 5);
        assertThat(result).isEqualTo(35);
    }

    @DisplayName("App#random는 1 ~ 100사이의 정수를 반환하는지 테스트")
    @Test
    public void test2() {
        System.out.println("(test2)");
        int n = app.random();
        System.out.println(n);
        assertThat(n)
                .isGreaterThanOrEqualTo(1)
                .isLessThanOrEqualTo(100);
    }

}