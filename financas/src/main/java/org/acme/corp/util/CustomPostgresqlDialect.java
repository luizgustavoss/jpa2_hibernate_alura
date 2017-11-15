package org.acme.corp.util;

import org.hibernate.dialect.PostgreSQL95Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class CustomPostgresqlDialect extends PostgreSQL95Dialect {   
    public CustomPostgresqlDialect() {
        super();
        registerFunction("truncate_date",
        new SQLFunctionTemplate( StandardBasicTypes.TIMESTAMP, "DATE(?1)" ));
    }
}
