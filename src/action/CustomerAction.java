package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.CustomerDAO;
import vo.Customer;

public class CustomerAction extends ActionSupport implements SessionAware {

	CustomerDAO dao = new CustomerDAO();
	Customer customer = new Customer();

	// 가입시 입력 값
	private String custid;
	private String pw;
	private String name;
	private String mail;
	private String rd;
	private String identify;
	private String addr;

	// 아이디 체크
	private String checkedID;
	private boolean checkResult;

	// 정보수정 세션사용
	private Map<String, Object> session = new HashMap<String, Object>();

	// 접속자목록
	private ArrayList<Customer> cusList;

	public String execute() {
		System.out.println("실행");
		return SUCCESS;
	}

	public String insertCustomer() {
		System.out.println("insertCustomer 실행");
		this.customer.setCustid(custid);
		boolean result = dao.insertCustomer(customer);
		System.out.println("등록성공여부 : " + result);
		return SUCCESS;
	}

	public String selectCusIf() throws Exception {
		System.out.println("selectCusIf 실행");
		System.out.println("왜 안들어있는거야?" + custid);
		System.out.println("왜 안들어있는거야?" + customer);
		this.customer = dao.selectCusIf(customer);
		System.out.println(customer);

		return SUCCESS;
	}

	public String idCheck() throws Exception {
		System.out.println("idCheck 실행");
		checkResult = dao.selectCusById(checkedID);
		return SUCCESS;
	}

	public String selectCusList() throws Exception {
		System.out.println("selectCusList 실행");
		cusList = dao.list("private");
		System.out.println("리스트(액션) : " + cusList);
		return SUCCESS;
	}

	public String update() throws Exception {

		System.out.println("update 실행");
		if (!session.get("loginId").equals("")) {
			System.out.println("update 비지니스 로직 실행");
			// this.customer = new Customer(custid, pw, name, mail, rd,
			// identify, addr);
			this.customer.setCustid(custid);
			// 찾기(찾아서 객체가져오기)
			Customer chk = dao.selectCusIf(customer);

			if (chk != null) {
				// 갱신
				this.customer = new Customer(custid, pw, name, mail, rd, identify, addr);
				System.out.println("새로 넣을 객체데이터" + customer);
				boolean result = dao.updateCustomer(customer);
				System.out.println("갱신성공여부 : " + result);
			}
		}
		return SUCCESS;
	}

	// get&set

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String login() throws Exception {

		return LOGIN;
	}

	public CustomerDAO getDao() {
		return dao;
	}

	public void setDao(CustomerDAO dao) {
		this.dao = dao;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRd() {
		return rd;
	}

	public void setRd(String rd) {
		this.rd = rd;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCheckedID() {
		return checkedID;
	}

	public void setCheckedID(String checkedID) {
		this.checkedID = checkedID;
	}

	public boolean isCheckResult() {
		return checkResult;
	}

	public void setCheckResult(boolean checkResult) {
		this.checkResult = checkResult;
	}

	public ArrayList<Customer> getCusList() {
		return cusList;
	}

	public void setCusList(ArrayList<Customer> cusList) {
		this.cusList = cusList;
	}

}
