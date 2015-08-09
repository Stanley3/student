package com.chejiawang.android.studentclient.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.chejiawang.android.studentclient.app.Logger;


public class FileUtils {
	private static final String TAG = "FileUtils";
	private static final int BUFFER = 8192;
	private static final long ONE_DAY_MILLIS = 0x5265c00L;

	/**
	 * 写文件
	 * 
	 * @param file
	 * @param data
	 * @throws Exception
	 */
	public static void writeFile(File file, byte[] data) throws Exception {
		if (file != null && data != null) {
			OutputStream fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);
			dos.write(data);
			if (dos != null) {
				dos.close();
			}
		}
	}

	public static void move(File file, File file1) throws IOException {
		copy(file, file1);
		delete(file);

	}

	public static void copy(File source, File target) throws IOException {

		if (source != null && !source.exists()) {
			Logger.e(
					TAG,
					"the source file is not exists: "
							+ source.getAbsolutePath());
		} else if (source.isFile()) {
			copyFile(source, target);
		} else {
			copyDirectory(source, target);
		}
	}
	/**
	 * 复制文件
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void copyFile(File source, File target) throws IOException {
		if (source != null && target != null) {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(source));
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(target));

			try {
				byte[] buffer = new byte[BUFFER];
				int i = -1;
				while ((i = bis.read(buffer)) != -1) {
					bos.write(buffer, 0, i);
				}
				bos.flush();
			} finally {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			}
		}
	}
	/**
	 * 复制文件夹
	 * @param sourceDir
	 * @param targetDir
	 * @throws IOException
	 */
	public static void copyDirectory(File sourceDir, File targetDir) throws IOException {
		targetDir.mkdirs();
		if( sourceDir != null){
			File[] f = sourceDir.listFiles();
			for(int i = 0; i< f.length; i++){
				if( f[i].isFile()){
					copyFile(f[i], new File(new StringBuffer(String.valueOf(targetDir.getAbsolutePath())).append(File.separator).append(f[i].getName()).toString()));
				}else if( f[i].isDirectory()){
					copyDirectory(new File(sourceDir, f[i].getName()), new File(targetDir, f[i].getName()));
				}
			}
		}
	}
	/**
	 * 删除
	 * @param file
	 */
	public static boolean delete(File file) {
		boolean flag;
		if(file != null && !file.exists()){
			return false;
		}else if( file != null && file.isFile()){
			flag = deleteFile(file);
		}else 
			flag = deleteDirectory(file, true);
		return flag;
	}

	public static boolean deleteDirectory(File file, boolean flag) {

		return deleteDirectory(file, null, flag, false);
	}

	public static boolean deleteDirectory(File file, String s, boolean flag, boolean flag1){
		if(file == null){
			return false;
		}
		if( !file.exists() || !file.isDirectory()){
			return false;
		}
		boolean flag3 = true;
		File[] f = file.listFiles();
		for(int i = 0; i < f.length; i++){
			if(f[i].isFile()){
				if( s == null || f[i].getName().toLowerCase().endsWith("." + s.toLowerCase())){
					flag3 = deleteFile(f[i]);
					if( !flag3 )
						break;
				}
			}else{
				if( !flag1){
					flag3 = deleteDirectory(f[i], true);
					if( !flag3)
						break;
				}
			}
		}
		if( !flag3){
			Logger.e(TAG, "delete directory file: " + file.getAbsolutePath());
		}else if(flag){
			if( file.delete()){
				return true;
			}else{
				Logger.e(TAG, "delete directory file: " + file.getAbsolutePath());
			}
		}else{
			return true;
		}
		return false;
	}
	public static boolean deleteFile(File file) {
		if(file != null && file.isFile() && file.exists()){
			file.delete();
			return true;
		}
		return false;
	}

	public static byte[] readFileToByte(File file) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int)file.length());
		InputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		try {
			int i = -1;
			byte[] buffer = new byte[1024];
			while( (i = bis.read(buffer, 0, 1024)) != -1){
				bos.write(buffer, 0, i);
			}
			return bos.toByteArray();
		} finally{
			if(bis != null){
				bis.close();
			}
			if(bos != null){
				bos.close();
			}
		}
		
	}
	
}
