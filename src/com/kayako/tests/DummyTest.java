package com.kayako.tests;

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DummyTest{
	@Test
	public void testAlwaysSuccess(){
		int i =42;
		Assert.assertEquals(42,i);
	}
}

