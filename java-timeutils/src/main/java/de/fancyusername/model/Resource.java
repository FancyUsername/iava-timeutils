package de.fancyusername.model;

import de.fancyusername.utils.time.Timespan;

public class Resource {
	public void addDowntime(Timespan downtime) {
		addDowntime(downtime, 1);
	}
	
	public void addDowntime(Timespan downtime, int quantity) {
	}
}
