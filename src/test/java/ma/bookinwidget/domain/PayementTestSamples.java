package ma.bookinwidget.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PayementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Payement getPayementSample1() {
        return new Payement().id(1L).firstName("firstName1").lastName("lastName1").email("email1").cardNum("cardNum1").cryptogram(1);
    }

    public static Payement getPayementSample2() {
        return new Payement().id(2L).firstName("firstName2").lastName("lastName2").email("email2").cardNum("cardNum2").cryptogram(2);
    }

    public static Payement getPayementRandomSampleGenerator() {
        return new Payement()
            .id(longCount.incrementAndGet())
            .firstName(UUID.randomUUID().toString())
            .lastName(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .cardNum(UUID.randomUUID().toString())
            .cryptogram(intCount.incrementAndGet());
    }
}
