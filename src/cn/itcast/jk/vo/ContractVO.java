package cn.itcast.jk.vo;

import java.util.List;

import cn.itcast.jk.domain.ContractProduct;

public class ContractVO {

	List<ContractProduct> contractProducts;

	private String id;
	private String contractProductId;
	private String cpFactoryId;
	private String extContractProductId;
	private String extFactoryId;

	public List<ContractProduct> getContractProducts() {
		return contractProducts;
	}

	public void setContractProducts(List<ContractProduct> contractProducts) {
		this.contractProducts = contractProducts;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContractProductId() {
		return contractProductId;
	}

	public void setContractProductId(String contractProductId) {
		this.contractProductId = contractProductId;
	}

	public String getCpFactoryId() {
		return cpFactoryId;
	}

	public void setCpFactoryId(String cpFactoryId) {
		this.cpFactoryId = cpFactoryId;
	}

	public String getExtContractProductId() {
		return extContractProductId;
	}

	public void setExtContractProductId(String extContractProductId) {
		this.extContractProductId = extContractProductId;
	}

	public String getExtFactoryId() {
		return extFactoryId;
	}

	public void setExtFactoryId(String extFactoryId) {
		this.extFactoryId = extFactoryId;
	}

}
