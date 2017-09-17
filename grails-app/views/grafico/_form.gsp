<%@ page import="kairosdb.Consulta" %>

<div>
	<label for="metrica">Métrica</label>
	<g:select name="metrica" from='${listaMetricas}' value="${consulta?.metrica}"/>
</div>

<div>
	<label for="desde">Últimos días</label>
	<g:field name="desde" type="number" min="1" value="${consulta?.desde}" required=""/>
</div>

<div hidden="true">
	<label for="hasta">Hasta</label>
	<g:field name="hasta" type="number" value="1" required=""/>
</div>
