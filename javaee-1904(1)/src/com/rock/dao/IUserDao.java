package com.rock.dao;

import com.rock.entities.User;
import com.rock.entities.Userinfo;

/**
 * 对User用户信息操作的对象
 */
public interface IUserDao {
    public User isTrue(String userid,String password);

    public  boolean register(Userinfo userinfo);

    boolean updatePwd(String userid, String password, String newpwd);
}
