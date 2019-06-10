package com.rock.serivce;

import com.rock.entities.User;
import com.rock.entities.Userinfo;

/**
 * 对User的相关操作
 */
public interface IUserService {
    public User login(User user);
    public boolean register(Userinfo userinfo);

    /**
     * Miami修改
     * @param userid
     * @param password
     * @param newpwd
     * @return
     */
    public boolean updatePassword(String userid,String password,String newpwd);
}
