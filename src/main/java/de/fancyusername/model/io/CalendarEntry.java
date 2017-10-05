package de.fancyusername.model.io;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class CalendarEntry {
	private String start;
	private String end;
	private int quantity;

	public CalendarEntry() {
	}

	public CalendarEntry(String start, String end, int quantity) {
		this.start = start;
		this.end = end;
		this.quantity = quantity;
	}

	@XmlAttribute
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	@XmlAttribute
	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@XmlAttribute
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
