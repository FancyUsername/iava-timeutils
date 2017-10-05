package de.fancyusername.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;

import de.fancyusername.utils.time.Timespan;

public class MultiResource extends Resource {
	public static MultiResource fromRangeMap(RangeMap<Date, Integer> uptimes) {
//		System.out.println("uptimes:\n\t" + uptimes.asMapOfRanges().entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining("\n\t")));
		MultiResource resource = new MultiResource(uptimes.asMapOfRanges().values().stream().max(Integer::compare).get());
		Date lastEnd = null;
		
		for (Entry<Range<Date>, Integer> entry : uptimes.asMapOfRanges().entrySet()) {
			if (lastEnd != null && lastEnd.compareTo(entry.getKey().lowerEndpoint()) < 0) {
				resource.addDowntime(new Timespan(lastEnd, entry.getKey().lowerEndpoint()), resource.size());
			}

			resource.addDowntime(new Timespan(entry.getKey().lowerEndpoint(), entry.getKey().upperEndpoint()), resource.size() - entry.getValue());
			lastEnd = entry.getKey().upperEndpoint();
		};
		
		return resource;
	}

	public void addDowntime(Timespan downtime, int quantity) {
		if (quantity > 0) {
			debugStringBuilder.append("added: " + downtime + ": " + quantity).append("\r\n");
			IntStream.range(0, quantity).forEach(i -> resources.get(i).addDowntime(downtime));
		}
	}

	private List<Resource> resources;
	
	private StringBuilder debugStringBuilder = new StringBuilder();

	public MultiResource(int count) {
		this.resources = new ArrayList<>(count);
		IntStream.range(0, count).forEach(i -> this.resources.add(new Resource()));
	}
	
	public int size() {
		return resources.size();
	}

	@Override
	public String toString() {
		return debugStringBuilder.toString();
//		return resources.stream().map(r -> r.toString()).collect(Collectors.joining("\n"));
	}
}
