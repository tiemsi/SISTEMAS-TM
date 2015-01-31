package es.jar.tm.sistemastm.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.jar.tm.sistemastm.files.Fichero;


@Service("listadoFicherosService")
@Transactional
public class ListadoFicherosServiceImpl implements ListadoFicherosService {

	public List<Fichero> obtenerFicherosDirectorios(File directorio, String directorioRaiz) {
		
		List<Fichero> ficherosDirectorio = new ArrayList<Fichero>();
		
		List<File> directorioRecursivo = listarDirectorio(directorio);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			 for(File archivo : directorioRecursivo){
				 Fichero fichero = new Fichero();
				 String pathFichero = archivo.getAbsolutePath();
				 pathFichero = pathFichero.substring(directorioRaiz.length());
				 
				 String pathPadre = archivo.getParentFile().getAbsolutePath();
				 
				 if(pathPadre.equals(directorioRaiz.substring(0, directorioRaiz.length()-1))){
					 pathPadre = pathPadre.replace(directorioRaiz.substring(0, directorioRaiz.length()-1),""); 
				 }else{
					 pathPadre = pathPadre.replace(directorioRaiz,"");
				 }
				 
				 fichero.setFileName(archivo.getName());
				 fichero.setIsFile(archivo.isFile());
				 fichero.setIsFolder(archivo.isDirectory());
				 fichero.setPathFichero(pathFichero);
				 fichero.setPathPadre(pathPadre);
				 fichero.setRutaAbsoluta(archivo.getAbsolutePath());
				 fichero.setFechaCreacion(sdf.format(new Date(archivo.lastModified())));
				 fichero.setSize(archivo.length()/1024);
				 
				 ficherosDirectorio.add(fichero);
			}
			 
			 return ficherosDirectorio;
}


/**Este método se encarga de recibir una ruta del servidor y recorrer recursivamente todas las carpetas y ficheros existentes en el.
 * Después los añadirá en un ArrayList y lo retornara.
* @param File
* @return ArraList<.File.>
*/
	public static List<File> listarDirectorio(File f){  
		List<File> ficheros = new ArrayList<File>();
		  
		for (File fichero : f.listFiles()){
			ficheros.add(fichero);
			if (fichero.isDirectory()){
				ficheros.addAll(listarDirectorio(fichero));
			}
		}
		        
	    return ficheros;                   
	}


	public HttpEntity<byte[]> descargarFicheros(String url, String directorioRaiz) throws IOException {
		File fr = new File(directorioRaiz.concat(url));
	    byte[] documentBody = getFileBytes(fr);
	
	    HttpHeaders header = new HttpHeaders();
	    header.set("Content-Disposition",
	                   "attachment; filename=".concat(fr.getName()));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
	} 
	
	/** Este método se encargará de recibir un fichero y convertirlo todo en un array de bytes.
	 * @param file
	 * @return Array -> byte[]
	 * @throws IOException
	 */
	public static byte[] getFileBytes(File file) throws IOException {
	    ByteArrayOutputStream ous = null;
		InputStream ios = null;
		try {
			ous = new ByteArrayOutputStream();
			ios = new FileInputStream(file);
			byte[] buffer = new byte[(int)file.length()];
				    
			int read = 0;
			while ((read = ios.read(buffer)) != -1){
				ous.write(buffer, 0, read);
			}
			
		}finally{
			ios.close();	
		}
    	
	    return ous.toByteArray();
	}

}
