package com.training.model;

public class Company {
	private int branchid; 
	private String branchname; 
	private String address; 
	private String ownerName; 
	private long  mobileNo; 
	private long frontdeskNo;
	public Company() {
		super();
	}
	public Company(int branchid, String branchname, String address, String ownerName, long mobileNo, long frontdeskNo) {
		super();
		this.branchid = branchid;
		this.branchname = branchname;
		this.address = address;
		this.ownerName = ownerName;
		this.mobileNo = mobileNo;
		this.frontdeskNo = frontdeskNo;
	}
	public int getBranchid() {
		return branchid;
	}
	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}
	public String getbranchname() {
		return branchname;
	}
	public void setbranchname(String branchname) {
		this.branchname =branchname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public long getFrontdeskNo() {
		return frontdeskNo;
	}
	public void setFrontdeskNo(long frontdeskNo) {
		this.frontdeskNo = frontdeskNo;
	}
	@Override
	public String toString() {
		return "Company [branchid=" + branchid + ", name=" +branchname+ ", address=" + address + ", ownerName=" + ownerName	+ ", mobileNo=" + mobileNo + ", frontdeskNo=" + frontdeskNo + "]";
	} 
	
	
}
