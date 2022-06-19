package org.lordrose.capstonetest.domains.converters;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;
import org.lordrose.capstonetest.domains.constants.ReviewPeriodStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ReviewPeriodStatusPostgreEnum extends EnumType<ReviewPeriodStatus> {

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,
                            SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            st.setObject(index, value.toString(), Types.OTHER);
        }
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session,
                              Object owner) throws SQLException {
        String joined = Arrays.stream(names).collect(Collectors.joining(", ", "[", "]"));
        System.out.println(joined);
        
        return super.nullSafeGet(rs, names, session, owner);
    }
}
