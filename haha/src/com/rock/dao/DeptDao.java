package com.rock.dao;

import com.rock.pojo.Dept;

public interface DeptDao {
	public Dept queryById(String deptno);
	public Dept queryByIdWithEmployees(String deptno);
}
