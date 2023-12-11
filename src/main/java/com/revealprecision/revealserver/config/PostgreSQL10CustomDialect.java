package com.revealprecision.revealserver.config;

import org.hibernate.dialect.PostgreSQL10Dialect;
import com.vladmihalcea.hibernate.type.array.StringArrayType;

import java.sql.Types;

public class PostgreSQL10CustomDialect extends PostgreSQL10Dialect {

    public PostgreSQL10CustomDialect() {
        super();
        // Register custom types here
        this.registerHibernateType(Types.ARRAY, "string-array");
        // You can register more types if needed
    }
}
