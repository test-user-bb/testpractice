package com.niraj.code.config;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigTest {

	@Test
	public void test() {
		AuctionConfig config = AuctionConfig.loadConfig();
		assertEquals(10, config.getAuctionCreateThreadsCount());
		System.out.println(config.getAuctionCreateThreadsCount());
	}

}
