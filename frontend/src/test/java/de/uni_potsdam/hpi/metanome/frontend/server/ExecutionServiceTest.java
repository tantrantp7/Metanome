package de.uni_potsdam.hpi.metanome.frontend.server;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.uni_potsdam.hpi.metanome.algorithm_integration.configuration.ConfigurationValue;
import de.uni_potsdam.hpi.metanome.algorithm_integration.configuration.ConfigurationValueBoolean;
import de.uni_potsdam.hpi.metanome.algorithm_integration.configuration.ConfigurationValueSimpleRelationalInputGenerator;
import de.uni_potsdam.hpi.metanome.algorithm_integration.configuration.ConfigurationValueString;
import de.uni_potsdam.hpi.metanome.frontend.client.parameter.InputParameterBoolean;
import de.uni_potsdam.hpi.metanome.frontend.client.parameter.InputParameterCsvFile;
import de.uni_potsdam.hpi.metanome.frontend.client.parameter.InputParameterString;
import de.uni_potsdam.hpi.metanome.input.CsvFileGenerator;
import junit.framework.TestCase;

public class ExecutionServiceTest extends TestCase {
	
	ExecutionServiceImpl executionService = new ExecutionServiceImpl();
	InputParameterString stringParam = new InputParameterString("test");
	InputParameterBoolean boolParam = new InputParameterBoolean("boolean");
	InputParameterCsvFile csvParam = new InputParameterCsvFile("inputFile");
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testConvertToInputParameter() throws FileNotFoundException {
		//Setup
		csvParam.setFileNameValue(ClassLoader.getSystemResource("inputData").getPath() + "/inputA.csv");
		
		//Execute
		ConfigurationValue confString = executionService.convertToConfigurationValue(stringParam);
		ConfigurationValue confBool = executionService.convertToConfigurationValue(boolParam);
		ConfigurationValue confCsv = executionService.convertToConfigurationValue(csvParam);
		
		//Check
		assertTrue(confString instanceof ConfigurationValueString);
		assertTrue(confBool instanceof ConfigurationValueBoolean);
		assertTrue(confCsv instanceof ConfigurationValueSimpleRelationalInputGenerator);
	}
	
	@Test
	public void testBuildCsvFileGenerator() throws FileNotFoundException, IOException{		
		//Setup 
		boolean exception = false;
		csvParam.setFileNameValue("some/file/path");
		
		//Execute
		try {
			CsvFileGenerator generator = executionService.buildCsvFileGenerator(csvParam);
		} catch (FileNotFoundException e){
			exception = true;
		}

		//Check
		assertTrue(exception);
	}
}