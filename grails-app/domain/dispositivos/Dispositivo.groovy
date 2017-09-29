package dispositivos

class Dispositivo {

	String codigo
	String descripcion

	static hasMany = [sensores: dispositivos.Sensor]

	public String toString(){
		return codigo
	}

    static constraints = {
    	codigo unique:true
    	descripcion blank:true
    	sensores()
    }
}
