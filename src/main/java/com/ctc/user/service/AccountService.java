package com.ctc.user.service;

import com.ctc.user.pojo.AccountClass;
import com.ctc.user.pojo.Account;

import java.util.List;

public interface AccountService {
    public Account getAccountAndAccountClassAndUser(int id);

    public List<AccountClass> findAllAccountClass();

    public int addAccount (Account account);

    int updateAccount (Account account);

    int deleteUserAndAccount (int userId);

    Account getAccountByANameAndACid (String aName , String aCid);

    Account getAccountByUserId (Integer id);
}
