package com.pxy.studyhelper.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * User: Pxy(15602269883@163.com)
 * Date: 2016-02-04
 * Time: 23:38
 * FIXME
 */
public class Test extends BmobObject{

    private String  name;
    private BmobFile TestFile;
    private int sort1;
    private int sort2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BmobFile getTestFile() {
        return TestFile;
    }

    public void setTestFile(BmobFile testFile) {
        TestFile = testFile;
    }

    public int getSort1() {
        return sort1;
    }

    public void setSort1(int sort1) {
        this.sort1 = sort1;
    }

    public int getSort2() {
        return sort2;
    }

    public void setSort2(int sort2) {
        this.sort2 = sort2;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", TestFile=" + TestFile +
                ", sort1=" + sort1 +
                ", sort2=" + sort2 +
                '}';
    }
}
