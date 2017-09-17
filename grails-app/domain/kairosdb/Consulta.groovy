package kairosdb

class Consulta {

	String metrica
	Integer desde
	Integer hasta

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
	
    static constraints = {
    }
}
