package com.stolser.javatraining.collections.treemap;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CustomTreeMapTest {

    private CustomTreeMap<Integer, String> map;

    @Before
    public void setup() {
        map = new CustomTreeMap<>();
    }

    @Test
    public void put_Should_IncreaseSizeByOne() throws Exception {
        assertEquals(0, map.size());

        map.put(10, "ten");
        assertEquals(1, map.size());

        map.put(100, "a hundred");
        assertEquals(2, map.size());


    }
}