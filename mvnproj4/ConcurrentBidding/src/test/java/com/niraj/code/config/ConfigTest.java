package com.niraj.code.config;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class ConfigTest {

	//@Test
	public void test() {
		AuctionConfig config = AuctionConfig.loadConfig();
		assertEquals(10, config.getAuctionCreateThreadsCount());
		System.out.println(config.getAuctionCreateThreadsCount());
	}

	@Test
	public void testDate() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentDateTime=df.format(new Date());
		currentDateTime+="999";
		System.out.println(currentDateTime);
	}

}
