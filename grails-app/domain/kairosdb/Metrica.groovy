package kairosdb

class Metrica {

	String codigo
	
	public String toString(){
		return codigo
	}

    static constraints = {
    	codigo unique: true
    }
}
