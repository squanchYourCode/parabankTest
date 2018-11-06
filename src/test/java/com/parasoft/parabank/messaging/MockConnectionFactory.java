package com.parasoft.parabank.messaging;

import javax.jms.*;

public class MockConnectionFactory implements ConnectionFactory {
    public MockConnectionFactory() throws Exception {
        createConnection();
        createConnection(null, null);
    }

    @Override
    public Connection createConnection() throws JMSException {
        return null;
    }

    @Override
    public Connection createConnection(String userName, String password) throws JMSException {
        return null;
    }
}
