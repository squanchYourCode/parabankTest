package com.parasoft.parabank.domain.logic.impl;

import java.util.*;

import com.parasoft.parabank.domain.*;
import com.parasoft.parabank.domain.logic.*;

/**
 * Provides skeleton algorithm for determining loan approval
 */
public abstract class AbstractLoanProcessor implements LoanProvider {
    private AdminManager adminManager;

    public void setAdminManager(AdminManager adminManager) {
        this.adminManager = adminManager;
    }

    @Override
    public final LoanResponse requestLoan(LoanRequest loanRequest) {
        LoanResponse response = new LoanResponse();
        response.setResponseDate(new Date());
        response.setApproved(true);

        if (loanRequest.getDownPayment().compareTo(loanRequest.getAvailableFunds()) > 0) {
            response.setApproved(false);
            response.setMessage("error.insufficient.funds.for.down.payment");
            return response;
        }

        if (getQualifier(loanRequest) < getThreshold()) {
            response.setApproved(false);
            response.setMessage(getErrorMessage());
        }

        return response;
    }

    protected abstract double getQualifier(LoanRequest loanRequest);

    protected abstract String getErrorMessage();

    private double getThreshold() {
        String threshold = adminManager.getParameter("loanProcessorThreshold");
        return Integer.parseInt(threshold) / 100.0;
    }
}
