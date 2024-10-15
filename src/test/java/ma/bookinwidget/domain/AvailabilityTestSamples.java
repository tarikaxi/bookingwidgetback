package ma.bookinwidget.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AvailabilityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Availability getAvailabilitySample1() {
        return new Availability().id(1L).country("country1").adultNumber(1).childNumber(1).flexibilityOnDays(1);
    }

    public static Availability getAvailabilitySample2() {
        return new Availability().id(2L).country("country2").adultNumber(2).childNumber(2).flexibilityOnDays(2);
    }

    public static Availability getAvailabilityRandomSampleGenerator() {
        return new Availability()
            .id(longCount.incrementAndGet())
            .country(UUID.randomUUID().toString())
            .adultNumber(intCount.incrementAndGet())
            .childNumber(intCount.incrementAndGet())
            .flexibilityOnDays(intCount.incrementAndGet());
    }
}
