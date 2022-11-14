package com.ctc.user.service.impl;

import com.ctc.user.mapper.AccountClassMapper;
import com.ctc.user.mapper.AccountMapper;
import com.ctc.user.pojo.Account;
import com.ctc.user.pojo.AccountClass;
import com.ctc.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-08 16:10
 * @version:1.0
 **/
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountClassMapper accountClassMapper;
    @Override
    public Account getAccountAndAccountClassAndUser (int id) {
        Account accountClassAndUser = accountMapper.getAccountAndAccountClassAndUser ( id );
        return accountClassAndUser;
    }

    @Override
    public int addAccount (Account account) {
        int i = accountMapper.addAccount(account);
        return i;
    }

    @Override
    public List<AccountClass> findAllAccountClass ( ) {
        List<AccountClass> allAccountClass = accountClassMapper.findAllAccountClass ( );
        return allAccountClass;
    }

    @Override
    public int updateAccount (Account account) {
        int i = accountMapper.updateAccount(account);
        return i;
    }

    @Override
    public int deleteUserAndAccount (int userId) {
        int i = accountMapper.deleteUserAndAccount(userId);
        return i;
    }

    @Override
    public Account getAccountByANameAndACid (String aName , String aCid) {
        Account account = accountMapper.getAccountByANameAndACid(aName,aCid);
        return account;
    }

    @Override
    public Account getAccountByUserId (Integer id) {
        Account account = accountMapper.getAccountByUserId(id);
        return account;
    }
}
