package com.konak.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flight")
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int flightId;
	
	private String source;
	
	private String destination;
	
	private String airline;
	
	private String price;
	
	private LocalDate date;
	
	private String time;
	
	

	public Flight(int flightId, String source, String destination, String airline, String price, LocalDate date,
			String time) {
		super();
		this.flightId = flightId;
		this.source = source;
		this.destination = destination;
		this.airline = airline;
		this.price = price;
		this.date = date;
		this.time = time;
	}
	
	public Flight(String source, String destination, String airline, String price, LocalDate date,
			String time) {
		super();
		this.source = source;
		this.destination = destination;
		this.airline = airline;
		this.price = price;
		this.date = date;
		this.time = time;
	}
	
	public Flight() {
		
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	@Override
	public String toString() {
		return "Flight [source=" + source + ", destination=" + destination + ", airline=" + airline + ", price=" + price
				+ ", date=" + date + ", time=" + time + "]";
	}

	
	
	

}
