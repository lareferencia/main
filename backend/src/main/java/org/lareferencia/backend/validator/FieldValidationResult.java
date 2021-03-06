/*******************************************************************************
 * Copyright (c) 2013 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Lautaro Matas (lmatas@gmail.com) - Desarrollo e implementación
 *     Emiliano Marmonti(emarmonti@gmail.com) - Coordinación del componente III
 * 
 * Este software fue desarrollado en el marco de la consultoría "Desarrollo e implementación de las soluciones - Prueba piloto del Componente III -Desarrollador para las herramientas de back-end" del proyecto “Estrategia Regional y Marco de Interoperabilidad y Gestión para una Red Federada Latinoamericana de Repositorios Institucionales de Documentación Científica” financiado por Banco Interamericano de Desarrollo (BID) y ejecutado por la Cooperación Latino Americana de Redes Avanzadas, CLARA.
 ******************************************************************************/
package org.lareferencia.backend.validator;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldValidationResult {
	
	private String fieldName;
	private boolean mandatory;
	private boolean valid;
	private List<ContentValidationResult> results;
	//private Map<Integer,List<ContentValidationResult>> contentResultsByOccurrenceNumber;


	public FieldValidationResult() {
		results = new ArrayList<ContentValidationResult>();
	}

	public FieldValidationResult(boolean idValid, boolean isMandatory, String fieldName,
			List<ContentValidationResult> contentResults) {
		super();
		this.valid = idValid;
		this.mandatory = isMandatory;
		this.fieldName = fieldName;
		this.results = contentResults;
	}
	
	@Override
	public String toString() {
		
		String toStr = "\tfield valid=" + valid + "\tmandatory:" + mandatory + "\n";

		for ( ContentValidationResult cr: results ) {
			toStr += "\t" + cr.toString() + ":\n";
		}
		
		return toStr;
	}

}
