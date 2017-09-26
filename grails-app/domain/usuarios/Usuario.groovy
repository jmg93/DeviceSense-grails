package usuarios

class Usuario {

	String username
	String password
	
	static hasMany = [dashboards: dashboards.Dashboard]

    static constraints = {
    }
}
