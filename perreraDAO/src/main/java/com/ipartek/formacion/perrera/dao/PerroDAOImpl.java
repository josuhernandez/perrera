package com.ipartek.formacion.perrera.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.ipartek.formacion.perrera.pojo.Perro;
import com.ipartek.formacion.perrera.util.HibernateUtil;

public class PerroDAOImpl implements PerroDAO {

	// instancia unica para 'patron Singleton'
	private static PerroDAOImpl INSTANCE = null;

	// constructor privado para que no se pueda instanciar esta clase
	private PerroDAOImpl() {
		super();
	}

	// unico metodo para creaar un objeto de esta clase
	public synchronized static PerroDAOImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PerroDAOImpl();
		}
		return INSTANCE;
	}

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
		// comenzamos la transaccion. Como no estamos mofificando la BBDD no
		// haria falta (lo comentamos)
		// s.beginTransaction();

		try {
			lista = (ArrayList<Perro>) s.createCriteria(Perro.class).addOrder(Order.desc(campo)).list();
			// finalizamos la transaccion. Como no estamos mofificando la BBDD
			// no haria falta (lo comentamos)
			// s.beginTransaction().commit();

		} catch (Exception e) {
			// en caso de que casque capturamos la excepcion y deshace lo hecho
			// (vuelve atrás). Como no estamos mofificando la BBDD no haria
			// falta (lo comentamos)
			// s.beginTransaction().rollback();
			e.printStackTrace();
		} finally {
			// cerramos la session
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
			// s.beginTransaction().commit();
			// s.close();
		} catch (Exception e) {
			// en caso de que casque capturamos la excepcion y deshace lo hecho
			// (vuelve atrás)
			// s.beginTransaction().rollback();
			e.printStackTrace();
		} finally {
			// cerramos la session
			s.close();
		}
		return perro;
	}

	public boolean delete(long idPerro) {
		Perro pElimnar = null;
		boolean resul = false;
		Session s = HibernateUtil.getSession();
		try {
			s.beginTransaction();
			pElimnar = (Perro) s.get(Perro.class, idPerro);
			s.beginTransaction().commit();
			if (pElimnar != null) {

				s.beginTransaction();
				s.delete(pElimnar);
				s.beginTransaction().commit();
				resul = true;
			}
		} catch (final Exception e) {
			e.printStackTrace();
			s.beginTransaction().rollback();
		} finally {
			s.close();
		}
		return resul;
	}

	public boolean insert(Perro perro) {
		boolean resul = false;
		Session s = HibernateUtil.getSession();
		try {
			s.beginTransaction();
			long idCreado = (Long) s.save(perro);
			if (idCreado > 0) {
				resul = true;
				s.beginTransaction().commit();
			} else {
				s.beginTransaction().rollback();
			}
		} catch (Exception e) {
			s.beginTransaction().rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return resul;
	}

	public boolean update(Perro perro) {
		boolean resul = false;
		Session s = HibernateUtil.getSession();

		try {
			s.beginTransaction();
			s.update(perro);
			s.beginTransaction().commit();
			resul = true;
		} catch (final Exception e) {
			e.printStackTrace();
			s.beginTransaction().rollback();
		} finally {
			s.close();
		}
		return resul;
	}

}
