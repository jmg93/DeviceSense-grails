package dispositivos

class Dispositivo {

	String id
	String descripcion
	List sensores

	static hasMany = [sensores: dispositivos.Sensor]

	public String toString(){
		return descripcion
	}

    static constraints = {
    }
}
