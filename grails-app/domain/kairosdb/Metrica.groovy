package kairosdb

class Metrica {

	String nombre
	String descripcion

	static belongsTo = [sensor: dispositivos.Sensor]

	public String toString(){
		return nombre
	}

    static constraints = {
    	nombre unique: true
    	descripcion blank:true
    	sensor nullable: true
    }
}
