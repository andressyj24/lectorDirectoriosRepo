package com.dichem.model;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LectorDirectorios {

	private Path dir;
	private Map<String, Path> directoriosTipoInformacion;
	private Map<String, Path> archivos;
	private String ruc;

	public LectorDirectorios(String directorioPadre, String ruc) {
		this.dir = Paths.get(directorioPadre);
		this.directoriosTipoInformacion = new HashMap<String, Path>();
		this.archivos = new TreeMap<>();
		this.ruc = ruc;
	}

	public LectorDirectorios() {
	}

	public void leerDirectorios() {
		try {
			obtenerDirectoriosDeTipoInformacion();
			explorarDirectoriosDeTiposInformacion();
		} catch (IOException | DirectoryIteratorException x) {
			x.printStackTrace();
		}
	}

	private void explorarDirectoriosDeTiposInformacion() throws IOException {
		for (String directorio : directoriosTipoInformacion.keySet()) {
			Path dirTipoInformacion = directoriosTipoInformacion.get(directorio);
			filtrarDirectoriosPorRUC(dirTipoInformacion);
		}
	}

	private void filtrarDirectoriosPorRUC(Path dirTipoInformacion) throws IOException {
		for (Path directorioInterno : Files.newDirectoryStream(dirTipoInformacion)) {
			if (ruc != null && directorioInterno.endsWith(ruc)) {
				agregarArchivosAMapa(directorioInterno);
			}
		}
	}

	private void agregarArchivosAMapa(Path directorioInterno) throws IOException {
		for (Path archivo : Files.newDirectoryStream(directorioInterno)) {
			archivos.put(archivo.getFileName().toString(), archivo);
		}
	}

	private void obtenerDirectoriosDeTipoInformacion() throws IOException {
		for (Path directorioInterno : Files.newDirectoryStream(dir)) {
			directoriosTipoInformacion.put(directorioInterno.getFileName().toString(), directorioInterno);
		}
	}

	public Path getDir() {
		return dir;
	}

	public void setDir(Path dir) {
		this.dir = dir;
	}

	public Map<String, Path> getDirectoriosTipoInformacion() {
		return directoriosTipoInformacion;
	}

	public void setDirectoriosTipoInformacion(Map<String, Path> directoriosTipoInformacion) {
		this.directoriosTipoInformacion = directoriosTipoInformacion;
	}

	public Map<String, Path> getArchivos() {
		return archivos;
	}

	public void setArchivos(Map<String, Path> archivos) {
		this.archivos = archivos;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

}
