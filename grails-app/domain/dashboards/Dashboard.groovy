package dashboards

class Dashboard {

	String codigo
	String descripcion
	
	static hasMany = [dispositivos: dispositivos.Dispositivo]
	static belongsTo = [usuario: usuarios.Usuario] 

	public String toString(){
		return codigo
	}

    static constraints = {
    	codigo unique: true
    	descripcion blank:true
    	dispositivos()
    }
}
