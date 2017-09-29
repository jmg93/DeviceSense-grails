package usuarios

class Usuario {

	String username
	String password
	
	static hasMany = [dashboards: dashboards.Dashboard]

    static constraints = {
    	username blank: false, size: 6..20, unique: true
    	password blank: false, size: 6..20
    	dashboards()
    }
}
