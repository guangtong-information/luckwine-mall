package com.luckwine.trade.service.test.base.Profiles;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface UnitTestProfiles {
    String[] profiles() default {};
}
