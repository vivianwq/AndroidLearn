package com.wq.andoidlearning.pull;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParseByPull {
    //采用XmlPullParser来解析文件
    public static List<Student> getStudents(InputStream inputStream) throws XmlPullParserException, IOException {
        List<Student> students = null;
        Student student = null;

        //创建XmlPullParser
        XmlPullParser parser = Xml.newPullParser();
        //解析文件输入流
        parser.setInput(inputStream, "UTF-8");
        //得到当前的解析对象
        int eventType = parser.getEventType();
        //当解析工作还没完成时,调用next()方法得到下一个解析事件
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    //解析开始的时候初始化list
                    students = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    //获得解析器当前指向的元素的名字
                    //当指向元素的名字和id,name,sex这些属性重合时可以返回他们的值
                    String XPPname = parser.getName();
                    if ("student".equals(XPPname)) {
                        //通过解析器获取id的元素值,并设置一个新的Student对象的id
                        student = new Student();
                        student.setId(parser.getAttributeValue(0));
                    }
                    if (student != null) {
                        if ("name".equals(XPPname)) {
                            //得到当前指向元素的值并赋值给name
                            student.setName(parser.nextText());
                        }
                        if ("age".equals(XPPname)) {
                            //得到当前指向元素的值并赋值给age
                            student.setAge(new Short(parser.nextText()));
                        }
                        if ("sex".equals(XPPname)) {
                            //得到当前指向元素的值并赋值给sex
                            student.setSex(parser.nextText());
                        }
                    }
                    break;
                //触发结束元素事件
                case XmlPullParser.END_TAG:
                    if ("student".equals(parser.getName())) {
                        students.add(student);
                        student = null;
                    }
                    break;
                default:
                    break;

            }
            eventType = parser.next();
        }
        return students;
    }
}