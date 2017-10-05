package de.fancyusername.utils.time;

import java.util.Date;

public class Timespan implements Comparable<Timespan> {
	private Date start;

	private Date end;

	public Timespan() {
	}

	public Timespan(Date start, Date end) {
		this.start = start;
		this.end = end;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int compareTo(Timespan o) {
		return start.compareTo(o.start);
	}

	public boolean intersects(Date date) {
		return date.compareTo(start) >= 0 && date.compareTo(end) < 0;
	}

	public long duration() {
		return end.getTime() - start.getTime();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Timespan o = (Timespan) obj;
		return start.equals(o.start) && end.equals(o.end);
	}

	@Override
	public String toString() {
		return "Timespan [start=" + start + ", end=" + end + "]";
	}
}
