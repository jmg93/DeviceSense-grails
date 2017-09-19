package usuarios

class Usuario {

	String username
	String password
	List dashboards

	static hasMany = [dashboards: dashboards.Dashboard]

    static constraints = {
    }
}
