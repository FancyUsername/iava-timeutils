package de.fancyusername.utils.time;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import de.fancyusername.model.MultiResource;
import de.fancyusername.model.io.Resources;
import de.fancyusername.utils.time.DateRange;

public class RangeTest {
	@Test
	public void rangeMapDemo() {
		RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
		rangeMap.put(Range.closed(1, 10), "foo"); // {[1, 10] => "foo"}
		rangeMap.put(Range.open(3, 6), "bar"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo"}
		rangeMap.put(Range.open(10, 20), "foo"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo", (10, 20) => "foo"}
		rangeMap.remove(Range.closed(5, 11)); // {[1, 3] => "foo", (3, 5) => "bar", (11, 20) => "foo"}
	}

	@Test
	public void rangeMap_MultiResource() throws JAXBException, IOException {
		Resources resources = Resources.class.cast(JAXBContext.newInstance(Resources.class).createUnmarshaller().unmarshal(getClass().getResourceAsStream("dates.xml")));
		RangeMap<Date, Integer> actualMap = DateRange.build(resources.getResource().get(0).getCalendar());
		RangeMap<Date, Integer> expectedMap = DateRange.build(resources.getResource().get(1).getCalendar());

		assertEquals(expectedMap, actualMap);
		assertEquals(6, (int) actualMap.asMapOfRanges().values().stream().max(Integer::compare).get());
		MultiResource multiResource = MultiResource.fromRangeMap(actualMap);
		assertEquals(IOUtils.toString(getClass().getResourceAsStream("expected"), "UTF8"), multiResource.toString());
	}
}
