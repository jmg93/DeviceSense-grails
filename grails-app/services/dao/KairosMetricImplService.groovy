package dao

import grails.gorm.transactions.Transactional
import org.kairosdb.client.HttpClient;
import org.kairosdb.client.builder.AggregatorFactory;
import org.kairosdb.client.builder.QueryBuilder;
import org.kairosdb.client.builder.TimeUnit;
import org.kairosdb.client.response.QueryResponse;

@Transactional
class KairosMetricImplService extends MetricDaoService{

	//@Override
	public List getMetricNames() {
		List result = null;
    	try{
	    	HttpClient client = new HttpClient("http://localhost:8090");
	    	result = client.getMetricNames().getResults();
	    	client.shutdown();
    	} catch(Exception e){
    		System.out.println("Error: " + e.getMessage());
    	}
		return result;
	}

	//@Override
	public QueryResponse getMetricValues(String metricName) {
		QueryResponse response = null;
    	QueryBuilder builder = QueryBuilder.getInstance();
    	builder.setStart(5, TimeUnit.HOURS)
    	       .addMetric("metricName")
    	       .addAggregator(AggregatorFactory.createSumAggregator(1, TimeUnit.MILLISECONDS));
    	
    	try{
    		HttpClient client = new HttpClient("http://localhost:8090");
    		response = client.query(builder);
    		client.shutdown();
    	} catch (Exception e){
    		System.out.println("Error: " + e.getMessage());
    	}
		return response;
	}


    def serviceMethod() {

    }
}

