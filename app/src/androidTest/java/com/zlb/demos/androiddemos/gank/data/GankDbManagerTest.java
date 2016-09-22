package com.zlb.demos.androiddemos.gank.data;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.zlb.demos.androiddemos.gank.bean.CommenImage;
import com.zlb.demos.androiddemos.utils.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/19 下午1:51
 */
@RunWith(AndroidJUnit4.class)
public class GankDbManagerTest {

    private GankDbManager manager;
    private static final String TEST_URL = "http://test_url";

    @Before
    public void setup() {
        manager = GankDbManager.getInstance(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void cleanUp() {
        manager.deleteAllImage();
    }

    @Test
    public void testSaveImage() throws Exception {
        CommenImage image = new CommenImage();
        image.setStar(true);
        image.setUrl(TEST_URL);
        image.setLocalUrl("local://test_url");
        image.setDescription("des");
        image.setId("" + System.currentTimeMillis());
        image.setName("testName");

        manager.saveImage(image);
    }



    @Test
    public void testPreConditions() {
        assertNotNull(manager);
    }

    @Test
    public void testDeleteImage() throws Exception {

    }

    @Test
    public void testDeleteImageUrl() throws Exception {

    }

    @Test
    public void testUpdateImage() throws Exception {
        assertEquals(1, 1);
    }

    @Test
    public void testQueryImage() throws Exception {
        CommenImage image = new CommenImage();
        image.setStar(true);
        image.setUrl(TEST_URL);
        image.setLocalUrl("local://test_url");
        image.setDescription("des");
        image.setId("" + System.currentTimeMillis());
        image.setName("testName");

        manager.saveImage(image);

        CommenImage commenImage = manager.queryImage(TEST_URL);
        assertEquals(TEST_URL, commenImage.getUrl());
        Logger.d("image", "--" + commenImage);
    }


}