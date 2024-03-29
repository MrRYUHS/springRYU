package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class) //ac를 자동으로 만들어줌
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //xml 경로 지정해주기
public class DBConnectionTest2Test {
    @Autowired
    DataSource ds;

    @Test
    public void insertUserTest() throws Exception{
        User user = new User("hering","1234","abc","aaa@aaa.com",new Date(),"fb",new Date());
        deleteAll();
        int rowCnt=insertUser(user);

        assertTrue(rowCnt==1);
    }

    private void deleteAll() throws Exception{
        Connection conn = ds.getConnection(); //데이터소스로부터 데이터베이스 연결을 가져온 뒤

        String sql="delete from user_info "; //sql문 작성

        PreparedStatement pstmt = conn.prepareStatement(sql); //?에 해당하는 것들을 넣기
        pstmt.executeUpdate(); //sql문 실행 - insert,delete,update
    }

    @Test
    public void selectUserTest() throws Exception{
        deleteAll();
        User user = new User("hering","1234","abc","aaa@aaa.com",new Date(),"fb",new Date());
        int rowCnt=insertUser(user);
        User user2 = selectUser("hering");

        assertTrue(user.getId().equals("hering"));
    }
    @Test
    public void deleteUserTest() throws Exception{
        deleteAll();
        int rowCnt = deleteUser("hering");

        assertTrue(rowCnt==0);
        User user = new User("hering","1234","abc","aaa@aaa.com",new Date(),"fb",new Date());
        rowCnt = insertUser(user);
        assertTrue(rowCnt==1);

        rowCnt = deleteUser(user.getId());
        assertTrue(rowCnt==1);

        assertTrue(selectUser(user.getId())==null);
    }

    // 매개변수로 받은 사용자 정보로 user_info 테이블을 update 하는 메서드
//    public int updateUser(User user)throws Exception{
//        Connection conn = ds.getConnection();
//        String sql = "update user_info set pwd='5678' name='gudtn' where name='abc'";
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setString(1, user.getPwd());
//        pstmt.setString(2, user.getName());
//        return 0;
//    }

    public int deleteUser(String id)throws Exception{
        Connection conn = ds.getConnection();

        String sql = "delete from user_info where id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql); //?에 해당하는 것들을 넣기
        pstmt.setString(1, id);
//        int rowCnt = pstmt.executeUpdate(); //sql문 실행 - insert,delete,update
//        return rowCnt;
        return pstmt.executeUpdate(); //sql문 실행 - insert,delete,update
    }

    public User selectUser(String id) throws Exception{
        Connection conn = ds.getConnection(); //데이터소스로부터 데이터베이스 연결을 가져온 뒤

        String sql="select * from user_info where id=?"; //sql문 작성

        PreparedStatement pstmt = conn.prepareStatement(sql); //?에 해당하는 것들을 넣기
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){ //rs에 값이 있으면
            User user = new User(); //rs로부터 값을 받아다 User 객체를 채운 뒤 반환하면 된다
            user.setId(rs.getString(1)); //첫 번째 컬럼 값 가져와서 저장
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new Date(rs.getDate(5).getTime()));
            user.setSns((rs.getString(6)));
            user.setReg_date(new Date(rs.getTimestamp(7).getTime()));

            return user;
        }
        return null;
    }
    @Test
    public void transactionTest()throws Exception{
        Connection conn=null;
        try {
            deleteAll();
            conn = ds.getConnection(); //데이터소스로부터 데이터베이스 연결을 가져온 뒤
            conn.setAutoCommit(false); //conn.setAutoCommit(true);

//       insert into user_info (id, pwd, name, email, birth, sns, reg_date)
//       values ('hyerin','1234','hyerin','khr5830@naver.com','1997-06-23','instagram',now());

            String sql="insert into user_info values (?,?,?,?,?,?,now()) "; //sql문 작성

            PreparedStatement pstmt = conn.prepareStatement(sql); //?에 해당하는 것들을 넣기
            pstmt.setString(1, "asdf"); //sql injection 공격, 성능 향상
            pstmt.setString(2, "1234");
            pstmt.setString(3, "abc");
            pstmt.setString(4, "aaa@aaa.com");
            pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
            pstmt.setString(6, "fb");

            int rowCnt = pstmt.executeUpdate(); //sql문 실행 - insert,delete,update

            pstmt.setString(1, "asdf2");
            rowCnt = pstmt.executeUpdate();

            conn.commit();

        } catch (Exception e) {
            conn.rollback();
            throw new RuntimeException(e);
        } finally {
        }
    }


    //사용자 정보를 user_info테이블에 저장하는 메서드
    public int insertUser(User user) throws Exception{

        Connection conn = ds.getConnection(); //데이터소스로부터 데이터베이스 연결을 가져온 뒤

//       insert into user_info (id, pwd, name, email, birth, sns, reg_date)
//       values ('hyerin','1234','hyerin','khr5830@naver.com','1997-06-23','instagram',now());

        String sql="insert into user_info values (?,?,?,?,?,?,now()) "; //sql문 작성

        PreparedStatement pstmt = conn.prepareStatement(sql); //?에 해당하는 것들을 넣기
        pstmt.setString(1, user.getId()); //sql injection 공격, 성능 향상
        pstmt.setString(2, user.getPwd());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6, user.getSns());

        int rowCnt = pstmt.executeUpdate(); //sql문 실행 - insert,delete,update

        return rowCnt;
    }
    @Test
    public void springJdcConnectionTest() throws Exception{
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn!=null); // 괄호 안의 조건식이 true면, 테스트 성공, 아니면 실패
    }
}