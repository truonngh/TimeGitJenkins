package time;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	void testGetTotalSecondsGood() {
		int seconds=Time.getTotalSeconds("05:05:05");
		assertTrue("The total seconds were not calculated properly",seconds==18305);
	}
	
	@Test
	void testGetTotalSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, 
				()->{Time.getTotalSeconds("10:00");});
	}
	@ParameterizedTest
	@ValueSource(strings = { "00:00:00", "23:59:59" })
	void testGetTotalSecondsBoundary(String candidate) {
		int tSeconds=Time.getTotalSeconds(candidate);
		assertTrue("The seconds were out of bound for a day",(0<=tSeconds&&tSeconds<=86399));
	}
	@Test
	void testGetSecondsGood() {
		int seconds=Time.getSeconds("12:12:12");
		assertTrue("The seconds were not calculated properly ", seconds==12);
	}
	@Test
	void testGetSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, 
				()->{Time.getSeconds("12:12:7");});
	}
	@ParameterizedTest
	@ValueSource(strings = { "00:00:00", "00:00:59" })
	void testGetSecondsBoundary(String candidate) {
		int seconds=Time.getSeconds(candidate);
		assertTrue("The seconds were fine ", 0<=seconds&&seconds<=59);
	}
	@Test
	void testGetTotalMinutesGood() {
		int minutes=Time.getTotalMinutes("12:12:12");
		assertTrue("The minutes were not calculated properly", minutes==12);
	}
	@Test
	void testGetTotalMinutesBad() {
		assertThrows(NumberFormatException.class, 
				()->{Time.getTotalMinutes("12:1:12");});
	}
	@ParameterizedTest
	@ValueSource(strings = { "00:59:00", "00:59:59" })
	void testGetTotalMinutesBoundary(String candidate) {
		int minutes=Time.getTotalMinutes(candidate);
		assertTrue("The minutes were not calculated properly", minutes==59);
	}

	@Test
	void testGetTotalHoursGood() {
		int hours=Time.getTotalHours("13:59:20");
		assertTrue("The minutes were not calculated properly", hours==13);
	}
	@Test
	void testGetTotalHoursBad() {
		assertThrows(NumberFormatException.class, 
				()->{Time.getTotalHours("1:1:12");});
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "02:00:00", "02:59:59" })
	void testGetTotalHoursBoundary(String candidate) {
		int hours=Time.getTotalHours(candidate);
		assertTrue("The minutes were not calculated properly", hours==2);
	}
	
	@Test
	void testGetMillisecondsGood() {
		int ms=Time.getMilliseconds("10:59:59:005");
		assertTrue("The Milliseconds were not calculated correctly", ms==5);
	}
	@Test
	void testGetMillisecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class,
				()->{Time.getMilliseconds("01:01:12:1");});
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "02:00:00:000", "02:59:59:999" })
	void testGetMillisecondBoundary(String candidate) {
		int ms=Time.getMilliseconds(candidate);
		assertTrue("The milliseconds were out of bound",ms>=0&&ms<=999);
	}
}
