package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Blog;
import utility.ConnectionManager;
import utility.HibernateConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface{
	private SessionFactory sessionFactory = HibernateConnectionManager.getSessionFactory();

	@Override
	public void insertBlog(Blog blog) throws SQLException {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(blog);
		tx.commit();
		session.close();
		
	}

	@Override
	public Blog selectBlog(int blogid) throws SQLException {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Blog blog = session.get(Blog.class, blogid);
		session.close();
		return blog;
		
	}

	@Override
	public List<Blog> selectAllBlogs() throws SQLException {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Blog> query = builder.createQuery(Blog.class);
		Root<Blog> root = query.from(Blog.class);
		query.select(root);
		Query<Blog> q = session.createQuery(query);
		List<Blog> listBlog = q.getResultList();
		return listBlog;
	}

	@Override
	public void deleteBlog(int id) throws SQLException {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Blog blog = session.get(Blog.class, id);
		session.delete(blog);
		tx.commit();
		session.close();
	
	}

	@Override
	public void updateBlog(Blog blog) throws SQLException, Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(blog);
		tx.commit();
		session.close();
	}
	
}