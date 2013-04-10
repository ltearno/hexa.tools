package com.hexa.client.comm;

public @interface Cache {
	String table() default "";
	boolean inv() default false;
	boolean useCache() default true;
	String paramTable() default "";
}
