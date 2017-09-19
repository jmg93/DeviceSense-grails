package dispositivos

class Sensor {

	String id
	String descripcion
	kairosdb.Metrica metrica

	static belongsTo = [dispositivo: dispositivos.Dispositivo]

	public String toString(){
		return descripcion
	}

    static constraints = {
    }
}
