package com.rock.ssm.dao;

import com.rock.ssm.entities.Userlevel;
import com.rock.ssm.entities.UserlevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserlevelMapper {
    long countByExample(UserlevelExample example);

    int deleteByExample(UserlevelExample example);

    int deleteByPrimaryKey(Integer levelid);

    int insert(Userlevel record);

    int insertSelective(Userlevel record);

    List<Userlevel> selectByExample(UserlevelExample example);

    Userlevel selectByPrimaryKey(Integer levelid);

    int updateByExampleSelective(@Param("record") Userlevel record, @Param("example") UserlevelExample example);

    int updateByExample(@Param("record") Userlevel record, @Param("example") UserlevelExample example);

    int updateByPrimaryKeySelective(Userlevel record);

    int updateByPrimaryKey(Userlevel record);
}