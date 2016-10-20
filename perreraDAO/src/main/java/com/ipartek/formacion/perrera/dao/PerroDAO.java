package com.ipartek.formacion.perrera.dao;

import java.util.List;

import com.ipartek.formacion.perrera.pojo.Perro;

public interface PerroDAO {

	List<Perro> getAll(String order, String campo);

	Perro getById(long idPerro);

	boolean delete(long idPerro);

	boolean insert(Perro perro);

	boolean update(Perro perro);

}
