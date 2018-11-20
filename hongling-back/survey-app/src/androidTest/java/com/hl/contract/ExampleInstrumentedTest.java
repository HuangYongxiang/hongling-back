package com.hl.contract;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.hl.core.lib.network.util.MD5Util;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.hl.contract", appContext.getPackageName());
    }

    @Test
    public void userAppContext() throws  Exception{

        String data = "JY{data:{passWord:0000,userName:262000047},operatingTime:1525255617248,requestSourceCode:JY}";
        String stringMD5 = MD5Util.GetMD5Code(data);
        assertEquals("b7bcad711f5ee258273dc5e879813c7c",stringMD5);
    }
}
