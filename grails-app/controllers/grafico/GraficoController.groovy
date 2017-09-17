package grafico

import org.kairosdb.client.HttpClient;
import org.kairosdb.client.builder.*;
import org.kairosdb.client.response.*;
import dao.MetricDaoService;
import dao.KairosMetricImplService
import kairosdb.Consulta
import grails.converters.JSON

class GraficoController {

	def KairosMetricImplService
	
    def index() { 
    	//def consulta = new Consulta()
    	List listaMetricas = KairosMetricImplService.getMetricNames()
    	//consulta.setDesde(200)
    	//consulta.setHasta(1)
    	render view:"grafico", model:[listaMetricas: listaMetricas]
    }

	def devuelveDatos(Consulta consulta) throws URISyntaxException, IOException{
	
	QueryBuilder builder = QueryBuilder.getInstance();
	builder.addMetric(consulta.getMetrica());					
	builder.setStart(consulta.getDesde(), TimeUnit.DAYS);
	HttpClient client = null;
	client = new HttpClient("http://localhost:8090");
	QueryResponse queryResponse;	
	queryResponse = client.query(builder);
	client.shutdown();
	List datos = ((queryResponse.getQueries()).get(0)).getResults().get(0).getDataPoints();
	render datos as JSON;
	
	
	}
}
