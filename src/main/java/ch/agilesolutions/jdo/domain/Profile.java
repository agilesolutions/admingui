package ch.agilesolutions.jdo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Profile {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	
	private String domain, name, host, environment, ticket;
	
	public Profile() {}

	public Profile(String domain, String name, String host, String environment, String ticket) {
		super();
		this.domain = domain;
		this.name = name;
		this.host = host;
		this.environment = environment;
		this.ticket = ticket;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
}
