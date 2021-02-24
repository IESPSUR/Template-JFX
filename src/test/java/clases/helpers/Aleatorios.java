package clases.helpers;

import java.util.Random;

public class Aleatorios {
	
	private static Random r = new Random();

	public Aleatorios() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Construye cadenas aleatorias de tamaño variable
	 * @param minlen
	 * @param maxlen
	 * @return
	 */
	public static String cadenaAleatoria(int minlen, int maxlen) {
		StringBuilder cad = new StringBuilder();
		
		int len = numeroAleatorio(minlen, maxlen);
		
		for(int i=0;i<len;i++) {
			String car = String.valueOf((char)(r.nextInt('z'-'a'+1)+'a'));
			if(r.nextBoolean())
				car.toUpperCase();
			
			cad.append(car);
		}
		
		return cad.toString();
	}
	
	public static int numeroAleatorio(int min, int max) {
		return r.nextInt((max-min)+1)+min;
	}
	
	public static float numeroAleatorio(float min, float max) {
		return r.nextFloat()*(max - min) + min;
	}
	
	public static float [][] arrayFloat(int rows, int cols, float min, float max){
		float [][] array = new float[rows][cols];
		
		for (int i = 0; i < array.length; i++) {
			float[] fs = array[i];
			for (int j = 0; j < fs.length; j++) {
				 fs[j]=numeroAleatorio(min,max);
				
			}
		}
		return array;
	}

}
