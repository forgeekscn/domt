package cn.itcast.jk.vo;

import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Factory;

public class ExtCproductVO {

	private String id;
	
	private Factory factory;
	private ContractProduct contractProduct;

	private String factoryName;
	private String ctype;
	private String productNo;
	private String productImage;
	private String productDesc;
	private Integer cnumber;
	private String packingUnit; // 包装单位
	private Double price;
	private Double amount;
	private String productRequest;
	private Integer orderNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Factory getFactory() {
		return factory;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
	}

	public ContractProduct getContractProduct() {
		return contractProduct;
	}

	public void setContractProduct(ContractProduct contractProduct) {
		this.contractProduct = contractProduct;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Integer getCnumber() {
		return cnumber;
	}

	public void setCnumber(Integer cnumber) {
		this.cnumber = cnumber;
	}

	public String getPackingUnit() {
		return packingUnit;
	}

	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getProductRequest() {
		return productRequest;
	}

	public void setProductRequest(String productRequest) {
		this.productRequest = productRequest;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

}
