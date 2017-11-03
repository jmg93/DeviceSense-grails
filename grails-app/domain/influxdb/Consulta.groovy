package influxdb

class Consulta {

	String metrica
	Integer desde
	Integer hasta
	Integer tipoQuery
	String parametroGroupBy

	def getMetrica() {
		return metrica;
	}

	def setMetrica(String metrica) {
		this.metrica = metrica;
	}

	def getDesde() {
		return desde;
	}

	def setDesde(Integer desde) {
		this.desde = desde;
	}

	def getHasta() {
		return hasta;
	}

	def setHasta(Integer hasta) {
		this.hasta = hasta;
	}

	def getTipoQuery(){
		return tipoQuery
	}

	def setTipoQuery(Integer tipo){
		this.tipoQuery = tipo
	}

	def getParametroGroupBy(){
		return parametroGroupBy
	}

	def setParametroGroupBy(String parametro){
		this.parametroGroupBy = parametro
	}
	
    static constraints = {
    }
}
