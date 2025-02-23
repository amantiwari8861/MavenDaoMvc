package com.training.dao.util;

import java.util.List;

import com.training.model.Company;
 
public  interface CompanyDao {
	
	long addCompany(Company comp);
	long updateCompany(Company  comp);
	 Company getCompanyById(long branchId);
	List<Company> getAllComapny();
	long deleteCompanyById(long branchId);
    public String[] getAllColumns();//  Search Again
	
	
	

}
