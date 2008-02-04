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

package org.apache.poi.xssf.io;

import java.io.File;

import junit.framework.TestCase;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TestLoadSaveXSSF extends TestCase {

    String filename;

    protected void setUp() throws Exception {
        super.setUp();
        filename = System.getProperty("HSSF.testdata.path");
    }
    
    public void testLoadSample() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(new File(filename, "sample.xlsx").getAbsolutePath());
        assertEquals(3, workbook.getNumberOfSheets());
        assertEquals("Sheet1", workbook.getSheetName(0));
    }

}