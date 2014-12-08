package com.dichem.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.dichem.model.LectorDirectorios;

public class LeerDirectoriosTest {

	String directorioPadre = "C:\\Users\\Andres\\workspaceLuna\\lectorDeDirectorios\\src\\test\\resources\\DirectorioPadre";

	@Test
	public void debeListarElContenidoDeLosSubdirectoriosPorRUC() {
		// Arrange
		String ruc = "111";
		LectorDirectorios lector = new LectorDirectorios(directorioPadre, ruc);
		// Act
		lector.leerDirectorios();
		// Assert
		Assert.assertEquals(2, lector.getArchivos().size());
		String[] arregloEsperado = { "1.txt", "1xml.xml" };
		for (String string : lector.getArchivos().keySet()) {
			System.out.println(string);
		}
		Assert.assertArrayEquals(arregloEsperado, lector.getArchivos().keySet().toArray());

	}

	@Ignore
	@Test
	public void debeListarElContenidoDeLosSubdirectorios() {
		// Arrange
		LectorDirectorios lector = new LectorDirectorios(directorioPadre, null);
		// Act
		lector.leerDirectorios();
		// Assert
		Assert.assertEquals(6, lector.getArchivos().size());

	}

	@Test
	public void debeListarDirectoriosDeUnDirectorioDado() {
		// Arrange
		LectorDirectorios lector = new LectorDirectorios(directorioPadre, null);
		// Act
		lector.leerDirectorios();
		// Assert
		Assert.assertEquals(3, lector.getDirectoriosTipoInformacion().size());
		String[] arregloEsperado = { "DirUno", "DirDos", "DirTres" };
		Assert.assertArrayEquals(arregloEsperado, lector.getDirectoriosTipoInformacion().keySet().toArray());

	}
}
