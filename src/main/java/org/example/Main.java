package org.example;

import DAO.UserDAO;
import VO.UserVO;

import java.sql.SQLException;

// Shift을(를) 두 번 눌러 전체 검색 대화상자를 열고 'show whitespaces'를 입력한 다음,
// Enter를 누르세요. 그러면 코드 내에서 공백 문자를 확인할 수 있습니다.
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 캐럿을 강조 표시된 텍스트에 놓고 Ctrl+1을(를) 누르면
        // IntelliJ IDEA의 수정 제안을 볼 수 있습니다.
        UserDAO dao = new UserDAO();
        UserVO vo = new UserVO();
        vo.setId("Test");
        vo.setName("테스트용");
        vo.setPassword("1234");
//        dao.add(vo);
        vo = dao.get("singnanda");
        System.out.println("#######################################");
        System.out.println("vo = " + vo.toString());
        //테스트하나둘
    }
}