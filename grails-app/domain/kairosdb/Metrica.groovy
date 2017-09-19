package kairosdb

class Metrica {

	String id
	String descripcion

	static belongsTo = [sensor: dispositivos.Sensor]

	public String toString(){
		return descripcion
	}

    static constraints = {
    }
}
