package com.eleven.icloud.elevenzk;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

/**
 * @author by YingLong on 2021/11/22
 */
public class ZkTest {

    @Test
    public void generateSuperDigest() throws NoSuchAlgorithmException {
        String sId = DigestAuthenticationProvider.generateDigest("gj:test");
        System.out.println(sId);//  gj:X/NSthOB0fD/OT6iilJ55WJVado=
    }

}
