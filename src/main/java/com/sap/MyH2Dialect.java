package com.sap;

import org.hibernate.dialect.H2Dialect;

public class MyH2Dialect extends H2Dialect {

    @Override
    public boolean dropConstraints() {
        return true;
    }

    @Override
    public boolean supportsIfExistsAfterAlterTable() {
        return true;
    }

}