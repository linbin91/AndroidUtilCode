package com.blankj.utilcode.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by yl1445 on 2017/11/1.
 */

public class CloneUtil {

	public static <T extends Serializable> T clone(T obj) {
		T cloneObj = null;
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
			objOut.writeObject(obj);
			objOut.close();

			ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			ObjectInputStream objIn = new ObjectInputStream(byteIn);
			try {
				cloneObj = (T)objIn.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cloneObj;
	}


//	class  InfoBean implements  Serializable{
//		public int age;
//		public int name;
//	}
//
//	class PeopleBean implements Serializable{
//		public String vipId;
//		public InfoBean infoBean;
//
//		@Override
//		public Object clone(){
//			return CloneUtil.clone(this);
//		}
//	}


	//或者

	class  InfoBean implements  Cloneable{
		public int age;
		public int name;

		@Override
		protected Object clone(){

			try {
				return super.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	class PeopleBean implements Cloneable{
		public String vipId;
		public InfoBean infoBean;

		@Override
		protected Object clone() {
			try {
				PeopleBean bean = (PeopleBean) super.clone();
				bean.infoBean = (InfoBean) infoBean.clone();
				return bean;
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}

			return null;
		}
	}

}
