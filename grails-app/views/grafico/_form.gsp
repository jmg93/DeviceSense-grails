<%@ page import="kairosdb.Consulta" %>

<div>
	<label for="metrica">Métrica</label>
	<g:select name="metrica" from='${listaMetricas}' value="${consulta?.metrica}"/>
</div>

<div>
	<label for="desde">Desde</label>
	<g:field name="desde" type="number" value="${consulta?.desde}" required=""/>
</div>

<div>
	<label for="hasta">Hasta</label>
	<g:field name="hasta" type="number" value="${consulta?.hasta}" required=""/>
</div>