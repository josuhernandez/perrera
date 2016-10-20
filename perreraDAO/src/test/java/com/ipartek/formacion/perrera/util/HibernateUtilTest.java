package com.ipartek.formacion.perrera.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class HibernateUtilTest {

	@Test
	public void test() {
		assertNotNull("Configuracion BBDD incorrecta", HibernateUtil.getSession());
	}

}
