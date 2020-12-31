package com.api.db.mysql.daos;

import com.tester.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

import com.api.model.TestDate;
public class DataDao {
    SqlSession session=null;
    {
        try {
            session = DatabaseUtil.getSqlSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<TestDate> selectTestDataListById(String id) {
        List<TestDate> testDate =session.selectList("testDataId",id);
        return testDate;
    }
}
