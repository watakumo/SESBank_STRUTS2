package dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.Board;
import vo.Customer;

public class CustomerDAO {
	SqlSessionFactory sqlSessfac = MybatisConfig.getSqlSessionFactory();
	SqlSession sqlSess;
	ArrayList<Customer> result = null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Customer> list(String type) {
		ArrayList<Customer> result = null;
		System.out.println("dao 파라미터 출력: " + type);
		try {
			sqlSess = sqlSessfac.openSession();
			result = (ArrayList) sqlSess.selectList("Customer.selectList", type);
			sqlSess.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSess != null)
				sqlSess.close();
		}

		return result;

	}

	public boolean insertCustomer(Customer customer) {
		boolean result = false;
		try {
			sqlSess = sqlSessfac.openSession();
			int cnt = sqlSess.insert("Customer.insertCustomer", customer);
			if (cnt > 0)
				result = true;

			sqlSess.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSess != null)
				sqlSess.close();
		}
		return result;
	}

	public Customer selectCusIf(Customer customer) {
		Customer result = new Customer(); 
		try {
			System.out.println(customer+"파라미터값");
			sqlSess = sqlSessfac.openSession();
			result = sqlSess.selectOne("Customer.selectCusIf", customer);
			System.out.println("dao에서 찾은 회원 데이터 : "+result);
			sqlSess.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSess != null)
				sqlSess.close();
		}
		return result;
	}

	public boolean selectCusById(String checkedID) {
		boolean result = false;
		try {
			sqlSess = sqlSessfac.openSession();
			Customer cus = sqlSess.selectOne("Customer.idCheck", checkedID);
			sqlSess.commit();

			if(cus != null) result = true;  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSess != null)
				sqlSess.close();
		}

		return result;
	}
	
	public Customer checkLoginUser(Customer cus){
		System.out.println("CUS_DAO : checkLoginUser(회원확인) 실행 ");
		Customer result = null;
		
		try{
			sqlSess = sqlSessfac.openSession();	
			result = sqlSess.selectOne("Customer.checkLogin",cus); 
			sqlSess.commit();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sqlSess != null)sqlSess.close();
		}
		return result; 
	}

	public boolean updateCustomer(Customer customer) {
		boolean result = false; 
		try{
			sqlSess = sqlSessfac.openSession();
			int data = sqlSess.update("Customer.update", customer);
			if(data !=0){result = true;}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sqlSess != null)sqlSess.close();
		}
		
		return result;
	}
	
}