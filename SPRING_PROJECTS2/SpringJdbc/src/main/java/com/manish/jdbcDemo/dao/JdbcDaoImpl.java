/*
 * author:Manish Kumar
 */

package com.manish.jdbcDemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.manish.model.Circle;
@SuppressWarnings("deprecation")
@Component
public class JdbcDaoImpl {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate=null;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate=null;
	//private SimpleJdbcTemplate simpleJdbcTemplate=null;    /*USED WHEN JdbcTemplate & NamedParameterJdbcTemplate is required*/
	

	public DataSource getDataSource() {
		return dataSource;
	}


	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);					/*Customising the setter to instantiate jdbcTemplate*/
		this.namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource); /*Only when we use NamedParameters*/
	}


	/*USING SPRING WITHOUT JDBC TEMPLATE*/
	public Circle getCircleName(int circleId)
	{
		Connection conn=null;
		try{
		
			conn=dataSource.getConnection();
			PreparedStatement ps=conn.prepareStatement("select * from circle where id=?");
			ps.setInt(1,circleId);
			Circle circle=null;
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				circle=new Circle(circleId, rs.getString("name"));
			}
			rs.close();
			ps.close();
			return circle;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	/*USING JDBC TEMPLATE*/
	public int getCircleCount(){
		
		String sql="select count(*) from circle";
		return jdbcTemplate.queryForObject(sql,Integer.class);
		/*OR*/
		
//		return jdbcTemplate.querForInt(sql);		
	}
	
	public String getCircleNameusingTemplate(int circleId){
		String sql="SELECT NAME FROM CIRCLE WHERE ID=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {circleId},String.class);
	}
	
	public Circle getCircleUsingMapper(int circleId){
		String sql="SELECT * FROM CIRCLE WHERE ID=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {circleId},new CircleMapper());
		
	}
	
	public List<Circle> getCircles(){
		String sql="SELECT * FROM CIRCLE";
		return jdbcTemplate.query(sql, new CircleMapper());
	}
	
	public void insertCircle(Circle circle){
		String sql="INSERT INTO CIRCLE (ID,NAME) VALUES(?,?)";
		jdbcTemplate.update(sql,new Object[] {circle.getId(),circle.getName()});
	}
	
	public void createTriangleTable(){
		String sql="CREATE TABLE TRIANGLE (BASE INTEGER,HEIGHT INTEGER,TYPE VARCHAR(50))";
		jdbcTemplate.execute(sql);
	}
	
	public void insertCircleUsingNamedParameterTemplate(Circle circle){
		String sql="INSERT INTO CIRCLE (ID,NAME) VALUES(:id,:name)";
		SqlParameterSource namedParameter=new MapSqlParameterSource("id",circle.getId()).addValue("name",circle.getName());
		namedParameterJdbcTemplate.update(sql, namedParameter);
	}
	
	private static final class CircleMapper implements RowMapper<Circle>{

		public Circle mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			
			Circle circle=new Circle();
			circle.setId(resultSet.getInt("ID"));
			circle.setName(resultSet.getString("NAME"));
			return circle;
			
		}
		
	}
}




