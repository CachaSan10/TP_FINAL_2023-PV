package ar.edu.unju.fi.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase que representa un archivo que se va a subir al proyecto.
 * @author Nahuel Alberto Cachambi
 *
 */
@Component
public class UploadFile {

public final static String IMAGES_FOLDER="src/main/resources/static/images";
	
	public  Resource load(String filename) throws MalformedURLException {
		Path path = getPath(filename);
		Resource resource = new UrlResource(path.toUri());
		return resource;
	}
	
	public  Path getPath(String filename) {
		return Paths.get(IMAGES_FOLDER).resolve(filename).toAbsolutePath();
	}
	
	public  String copy(MultipartFile file) throws IOException{
		String uniqueFilename =  file.getOriginalFilename();
		Path rootPath = getPath(uniqueFilename);
		Files.copy(file.getInputStream(), rootPath);
		return uniqueFilename;
	}
	
	public boolean delete(String filename) {
		Path rootPath = getPath(filename);
		File file = rootPath.toFile();
		if(file.exists() && file.canRead()) {
			if(file.delete()) {
				return true;
			}
		}
		return false;
	}

}
