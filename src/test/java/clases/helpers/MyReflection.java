package clases.helpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase usa la API de java Reflection
 * para analizar aspectos de construccion de las clases
 * @author eserrano
 *
 */
public class MyReflection {
	
	/**
	 * Permite crear un objeto por su nombre de clase usando
	 * el constructor por defecto
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public static Object invocaConstructor(String className) throws Exception {
		Class<?> clazz = Class.forName(className);
		Constructor<?> ctor = clazz.getConstructor();
		return ctor.newInstance(new Object[] {} );
	}
	
	/**
	 * Permite crear un objeto usando su nombre de clase
	 * y los parametros del constructor sobrecargado
	 * @param className
	 * @param initialValues
	 * @param classes
	 * @return
	 * @throws Exception
	 */
	public static Object invocaConstructor(String className, Object[] initialValues, Class<?>...classes ) throws Exception {
		Class<?> clazz = Class.forName(className);
		Constructor<?> ctor = clazz.getConstructor(classes);
		return ctor.newInstance(initialValues );
	}
	
	/**
	 * Devuelve el numero de constructores que tiene una clase
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public static int cuentaConstructores(String className) throws Exception{
		Class<?> clase = Class.forName(className);

		Constructor<?>[] constructors = clase.getConstructors();
		return constructors.length;
	}
	
	/**
	 * Devuelve el Nombre de un campo que se pasa como parametro
	 * @param className
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static String getField(String className, String fieldName) throws Exception{
		Field field = Class.forName(className)
			      .getDeclaredField(fieldName);
		Class<?> fieldClass = field.getType();

		 return fieldClass.getSimpleName();
	}
	
	/**
	 * Devuelve una lista con todos los metodos publicos de la clase
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static List<String> getPublicMethods(String className) throws ClassNotFoundException{
		Class<?> clase = Class.forName(className);
	    return getMethodNames(clase.getDeclaredMethods());
	}
	
	/**
	 * Devuelve una lista con todos los metodos de la clase
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static List<String> getAllMethods(String className) throws ClassNotFoundException{
		Class<?> clase = Class.forName(className);
	    return getMethodNames(clase.getMethods());
	}
	
	/**
	 * Convierte un array de Metodos en una lista con sus nombres
	 * @param methods
	 * @return
	 */
	private static List<String> getMethodNames(Method[] methods) {
	    List<String> methodNames = new ArrayList<String>();
	    for (Method method : methods)
	      methodNames.add(method.getName());
	    return methodNames;
	}
	
	/**
	 * Metodo que invoca un metodo de un objeto que se pasa como parametro
	 * @param objeto invocado
	 * @param className Clase del objeto invocado
	 * @param methodName Nombre del metodo a invocar
	 * @param parameters Parmetros del metodo a invocar
	 * @param parameterTypes Tipos de los parametros
	 * @return
	 * @throws Exception
	 */
	public static Object ejecutaMetodo(Object objeto, String className, 
			String methodName,Object[] parameters, Class<?>...parameterTypes) throws Exception {
		Object resultado = null;
		Class<?> clase = Class.forName(className);
		
		Method metodo;
		if(parameters!=null) {
			metodo = clase.getDeclaredMethod(methodName, parameterTypes);
			resultado = metodo.invoke(objeto, parameters);
		}
		else {
			metodo = clase.getDeclaredMethod(methodName);
			resultado = metodo.invoke(objeto);
		}
		
		return resultado;
	}
	
	/**
	 * Devuelve la comprobacion de si una clase implementa o no una interfaz
	 * @param className
	 * @param interfaceName
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static boolean implementsInterface(String className, String interfaceName) throws ClassNotFoundException {
		Class<?> clase = Class.forName(className);
		Class<?> [] interfaces = clase.getInterfaces();
		
		boolean implementa = false;
		for (Class<?> class1 : interfaces) {
			if(class1.getName().equals(interfaceName)) {
				implementa=true;
				break;
			}
		}
		return implementa;
	}
	

}
