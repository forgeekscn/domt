package cn.itcast.jk.vo;

import java.util.List;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.domain.Factory;

public class ContractProductVO {

	private String id;

	Factory factory;
	Contract contract;
	List<ExtCproduct> extCproducts;
	
	private String factoryName;
	private String productNo;
	private String productImage;
	private String productDesc;
	private Integer cnumber;
	private Integer outNumber;
	private String loadingRate;			//装率
	private Integer boxNum;				//箱数
	private String packingUnit;			//包装单位
	private Double price;
	private Double amount;
	private Integer finished;
	private String exts;
	private Integer orderNo;
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	public List<ExtCproduct> getExtCproducts() {
		return extCproducts;
	}
	public void setExtCproducts(List<ExtCproduct> extCproducts) {
		this.extCproducts = extCproducts;
	}
	public String getId() {
		return id;
	}
	
	public Factory getFactory() {
		return factory;
	}
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
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
	public Integer getOutNumber() {
		return outNumber;
	}
	public void setOutNumber(Integer outNumber) {
		this.outNumber = outNumber;
	}
	public String getLoadingRate() {
		return loadingRate;
	}
	public void setLoadingRate(String loadingRate) {
		this.loadingRate = loadingRate;
	}
	public Integer getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(Integer boxNum) {
		this.boxNum = boxNum;
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
	public Integer getFinished() {
		return finished;
	}
	public void setFinished(Integer finished) {
		this.finished = finished;
	}
	public String getExts() {
		return exts;
	}
	public void setExts(String exts) {
		this.exts = exts;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	
	
}
