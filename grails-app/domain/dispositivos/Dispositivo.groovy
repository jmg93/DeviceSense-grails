package dispositivos

class Dispositivo {

	String nombre
	String descripcion

	static hasMany = [sensores: dispositivos.Sensor]

	public String toString(){
		return nombre
	}

    static constraints = {
    	nombre unique:true
    	descripcion blank:true
    }
}
