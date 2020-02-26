package com.crf.menu.service.Impl;

import com.crf.menu.dto.UserDTO;
import com.crf.menu.entity.User;
import com.crf.menu.enums.ExpTime;
import com.crf.menu.enums.StatusCode;
import com.crf.menu.exception.UserException;
import com.crf.menu.mapper.UserMapper;
import com.crf.menu.service.UserService;
import com.crf.menu.utils.FileUtil;
import com.crf.menu.utils.MD5Util;
import com.crf.menu.utils.UserTokenUtilImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTokenUtilImpl tokenUtil;

    @Value("${user.imagePath}")
    String userImagePath;

    @Value("${Image.menu}")
    String menuImagePath;

    @Override
    public User selectByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    @Override
    public String login(String username, String password)
    {
        User user = userMapper.selectByUserName(username);
        if(user == null)
        {
            log.error("用户名:" + username + "不存在");
            throw new UserException(StatusCode.UserNameNoExit);
        }
        if(!MD5Util.encode(password).equals(user.getPassword()))
        {
            log.error("用户名:" + username + "的密码错误");
            throw new UserException(StatusCode.UserPasswordError);
        }
        String token = tokenUtil.create(user, ExpTime.OneDay).getToken();

        return token;
    }

    @Override
    public Integer register(String username,String password)
    {
        if(userMapper.selectByUserName(username) != null)
        {
            throw new UserException(StatusCode.UserNameExit);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Util.encode(password));
        user.setPhotoAddress("default.jpg");
        user.setNickName("厨房小生");
        Integer insCnt = userMapper.insertSelective(user);
        return insCnt;
    }

    @Override
    public Integer updNickName(String token,String nickname)
    {
        Integer updCnt = 0;
        User user = tokenUtil.getUser(token);
        if(nickname.equals(user.getNickName()))
        {
            return updCnt;
        }

        user.setNickName(nickname);
        updCnt = userMapper.updateByPrimaryKeySelective(user);
        return updCnt;
    }

    @Override
    public HashMap uploadImage(User user,MultipartFile file)
    {
        String imageName = FileUtil.MupFileMoveTo(file,userImagePath);
        if(!"default.jpg".equals(user.getPhotoAddress())){
            String old_photo = user.getPhotoAddress();
            File oldFile = new File(userImagePath+old_photo);
            if(oldFile.delete()){
                log.info("用户旧照片:"+old_photo+"删除成功");
            }
        }
        user.setPhotoAddress(imageName);
        Integer updCnt = userMapper.updateByPrimaryKeySelective(user);
        HashMap hashMap = new HashMap();
        hashMap.put("updCnt",updCnt);
        hashMap.put("imagePath",imageName);
        return hashMap;
    }

    @Override
    public UserDTO getInfo(String token) {
        User user = tokenUtil.getUser(token);

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        if(user.getPhotoAddress() == null){
            BeanUtils.copyProperties(user,userDTO);
        }else{
            BeanUtils.copyProperties(user,userDTO);
            userDTO.setPhotoAddress(menuImagePath+"user/"+user.getPhotoAddress());
        }
        return userDTO;
    }

    @Override
    public User selectByUserId(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
