//Inicializar la función indexOf en el objeto Array, ya que Internet Explorer no la implementa por defecto
if (!Array.prototype.indexOf) { 
    Array.prototype.indexOf = function(obj, start) {
         for (var i = (start || 0), j = this.length; i < j; i++) {
             if (this[i] === obj) { return i; }
         }
         return -1;
    }
}

//función para eliminar los "null" de un array
function removeNullsFromArray(actual){
	  var newArray = new Array();
	  for(var i = 0; i<actual.length; i++){
	      if (actual[i]){
	        newArray.push(actual[i]);
	    }
	  }
	  return newArray;
}

//para formatear campos numericos en la vista
function numFormat(cellvalue){
	var numberAsText= '' + cellvalue;
	var valorComa = 0;
	var valorPunto = 0;	
	for(i=0; i < numberAsText.length; i++){
		if(numberAsText[i] == ','){
			valorComa = i;
		}
		else if(numberAsText[i] == '.'){
			valorPunto = i;
		}
	}	
	
	if((valorPunto < valorComa) && valorPunto > 0){
		numberAsText = numberAsText.replace(/\,/g, "¬");		
		numberAsText = numberAsText.replace(/\./g, ",");		
		numberAsText = numberAsText.replace(/\¬/g, ".");		
	}
	
	signo = numberAsText.charAt(0);
    var parts = numberAsText.split(".");
    var parteEntera = $.number(parts[0]);
    
    parteEntera = parteEntera.replace(/\,/g, ".");    
    
    var parteDecimal = parts[1];
    if(!parteDecimal){
    	parteDecimal = '00';
    }
    if(parteDecimal.length == 1){
    	parteDecimal += '0';
    }    
    
    if (signo == '-'){    	
    	return signo + parteEntera + "," + parteDecimal;
    }
    else{    	
    	return parteEntera + "," + parteDecimal;
    }
    
}

function engNumFormat(cellvalue){
	var numberAsText= cellvalue.toString();
	return $.number(numberAsText, 2);
}

//para quitar el formato
function unFormat(cellvalue){
	var signo = cellvalue[0];
    var parts = cellvalue.split(",");
    var parteEntera = parts[0].replace(/\./g, "");
    var parteDecimal = parts[1];
    if (signo == '-'){
    	if(parteDecimal)
    		return signo + parteEntera + "." + parteDecimal;
    	else
    		return signo + parteEntera;
	}		
    else{
    	if(parteDecimal)
    		return parteEntera + "." + parteDecimal;
    	else
    		return parteEntera;
    }   
}


function formateaImportesFormularioES(){
	var campos = $(".formatNumber");
	$(campos).each(
		function(indice, campo){
			if (campo.value!=""){
				formato = deduceFormato(campo.value);
				if(formato!="ES" && formato!="ANY"){
					campo.value = numFormat(campo.value);
				}
			}
		}
	)
}


function formateaImportesFormularioEN(){
//	console&&console.log("DESFORMATEANDO CAMPOS");
	var campos = $(".formatNumber");
	$(campos).each(
		function(indice, campo){
			if (campo.value!=""){
				formato = deduceFormato(campo.value);
				if(formato!="EN"){
					campo.value = unFormat(campo.value);
				}
			}
		}
	)
}

function soloNumeros(){
	$(".soloNumeros").keydown(function(event) {
		if(event.shiftKey){
			event.preventDefault();
		 }
		 if(event.keyCode!=46 && event.keyCode!= 8) {
			 if (event.keyCode < 95) {
				 if (event.keyCode < 48 || event.keyCode > 57) {
					 event.preventDefault();
		          }
			 } 
		     else {
		    	 if (event.keyCode < 96 || event.keyCode > 105) {
		            event.preventDefault();
		          }
		     }
		   }
	});
}

function formatterEs(cellvalue){
	return numFormat(''+cellvalue);
}
//para formatear campos numéricos mientras se va escribiendo
function formatoEspParaOnKeyUp(cifra,maxEnteros,maxDecimales){
//	console&&console.log("formateando la cifra "+cifra);
	if(cifra.length>0){
		if(maxEnteros==undefined){
			maxEnteros=13;
		}
		if(maxDecimales==undefined){
			maxDecimales=2;
		}
		var numberAsText= '' + cifra;
		var entero	= numberAsText.split(",")[0];
		var decimal	= numberAsText.split(",")[1];
		negativo = false;
		if(entero.charAt(0)=='-'){
			negativo = true;
		}
		var valorPlano = entero.replace(".", "");
		while(valorPlano.indexOf('.')>=0){
			valorPlano = valorPlano.replace(".", "")
		}
		
		for(var i = 0; i<valorPlano.length; i++){
			if(isNaN(valorPlano.charAt(i)) || valorPlano.charAt(i)==' '){
				valorPlano = valorPlano.replace(valorPlano.charAt(i),"");
			}
		}
		if(decimal!=undefined){
			for(var i = 0; i<decimal.length; i++){
				if(isNaN(decimal.charAt(i)) || decimal.charAt(i)==' '){
					decimal = decimal.replace(decimal.charAt(i),"");
				}
			}
		}
		
		valorPlano=valorPlano.substring(0,maxEnteros);
//		console&&console.log("valorPlano = "+valorPlano);
		var valorConPuntos = new Array();
		var contadorCiftras=0;
		for(var i=valorPlano.length-1;i>=0;i--){
//			console&&console.log("recorriendo<"+valorPlano+"> voy por <"+valorPlano.charAt(i)+"> contadorCifras="+contadorCiftras+"(+1%3=<"+((contadorCiftras)+1%3)+">)");
			if(contadorCiftras!=0 && contadorCiftras%3==0){
				valorConPuntos.unshift(valorPlano.charAt(i)+'.');
			}else{
				valorConPuntos.unshift(valorPlano.charAt(i));
			}
			//valorPlano
			contadorCiftras++;
		}
		resultadoEntero = "";
		for(var i=0;i<valorConPuntos.length;i++){
			resultadoEntero += valorConPuntos[i];
		}
		
		if(negativo){
			resultadoEntero = '-'+resultadoEntero
		}
		
		if(decimal!=undefined){
			decimal=decimal.substring(0,maxDecimales);
//			console&&console.log("formateada: "+resultadoEntero+","+decimal+"\n\n");
			return resultadoEntero+","+decimal
		}else{
//			console&&console.log("formateada: "+resultadoEntero+"\n\n");
			return resultadoEntero
		}
    }else{
//    	console&&console.log("formateada: "+cifra+"\n\n");
    	return cifra
	}
}

function handleLoadError(xhr, status, error){
	divContenedor = $(this).parent().find("div");

	var urlGrid		=	this.p.url
	
	var errorHtml	= xhr.responseText;
	var errorStr	= "La dirección \""+$("#list").get(0).p.url+"\" ha devuelto un error:<br/>";
	
	if(xhr.status==200){//El código es de una respuesta correcta
		if($(errorHtml).find("div#message").length>0){//Lo que vuelve no son datos, sino una página de error de la aplicación
			var errorDesc	= $(xhr.responseText).find("div#message").html().trim();
			errorStr		= errorStr+errorDesc;
			errorHtml		= "";
		}
	}else{//La respuesta trae un código de error
		errorStr	= errorStr+xhr.status+" - "+xhr.statusText
	}
	
	var divDesc		= $(document.createElement('div'));
	var divHtml		= $(document.createElement('div'));
	
	divDesc.html(errorStr);
	divHtml.html(errorHtml);
	
	divDesc.addClass("error center");
	
	divContenedor.append(divDesc);
	divContenedor.append(divHtml);
}

function aplicaAutoCorreccionImportes(){
	campos = $(".formatNumber");
	$(campos).each(
		function(indice, campo){
			if(campo.value!=undefined){
				if(campo.value!= undefined && campo.value.length>0){
					campo.value = numFormat(campo.value);
				}
			}
			campo.onkeyup = function(event){
				try{
					//Estos códigos corresponden a las flechas, shift, control, inicio, fin... para que se puedan usar sin que se vaya el cursor al final del campo.
					teclasAIgnorar = [38, 39, 40, 37, 36, 35, 16, 17];
					if(teclasAIgnorar.indexOf(event.which)>0){return;}
				}catch(e){}
				campo.value = formatoEspParaOnKeyUp(campo.value)
			};
			//Se aplica al formulario padre del campo que acabamos de formatear el evento onSubmit
			//para que desformatee sus campos antes de hacer submit
			$(campo).closest("form").submit(function(){
				$(this).find(".formatNumber").each(function(indice, campo){
//					console&&console.log("campo:"+campo.name);
//					console&&console.log("valor:"+campo.value);
					formato = deduceFormato(campo.value);
//					console&&console.log("formato detectado:"+formato);
					if(formato!="EN"){
//						console&&console.log("El formato no es inglés ('EN'), hay que desformatear");
//						console&&console.log("desformateando:"+campo.value);
						campo.value=unFormat(campo.value);
					}
//					console&&console.log("valor final:"+campo.value+"\n\n");
				});
			});
		}
	);
}

/**
* Esta función comprobará varios casos posibles para cifras en formato inglés (EN), ej: 100,000,000.01
* Y en formato español (ES), ej 100.000.000,01, y devolverá una cadena indicando el formato deducido
* IMPORTANTE: este método está diseñado para recibir números formateados en cualquiera de ambos formatos
* pero sea cual sea, debe ser correcto, es decir, los separadores de miles deben estar cada 3 cifras y
* los decimales deben ser 2 o ser informado por parámetro
* Valores posibles:
* ES:  formato español
* EN:  formato inglés
* ANY: cualquier formato (número sin separadores)
* ?:   no se ha podido deducir el formato
*/
function deduceFormato(cifra, numDecimales){
	if(!numDecimales){
		numDecimales=2;
	}
	if(cifra!=undefined){
		//console&&console.log("cifra no es undefined");
		cifraTexto = ''+cifra;
		longitud = cifraTexto.length-1;
		indiceSeparadorDecimal = longitud-numDecimales
		if(indiceSeparadorDecimal>=0){
			//console&&console.log("hay un caracter en la posición del separador decimal");
			separadorDecimal = cifraTexto[indiceSeparadorDecimal]
		}else{
			separadorDecimal = null;
		}
		//El caracter situado a 2 posiciones del final de la cadena
		//Es el separador decimal (si lo hay), así podemos comprobar
		//si es '.'(EN) o ','(ES) y con ello obtenemos el formato
		if(separadorDecimal!=null && separadorDecimal=='.'){
			//console&&console.log("el separador decimal es un punto ("+separadorDecimal+")");
			return 'EN';
		}else{
			if(separadorDecimal!=null && separadorDecimal==','){
				//console&&console.log("el separador decimal es una coma ("+separadorDecimal+")");
				return 'ES';
			}else{
				//console&&console.log("En la posición de separador decimal no hay punto ni coma ("+separadorDecimal+")");
				//En caso de que no haya separador decimal, podemos probar a buscar varios separadores de miles
				//Si hay varios '.', quiere decir que es formato español, mientras que si hay varios ',' quiere
				// decir que es inglés
				
				//contamos las ocurrencias de puntos
				totalPuntos = cifraTexto.split(".").length - 1;
				totalComas = cifraTexto.split(",").length - 1;
				if(totalPuntos>1){
					//console&&console.log("Hay varios puntos ("+totalPuntos+")");
					return "ES";
				}
				
				if(totalComas>1){
					//console&&console.log("Hay varias comas ("+totalComas+")");
					return "EN";
				}
				
				if(totalPuntos==0 && totalComas==0){
					//console&&console.log("No hay puntos("+totalPuntos+") ni comas("+totalComas+") ");
					return "ANY";
				}
				if(totalPuntos>0){
					//console&&console.log("Hay algún punto ("+totalPuntos+")");
					//Partimos la cifra por puntos y comprobamos si el último segmento tiene una longitud de 3 dígitos
					//ya que si tienes 2 es parte decimal, pero si son 3 es un separador de miles, y como sabemos que
					//son puntos, deducimos que es formato español
					partes = cifraTexto.split(".");
					if(partes[partes.length-1].length==3){
						//console&&console.log("la última parte de la cifra separada por puntos ("+partes[partes.length-1]+") tiene una longitud de 3");
						return "ES"
					}else{
						//Si después del punto hay menos de 3 cifras quiere decir que es algo como 1.2 o 1.22, que son cifras inglesas
						//Si hubiera más, tal como 1.2222, también son inglesas
						return "EN"
					}
				}
				
				if(totalComas>0){
					//console&&console.log("Hay alguna coma ("+totalComas+")");
					//Partimos la cifra por puntos y comprobamos si el último segmento tiene una longitud de 3 dígitos
					//ya que si tienes 2 es parte decimal, pero si son 3 es un separador de miles, y como sabemos que
					//son comas, deducimos que es formato inglés
					partes = cifraTexto.split(",");
					if(partes[partes.length-1].length==3){
						//console&&console.log("la última parte de la cifra separada por comas ("+partes[partes.length-1]+") tiene una longitud de 3");
						return "EN"
					}else{
						//Si después de la coma hay menos de 3 cifras quiere decir que es algo como 1,2 o 1,22, que son cifras españolas
						//Si hubiera más, tal como 1,2222, también son españolas
						return "ES"
					}
				}
				//console&&console.log("Se ha llegado al final de la función sin deducir ningún formato");
				return "?";
			}
		}
	}else{
		//console&&console.log("cifra es undefined");
		return "?"
	}
	//console&&console.log("Se ha llegado al final de la función sin deducir ningún formato");
	return "?";
}

//Para formatear las fechas
function dateFormat(cellvalue, options, rowObject ){
	var d = new Date(cellvalue); 
	var curr_date = d.getDate();
	var curr_month = d.getMonth() + 1;
	var curr_year = d.getFullYear();
	var curr_hour = d.getHours(); 
	var curr_min = d.getMinutes();
	var fecha = curr_date + "/" + curr_month + "/" + curr_year + "-";
	var hora = curr_hour + ":" + curr_min; 
	return fecha + hora; 
}

function fechaFormat(cellvalue, options, rowObject ){
	if(cellvalue!=null){
		d=new Object();
		if((''+cellvalue).indexOf('-')>0){
			d.dia  = cellvalue.split('-')[2];
			d.mes  = cellvalue.split('-')[1];
			d.anio = cellvalue.split('-')[0];
		}else{
			var dateObj = new Date(cellvalue);
			d.dia  = dateObj.getDate();
			d.mes  = dateObj.getMonth() + 1;
			d.anio = dateObj.getFullYear();
		}
		
		var curr_date = rellenaCadena(d.dia, 2);
		var curr_month = rellenaCadena(d.mes, 2);
		var curr_year = rellenaCadena(d.anio, 4);
		var fecha = curr_date + "/" + curr_month + "/" + curr_year;
		return fecha; 
	}else{
		return "-"
	}
}

/**	Recibe un dato, una longitud y un elemento de relleno
 * 	añadirá relleno al principio de la cadena hasta que la longitud iguale o supere la indicada
 * 	si no se especifica relleno se usarán ceros 
 */
function rellenaCadena(dato, longitud, relleno){
	var strDato = ''+dato;
	if(relleno == undefined){
		relleno='0';
	}
	if(jQuery.type(longitud)=="number"){
		while(strDato.length<longitud){
			strDato = relleno+strDato;
		}
	}
	return strDato
}

function getSelectedIdFromJqGrid(jqGrid){
	var ids = $(jqGrid).getDataIDs()
	var selectedId = null;
	$(ids).each(
		function(indice,elemento){
			if($("#jqg_list_"+elemento)[0].checked){
				selectedId = elemento
				return false;
			}
		}
	);
	return selectedId;
}

function showLoadingJQgrid(tabla){
	divLoading = tabla.parent().parent().parent().parent().find("#load_"+tabla.get(0).p.id);
	if(divLoading.is(":animated")){
		divLoading.stop(true,true)
	}
	divLoading.slideDown();
}

function hideLoadingJQgrid(tabla){
	divLoading = tabla.parent().parent().parent().parent().find("#load_"+tabla.get(0).p.id);
	if(divLoading.is(":animated")){
		divLoading.stop(true,true)
	}
	divLoading.slideUp();
}

function openUrl(url){
	document.location.href = url;
}


//para remarcar la opcion seleccionada en el menu
function seleccionarMenu(){
	//elementos del menú principal (sin submenús)
	elementosDelMenu = $("ul.sf-menu > li");
	//url en la que está el navegador
	urlActual = window.location.href;

	//recorremos los elementos principales del menú
	for(var i = 0; i<elementosDelMenu.length; i++){
		//cada tag LI del menú, uno por cada vuelta del for
		elementoMenu = $(elementosDelMenu[i]);
		if(elementoMenu!=undefined){
			//Tags A de cada elemento LI del menú
			enlacesElementoMenu = elementoMenu.find("A");
			//Enlace visible del menú (al que se le aplicará el estilo si el o alguno de sus subMenús coincide en la búsqueda)
			enlaceTituloMenu	= enlacesElementoMenu[0];
			for(var j=0; j<enlacesElementoMenu.length; j++){
				//URL de cada tag A del LI actual
				urlElementoMenu = enlacesElementoMenu[j].href;
				if(urlActual.indexOf(urlElementoMenu)>=0){
					//si la URL que estamos comprobando es en la que estamos, se le aplica 
					//el class "Selected" al tag A titular del submenú que estamos comprobando
					$(enlaceTituloMenu).addClass("selected");
					return;
				}
			}
		}
	}
}


function cambiarComa(element){
	 var value = element.value;
	 if (value){
		    value = value.replace(",",".");
		    element.value = value;
		}
	 }

//funcion usada en los inputs para que no realice las búsquedas
//mientras estemos escribiendo
var timer = 750;
var timeout = 750;

function searchDelayed(field, button){	
	
	if (typeof timer != undefined)
		clearTimeout(timer);
	
	timer = setTimeout(function() {
		$('#' + button + '').click();
	}, timeout);
}	


function validateform(){
	var totalCampos=document.forms[0].elements.length;
	var formCompletado = true;
	validaFechaVencimientoAval(document.getElementById("fechaVencimiento"));
	validaPeriodicidadProrroga(document.getElementById("periodicidad"));
	for (i=0;i<totalCampos;i++) {
		if (document.forms[0].elements[i].type=="text" || document.forms[0].elements[i].type=="textarea" || document.forms[0].elements[i].tagName=="SELECT"){
			if (stringContains(document.forms[0].elements[i].className, "obligatorioInformado")){
				if (document.forms[0].elements[i].value==""){
					cambiaEstiloaObligatorio(document.forms[0].elements[i]);
					formCompletado = false;
				}
			}
			if (stringContains(document.forms[0].elements[i].className, "obligatorio")){
				if (document.forms[0].elements[i].value!=""){
					cambiaEstiloaInformado(document.forms[0].elements[i]);
				}
				else{
					formCompletado = false;
				}
			}
		}
	}
	//habilitar y deshabilitar botones
	for (i=0;i<totalCampos;i++) {
		if (document.forms[0].elements[i].type=="submit"){
			if (formCompletado){
				document.forms[0].elements[i].disabled=false;
				cambiaEstiloBotonaVerde(document.forms[0].elements[i]);
			}
			else{
				document.forms[0].elements[i].disabled=true;
				cambiaEstiloBotonaRojo(document.forms[0].elements[i]);
			}
		}
	}
	
}

function validateformDocumentos(){
	
	var totalCampos=document.forms[0].elements.length;
	var formCompletado = true;
	for (i=0;i<totalCampos;i++) {
		if (document.forms[0].elements[i].type=="text" || document.forms[0].elements[i].type=="file"){
			if (stringContains(document.forms[0].elements[i].className, "obligatorioInformado")){
				if (document.forms[0].elements[i].value==""){
					cambiaEstiloaObligatorio(document.forms[0].elements[i]);
					formCompletado = false;
				}
			}
			if (stringContains(document.forms[0].elements[i].className, "obligatorio")){
				if (document.forms[0].elements[i].value!=""){
					cambiaEstiloaInformado(document.forms[0].elements[i]);
				}
				else{
					formCompletado = false;
				}
			}
		}
	}
}

function stringContains(cadena,busqueda){
	if (cadena!=null && cadena.indexOf(busqueda) !== -1){
		return true;
	}
	else{
		return false;
	}
}

function cambiaEstiloaInformado(campo){
	$(campo).removeClass("obligatorio");
	$(campo).addClass("obligatorioInformado");
}

function cambiaEstiloaObligatorio(campo){
	$(campo).removeClass("obligatorioInformado");
	$(campo).addClass("obligatorio");
}

function cambiaEstiloBotonaVerde(campo){
	$(campo).removeClass("button-red");
	$(campo).addClass("button-green");
}

function cambiaEstiloBotonaRojo(campo){
	$(campo).removeClass("button-green");
	$(campo).addClass("button-red");
}

function desactivarCamposDeFormulario(formulario){
	inputs		= formulario.getElementsByTagName('input');
	selects		= formulario.getElementsByTagName('select');
	textAreas	= formulario.getElementsByTagName('textarea');
	fechas		= $(formulario).find(".fecha")
	for(var i=0;i<inputs.length;i++){
		$(inputs[i]).attr('readonly','readonly');
	}
	for(var i=0;i<textAreas.length;i++){
		$(textAreas[i]).attr('readonly','readonly');
	}
	for(var i=0;i<selects.length;i++){
		$(selects[i]).attr('disabled','disabled');
	}
	for(var i=0;i<fechas.length;i++){
		$(fechas[i]).removeClass("fecha");
	}
}

/**
 * Esta función recoge todos los elementos que contengan el atributo "Disabled" y les elimina dicho atributo
 * De esta forma, los campos deshabilitados se activan (son editables y envían datos al server al hacer submit())
 * 
 * @param	formulario El formulario en el que buscar los elementos deshabilitados para reactivarlos.
 * 			NOTA: si no recibe un formulario por parámetro, se hará la búsqueda y reactivación en toda la página
 */
function activaTodosCampos(formulario){
	if(formulario==undefined){
		var campos = $(document).find("[disabled]");
	}else{
		var campos = $(formulario).find("[disabled]");
	}
	campos.removeAttr("disabled");
}

/**
 * Esta función recibe un formulario por parámetro y le asigna al submit la función que reactiva sus campos deshabilitados
 * de esta manera, los campos que no se pueden editar, se activarán en el momento de submit y el server recibirá sus datos.
 * 
 * @param formulario El formulario que se tiene que reactivar al hacer submit
 */
function activaCamposOnSubmit(formulario){
	$(formulario).submit(
		function(){activaTodosCampos(formulario);}
	)
}


function buscar_combo(obj,objfoco){ 
		  var input="" + objfoco;
		  input = input.toLowerCase();
		  var output=obj.options;
		  for(var i=0;i<output.length;i++) {
		    if(output[i].text.toLowerCase().indexOf(input)>=0){
		      output[i].selected=true;
		    }
		    if(objfoco.value==''){
		      output[0].selected=true;
		    }
		  }
	}

function asignaCamposFecha(){
	$(getCamposFecha()).each(
		function(indice, elemento){
			var valorAnterior = elemento.value;
//			console&&console.log("asignando campo fecha nombre("+elemento.name+") valor("+elemento.value+")")
			$(elemento).datepicker();
			$(elemento).datepicker("option", "dateFormat", 			"dd/mm/yy" );
			$(elemento).datepicker("option", "dayNames",			["Domingo",	"Lunes",	"Martes",	"Miércoles",	"Jueves",	"Viernes",	"Sábado"	]);
			$(elemento).datepicker("option", "dayNamesShort",		["Dom",		"Lun",		"Mar",		"Mié",			"Jue",		"Vie",		"Sáb"		]);
			$(elemento).datepicker("option", "dayNamesMin",			["Dom",		"Lun",		"Mar",		"Mié",			"Jue",		"Vie",		"Sáb"		]);
			
			$(elemento).datepicker( "option", "monthNames",			["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"]);
			$(elemento).datepicker( "option", "monthNamesShort",	["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"]);
			
			$(elemento).datepicker( "option", "changeYear",	true);
			$(elemento).datepicker( "option", "yearRange",	"-100:+100");
			
			$(elemento).datepicker( "option", "nextText", "Siguiente" );
			$(elemento).datepicker( "option", "prevText", "Anterior" );
			
			if(valorAnterior!=undefined){
				$(elemento).datepicker('setDate', valorAnterior);
			}
			
			$(elemento).prop('readonly', true);
//			console&&console.log("\t finalizado: asignando campo fecha nombre("+elemento.name+") valor("+elemento.value+")\n\n")
		}
	)
}

function getCamposFecha(){
	var campos = $(document.forms).find("input");
	var camposFecha = new Array();
	$(campos).each(
		function(i,campo){
			if($(campo).hasClass("fecha")){
//				console&&console.log("Encontrado un campo tipo fecha ("+campo.name+") valor("+campo.value+")")
				camposFecha.push(campo);
			}
		}
	)
	return camposFecha;
}

function setMaxDate(campoFecha, maxDate){
//	console&&console.log("aplicando la fecha máxima:\n\t"+maxDate+"\nal campo:"+campoFecha.name);
	$(campoFecha).datepicker( "option", "maxDate", maxDate );
}

function setMinDate(campoFecha, minDate){
//	console&&console.log("aplicando la fecha mínima:\n\t"+minDate+"\nal campo:"+campoFecha.name);
	$(campoFecha).datepicker( "option", "minDate", minDate );
}

/**
* Esta función recoge todos los campos de tipo fecha (Datepicker)y compruba que sean parte de un tramo desde/hasta
* En caso de que lo sean, busca al complementario de cada campo y le asigna como tope (min/max) el valor
* de su complementario.
*/
function setTopesFechas(){
	//Recogemos todos los campos que funcionan con jQuery datepicker
	camposFechas = $(".hasDatepicker");
	//Los recorremos uno a uno
	$(camposFechas).each(function(ind,elem){
		
		//En caso de que contenga "Desde" se considera el principio de un tramo de fechas
		if(elem.name.indexOf('Desde')>0){
			//Deducimos el nombre del campo 'hasta' complementario de este 'desde'
			nombre			= elem.name; 
			nombreBase		= nombre.substring(0,nombre.indexOf('Desde'));
			nombreHasta		= nombreBase+'Hasta';
			campoFechaHasta	= document.getElementsByName(nombreHasta)[0];
			if(campoFechaHasta==undefined){
				campoFechaHasta = document.getElementById(nombreHasta);
			}
			//intentamos encontrar el campo 'hasta' complementario a partir del nombre deducido
			if(campoFechaHasta!=undefined){
				minDate = $(elem).datepicker( "getDate" )
				//si tenemos un valor en el campo 'desde' actual, lo aplicamos como mínimo a su campo 'hasta' complementario
				if(elem.value.length>0){
					setMinDate(campoFechaHasta, minDate);
				}
			}
		}else{
			//En caso de que contenga "Hasta" se considera el final de un tramo de fechas
			if(elem.name.indexOf('Hasta')>0){
				//Deducimos el nombre del campo 'desde' complementario de este hasta
				nombre			= elem.name; 
				nombreBase		= nombre.substring(0,nombre.indexOf('Hasta'));
				nombreDesde		= nombreBase+'Desde';
				campoFechaDesde	= document.getElementsByName(nombreDesde)[0];
				if(campoFechaDesde==undefined){
					campoFechaDesde = document.getElementById(nombreDesde);
				}
				//intentamos encontrar el campo 'desde' complementario a partir del nombre deducido
				if(campoFechaDesde!=undefined){
					maxDate = $(elem).datepicker( "getDate" )
					//si tenemos un valor en el campo 'hasta' actual, lo aplicamos como máximo a su campo 'desde' complementario
					if(elem.value.length>0){
						setMaxDate(campoFechaDesde, maxDate);
					}
				}
			}
		}
		//Aplicamos esta función al evento onchange de cada campo de forma que se recalculen los topes con cada cambio
		elem.onchange=setTopesFechas;
	})
}


function openUrl(url){
	document.location.href = url;
}


function marcaChecksPorIds(ids, tipoCheck, tabla, marcaOrigen){
	ids = removeNullsFromArray(ids);
	$(ids).each(function(i,id){
		
		//Intentamos obtener el check correspondiente a la tabla, id y tipo que dictan los parámetros
		//Pero es posible que el check no exista, ya que puede llegar una id de otra página
		cBox = getCheckBox(tabla,id,tipoCheck);
		
		if(cBox != undefined){//Si el id está en la página actual, lo marcamos según la marca de origen (el check masivo que pulsa el usuario)
			//Solo invocaremos el evento click del checkbox si la marca de este es distinta la marca de origen
			if($(cBox).is(":checked") != marcaOrigen){
				// Al invocar el evento click, hacemos un check tal cual se hace con el ratón, 
				// pasando sus validaciones y evitando el click si el check está deshabilitado
				cBox.click()
			}
		}else{//Si el check buscado es de otra página, insertamos el cambio en el array directamente
			
			//Creamos un nuevo objeto cambio para introducirlo en el array de cambios ya existente
			var cambio = {
		    		idOperacion			: null,
		    		checkProvisional	: null,
		    		checkDefinitivo		: null
		    };
			
			cambio.idOperacion = id;
			
			if(tipoCheck=='marcadoDefinitivo'){//Se ha pulsado el check para marcar todas las operaciones como definitivas
				
				if(marcaOrigen==true){
					cambio.checkDefinitivo	= true;
					cambio.checkProvisional	= false;
				}else{
					cambio.checkDefinitivo	= false;
					cambio.checkProvisional	= false;
				}
				
			}else{
				if(tipoCheck=='marcadoProvisional'){//Se ha pulsado el check para marcar todas las operaciones como provisionales
					
					if(marcaOrigen==true){
    					cambio.checkProvisional	= true;
    					cambio.checkDefinitivo	= false;
					}else{
						cambio.checkProvisional	= false;
						cambio.checkDefinitivo	= false;
					}
				
				}else{	//Este caso es teóricamente imposible. Pero se controla para prevenir errores debido a cambios posteriores
						//Se daría en caso de pulsar un check que no tenga el atributo "tipoCheck" con ninguno de los valores
						//esperados: "marcadoDefinitivo" o "marcadoProvisional"
	    			alert(message_error_marcado_check_inesperado);
	    			throw new Error(message_error_marcado_check_inesperado);
				}
			}
			
			//Esta función guarda el cambio realizando las validaciones necesarias.
			nuevoCambio(cambio);
		}
	});
}

function getCheckBox(tabla, idFila, nombreCheck){
	idTabla = tabla.prop('id');
	var cb = tabla.find("tr#"+idFila).find("td[aria-describedby='"+idTabla+"_"+nombreCheck+"']").find("input")[0];
	return cb;
}

function informaSiNoHayRegistros(tabla){
	if(tabla==undefined){
		tabla=this;
	}
	if(tabla.getRowData().length<=0){//en caso de que no haya resultados
		console&&console.log("mostrando reg0");
		idTabla = tabla.get(0).p.id
		console&&console.log("idTabla = "+idTabla);
		contenedor = $("#gbox_"+idTabla)
		console&&console.log("contenedor");
		console&&console.log(contenedor);
		divCargando = contenedor.find("#load_"+idTabla)
		console&&console.log("divcargando");
		console&&console.log(divCargando);
		mensajeNoRegistros = tabla.getGridParam('emptyrecords')
		
		console&&console.log("Mensaje sin registros: <<"+mensajeNoRegistros+">>");
		
		console&&console.log("asignando html");
		$(divCargando).html(mensajeNoRegistros);
		
		console&&console.log("apareciendo");
		
		setTimeout(function(){{divCargando:divCargando}divCargando.slideDown()},500)
		
	}else{
	}
}


function setChecksTabla(tabla){
	filas = tabla.getRowData();
	$(filas).each(
		function(i,fila){
			var deshabilitado	= (fila.editable == "false");
			var idFila			= fila.idOperacionContrato
			
			checkProvisional	= getCheckBox(tabla, idFila, 'marcadoProvisional');
			checkDefinitivo		= getCheckBox(tabla, idFila, 'marcadoDefinitivo');
			
			$(checkProvisional).prop('disabled', deshabilitado);
			$(checkDefinitivo).prop('disabled', deshabilitado);

			$(checkProvisional).prop('idFila',idFila);
			$(checkDefinitivo).prop('idFila',idFila);
			
			$(checkProvisional).prop('tipoCheck','marcadoProvisional');
			$(checkDefinitivo).prop('tipoCheck','marcadoDefinitivo');
			
			$(cambios).each(
				function(i, cambio){
					if(cambio.idOperacion==idFila){
						$(checkProvisional).attr('checked', cambio.checkProvisional)
						$(checkProvisional).attr('value', cambio.checkProvisional)
						$(checkDefinitivo).attr('checked', cambio.checkDefinitivo)
						$(checkDefinitivo).attr('value', cambio.checkDefinitivo)
					}
				}
			);
			
		}
	);
}

function setCheckBoxEventsTabla(tabla){
	filas = tabla.getRowData();
	$(filas).each(
		function(i,fila){
			var idFila			= fila.idOperacionContrato
			
			checkProvisional	= getCheckBox(tabla, idFila, 'marcadoProvisional');
			checkDefinitivo		= getCheckBox(tabla, idFila, 'marcadoDefinitivo');
			
			$(checkProvisional).unbind( "change" )
			$(checkDefinitivo).unbind( "change" )
			
			$(checkProvisional).change(clickCheckBox);
			$(checkDefinitivo).change(clickCheckBox);
		}
	);
}

function clickCheckBox(){
	
	idTabla = $(this).closest("table").attr('id');
	
	var checkProvisional	= $(getCheckBox($("#"+idTabla),$(this).prop('idFila'),"marcadoProvisional"));
	var checkDefinitivo		= $(getCheckBox($("#"+idTabla),$(this).prop('idFila'),"marcadoDefinitivo" ));
	
	//Comprobar si el check ha sido marcado (true), y en su caso, desmarcar el contrario
	if($(this).is(":checked")){
		
		var checkDesmarcar = null;
		
		if($(this)[0]==checkProvisional[0]){//Si se ha marcado el check de provisional, se debe desmarcar el definitivo
			checkDesmarcar = checkDefinitivo;
		}else{
    		if($(this)[0]==checkDefinitivo[0]){//Si se ha marcado el definitivo, se debe desmarcar el provisional
    			checkDesmarcar = checkProvisional;
    		}else{
    			alert(message_error_marcado_check_inesperado);
    			throw new Error(message_error_marcado_check_inesperado);
    		}
		}
		
		if((checkDesmarcar.is(":checked"))){
			$(checkDesmarcar).attr('checked',false);
    		$(checkDesmarcar).attr('value',false);
    		setTimeout(function(){$(checkDesmarcar).change();},200);	
		}
		
	}else{//si el check se está desmarcando, buscamos el check masivo correspondiente y lo desmarcamos
		var contenedorTabla = $("#gbox_"+idTabla);
		checkMasivo = contenedorTabla.find("[onclick='checkTodos(this)'][tipoCheck='"+$(this).prop("tipoCheck")+"']");
		checkMasivo.attr("checked", false);
	}
	
	var cambio = {
    		idOperacion			: null,
    		checkProvisional	: null,
    		checkDefinitivo		: null
    };
	
	cambio.idOperacion			= $(this).prop('idFila');
	cambio.checkProvisional		= $(checkProvisional).is(":checked");
	cambio.checkDefinitivo		= $(checkDefinitivo).is(":checked");
	
	nuevoCambio(cambio);
}

function nuevoCambio(cambio){
	$(cambios).each(
		function(i, cambioExistente){
			if(cambio.idOperacion==cambioExistente.idOperacion){
				cambios.splice(cambios.indexOf(cambioExistente),1)
			}
		}
	);
	cambios.push(cambio);
	
	$("#listMarcadoIFI").setGridParam({postData:{cambios:JSON.stringify(cambios)}});
	$("#listMarcadoICO").setGridParam({postData:{cambios:JSON.stringify(cambios)}});
	
}

function guardarCambios(cambio){
    $.ajax({
        type: "GET",
        url: url_multilaterales_marcado_guardado,
        data: 'cambios=' + JSON.stringify(cambios),
        success: function(data, textStatus, resp){
        	$("#mensajeGuardado")[0].className = 'success';
        	$("#mensajeGuardado").html(data);
        	
        	cambios = new Array();
        	
//			setTimeout(function(){$("#listMarcadoIFI").trigger('reloadGrid');}, 100);
//			setTimeout(function(){$("#listMarcadoICO").trigger('reloadGrid');}, 500);
			
			checksMasivos = $("[onclick='checkTodos(this)']");
			checksMasivos.attr("checked", false);
        	
        },
        error: function(resp, stat, errorThrown ){
        	$("#mensajeGuardado")[0].className = 'error';
        	$("#mensajeGuardado").html(resp.responseText);
        }
        
	});		
}