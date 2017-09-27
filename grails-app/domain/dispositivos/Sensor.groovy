package dispositivos

class Sensor {

	String nombre
	String descripcion
	kairosdb.Metrica metrica

	static belongsTo = [dispositivo: dispositivos.Dispositivo]

	public String toString(){
		return nombre
	}

    static constraints = {
    	nombre unique: true
    	descripcion blank:true
    	metrica nullable:true
    }
}
