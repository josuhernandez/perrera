package com.ipartek.formacion.perrera.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.ipartek.formacion.perrera.pojo.Perro;

public class PerroDAOImplTest {

	@Test
	public void test() {

		PerroDAOImpl dao = new PerroDAOImpl();
		int numPerrosIniciales;
		int numPerrosTrasInsertarUnoNuevo;
		int numPerrosTrasBorrar;

		// *****************************************************
		// ******* COMPROBAR EL METODO getAll() ****************
		// *****************************************************
		// obtenemos la lista de perros
		ArrayList<Perro> lista = (ArrayList<Perro>) dao.getAll("asc", "id");
		// comprobamos que la lista no es nula
		assertNotNull("No puede ser null", lista);
		numPerrosIniciales = lista.size();

		// *********************************************************
		// ******* COMPROBAR EL METODO insert(Perro perro) *********
		// *****************************************************
		// creamos un perro nuevo
		Perro perro = new Perro();
		perro.setNombre("Boby");
		perro.setRaza("Caniche");

		// comprobamos que inserta OK
		assertTrue("Fallo al insertar", dao.insert(perro));
		// obtenemos la lista despues de haber insertado un nuevo perro
		ArrayList<Perro> listaDespuesInsertar = (ArrayList<Perro>) dao.getAll("asc", "id");
		numPerrosTrasInsertarUnoNuevo = listaDespuesInsertar.size();
		// comprobamos que el tamaño de la lista se ha incrementado en 1
		assertEquals(numPerrosIniciales + 1, numPerrosTrasInsertarUnoNuevo);

		// *****************************************************
		// ******* COMPROBAR EL METODO update() ****************
		// *****************************************************
		// cambiamos los parámetro de 'nombre' y 'raza' al perro creado
		perro.setNombre("Lur");
		perro.setRaza("Doberman");
		// comprobamos que lo actualiza correctamente
		assertTrue("Fallo al actualizar", dao.update(perro));

		// *****************************************************
		// ******* COMPROBAR EL METODO getById(id) ****************
		// *****************************************************
		int idPerro = perro.getId();
		Perro perroObtenido = dao.getById(idPerro);
		assertNotNull("Fallo al obtener el perro con id=" + idPerro, dao.getById(idPerro));
		assertEquals("Lur", perroObtenido.getNombre());
		assertEquals("Doberman", perroObtenido.getRaza());

		// ******************************************************
		// ******* COMPROBAR EL METODO delete(id) ****************
		// *****************************************************
		// comprobamos que borra el perro con 'id' correctamente
		assertTrue("Fallo al borrar el perro con id=" + perro.getId(), dao.delete(idPerro));
		// volvemos a obtener la lista tras borrar un elemento
		ArrayList<Perro> listaDespuesBorrar = (ArrayList<Perro>) dao.getAll("asc", "id");
		// comprobamos que la lista no es nula y que se ha decrementado en uno
		numPerrosTrasBorrar = listaDespuesBorrar.size();
		assertNotNull("No puede ser Null", numPerrosTrasBorrar);
		assertEquals(numPerrosTrasInsertarUnoNuevo - 1, numPerrosTrasBorrar);

	}

}
