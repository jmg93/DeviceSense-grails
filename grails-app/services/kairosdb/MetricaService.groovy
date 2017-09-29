package kairosdb

import grails.gorm.transactions.Transactional
import org.kairosdb.client.HttpClient;
import org.kairosdb.client.builder.*;
import org.kairosdb.client.response.*;
import dao.MetricDaoService;
import dao.KairosMetricImplService

@Transactional
class MetricaService {

	def crearMetricaKairos(Metrica metrica){
		MetricBuilder builder = MetricBuilder.getInstance()
		builder.addMetric(metrica.getCodigo())
				.addTag("tag", metrica.getCodigo())
		HttpClient client = new HttpClient("http://localhost:8090");
		client.pushMetrics(builder);
		client.shutdown();
	}

	def agregarDatosRandom(Metrica metrica) throws URISyntaxException, IOException, InterruptedException{
		
		MetricBuilder builder = MetricBuilder.getInstance();

		for(int i=1;i<=10;i++){
			builder.addMetric(metrica.getCodigo())
					.addTag("tag", metrica.getCodigo())
					.addDataPoint(System.currentTimeMillis(), Math.floor((Math.random()*100))); // 1/1/2017 = 1483228800000 ms
			Thread.sleep(5);
		}
		HttpClient client = new HttpClient("http://localhost:8090");
		client.pushMetrics(builder);
		client.shutdown();
	}

    def serviceMethod() {

    }
}
