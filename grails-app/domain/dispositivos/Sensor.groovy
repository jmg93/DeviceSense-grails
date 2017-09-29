package dispositivos

class Sensor {

	String codigo
	String descripcion

	static belongsTo = [dispositivo: dispositivos.Dispositivo]

	public String toString(){
		return codigo
	}

    static constraints = {
    	codigo blank:false
    	descripcion blank:true
    }
}
