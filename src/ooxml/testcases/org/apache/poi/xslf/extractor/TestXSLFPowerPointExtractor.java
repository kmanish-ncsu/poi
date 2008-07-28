/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
package org.apache.poi.xslf.extractor;

import java.io.File;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xslf.XSLFSlideShow;

import junit.framework.TestCase;

/**
 * Tests for HXFPowerPointExtractor
 */
public class TestXSLFPowerPointExtractor extends TestCase {
	/**
	 * A simple file
	 */
	private XSLFSlideShow xmlA;
	private File fileA;

	protected void setUp() throws Exception {
		super.setUp();
		
		fileA = new File(
				System.getProperty("HSLF.testdata.path") +
				File.separator + "sample.pptx"
		);
		assertTrue(fileA.exists());
		
		xmlA = new XSLFSlideShow(fileA.toString());
	}

	/**
	 * Get text out of the simple file
	 */
	public void testGetSimpleText() throws Exception {
		new XSLFPowerPointExtractor(xmlA);
		new XSLFPowerPointExtractor(
				POIXMLDocument.openPackage(fileA.toString()));
		
		XSLFPowerPointExtractor extractor = 
			new XSLFPowerPointExtractor(xmlA);
		extractor.getText();
		
		String text = extractor.getText();
		assertTrue(text.length() > 0);
		
		// Check Basics
		assertTrue(text.startsWith("Lorem ipsum dolor sit amet\n"));
		assertTrue(text.endsWith("amet\n\n"));
		
		// Just slides, no notes
		text = extractor.getText(true, false);
		assertEquals(
				"Lorem ipsum dolor sit amet\n" +
				"Nunc at risus vel erat tempus posuere. Aenean non ante.\n" +
				"\n" +
				"Lorem ipsum dolor sit amet\n" +
				"Lorem\n" +
				"ipsum\n" +
				"dolor\n" +
				"sit\n" +
				"amet\n" +
				"\n", text
		);
		
		// Just notes, no slides
		text = extractor.getText(false, true);
		assertEquals(
				"\n\n\n\n", text
		);
		
		// Both
		text = extractor.getText(true, true);
		assertEquals(
				"Lorem ipsum dolor sit amet\n" +
				"Nunc at risus vel erat tempus posuere. Aenean non ante.\n" +
				"\n\n\n" +
				"Lorem ipsum dolor sit amet\n" +
				"Lorem\n" +
				"ipsum\n" +
				"dolor\n" +
				"sit\n" +
				"amet\n" +
				"\n\n\n", text
		);
		
		// Via set defaults
		extractor.setSlidesByDefault(false);
		extractor.setNotesByDefault(true);
		text = extractor.getText();
		assertEquals(
				"\n\n\n\n", text
		);
	}
}