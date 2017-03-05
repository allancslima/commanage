package br.edu.ifal.commanage.model;

import java.util.ArrayList;
import br.edu.ifal.commanage.dao.EmployeeDAO;
import br.edu.ifal.commanage.dao.ProductDAO;
import br.edu.ifal.commanage.dao.PurchaseDAO;

import java.sql.SQLException;

public class Company {

	private String name;
	private String cnpj;
	private String phone;
	// private ArrayList<Employee> employees = new ArrayList<>();
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	// private ArrayList<Product> products = new ArrayList<>();
	private ProductDAO productDAO = new ProductDAO();
	// private ArrayList<Purchase> purchases = new ArrayList<>();
	private PurchaseDAO purchaseDAO = new PurchaseDAO();
	private ArrayList<Sale> sales = new ArrayList<>();
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getCnpj () {
		return cnpj;
	}
	
	public void setCnpj (String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getPhone () {
		return phone;
	}
	
	public void setPhone (String phone) {
		this.phone = phone;
	}
	
	/* public int getEmployeeQuantity () { 
		return employees.size();
	} */
	
	public void addEmployee (Employee employee) throws SQLException {
		// employees.add(employee);
		employeeDAO.create(employee);
	}
	
	public void updateEmployee (Employee employee) throws SQLException {
		employeeDAO.update(employee);
	}
	
	/* public boolean removeEmployee (Employee employee) {
		return employees.remove(employee);
	} */
	
	/* public int getProductQuantity () { 
		return products.size();
	} */
	
	public void addProduct (Product product) throws SQLException {
		// products.add(product);
		productDAO.create(product);
	}
	
	public void updateProduct(Product product) throws SQLException {
		productDAO.update(product);
	}
	
	/* public boolean removeProduct (Product product) {
		return products.remove(product);
	} */
	
	/* public int getPurchaseQuantity () {
		return purchases.size();
	} */
	
	public void addPurchase (Purchase purchase) throws SQLException {
		// purchases.add(purchase);
		purchaseDAO.create(purchase);
	}
	
	/* public boolean removePurchase (Purchase purchase) {
		return purchases.remove(purchase);
	} */
	
	public int getSaleQuantity (){
		return sales.size();
	}

	public void addSale (Sale sale) {
		sales.add(sale);
	}
	
	public boolean removeSale (Sale sale) {
		return sales.remove(sale);
	}
}