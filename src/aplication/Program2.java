package aplication;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		/*
		 * System.out.
		 * println("============================TESTE #1 insert==============================="
		 * ); System.out.print("Digite o nome do departamento: "); String nome =
		 * sc.next(); Department dep = new Department(null,nome);
		 * departmentDao.insert(dep);
		 */

		/*
		 * System.out.
		 * println("============================TESTE #2 update==============================="
		 * ); System.out.print("Digite o Id do departamento que desejar alterar: "); int
		 * id = sc.nextInt(); sc.nextLine();
		 * System.out.print("Digite o novo nome do departamento: "); String nomeUpdate =
		 * sc.next(); Department depUpdate = new Department(id,nomeUpdate);
		 * departmentDao.update(depUpdate);
		 */

		/*
		 * System.out.
		 * println("============================TESTE #3 deleteById==============================="
		 * ); System.out.print("Digite o Id do departamento que desejar deletar: "); int
		 * idDelete = sc.nextInt(); departmentDao.deleteById(idDelete);
		 */

		/*
		 * System.out.
		 * println("============================TESTE #4 findById==============================="
		 * ); System.out.print("Digite o Id do departamento: "); int idFind =
		 * sc.nextInt(); Department find = departmentDao.findById(idFind);
		 * System.out.println(find);
		 */

		System.out.println("============================TESTE #5 findAll===============================");
		List<Department> findAll = departmentDao.findAll();
		for (Department d : findAll) {
			System.out.println(d);
		}

	}

}
