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
	static scaffold = usuarios.Usuario

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

	def agregarDatosRandom(Consulta consulta) throws URISyntaxException, IOException, InterruptedException{
		
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
}
