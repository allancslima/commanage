package br.edu.ifal.commanage.model;

import java.sql.SQLException;

import br.edu.ifal.commanage.dao.EmployeeDAO;
import br.edu.ifal.commanage.dao.ProductDAO;
import br.edu.ifal.commanage.dao.ProviderDAO;
import br.edu.ifal.commanage.dao.PurchaseDAO;
import br.edu.ifal.commanage.dao.SaleDAO;
import br.edu.ifal.commanage.util.exception.StockException;

public class Company {

	private String name;
	private String cnpj;
	private String phone;
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	private ProductDAO productDAO = new ProductDAO();
	private PurchaseDAO purchaseDAO = new PurchaseDAO();
	private SaleDAO saleDAO = new SaleDAO();
	private ProviderDAO providerDAO = new ProviderDAO();
	
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
	
	public void addEmployee (Employee employee) throws SQLException {
		employeeDAO.create(employee);
	}
	
	public void updateEmployee (Employee employee) throws SQLException {
		employeeDAO.update(employee);
	}
	
	public void removeEmployee (int employeeId) throws SQLException {
		employeeDAO.delete(employeeId);
	}
	
	public void addProduct (Product product) throws SQLException {
		productDAO.create(product);
	}
	
	public void updateProduct (Product product) throws SQLException {
		productDAO.update(product);
	}
	
	public void removeProduct (int productId) throws SQLException {
		productDAO.delete(productId);
		
		purchaseDAO.productDeleted(productId);
		saleDAO.productDeleted(productId);
		stock.updateStockForRemovedProduct(productId);
	}
	
	public void addPurchase (Purchase purchase) throws SQLException {
		purchaseDAO.create(purchase);
		stock.updateStockForAddedProccess(purchase);
	}
	
	public void removePurchase (Purchase purchase) throws SQLException {
		purchaseDAO.delete(purchase.getId());
		stock.updateStockForRemovedProcess(purchase);
	}

	public void addSale (Sale sale) throws StockException, SQLException {
		try {
			stock.updateStockForAddedProccess(sale);
			saleDAO.create(sale);
		} catch (Exception e) {
			throw new StockException(e.getMessage());
		}
	}
	
	public void removeSale (Sale sale) throws SQLException {
		saleDAO.delete(sale.getId());
		stock.updateStockForRemovedProcess(sale);
	}
	
	public void addProvider (Provider provider) throws SQLException {
		providerDAO.create(provider);
	}
	
	public void updateProvider (Provider provider) throws SQLException {
		providerDAO.update(provider);
	}
	
	public void removeProvider (int providerId) throws SQLException {
		providerDAO.delete(providerId);
	}
}