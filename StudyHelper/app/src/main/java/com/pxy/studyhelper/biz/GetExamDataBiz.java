package com.pxy.studyhelper.biz;

import android.content.Context;

import com.pxy.studyhelper.entity.Test;
import com.pxy.studyhelper.utils.Tools;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * User: Pxy(15602269883@163.com)
 * Date: 2016-02-04
 * Time: 22:43
 * FIXME
 */
public class GetExamDataBiz {


    /**
     * 下载文件
     * @param context
     * @param fileName
     */
    public static void getExamData(final Context  context,String fileName){

//        BmobProFile.getInstance(context).download(fileName, new DownloadListener() {
//
//            @Override
//            public void onSuccess(String fullPath) {
//                // TODO Auto-generated method stub
//                Log.i("bmob","下载成功："+fullPath);
//            }
//            @Override
//            public void onProgress(String localPath, int percent) {
//                // TODO Auto-generated method stub
//                Log.i("bmob", "download-->onProgress :" + percent);
////                dialog.setProgress(percent);
//                DialogUtil.showProgressDialog(context,"当前进度:"+percent);
//            }
//
//            @Override
//            public void onError(int statuscode, String errormsg) {
//                // TODO Auto-generated method stub
//                DialogUtil.closeProgressDialog();
//                Log.i("bmob","下载出错："+statuscode +"--"+errormsg);
//            }
//        });


//        下载文件
//        查询数据时获取文件对象的示例代码如下（文件下载的具体代码实现需要大家自行实现）：
        BmobQuery<Test>  bmobQuery=new BmobQuery<>();
        bmobQuery.findObjects(context, new FindListener<Test>() {
            @Override
            public void onSuccess(List<Test> object) {
                // TODO Auto-generated method stub
                Tools.ShowToast(context, "查询成功：共" + object.size() + "条数据。");
                for (Test examData : object) {
                    if (examData.getTestFile() != null) {
                        //文件名称
                        examData.getTestFile().getFilename();
                        //文件下载地址
                       String  url= examData.getTestFile().getFileUrl(context);
                        Tools.ShowToast(context, url);
                    }
                }
            }

            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                Tools.ShowToast(context,msg);
            }
        });
    }



}  
