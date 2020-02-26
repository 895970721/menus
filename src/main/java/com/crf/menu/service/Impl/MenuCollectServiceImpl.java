package com.crf.menu.service.Impl;

import com.crf.menu.entity.MenuCollect;
import com.crf.menu.entity.User;
import com.crf.menu.enums.StatusCode;
import com.crf.menu.exception.MenuCollectException;
import com.crf.menu.mapper.MenuCollectMapper;
import com.crf.menu.service.MenuCollectService;
import com.crf.menu.utils.UserTokenUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuCollectServiceImpl implements MenuCollectService {

    @Autowired
    private MenuCollectMapper menuCollectMapper;

    @Autowired
    private UserTokenUtilImpl tokenUtil;

    @Override
    public void addCollect(String token, Integer menu_id) {
        User user = tokenUtil.getUser(token);
        MenuCollect menuCollect = menuCollectMapper.selectByUserIdAndMenuId(menu_id,user.getId());
        if (menuCollect != null)
        {
            throw new MenuCollectException(StatusCode.MenuCollectExit);
        }
        menuCollect = new MenuCollect();
        menuCollect.setMenuId(menu_id);
        menuCollect.setUserId(user.getId());
        menuCollectMapper.insertSelective(menuCollect);
    }

    @Override
    public void delCollect(String token, Integer menu_id) {
        User user = tokenUtil.getUser(token);
        MenuCollect menuCollect = menuCollectMapper.selectByUserIdAndMenuId(menu_id,user.getId());
        if (menuCollect == null)
        {
            throw new MenuCollectException(StatusCode.MenuCollectNoExit);
        }
        menuCollectMapper.deleteByPrimaryKey(menuCollect.getId());
    }

    @Override
    public List<MenuCollect> selectByUserId(Integer user_id) {
        return menuCollectMapper.selectByUserId(user_id);
    }
}
