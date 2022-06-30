package com.example.demo.util;

import java.io.IOException;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

public class XlsDataSetLoader extends  AbstractDataSetLoader{

	protected IDataSet createDataSet(Resource resource) throws IOException, DataSetException {
	
		return new XlsDataSet(resource.getFile());
    }
}
