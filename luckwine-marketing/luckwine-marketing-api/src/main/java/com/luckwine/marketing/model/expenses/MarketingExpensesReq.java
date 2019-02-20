package com.luckwine.marketing.model.expenses;

import com.luckwine.marketing.model.base.MarketingExpenses;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class MarketingExpensesReq extends MarketingExpenses {


    private String createStartDate;

    private String createEndDate;


}
