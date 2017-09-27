package dashboards

class Dashboard {

	String nombre
	String descripcion
	
	static hasMany = [dispositivos: dispositivos.Dispositivo]
	static belongsTo = [usuario: usuarios.Usuario] 

	public String toString(){
		return nombre
	}

    static constraints = {
    	nombre unique: true
    	descripcion blank:true
    }
}
