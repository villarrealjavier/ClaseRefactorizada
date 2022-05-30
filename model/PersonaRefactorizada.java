package main.java.model;

import java.time.LocalDate;

public class PersonaRefactorizada {
	
	private static final int EDAD_MAXIMA_BONO = 30;
	private static final int EDAD_MINIMA_BONO = 18;
	private static final int CARACTERES = 8;
	private static final int LONGITUD_MAXIMA_DNI = 9;
	private static final int LETRA_DNI = 23;
	
	
	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fechaNacimiento;
	private Genero genero;



	
	public PersonaRefactorizada(String nombre, String apellido, String dni) {
		super();
		if(validarDatos(nombre, apellido, dni)) {
			this.nombre = nombre;
			this.apellido = apellido;
			this.dni = dni;
		}else {
			throw new PersonaException("Error al introducir datos");
		}
	}
	
	public PersonaRefactorizada(String nombre, String apellido, String dni, LocalDate fechaNacimiento, String genero) {
		this(nombre, apellido, dni);
		if(validarDatos(nombre, apellido, dni, fechaNacimiento, genero)) {	
			this.fechaNacimiento = fechaNacimiento;
			this.genero = Genero.valueOf(genero);
		}else {
			throw new PersonaException("Error de datos");
		}
	}
	
	
	public boolean validarDatos(String nombre, String apellido, String dni) {
		
		if(nombre == null || nombre.isEmpty()|| apellido == null || apellido.isEmpty()|| dni==null ||dni.isEmpty()) {
			return false;
		}
		
		return EsDNI(dni);
	}
	
	//Metodo extraido DNI
	private boolean EsDNI(String dni) {
		boolean esCorrecto=true;
		if(dni.length() < LONGITUD_MAXIMA_DNI || !Character.isAlphabetic(dni.charAt(CARACTERES))) {
			
			esCorrecto=false;
		}
		
		for(int i=0; i<dni.length()-1; i++) {
			if(!Character.isDigit(dni.charAt(i))) {
				esCorrecto=false;
			}
		}
		if ("TRWAGMYFPDXBNJZSQVHLCKE".charAt(Integer.valueOf(dni.substring(0, CARACTERES))%LETRA_DNI)!= dni.toUpperCase().charAt(CARACTERES)) {
			
			esCorrecto=false;
		}
		
		return esCorrecto;
	}
	
	public boolean validarDatos(String nombre, String apellido, String dni, LocalDate fechaNacimiento, String genero) {
		boolean esCorrecto=false;
		if(nombre == null || nombre.isEmpty()|| apellido == null || apellido.isEmpty()|| dni==null ||dni.isEmpty()|| fechaNacimiento==null ||genero==null ||genero.isEmpty()) {
			esCorrecto=false;
		}
		if(fechaNacimiento.isAfter(LocalDate.now())) {
			esCorrecto=false;
		}
		
		if(EsDNI(dni) && genero(genero)) {
			esCorrecto=true;
		}
			
		return esCorrecto;
	}

	private boolean genero(String genero) {
		boolean esCorrecto=true;
		if(!Genero.HOMBRE.equals(Genero.valueOf(genero)) && !Genero.MUJER.equals(Genero.valueOf(genero))) {
			esCorrecto=false;
		}
		return esCorrecto;
	}
	
	
	
	public boolean bonoJovenDisponible() {
		boolean esCorrecto=false;
		
		if(LocalDate.now().getYear()-this.fechaNacimiento.getYear()> EDAD_MINIMA_BONO && LocalDate.now().getYear()-this.fechaNacimiento.getYear()< EDAD_MAXIMA_BONO) {
			esCorrecto=true;			
		}
			
		return esCorrecto;
		
	}
	
	public int getEdad() {
		return LocalDate.now().compareTo(fechaNacimiento)>=0?
						LocalDate.now().minusYears(this.fechaNacimiento.getYear()).getYear()	:	-1;
	}
	
	public int obtenerEdadPara(int year) {
		if(year>=this.fechaNacimiento.getYear()) {
			return year-this.fechaNacimiento.getYear();
		}
		return -1;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public Genero getGenero() {
		return genero;
	}


	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	
	

}

