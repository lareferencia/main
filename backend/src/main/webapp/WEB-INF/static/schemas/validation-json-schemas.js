angular.module('validation.json.schemas', []).service('JSONValidationSchemas',  function() {
	
	
	this.validator_form = [   { "type": "section",
	                     	    "htmlClass": "row",
	                    	    "items": [
	                    	      {
	                    	        "type": "section",
	                    	        "htmlClass": "col-xs-6",
	                    	        "items": [
	                    	          "name"
	                    	        ]
	                    	      },
	                    	      {
	                    	        "type": "section",
	                    	        "htmlClass": "col-xs-6",
	                    	        "items": [
	                    	          "description"
	                    	        ]
	                    	      },
	                    	 
	                    	    ]
	                    	  },
	                    	  
	                    	  { type: "submit", title: "Guardar" }
	                        
	                       ];
	

	
	
	this.validator_schema = {
			type: "object",
		    properties: {
		      name : { type: "string", title: "Nombre", description: "" }, 
		      description : { type: "string", title: "Descripción", description: "" },
		    }
	};
	
	
	this.rule_data_form = [ "name",
	                        "description",
	                        "mandatory",
        			        { key: "quantifier", type: "select",
						        "titleMap": {
						        	"ZERO_ONLY" : "Ninguna", 
						        	"ONE_ONLY" : "Una y sólo una",
						        	"ZERO_OR_MORE" : "Cero o más", 
						        	"ONE_OR_MORE" : "Al menos una", 
						        	"ALL" : "Todas"
						        }
        			        }
    ];
	
	this.rule_data_schema = {
			type: "object",
		    properties: {
		      name :{ type: "string", title: "Nombre", description: "" }, 
		      description :{ type: "string", title: "Description", description: "" },
		      mandatory :{ type: "boolean", title: "¿Es obligatoria?", description: "La regla es determinante en la validez de registro" },
		      quantifier: {
		          title: "Cuantificador",
		          type: "string",
		          enum: [ "ZERO_ONLY", "ONE_ONLY", "ZERO_OR_MORE", "ONE_OR_MORE", "ALL"],
		          description: "¿Cuántas ocurrencias deben cumplir la regla?",
		          "default" : "ONE_OR_MORE"
		      }
		    }
	};
	
	
	
	var _RULES = [  
	
	{ 		
			name : "Validación por valores controlados",
			className: "org.lareferencia.backend.validation.validator.ControlledValueFieldContentValidatorRule",
			form: [ { "type": "help", "helpvalue": "Esta regla es válida si el campo contiene occurrencias con estos valores resulta válido</p>"}, 
			        "fieldname", "controlledValues", { type: "submit", title: "Guardar regla" }],
			schema: {
				type: "object",
				properties: {
					  fieldname :{ type: "string", title: "Campo", description: "El nombre del campo oai_dc. Ej: dc:type " }, 
				      controlledValues : { "type": "array", "title": "Valores Controlados", 
				          items: { "type": "string", "title":"valor" }
				      }
				}
			} /* fin schema */
	
	},
	
	
	{
		name: "Validación por valores controlados (large)",
		className: "org.lareferencia.backend.validation.validator.LargeControlledValueFieldContentValidatorRule",
		form: [ { "type": "help", "helpvalue": "Esta regla es válida si el campo contiene occurrencias con estos valores resulta válido</p>"}, 
		        "fieldname", { "key": "controlledValuesCSV", "type": "textarea" }, { type: "submit", title: "Guardar regla" }],
		schema: {
			    type: "object",
			    properties: {
			      fieldname :{ type: "string", title: "Campo", description: "El nombre del campo oai_dc. Ej: dc:type " }, 
			      controlledValuesCSV : {
			          type: "string",
			          title: "CSV Valores Controlados", 
			          description: "Texto con valores separados por ; sin espacios" 
			      }
			    }
		 } /* fin schema */
	},
		
	
	{ 
			name : "Validación por longitud de contenido",
			className: "org.lareferencia.backend.validation.validator.ContentLengthFieldContentValidatorRule",
			form: [ { "type": "help", "helpvalue": "Esta regla es válida si el campo contiene occurrencias de longitud entre un mínimo y un máximo</p>"}, 
			        "fieldname", "minLength", "maxLength", { type: "submit", title: "Guardar regla" }],
			schema: {
				type: "object",
				    properties: {
						fieldname :{ type: "string", title: "Campo", description: "El nombre del campo oai_dc. Ej: dc:type " }, 
				    	minLength :{ type: "integer", title: "Longitud mínima", description: "La longitud mímina aceptada" }, 
				    	maxLength :{ type: "integer", title: "Longitud máxima", description: "La longitud máxima aceptada" }, 
				    }
				} /* fin schema */
	},

	
	{ 
			name : "Validación por expresiones regulares",
			className: "org.lareferencia.backend.validation.validator.RegexFieldContentValidatorRule",
			form: [ { "type": "help", "helpvalue": "Esta regla es válida si el campo contiene occurrencias de longitud entre un mínimo y un máximo</p>"}, 
			        "fieldname",  { "key": "regexString", "type": "textarea" }, { type: "submit", title: "Guardar regla" }],
			schema: {
				type: "object",
				    properties: {
						fieldname :{ type: "string", title: "Campo", description: "El nombre del campo oai_dc. Ej: dc:type " }, 
				    	regexString : {
					          type: "string",
					          title: "Expresión regular", 
					          description: "Expresión regular que debe ser válida para la ocurrencia" 
					      }
				    }
				} /* fin schema */
	},
	
	{ 
		name : "Validación por expresiones de contenido",
		className: "org.lareferencia.backend.validation.validator.FieldExpressionValidatorRule",
		form: [ { "type": "help", "helpvalue": "Esta regla es válida si el contenido de los campos enunciados cumple la expresión booleana"}, 
		        { "key": "expression", "type": "textarea" }, { type: "submit", title: "Guardar regla" }],
		schema: {
			type: "object",
			    properties: {
			    	expression : {
				          type: "string",
				          title: "Expresión", 
				          description: "Es importante dejar espacios entre los paréntesis. \n Ej: ( dc:type=='info:eu-repo/semantics/article' AND dc:rights=%'info.+' ) OR ( dc:type=='info:eu-repo/semantics/bachelorThesis' )" 
				      }
			    }
			} /* fin schema */
}
]; //***** fin de _RULES ******/// 
	


	/** mapeo de las definiciones de reglas a un objeto **/
	var rules_defs_by_class  =  {};
	$.map( _RULES, function(rule, i ) { rules_defs_by_class[rule.className] = rule; });
	
	/** export del objeto con las definiciones de reglas **/
	this.ruleDefinitionByClassName = rules_defs_by_class;

	
	
});