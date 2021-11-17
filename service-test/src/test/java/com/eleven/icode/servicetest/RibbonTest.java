package com.eleven.icode.servicetest;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author by YingLong on 2021/10/18
 */
public class RibbonTest {

//    @Test
    public void listHashTest() {
        System.out.println(TimeUnit.SECONDS.toMillis(15L));
        System.out.println(RandomUtils.nextLong(0, TimeUnit.SECONDS.toMillis(15L)));
        List<String> servers = new ArrayList<>();
        servers.add("qweqweqweqw");
        servers.add("sdadasdasda");
        servers.add("czczzvzxvz");
        System.out.println();
        String serviceName = "czczzvzxvz";

        int index = servers.indexOf(serviceName);
        int lastIndex = servers.lastIndexOf(serviceName);

        int target = distroHash(serviceName) % servers.size();
        System.out.println(target);
        System.out.println(target >= index && target <= lastIndex);
    }

    private int distroHash(String serviceName) {
        return Math.abs(serviceName.hashCode() % Integer.MAX_VALUE);
    }

//    @Test
    public void testCalculateTimeIdx() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            long time = System.currentTimeMillis();
            calculateTimeIdx(time);
            Thread.sleep(100);
        }
    }

    private int calculateTimeIdx(/*@Valid*/ long timeMillis) {
        long timeId = timeMillis / 500;
        // Calculate current index so we can map the timestamp to the leap array.
        int index = (int)(timeId % 2);
        System.out.println("timeId =" + timeId + ", index=" + index + ", start=" + calculateWindowStart(timeMillis)+ ", timeMillis=" + calculateWindowStart(timeMillis));
        return index;
    }

    protected long calculateWindowStart(/*@Valid*/ long timeMillis) {
        return timeMillis - timeMillis % 500;
    }
}
