package com.hero.spa.core.util;


import java.util.HashMap;

public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public R() {
		put("code", 0);
		put("msg", "success");
	}


	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}

	public static R ok(Object data) {
		R r = new R();
		r.put("data", data);
		return r;
	}

	public static R ok(String msg, Object data) {
		R r = new R();
		r.put("msg", msg);
		r.put("data", data);
		return r;
	}

	public static R ok() {
		return new R();
	}

}
