package com.parasoft.parabank.domain;

import static org.junit.Assert.*;

import java.math.*;

import org.junit.*;

import com.parasoft.parabank.domain.Account.*;
import com.parasoft.parabank.test.util.*;

/**
 * @req PAR-27
 *
 */
public class AccountTest extends AbstractBeanTestCase<Account> {
    private static final BigDecimal ONE_HUNDRED_DOLLARS = new BigDecimal(100.00);
    @Test
    public void testGetAndSetType() {
        assertNull(bean.getType());
        bean.setType(0);
        assertEquals(AccountType.CHECKING, bean.getType());
    }
    @Test
    public void testGetAvailableBalance() {
        bean.setBalance(ONE_HUNDRED_DOLLARS);
        assertEquals(ONE_HUNDRED_DOLLARS, bean.getAvailableBalance());

        bean.setBalance(BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, bean.getAvailableBalance());

        bean.setBalance(ONE_HUNDRED_DOLLARS.negate());
        assertEquals(BigDecimal.ZERO, bean.getAvailableBalance());
    }
    @Test
    public void testCredit() {
        bean.setBalance(BigDecimal.ZERO);

        bean.credit(ONE_HUNDRED_DOLLARS);
        assertEquals(100, bean.getBalance().intValue());

        bean.credit(ONE_HUNDRED_DOLLARS);
        assertEquals(200, bean.getBalance().intValue());
    }
    @Test
    public void testDebit() {
        bean.setBalance(ONE_HUNDRED_DOLLARS);

        bean.debit(ONE_HUNDRED_DOLLARS);
        assertEquals(0, bean.getBalance().intValue());

        bean.debit(ONE_HUNDRED_DOLLARS);
        assertEquals(-100, bean.getBalance().intValue());
    }
}
