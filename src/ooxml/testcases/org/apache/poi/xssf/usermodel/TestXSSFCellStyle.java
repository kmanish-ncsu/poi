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

package org.apache.poi.xssf.usermodel;

import junit.framework.TestCase;

import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STBorderStyle;


public class TestXSSFCellStyle extends TestCase {
	
	private StylesTable stylesTable;
	private CTBorder ctBorderA;
	private CTBorder ctBorderB;
	private CTXf cellStyleXf;
	private CTXf cellXf;
	private XSSFCellStyle cellStyle;

	public void setUp() {
		stylesTable = new StylesTable();
		
		CTStylesheet ctStylesheet = stylesTable._getRawStylesheet();
		
		// Until we do XSSFBorder properly, cheat
		ctBorderA = CTBorder.Factory.newInstance();
		long borderId = stylesTable.putBorder(ctBorderA);
		assertEquals(0, borderId);
		
		XSSFCellBorder borderB = new XSSFCellBorder();
		ctBorderB = borderB.getCTBorder();
		assertEquals(1, stylesTable.putBorder(borderB));
		
		cellStyleXf = ctStylesheet.addNewCellStyleXfs().addNewXf();
		cellStyleXf.setBorderId(0);
		cellXf = ctStylesheet.addNewCellXfs().addNewXf();
		cellXf.setXfId(0);
		cellStyle = new XSSFCellStyle(cellXf, cellStyleXf, stylesTable);
	}
	
	public void testGetBorderBottom() {		
		ctBorderA.addNewBottom().setStyle(STBorderStyle.THIN);
		assertEquals((short)1, cellStyle.getBorderBottom());
	}

	public void testGetBorderBottomAsString() {
		ctBorderA.addNewBottom().setStyle(STBorderStyle.THIN);
		assertEquals("thin", cellStyle.getBorderBottomAsString());
	}
	
	public void testGetBorderRight() {
		ctBorderA.addNewRight().setStyle(STBorderStyle.MEDIUM);
		assertEquals((short)2, cellStyle.getBorderRight());
	}

	public void testGetBorderRightAsString() {
		ctBorderA.addNewRight().setStyle(STBorderStyle.MEDIUM);
		assertEquals("medium", cellStyle.getBorderRightAsString());
	}
	
	public void testGetBorderLeft() {
		ctBorderA.addNewLeft().setStyle(STBorderStyle.DASHED);
		assertEquals((short)3, cellStyle.getBorderLeft());
	}

	public void testGetBorderLeftAsString() {
		ctBorderA.addNewLeft().setStyle(STBorderStyle.DASHED);
		assertEquals("dashed", cellStyle.getBorderLeftAsString());
	}
	
	public void testGetBorderTop() {
		ctBorderA.addNewTop().setStyle(STBorderStyle.HAIR);
		assertEquals((short)7, cellStyle.getBorderTop());
	}

	public void testGetTopBottomAsString() {
		ctBorderA.addNewTop().setStyle(STBorderStyle.HAIR);
		assertEquals("hair", cellStyle.getBorderTopAsString());
	}
}