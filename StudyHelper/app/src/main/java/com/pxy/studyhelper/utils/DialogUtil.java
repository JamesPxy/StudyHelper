package com.pxy.studyhelper.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtil {
	private static ProgressDialog  progressDialog=null;
	public  static void showProgressDialog(Context context,String message){
		if(progressDialog==null){
			progressDialog=new ProgressDialog(context);
			progressDialog.setMessage(message);
			progressDialog.setCanceledOnTouchOutside(false);
			progressDialog.show();
		}
	}

	public static void closeProgressDialog(){
		if(progressDialog!=null){
			progressDialog.cancel();	
			progressDialog=null;
			//提醒垃圾回收机制回收
			System.gc();
		}
	}



}
