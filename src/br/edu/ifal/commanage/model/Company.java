package br.edu.ifal.commanage.model;

import java.sql.SQLException;

import br.edu.ifal.commanage.dao.EmployeeDAO;
import br.edu.ifal.commanage.dao.ProductDAO;
import br.edu.ifal.commanage.dao.PurchaseDAO;
import br.edu.ifal.commanage.dao.SaleDAO;
import br.edu.ifal.commanage.util.exception.StockException;

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
	// private ArrayList<Sale> sales = new ArrayList<>();
	private SaleDAO saleDAO = new SaleDAO();
	
	private Stock stock = new Stock();
	
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
	
	public void removeEmployee (int employeeId) throws SQLException {
		// return employees.remove(employee);
		employeeDAO.delete(employeeId);
	}
	
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
	
	public void removeProduct (int productId) throws SQLException {
		// return products.remove(product);
		productDAO.delete(productId);
	}
	
	/* public int getPurchaseQuantity () {
		return purchases.size();
	} */
	
	public void addPurchase (Purchase purchase) throws SQLException {
		// purchases.add(purchase);
		purchaseDAO.create(purchase);
		stock.updateStockForAddedProccess(purchase);
	}
	
	public void removePurchase (Purchase purchase) throws SQLException {
		// return purchases.remove(purchase);
		purchaseDAO.delete(purchase.getId());
		stock.updateStockForRemovedProcess(purchase);
	}
	
	/* public int getSaleQuantity (){
		return sales.size();
	} */

	public void addSale (Sale sale) throws StockException, SQLException {
		// sales.add(sale);
		try {
			stock.updateStockForAddedProccess(sale);
			saleDAO.create(sale);
		} catch (Exception e) {
			throw new StockException(e.getMessage());
		}
	}
	
	public void removeSale (Sale sale) throws SQLException {
		// return sales.remove(sale);
		saleDAO.delete(sale.getId());
		stock.updateStockForRemovedProcess(sale);
	}
}