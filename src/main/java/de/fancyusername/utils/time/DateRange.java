package de.fancyusername.utils.time;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import de.fancyusername.model.io.CalendarEntry;

public class DateRange {
	public static Range<Date> createHrMin(String string) {
		String[] strings = string.split("-");
		return createHrMin(strings[0], strings[1]);
	}

	public static Range<Date> createHrMin(String start, String end) {
		return Range.openClosed(TimeUtils.parseHrMin(start), TimeUtils.parseHrMin(end));
	}

	public static RangeMap<Date, Integer> build(List<CalendarEntry> calendar) {
		RangeMap<Date, Integer> rangeMap = TreeRangeMap.create();
		calendar.stream().forEach(entry -> {
			Range<Date> range = DateRange.createHrMin(entry.getStart(), entry.getEnd());

			if (entry.getQuantity() > 0) {
				rangeMap.put(range, entry.getQuantity());
			}
			else {
				rangeMap.remove(range);
			}
		});
		return rangeMap;
	}
}
