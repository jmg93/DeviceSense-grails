<%@ page import="influxdb.Consulta" %>

<div>
	<label for="metrica">Métrica</label>
	<g:select name="metrica" from='${listaMetricas}' value="${consulta?.metrica}"/>
</div>

<div>
	<label for="desde">Últimas horas</label>
	<g:field name="desde" type="number" min="1" value="${consulta?.desde}" required=""/>
</div>

<div>
	<label for="funcionDeAgregacion">Función de Agregación</label>
	<g:radioGroup name="tipoQuery"
              labels="['NINGUNA','MEDIA','DESVIO ESTANDAR']"
              values="[1,2,3]"
              value="${consulta?.tipoQuery}">
	<p>${it.radio} ${it.label}</p>
	</g:radioGroup>
</div>

<div>
	<label for="groupBy">Group By</label>
	<g:select name="parametroGroupBy" from="${['3s', '5s', '10s']}"/>
</div>