package org.lordrose.capstonetest.domains.converters;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;
import org.lordrose.capstonetest.domains.constants.CovenantType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class CovenantTypePostgreEnum extends EnumType<CovenantType> {

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,
                            SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            st.setObject(index, value.toString(), Types.OTHER);
        }
    }
}
