package grafico

import com.db.influxdb.*;
import influxdb.MetricaService
import influxdb.Consulta
import grails.converters.JSON
import org.json.simple.JSONObject
import java.util.ArrayList


class GraficoController {

	def MetricaService
	static scaffold = usuarios.Usuario		//Esto por que estaba aca??

    def index() { 

    	def consulta = new Consulta()
    	def listaMetricas = MetricaService.listaMetricas()
    	consulta.setDesde(3)
    	consulta.setTipoQuery(1)
    	render view:"grafico", model:[consulta: consulta,listaMetricas: listaMetricas]
    }

    def devuelveDatos(Consulta consulta) throws URISyntaxException, IOException{

    	def datos = MetricaService.consultarDatos(consulta)
 		def datosJSON = MetricaService.armarJSON(datos)
    	System.out.println(datosJSON)
    	System.out.println(consulta.getTipoQuery())
    	render datosJSON as JSON

    }


/*	def agregarDatosRandom(Consulta consulta) throws URISyntaxException, IOException, InterruptedException{
		
		MetricBuilder builder = MetricBuilder.getInstance();

		for(int i=1;i<=10;i++){
			builder.addMetric("2")
				.addTag("host", "server1")
				.addTag("customer", "Acme")
				.addDataPoint(System.currentTimeMillis(), Math.floor((Math.random()*100))); // 1/1/2017 = 1483228800000 ms
			Thread.sleep(5);
		}
		HttpClient client = new HttpClient("http://localhost:8090");
		//Response response = client.pushMetrics(builder);
		client.pushMetrics(builder);
		client.shutdown();
		//model.addAttribute("metricNames",metricDao.getMetricNames());
		//return "welcome";
		render view:"grafico"
	}
*/
}

