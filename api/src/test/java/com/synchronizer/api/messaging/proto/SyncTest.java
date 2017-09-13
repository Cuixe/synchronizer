package com.synchronizer.api.messaging.proto;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.Instant;

public class SyncTest {

    @Test
    public void pingTest() throws Exception {
        Sync.Ping.Builder pingBuilder = Sync.Ping.newBuilder();
        pingBuilder.setPingSequence(1);

        Sync.SyncProtocol.Builder syncBuilder = Sync.SyncProtocol.newBuilder();
        syncBuilder.setHeader(getHeader());
        syncBuilder.setPing(pingBuilder);
        Sync.SyncProtocol syncProtocol = syncBuilder.build();

        ByteArrayOutputStream outputStream = serialize(syncProtocol);
        Sync.SyncProtocol syncProtocolTmp = deserialize(outputStream);
        Assert.assertEquals(syncProtocol.getHeader().getId(), syncProtocolTmp.getHeader().getId());
        Assert.assertEquals(syncProtocol.getPing().getPingSequence(), syncProtocolTmp.getPing().getPingSequence());
        switch (syncProtocol.getMessageCase()) {
            case PING:
                break;
            default:
                Assert.fail();
        }
    }

    private Sync.Header.Builder getHeader() {
        Sync.Header.Builder headerBuilder = Sync.Header.newBuilder();
        headerBuilder.setId(1);
        headerBuilder.setSequence(100);
        headerBuilder.setTimestamp(System.currentTimeMillis());
        headerBuilder.setNanos(System.nanoTime());
        return headerBuilder;
    }

    @Test
    public void cicles() {
        int cicles = 100;
        int system[] = new int[cicles];
        int instant[] = new int[cicles];

        for(int i = 0; i< cicles; i++) {
            system[i] = calcCiclesPerMilliOnSystem();
        }
        for(int i = 0; i< cicles; i++) {
            instant[i] = calcCiclesPerMilliUsingInstant();
        }
        System.out.println("SYSTEM ");
        int sum = 0;
        for(int i = 0; i< cicles; i++) {
            sum += system[i];
        }
        System.out.println("PROMEDIO: " + (sum/cicles));
        System.out.println("\nINSTANT ");
        sum = 0;
        for(int i = 0; i< cicles; i++) {
            sum += instant[i];
        }
        System.out.println("PROMEDIO: " + (sum/cicles));
    }

    public int calcCiclesPerMilliOnSystem() {
        int i=0;
        long init= System.currentTimeMillis();
        long end = init;
        while(init == end) {
            i++;
            end = System.currentTimeMillis();
        }
        return i;
    }

    public int calcCiclesPerMilliUsingInstant() {
        int i=0;
        long init = Instant.now().toEpochMilli();
        long end = init;
        while(init == end) {
            i++;
            end = Instant.now().toEpochMilli();
        }
        return i;
    }

    private ByteArrayOutputStream serialize(Sync.SyncProtocol syncProtocol) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        syncProtocol.writeTo(stream);
        return stream;
    }

    private Sync.SyncProtocol deserialize(ByteArrayOutputStream outputStream) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return Sync.SyncProtocol.parseFrom(inputStream);
    }

}