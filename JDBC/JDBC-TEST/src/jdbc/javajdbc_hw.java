package jdbc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class javajdbc_hw  {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		Connection conn = null; //JDBC�� ������ ������ �������̽�
		
		try {
		String dburl = "jdbc:oracle:thin:@localhost:1521:orcl";
		String dbuser = "scott";
		String dbpw =  "tiger";
		
		
		PreparedStatement pstmt = null; //sql �̸� �غ�, ����� �Ű����� �޾� ����ϴ� ���
		ResultSet rs = null; // statement�� ���� ������ ����
		
		//���̺귯���� Ȱ���� Ŭ������ �ε�(�ʼ�), �����ͺ��̽� ����̹� �ε�
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("���̺귯�� �ε� ����"); //�������������� ���� �����ͺ��̽� ����
		
		conn = DriverManager.getConnection(dburl, dbuser, dbpw);
		System.out.println("���� ����");

		// insert ����
		//1.EMP ���̺� ���ο� ��� ������ �Է��ϴ� ���α׷��� �ۼ��غ���.
			
			  String sqlInsert = "insert into emp values(?,?,?,?,?,?,?,?)";
			  pstmt =conn.prepareStatement(sqlInsert);
			  pstmt.setInt(1,13);
			  pstmt.setString(2,"DALE");
			  pstmt.setString(3, "CUTE");
			  pstmt.setInt(4, 7890);
			  pstmt.setString(5, "2011/05/07");
			  pstmt.setInt(6, 3000);
			  pstmt.setInt(7,0);
			  pstmt.setInt(8, 40);
			  
			  int resultCnt = pstmt.executeUpdate(); if(resultCnt>0) {
			  System.out.println("���������� �ԷµǾ����ϴ�.");
			  System.out.println("--------------------------------"); }
			 
					
					Statement stmt = conn.createStatement();
					 String sql = "select * from emp order by empno";
					 rs=stmt.executeQuery(sql);
		
/*---------------------------------------------------------------------------*/
		

		
		
		//2.EMP ���̺��� ��� �����͸� ����ϴ� ���α׷��� �ۼ��غ���.
		System.out.println("���ӵ� ��� ����");
		
		while(rs.next()) {
			System.out.println("�����ȣ : "+rs.getInt(1)+"\t"
								+"����̸� : "+rs.getString(2)+"\t"
								+"���� : " +rs.getString(3)+"\t"
								+"�Ŵ��� : "+rs.getInt(4)+"\t"
								+"��볯¥ : "+rs.getString(5)+"\t"
								+"�޿� : "+rs.getInt(6)+"\t"
								+"Ŀ�̼� : "+rs.getInt(7)+"\t"
								+"�μ���ȣ : "+rs.getInt(8)+"\t");
		}
		
/*---------------------------------------------------------------------------*/
		
		//3. EMP ���̺� �� ��SCOTT�� ����� �޿�(sal) ������ 1000���� �ٲٴ� ���α׷��� �ۼ��غ���.
		String sqlUpdate = "update emp set sal=1000 where ename='SCOTT'";
		stmt.executeUpdate(sqlUpdate);
		
/*---------------------------------------------------------------------------*/
		
		//4.EMP ���̺� �� ��SCOTT�� �̸����� �˻��� ����� ����ϴ� ���α׷��� �ۼ��غ���.
		System.out.println("----------------------------------------------");
		//�˻��� ��� ã��
		String sqlPrint = "select * from emp where ename='SCOTT'";
		rs=stmt.executeQuery(sqlPrint);  // statement�� ���� ������ ����
		
		//���
		
		while(rs.next()) {
			System.out.println("�����ȣ : "+rs.getInt(1)+"\t"
								+"����̸� : "+rs.getString(2)+"\t"
								+"���� : " +rs.getString(3)+"\t"
								+"�Ŵ��� : "+rs.getInt(4)+"\t"
								+"��볯¥ : "+rs.getString(5)+"\t"
								+"�޿� : "+rs.getInt(6)+"\t"
								+"Ŀ�̼� : "+rs.getInt(7)+"\t"
								+"�μ���ȣ : "+rs.getInt(8)+"\t");
		}
		
/*---------------------------------------------------------------------------*/	
		//5.��� ��������� ����ϵ� �μ������� �Բ� ����ϴ� ���α׷��� �ۼ��غ���.
		
			
			 String sqlAllprint = "select * from emp e join dept d on e.deptno = d.deptno";
			 rs=stmt.executeQuery(sqlAllprint);
			 
			 while(rs.next()) {
					System.out.println("�����ȣ : "+rs.getInt(1)+"\t"
										+"����̸� : "+rs.getString(2)+"\t"
										+"���� : " +rs.getString(3)+"\t"
										+"�Ŵ��� : "+rs.getInt(4)+"\t"
										+"��볯¥ : "+rs.getString(5)+"\t"
										+"�޿� : "+rs.getInt(6)+"\t"
										+"Ŀ�̼� : "+rs.getInt(7)+"\t"
										+"�μ���ȣ : "+rs.getInt("deptno")+"\t"
										+"�μ��� : "+rs.getString("dname")+"\t"
										+"������ :"+rs.getString("loc")+"\t");
				}
		
		rs.close();
		stmt.close();
		pstmt.close();
		conn.close();
	} catch(ClassNotFoundException e) {
		e.printStackTrace();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
}
}

