package com.iny.opensoftware.application.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iny.opensoftware.domain.account.Account;
import com.iny.opensoftware.domain.account.AccountRepository;
import com.iny.opensoftware.domain.account.convert.AccountConverter;
import com.iny.opensoftware.domain.account.convert.impl.MyBatisAccountConverter;
import com.iny.opensoftware.domain.account.repository.impl.MybatisAccountRepository;
import com.iny.opensoftware.infrastructure.mybatis.dto.AccountObject;
import com.iny.opensoftware.infrastructure.mybatis.mapper.AccountMapper;

import io.jsonwebtoken.lang.Assert;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	private final AccountMapper mapper;
	
	private AccountConverter<AccountObject> converter;	
    private AccountRepository repository;

    @PostConstruct
    public void init() {
    	this.converter = new MyBatisAccountConverter();
    	this.repository = new MybatisAccountRepository(this.mapper, this.converter);
    }
    
    @Override	
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = this.repository.getOneAccountByAccountId(username);
           
            Assert.notNull(account, "계정이 없습니다.");
            
            List<GrantedAuthority> authList = new ArrayList<>();
            authList.add(new SimpleGrantedAuthority(account.getAuth().name()));
            
            return new User(account.getAccountId(), account.getPassword(), true, true, true, true, authList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}