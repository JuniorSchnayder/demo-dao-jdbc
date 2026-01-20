package aplication;


import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Department department = new Department(4,"Math");
		Seller seller = new Seller(null,"Junior Schnayder","juniorSch@gmail.com",new Date(),5227.0,department);
		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("===== TEST 1: seller findById =====");
		System.out.println(sellerDao.findById(3));
		System.out.println();

		System.out.println("===== TEST 2: seller findAll =====");
		List<Seller> seller2 = sellerDao.findAll();
		for(Seller s : seller2) {
			System.out.println(s);
		}
		System.out.println();
		
		System.out.println("===== TEST 3: seller findByDepartment =====");
		List<Seller> seller3 = sellerDao.findByDepartment(department);
		for(Seller s : seller3) {
			System.out.println(s);
		}
		System.out.println();
		
		System.out.println("===== TEST 4: seller insert =====");
		sellerDao.insert(seller);
		System.out.println("Sucesso! Novo ID: "+ seller.getId());

	}

}
