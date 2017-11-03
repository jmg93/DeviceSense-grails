package influxdb

import grails.gorm.transactions.Transactional
import influxdb.Consulta
import com.db.influxdb.*
import java.util.ArrayList
import org.json.simple.JSONObject

@Transactional
class MetricaService {

	def listaMetricas(){

		Configuration configuration = new Configuration("localhost", "8086", "root", "root", "pruebaopc");
    	Query query = new Query();
    	query.setCustomQuery("SHOW MEASUREMENTS ON pruebaopc");
    	DataReader dataReader = new DataReader(query,configuration);
		ResultSet resultSet = dataReader.getResult();
		return resultSet.results.series.values[0][0]

	}


	def consultarDatos(Consulta consulta){

		Configuration configuration = new Configuration("localhost", "8086", "root", "root", "pruebaopc");
    	Query query = new Query();
    	def metrica = consulta.getMetrica().replace("[", "").replace("]", "")
						    	System.out.println(metrica)
    	query.setMeasurement(metrica);
						    	System.out.println(query)
    	query.addField("value")
						    	System.out.println(query)
    	def duracion = consulta.getDesde() + "h"
    	query.setDuration(duracion)
    	query.fillNullValues("0");

		switch(consulta.getTipoQuery()) {
			case 1:	break

			case 2: query.setAggregateFunction(AggregateFunction.MEAN)
					query.setGroupByTime(consulta.getParametroGroupBy())
					break

			case 3: query.setAggregateFunction(AggregateFunction.STDDEV)
					query.setGroupByTime(consulta.getParametroGroupBy())
					break 			
		}

						       	System.out.println(query)
    	DataReader dataReader = new DataReader(query, configuration);
						    	System.out.println(dataReader)
		ResultSet resultSet = dataReader.getResult();
								System.out.println(resultSet)
								System.out.println(resultSet.results.series.values[0][0].getClass())
		List datos = resultSet.results.series.values[0][0]
								System.out.println(datos)
		return datos

	}

	def armarJSON(List datos){

		def datosJSON = []
		for(d in datos){
			def punto = new JSONObject()
			System.out.println(d[0])
			if (d[1] != null) {
				punto.put("timestamp", d[0]);
				punto.put("value", d[1]);
				datosJSON.add(punto);
			}
		}
		return datosJSON

	}

    def serviceMethod() {

    }
}
