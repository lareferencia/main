
package org.lareferencia.backend.domain;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Entity
@Getter
@Setter
public class NetworkSnapshot extends AbstractEntity {
	
	@Column(nullable = false)
	private SnapshotStatus status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date endTime;
	
	@Column(nullable = false)
	private Integer size;

	
	/** Atención, las one2many con colecciones grandes generan problemas con el flush/clean durante bulk inserts 
	 *  en los casos donde conviven en la misma session. La pertenencia al set evita que sean detacheados.
	 *  Por eso se evita el uso de la relación con los registros aquí, esto es un problema que hay que resolver. TODO  
	 **/
	//@OneToMany(cascade=CascadeType.ALL)
	//@JoinColumn(name="snapshot_id"/*, nullable=false*/)
	//private Collection<OAIRecord> records = new LinkedHashSet<OAIRecord>();
	
	public NetworkSnapshot() {
		super();
		this.status = SnapshotStatus.INITIALIZED;
		startTime = new DateTime().toDate();
		this.size = 0;
	}
	
	
}