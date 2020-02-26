package com.crf.menu.utils;

import com.crf.menu.entity.Role;
import com.crf.menu.entity.TokenEntity;
import com.crf.menu.enums.ExpTime;

public interface TokenUtil {
    public TokenEntity create(Role role, ExpTime expTime) throws Exception;

    public boolean verifie(String token);

    public boolean isExpire(String token);

    public String reSign(String token, ExpTime expTime);
}
