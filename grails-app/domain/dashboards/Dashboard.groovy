package dashboards

class Dashboard {

	String id
	String descripcion
	List dispositivos

	static hasMany = [dispositivos: dispositivos.Dispositivo]
	static belongsTo = [usuario: usuarios.Usuario] 

	public String toString(){
		return descripcion
	}

    static constraints = {
    }
}
