package ma.bookinwidget.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class BookingTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Booking getBookingSample1() {
        return new Booking()
            .id(1L)
            .clientFirstName("clientFirstName1")
            .clientLastName("clientLastName1")
            .rooms("rooms1")
            .ref("ref1")
            .clientEmail("clientEmail1")
            .clientPhone("clientPhone1")
            .adultNumber(1)
            .childNumber(1);
    }

    public static Booking getBookingSample2() {
        return new Booking()
            .id(2L)
            .clientFirstName("clientFirstName2")
            .clientLastName("clientLastName2")
            .rooms("rooms2")
            .ref("ref2")
            .clientEmail("clientEmail2")
            .clientPhone("clientPhone2")
            .adultNumber(2)
            .childNumber(2);
    }

    public static Booking getBookingRandomSampleGenerator() {
        return new Booking()
            .id(longCount.incrementAndGet())
            .clientFirstName(UUID.randomUUID().toString())
            .clientLastName(UUID.randomUUID().toString())
            .rooms(UUID.randomUUID().toString())
            .ref(UUID.randomUUID().toString())
            .clientEmail(UUID.randomUUID().toString())
            .clientPhone(UUID.randomUUID().toString())
            .adultNumber(intCount.incrementAndGet())
            .childNumber(intCount.incrementAndGet());
    }
}
