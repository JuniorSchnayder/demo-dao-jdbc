package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			st.setInt(1, id);

			rs = st.executeQuery();
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, dep);

				return seller;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Seller> list = new ArrayList<>();

		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department " + "ON seller.DepartmentId = department.Id ");

			rs = st.executeQuery();
			while (rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, dep);

				list.add(seller);
			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}
	
	@Override
	public List<Seller> findByDepartment(Department dep) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Seller> list = new ArrayList<>();

		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller RIGHT JOIN department " + "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY seller.Name");

			st.setInt(1,dep.getId() );
			rs = st.executeQuery();
			Map<Integer,Department> map = new HashMap<>();
			
			while (rs.next()) {		
				if(map.get(rs.getInt("DepartmentId")) == null) {
					map.put(rs.getInt("DepartmentId"), instantiateDepartment(rs));
				}
				
				Seller seller = instantiateSeller(rs, map.get(rs.getInt("DepartmentId")));

				list.add(seller);
			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(dep);
		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	

}
