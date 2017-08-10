package com.project.coffee.shop.utils;

import org.hamcrest.Matchers;
import org.junit.Test;

import static com.project.coffee.shop.utils.LoggerUtils.getFullClassName;
import static org.junit.Assert.assertThat;

public class LoggerUtilsTest {

    @Test
    public void getClassNameWhichInSomePackages() throws Exception {
        assertThat(getFullClassName(), Matchers.equalTo(LoggerUtilsTest.class.getName()));
    }

}
