package es.jar.tm.sistemastm.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpEntity;

import es.jar.tm.sistemastm.files.Fichero;

public interface ListadoFicherosService {

	/**Este método se encargara de abrir la carpeta de usuarios, llamará al método que se encarga de recorrer recursivamente todos los directorios y recibira un 
	 * arraylist con todos las carpetas y ficheros que se hayan encontrado.
	 * Una vez reciba el arraylist lo recorrerá y irá asignando al objeto Fichero las propiedades que necesitaremos para mostrar el arbol en el jsp.
	 * Se setearan las propiedades de las rutas absolutas, y además las variables de pathFichero y pathPadre serán los identificadores para el componente jqtreetable, para que
	 * este sea capaz de saber quien es hijo de quien y que estructura tiene el arbol.
	 * En caso de que el directorio no exista o no hayan carpetas dentro se informará de un error al usuario.
	 * @param uiModel
	 * @param req
	 * @param principal
	 * @param locale
	 * @return
	 */
	List<Fichero> obtenerFicherosDirectorios(File directorio,String directorioRaiz);
	
	/**Este método recibira una URL por parametro y se encargará de abrir el fichero, convertirlo a un array de bytes y después devolverselo al usuario para
	 * poder ser descargado.
	 * @param response
	 * @param url
	 * @return
	 * @throws IOException
	 */
	HttpEntity<byte[]> descargarFicheros(String url,String directorioRaiz) throws IOException;
}
