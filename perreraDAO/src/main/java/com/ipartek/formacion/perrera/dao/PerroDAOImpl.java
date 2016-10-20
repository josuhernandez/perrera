package com.ipartek.formacion.perrera.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.ipartek.formacion.perrera.pojo.Perro;
import com.ipartek.formacion.perrera.util.HibernateUtil;

public class PerroDAOImpl implements PerroDAO {

	/**
	 * Función que devuelve una lista de perros
	 * 
	 * @param order
	 *            Modo de ordenacion de la lista.<br>
	 *            Posibles valores asc/desc
	 * @param campo
	 *            Campo por el que se va a ordenar. <br>
	 *            Posibles valores id/nombre/raza
	 * @return List<Perro>
	 */
	public List<Perro> getAll(String order, String campo) {
		// inicializamos lista como un ArrayList de objetos Perro
		ArrayList<Perro> lista = new ArrayList<Perro>();
		// obtenemos la sesion
		Session s = HibernateUtil.getSession();
		// comenzamos la transaccion
		s.beginTransaction();

		try {

			lista = (ArrayList<Perro>) s.createCriteria(Perro.class).addOrder(Order.desc(campo)).list();
			// finalizamos la transaccion
			s.beginTransaction().commit();

		} catch (Exception e) {
			// en caso de que casque capturamos la excepcion y deshace lo hecho
			// (vuelve atrás)
			s.beginTransaction().rollback();
			e.printStackTrace();
		} finally {
			// cerramos la transaccion
			s.close();
		}
		return lista;
	}

	/**
	 * Función que devuelve el perro con id='id'
	 * 
	 * @param idPerro
	 *            id del perro a buscar
	 * @return Perro
	 */
	public Perro getById(long idPerro) {

		Perro perro = null;
		Session s = HibernateUtil.getSession();
		s.beginTransaction();

		try {
			perro = (Perro) s.get(Perro.class, idPerro);
			s.beginTransaction().commit();
			s.close();
		} catch (Exception e) {
			// en caso de que casque capturamos la excepcion y deshace lo hecho
			// (vuelve atrás)
			s.beginTransaction().rollback();
			e.printStackTrace();
		} finally {
			// cerramos la transaccion
			s.close();
		}
		return perro;
	}

	public boolean delete(long idPerro) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean insert(Perro perro) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(Perro perro) {
		// TODO Auto-generated method stub
		return false;
	}

}
