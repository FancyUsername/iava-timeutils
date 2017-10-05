package de.fancyusername.model.io;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Resource {
	private String id;
	
	private List<CalendarEntry> calendar = new ArrayList<>();

	public Resource() {
	}

	public Resource(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement
	public List<CalendarEntry> getCalendar() {
		return calendar;
	}

	public void setCalendar(List<CalendarEntry> calendar) {
		this.calendar = calendar;
	}
}
