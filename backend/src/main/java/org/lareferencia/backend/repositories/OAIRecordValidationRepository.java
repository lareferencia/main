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
package org.lareferencia.backend.repositories;


import java.util.List;

import org.lareferencia.backend.domain.NetworkSnapshot;
import org.lareferencia.backend.domain.OAIRecordValidationResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

/** 
 * 
 * @author lmatas
 * 
 */

@RepositoryRestResource(path = "validation", collectionResourceRel="validation")
public interface OAIRecordValidationRepository extends JpaRepository<OAIRecordValidationResult, Long> { 
	 
	  @Modifying
	  @Transactional
	  @Query("delete from OAIRecordValidationResult r where r.snapshot.id = ?1")
	  void deleteBySnapshotID(Long snapshot_id);
	 
	  @Query("select vr.field, count(*) from OAIRecordValidationResult vr where vr.snapshot.id = :snapshot_id group by vr.field")
	  List<Object[]> invalidRecordCountByField(@Param("snapshot_id") Long snapshot_id);
	  
	  @Query("select vr.field, count(*) from OAIRecordValidationResult vr where vr.snapshot.id = :snapshot_id and vr.record.repositoryDomain= :repository group by vr.field")
	  List<Object[]> invalidRecordRepositoryCountByField(@Param("snapshot_id") Long snapshot_id, @Param("repository") String repository);
	
	  @Query("select vr.record.repositoryDomain, count(*) from OAIRecordValidationResult vr where vr.snapshot.id = :snapshot_id group by vr.record.repositoryDomain")
	  List<Object[]> invalidRecordCountByRepository(@Param("snapshot_id") Long snapshot_id);  
	  
	  @Query("select vr.record.repositoryDomain, vr.field, count(*) from OAIRecordValidationResult vr where vr.snapshot.id = :snapshot_id group by vr.record.repositoryDomain, vr.field")
	  List<Object[]> invalidRecordCountByFieldAndRepository(@Param("snapshot_id") Long snapshot_id);
	  
	
}
