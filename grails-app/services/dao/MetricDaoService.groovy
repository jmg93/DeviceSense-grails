package dao

import grails.gorm.transactions.Transactional
import org.kairosdb.client.response.QueryResponse;

@Transactional
class MetricDaoService {

	List getMetricNames(){}
	QueryResponse getMetricValues(String metricNames){}

    def serviceMethod() {

    }
}
