import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/menuOrder.do")
public class MenuOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 요청메세지에 대한 인코딩 처리
        req.setCharacterEncoding("utf-8");

        // 사용자 입력값(문자열) 확인
        String mainMenu = req.getParameter("mainMenu");
        String sideMenu = req.getParameter("sideMenu");
        String drinkMenu = req.getParameter("drinkMenu");

        // 업무로직

        int mainMenuPrice = switch(mainMenu){
            case "한우버거" -> 5000;
            case "밥버거" -> 4500;
            case "치즈버거" -> 4000;
            default -> 0;
        };
        int sideMenuPrice = switch(sideMenu){
            case "감자튀김" -> 1500;
            case "어니언링" -> 1700;
            default -> 0;
        };
        int drinkMenuPrice = switch(drinkMenu){
            case "콜라" -> 1000;
            case "사이다" -> 1000;
            case "커피" -> 1500;
            case "밀크쉐이크" -> 2500;
            default -> 0;
        };

        // jsp전달하기 - request객체에 속성으로 저장
        int price = mainMenuPrice + sideMenuPrice + drinkMenuPrice;
        req.setAttribute("price", price);
        // jsp 포워딩 (RequestDispatcher)
        // 파일경로 (절대경로) src/main/webapp(웹루트) 이하부터 작성
        RequestDispatcher requestDispatcher =
                req.getRequestDispatcher("/WEB-INF/views/menuEnd.jsp");
        requestDispatcher.forward(req, resp);
    }
}