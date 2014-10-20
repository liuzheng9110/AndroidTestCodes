package com.example.androidtest.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

public class FileUtils {
	public final static String CACHE = "/taxoffice/files/";

	public final static String GWGL_SECOND_FILE_PATH = "gwlg/";
	public final static String QLYX_SECOND_FILE_PATH = "qlyx/";

	public final static String QLYX_TODO = "/todo/";
	public final static String QLYX_SUSPEND = "/suspend/";
	public final static String QLYX_ENDO = "/endo/";
	public final static String QLYX_XZSX = "/xzsx/";

	public final static String WORKLOG_SECOND_FILE_PATH = "worklog/";
	public final static String EMAIL_SECOND_FILE_PATH = "email/";
	public final static String NOTICE_SECOND_FILE_PATH = "notice/";


	/**
	 * Function:通过uri获取文件路径，区分4.4以后和4.4以前分别获取
	 * 
	 * @author yinjingxiang
	 * @created 2014-9-18 13:01:39
	 */
	public static String getPath(final Context context, final Uri uri) {

		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

		// DocumentProvider
		if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
			// ExternalStorageProvider
			if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/"
							+ split[1];
				}

				// TODO handle non-primary volumes
			}
			// DownloadsProvider
			else if (isDownloadsDocument(uri)) {

				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(id));

				return getDataColumn(context, contentUri, null, null);
			}
			// MediaProvider
			else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}

				final String selection = "_id=?";
				final String[] selectionArgs = new String[] { split[1] };

				return getDataColumn(context, contentUri, selection,
						selectionArgs);
			}
		}
		// MediaStore (and general)
		else if ("content".equalsIgnoreCase(uri.getScheme())) {

			// Return the remote address
			if (isGooglePhotosUri(uri))
				return uri.getLastPathSegment();

			return getDataColumn(context, uri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}

		return null;
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 * 
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @param selection
	 *            (Optional) Filter used in the query.
	 * @param selectionArgs
	 *            (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	public static String getDataColumn(Context context, Uri uri,
			String selection, String[] selectionArgs) {

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = { column };

		try {
			cursor = context.getContentResolver().query(uri, projection,
					selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				final int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri
				.getAuthority());
	}

	/**
	 * Function:通过uri获取文件路径
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-24 13:01:39
	 */
	public static String getPath0(Context context, Uri uri) {
		String filePath = "";
		if (DocumentsContract.isDocumentUri(context, uri)) {
			String docId = DocumentsContract.getDocumentId(uri);
			final String[] split = docId.split(":");
			final String type = split[0];
			String[] column = { MediaStore.Images.Media.DATA };
			String sel = MediaStore.Images.Media._ID + "=?";
			Cursor cursor = context.getContentResolver().query(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel,
					new String[] { type }, null);
			int columnIndex = cursor.getColumnIndex(column[0]);
			if (cursor.moveToFirst()) {
				filePath = cursor.getString(columnIndex);
			}
			cursor.close();
			return filePath;
		} else if ("content".equalsIgnoreCase(uri.getScheme())) {
			String[] projection = { MediaStore.Images.Media.DATA };// "_data"
			Cursor cursor = null;

			try {
				cursor = context.getContentResolver().query(uri, projection,
						null, null, null);
				int column_index = cursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);// "_data"
				if (cursor.moveToFirst()) {
					return cursor.getString(column_index);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}

		return null;
	}

	/**
	 * Function:从filePath文件中读取字节流，进行Base64编码后转为字符串
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-24 13:01:39
	 */
	public static String ReadFile(String filePath) {
		return ReadFileToBase64(filePath);
	}

	protected static String ReadTxtFile(String filePath) {
		String path = filePath;
		String content = ""; // 文件内容字符串
		// 打开文件
		File file = new File(path);
		// 如果path是传递过来的参数，可以做一个非目录的判断
		if (!file.exists()) {
			Log.d("ReadTxtFile", filePath + "<- The File doesn't not exist.");
		} else {
			try {
				InputStream instream = new FileInputStream(file);
				if (instream != null) {
					InputStreamReader inputreader = new InputStreamReader(
							instream);
					BufferedReader buffreader = new BufferedReader(inputreader);
					String line;
					// 分行读取
					while ((line = buffreader.readLine()) != null) {
						content += line + "\n";
					}
					instream.close();
				}
			} catch (java.io.FileNotFoundException e) {
				e.printStackTrace();
				Log.d("ReadTxtFile", filePath
						+ "<- The File doesn't not exist.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	/**
	 * Function:从filePath文件中读取字节流，进行Base64编码后转为字符串
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-24 13:01:39
	 */
	protected static String ReadImgFile(String filePath) {
		String path = filePath;
		String content = ""; // 文件内容字符串
		// 打开文件
		File file = new File(path);
		// 如果path是传递过来的参数，可以做一个非目录的判断
		if (!file.exists()) {
			Log.d("ReadImgFile", filePath + "<- The File doesn't not exist.");
		} else {
			try {
				InputStream instream = new FileInputStream(file);
				if (instream != null) {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int count = 0;
					while ((count = instream.read(buffer)) >= 0) {
						baos.write(buffer, 0, count);
					}
					byte[] bs = Base64.encode(baos.toByteArray(),
							Base64.NO_WRAP);// 进行Base64编码
					content = new String(bs);

					instream.close();
				}
			} catch (java.io.FileNotFoundException e) {
				e.printStackTrace();
				Log.d("ReadImgFile", "The File doesn't not exist.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	/**
	 * Function:从filePath文件中读取字节流，进行Base64编码后转为字符串
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-24 13:01:39
	 */
	public static String ReadFileToBase64(String filePath) {
		String path = filePath;
		String content = ""; // 文件内容字符串
		// 打开文件
		File file = new File(path);
		// 如果path是传递过来的参数，可以做一个非目录的判断
		if (!file.exists()) {
			Log.d("ReadFileToBase64", filePath
					+ "<- The File doesn't not exist.");
		} else {
			try {
				InputStream instream = new FileInputStream(file);
				if (instream != null) {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int count = 0;
					while ((count = instream.read(buffer)) >= 0) {
						baos.write(buffer, 0, count);
					}
					byte[] bs = Base64.encode(baos.toByteArray(),
							Base64.NO_WRAP);// 进行Base64编码
					content = new String(bs);

					instream.close();
				}
			} catch (java.io.FileNotFoundException e) {
				Log.d("ReadFileToBase64", "The File doesn't not exist.");
			} catch (IOException e) {
				Log.d("ReadFileToBase64", e.getMessage());
			}
		}
		return content;
	}

	/**
	 * Function:将Base64编码的字符串进行解码，并存到fileName文件中,如果文件存在则进行覆盖
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-24 13:01:39
	 */
	public static boolean WriteFileFromBase64(Context context,
			String fileContent, String fileName) {
		return WriteFileFromBase64(context, fileContent, null, fileName);
		// String strFileName = fileName;
		// try {
		// byte[] bs = Base64.decode(fileContent, Base64.NO_WRAP);// 进行Base64编码
		// String filePath = getFilePath(context);
		// File file = new File(filePath, strFileName);
		// if (file.exists()) {
		// file.delete();
		// Log.i("yinjingxiang", strFileName + " is Exist!!!");
		// }
		// FileOutputStream fOut = new FileOutputStream(file);
		// fOut.write(bs);
		// fOut.close();
		// return true;
		// } catch (IOException ioe) {
		// ioe.printStackTrace();
		// }
		//
		// return false;
	}

	/**
	 * Function:将Base64编码的字符串进行解码，并存到fileName文件中,如果文件存在则进行覆盖
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-24 13:01:39
	 */
	public static boolean WriteFileFromBase64(Context context,
			String fileContent, String fileSecondPath, String fileName) {
		String fileSecondPathTemp = fileSecondPath;
		if (fileSecondPathTemp == null || fileSecondPathTemp.isEmpty()
				|| fileSecondPathTemp.equalsIgnoreCase("/")) {
			fileSecondPathTemp = "";
		} else if (fileSecondPathTemp.lastIndexOf("/") + 1 < fileSecondPathTemp
				.length()) {
			fileSecondPathTemp += "/";
		}
		String filePath = getFilePath(context) + fileSecondPathTemp;
		File fileDictionary = new File(filePath + fileName);
		if (!fileDictionary.exists()) {
			fileDictionary.mkdirs();
		}
//		File filePath2 = new File(filePath+fileName);
//		if (!filePath2.exists()) {
//			filePath2.mkdirs();
//		}
		String strFileName = fileName;
		try {
			byte[] bs = Base64.decode(fileContent, Base64.NO_WRAP);// 进行Base64编码
			File file = new File(filePath, strFileName);
			if (file.exists()) {
				file.delete();
				Log.i("yinjingxiang", strFileName + " is Exist!!!");
			}
			FileOutputStream fOut = new FileOutputStream(file);
			fOut.write(bs);
			fOut.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return false;
	}

	/**
	 * Function:测试用：将Base64编码的字符串进行解码，并存到fileName文件中,如果文件存在则进行覆盖
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-24 13:01:39
	 */
	private static String WriteFileFromBase64_0(Context context,
			String fileContent) {
		// String content0 = "";
		String content = "";
		byte[] bs = Base64.decode(fileContent, Base64.NO_WRAP);// 进行Base64编码
		try {
			content = new String(bs, "gb2312");

			String filePath = getFilePath(context);
			String strFileName = "testfile-0.doc";
			File file0 = new File(filePath, "testfile-1.doc");
			File file = new File(filePath, strFileName);
			if (file.exists()) {
				file.delete();
				Log.i("yinjingxiang", strFileName + "is Exist!!!");
			}
			if (file0.exists()) {
				file0.delete();
				Log.i("yinjingxiang", "testfile-1.doc is Exist!!!");
			}

			FileOutputStream fOut = new FileOutputStream(file);

			OutputStreamWriter osw = new OutputStreamWriter(fOut);

			// ---write the string to the file---
			osw.write(content);
			osw.flush();
			osw.close();
			fOut.close();

			FileOutputStream fOut0 = new FileOutputStream(file0);
			fOut0.write(bs);
			fOut0.close();

			// OutputStreamWriter osw0 = new OutputStreamWriter(fOut);

			// ---write the string to the file---
			// content = new String(bs, "UTF-8");
			// osw0.write(bs);
			// osw0.flush();
			// osw0.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return content;
	}

	/**
	 * Function:将Assets中的文件放到sd卡中
	 * 
	 * @author yinjingxiang
	 * @created 2014-7-14 13:01:39
	 */
	protected static boolean writeFilefromAssets(Context context,
			String assetsFileName, String sdFileName, String secondPathName) {
		InputStream stream = null;
		try {
			stream = context.getAssets().open(assetsFileName);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		if (stream == null) {
			return false;
		}

		String filePath = getFilePath(context) + secondPathName;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(filePath + sdFileName);

		OutputStream output = null;
		try {
			output = new FileOutputStream(file);
			final byte[] buffer = new byte[1024];
			int read;
			while ((read = stream.read(buffer)) != -1) {
				output.write(buffer, 0, read);
			}
			output.flush();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				output.close();
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	// Android获取一个用于打开Word文件的intent
	private static Intent getWordFileIntent(String filePath) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(filePath));
		intent.setDataAndType(uri, "application/msword");
		return intent;
	}

	/**
	 * Function:获取缓存文件夹目录 如果不存在创建 否则则创建文件夹
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-24 13:01:39
	 */
	public static String getFilePath(Context context) {
		String filePath = "";
		if (!isSDExist()) {
			filePath = getSDPath(context) + "/";
		} else {
			filePath = getSDPath(context) + CACHE;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return filePath;
	}

	/**
	 * Function:获取sd卡的缓存路径， 一般在卡中sdCard就是这个目录,如果sd卡不存在则获取应用的文件位置
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-24 13:01:39
	 */
	protected static String getSDPath(Context context) {
		File sdDir = null;
		if (isSDExist()) {
			sdDir = Environment.getExternalStorageDirectory();// 获取根目录
		} else {
			Log.e("ERROR", "没有内存卡");
			sdDir = context.getFilesDir();
		}
		return sdDir.toString();

	}

	/**
	 * Function:判断sd卡是否存在
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-24 13:01:39
	 */
	public static boolean isSDExist() {
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		return sdCardExist;

	}

	/**
	 * Function:打开文件
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-27 12:20:03
	 */
	public static boolean openFile(Context context, String fileName) {
		return openFile(context, null, fileName);
		// String filePath = getFilePath(context) + fileName;
		// File file = new File(filePath);
		// if (!file.exists()) {
		// return false;
		// } else {
		// try {
		// context.startActivity(getWordFileIntent(filePath));
		// } catch (Exception e) {
		// Toast.makeText(context, "没有可以使用的应用打开文件", Toast.LENGTH_LONG).show();
		// return false;
		// }
		// return true;
		// }
	}

	/**
	 * Function:打开文件
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-27 12:20:03
	 */
	public static boolean openFile(Context context, String fileSecondPath,
			String fileName) {
		String fileSecondPathTemp = fileSecondPath;
		if (fileSecondPathTemp == null || fileSecondPathTemp.isEmpty()
				|| fileSecondPathTemp.equalsIgnoreCase("/")) {
			fileSecondPathTemp = "";
		} else if (fileSecondPathTemp.lastIndexOf("/") + 1 < fileSecondPathTemp
				.length()) {
			fileSecondPathTemp += "/";
		}
		String filePath = getFilePath(context) + fileSecondPathTemp + fileName;
		Log.i("yinjingxiang", "openFile:filePath=" + filePath);
		File file = new File(filePath);
		if (!file.exists()) {
			return false;
		} else {
			try {
				context.startActivity(getWordFileIntent(filePath));
			} catch (Exception e) {
				Toast.makeText(context, "没有可以使用的应用打开文件", Toast.LENGTH_LONG)
						.show();
				return false;
			}
			return true;
		}

	}

	/**
	 * Function:打开文件
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-27 12:20:03
	 */
	public static void openTestFile(Context context) {
		if (!FileUtils.openFile(context, "测试文档.doc")) {
			FileUtils.writeFilefromAssets(context, "gwzw.doc", "测试文档.doc", "");
			FileUtils.openFile(context, "测试文档.doc");
		}
	}

	/**
	 * Function:打开文件
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-27 12:20:03
	 */
	public static void openQpdFile(Context context, String secondFilePath) {
		if (!FileUtils.openFile(context, secondFilePath, "qianpidan.doc")) {
			FileUtils.writeFilefromAssets(context, "qianpidan.doc",
					"qianpidan.doc", secondFilePath);
			FileUtils.openFile(context, secondFilePath, "qianpidan.doc");
		}
	}

	/**
	 * Function:打开文件
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-27 12:20:03
	 */
	public static void openAttachFile(Context context, String secondFilePath) {
		if (!FileUtils.openFile(context, secondFilePath, "attach.doc")) {
			FileUtils.writeFilefromAssets(context, "qianpidan.doc",
					"attach.doc", secondFilePath);
			FileUtils.openFile(context, secondFilePath, "attach.doc");
		}
	}

	/**
	 * Function:删除文件
	 * 
	 * @author yinjingxiang
	 * @created 2014-6-27 12:20:03
	 */
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return true;
		} else {
			return file.delete();
		}
	}

	/**
	 * 
	 * Function:
	 * 
	 * @author liuzheng
	 * @created 2014-8-6 下午4:45:06
	 * @param path
	 * @param context
	 * @return
	 */
	public static boolean checkFileIsExist(String path, Context context) {
		String filePath = getFilePath(context) + path;

		return new File(filePath).exists();
	}

	/**
	 * Function:获取文件大小，单位:bytes
	 * 
	 * @author yinjingxiang
	 * @created 2014年8月27日 下午3:18:27
	 * @param context
	 * @param filePath
	 * @return
	 */
	public static long getFileSize(Context context, String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return 0;
		} else {
			Log.i("yinjingxiang", "getFileSize=" + file.length());
			return file.length();
		}
	}

	/**
	 * 获取文件名
	 * @param fileUrl
	 * @return
	 */
	public static String getFileName(String fileUrl) {
		if (fileUrl.isEmpty()) {
			return null;
		}
		int index = fileUrl.lastIndexOf("/") + 1;
		return fileUrl.substring(index, fileUrl.length());
	}
	
	// {后缀名，MIME类型} 
		private static final String[][] MIME_MapTable={ 
	            {".3gp",    "video/3gpp"}, 
	            {".apk",    "application/vnd.android.package-archive"}, 
	            {".asf",    "video/x-ms-asf"}, 
	            {".avi",    "video/x-msvideo"}, 
	            {".bin",    "application/octet-stream"}, 
	            {".bmp",    "image/bmp"}, 
	            {".c",  "text/plain"}, 
	            {".class",  "application/octet-stream"}, 
	            {".conf",   "text/plain"}, 
	            {".cpp",    "text/plain"}, 
	            {".chm",    "application/msword"}, 
	            {".doc",    "application/msword"}, 
	            {".docx",   "application/vnd.openxmlformats-officedocument.wordprocessingml.document"}, 
	            {".xls",    "application/vnd.ms-excel"},  
	            {".xlsx",   "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"}, 
	            {".exe",    "application/octet-stream"}, 
	            {".gif",    "image/gif"}, 
	            {".gtar",   "application/x-gtar"}, 
	            {".gz", "application/x-gzip"}, 
	            {".h",  "text/plain"}, 
	            {".htm",    "text/html"}, 
	            {".html",   "text/html"}, 
	            {".jar",    "application/java-archive"}, 
	            {".java",   "text/plain"}, 
	            {".jpeg",   "image/jpeg"}, 
	            {".jpg",    "image/jpeg"}, 
	            {".js", "application/x-javascript"}, 
	            {".log",    "text/plain"}, 
	            {".m3u",    "audio/x-mpegurl"}, 
	            {".m4a",    "audio/mp4a-latm"}, 
	            {".m4b",    "audio/mp4a-latm"}, 
	            {".m4p",    "audio/mp4a-latm"}, 
	            {".m4u",    "video/vnd.mpegurl"}, 
	            {".m4v",    "video/x-m4v"},  
	            {".mov",    "video/quicktime"}, 
	            {".mp2",    "audio/x-mpeg"}, 
	            {".mp3",    "audio/x-mpeg"}, 
	            {".mp4",    "video/mp4"}, 
	            {".mpc",    "application/vnd.mpohun.certificate"},        
	            {".mpe",    "video/mpeg"},   
	            {".mpeg",   "video/mpeg"},   
	            {".mpg",    "video/mpeg"},   
	            {".mpg4",   "video/mp4"},    
	            {".mpga",   "audio/mpeg"}, 
	            {".msg",    "application/vnd.ms-outlook"}, 
	            {".ogg",    "audio/ogg"}, 
	            {".pdf",    "application/pdf"}, 
	            {".png",    "image/png"}, 
	            {".pps",    "application/vnd.ms-powerpoint"}, 
	            {".ppt",    "application/vnd.ms-powerpoint"}, 
	            {".pptx",   "application/vnd.openxmlformats-officedocument.presentationml.presentation"}, 
	            {".prop",   "text/plain"}, 
	            {".rc", "text/plain"}, 
	            {".rmvb",   "audio/x-pn-realaudio"}, 
	            {".rtf",    "application/rtf"}, 
	            {".sh", "text/plain"}, 
	            {".tar",    "application/x-tar"},    
	            {".tgz",    "application/x-compressed"},  
	            {".txt",    "text/plain"}, 
	            {".wav",    "audio/x-wav"}, 
	            {".wma",    "audio/x-ms-wma"}, 
	            {".wmv",    "audio/x-ms-wmv"}, 
	            {".wps",    "application/vnd.ms-works"}, 
	            {".xml",    "text/plain"}, 
	            {".z",  "application/x-compress"}, 
	            {".zip",    "application/x-zip-compressed"}, 
	            {"",        "*/*"}   
	        }; 
		
		
		/**
		 * 获取文件MIME Type 以获取打开文件Intent
		 * 
		 * */
		public static String getMIMEType(File file){
			String type="*/*"; 
		    String fName = file.getName();
		    //获取后缀名前的分隔符"."在fName中的位置。 
		    int dotIndex = fName.lastIndexOf("."); 
		    if(dotIndex < 0){ 
		        return type; 
		    } 
		    /* 获取文件的后缀名*/ 
		    String end=fName.substring(dotIndex,fName.length()).toLowerCase(); 
		    if(end=="")return type; 
		    //在MIME和文件类型的匹配表中找到对应的MIME类型。 
		    for(int i=0;i<MIME_MapTable.length;i++){ //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？ 
		        if(end.equals(MIME_MapTable[i][0])) 
		            type = MIME_MapTable[i][1]; 
		    }        
		    return type; 
		}
	
	
}