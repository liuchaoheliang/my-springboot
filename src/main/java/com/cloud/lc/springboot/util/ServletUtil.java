package com.cloud.lc.springboot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;


/**
 * Servlet工具
 */
public class ServletUtil {
	/**
	 * 取得请求功能的路径，例如：http://127.0.0.1:8080/ctx/func1/aa.do -> func1/aa
	 */
	public static String getPageUriCode(HttpServletRequest request) {
		String uri = request.getRequestURI().replaceAll("/+", "/");
		String contextPath = request.getContextPath();
		if ("/".equals(contextPath) || "".equals(contextPath)) {
			uri = uri.substring(1);
		} else {
			uri = uri.substring(contextPath.length() + 1);
		}

		int dotPos = uri.indexOf(".");
		if (dotPos != -1) {
			uri = uri.substring(0, dotPos);
		}
		return uri;
	}

	/**
	 * 获取请求(post或get)参数或请求体内json格式的内容，转化为参数。对于请求体中是json格式，value支持单值或数组
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public static Map<String, List<String>> getRequestOrBodyParamMap( HttpServletRequest request ){
		Map<String, String[]> map = request.getParameterMap();
		if( map.size() == 0 ){
			String bodyContent = getRequestBodyContent( request ).trim();
			if( bodyContent != null && bodyContent.length() > 0 && bodyContent.startsWith( "{" ) ){
				Map<String, String> m = JSON.parseObject( bodyContent, HashMap.class );
				if( m.size() > 0 ){
					Map<String, List<String>> m2 = new HashMap<String, List<String>>();
					for( String key : m.keySet() ){
						List<String> list = new ArrayList<String>();
						list.add( m.get( key ) );
						m2.put( key, list );
					}
					return m2;
				}
			}
		}else{			
			return getParamsForList( request );
		}
		
		return new HashMap<String, List<String>>();
	}
	
	/**
	 * 获取请求(post或get)参数或请求体内json格式的内容，转化为参数。对于请求体中是json格式，value支持单值或数组
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getBodyJsonParamForMap( HttpServletRequest request ){
		String bc = getRequestBodyContent( request );
		try{
			Map<String, Object> map = JSON.parseObject( bc, Map.class );
			return map == null? new HashMap<String, Object>() : map;
		}catch( Exception e ){
			return new HashMap<String, Object>();
		}
	}
	
	/**
	 * 取得请求公网的ip
	 */
	public static String getRequestIp(HttpServletRequest req) {
		String ip = req.getHeader("X-Forwarded-For");

		if (ip == null || "".equals(ip.trim())) {
			ip = req.getHeader("X-Real-IP");
		}
		if (ip == null || "".equals(ip.trim())) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (ip == null || "".equals(ip.trim())) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || "".equals(ip.trim())) {
			ip = req.getRemoteAddr();
		}
		if (ip == null || "".equals(ip.trim())) {
			ip = "";
		}

		return ip;
	}
	
	/**
	 * 取得某个cookie的值
	 */
	public static String getCookie( HttpServletRequest request, String key ){
		Cookie[] cs = request.getCookies();
		if( cs != null ){
			for( Cookie c : cs ){
				if( c.getName().equals( key ) ){
					return c.getValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取请求参数信息用于展示，例如：userId=123, groupIdList=[1,4,5,6]...
	 */
	public static String getRequestParamsInfoShow(HttpServletRequest request){
		Set<String> keySet = request.getParameterMap().keySet();
		StringBuilder sb = new StringBuilder();
		if (keySet != null && keySet.size() > 0) {
			int size = keySet.size();
			int i = 0;
			for (String key : keySet) {
				i += 1;
				
				String[] values = request.getParameterValues( key);
				String vStr = "";
				if( values.length == 1 ){
					vStr = values[0];
				}else{
					vStr = "[";
					for( int h = 0; h < values.length; h ++ ){
						vStr = vStr + ( h > 0? ", " : "" ) + values[h];
					}
					vStr = vStr + "]";
				}
				sb.append(key).append("=").append(vStr);
				if( i != size ){
					sb.append(", ");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 获取所有请求的参数，返回Map，key(参数值), value(值，List)
	 */
	public static Map<String, List<String>> getParamsForList( HttpServletRequest request ){
		Map<String, List<String>> param = new HashMap<String, List<String>>();
		Set<String> keySet = request.getParameterMap().keySet();
		if (keySet != null && keySet.size() > 0) {
			for (String key : keySet) {
				String[] vs = request.getParameterValues( key);
				List<String> vList = new ArrayList<String>( vs.length );
				for( String v : vs ){
					vList.add( v );
				}
				
				if( vList.size() > 0 ){
					param.put( key, vList );
				}
			}
		}
		return  param;
	}
	
	/**
	 * 获取请求头信息
	 */
	public static String getHeadField( HttpServletRequest request, String k ){
		return request.getHeader( k );
	}
	
	/**
	 * 获取所有请求的参数，返回Map，key(参数值), value(值，List，去除了空值)
	 */
	public static Map<String, List<String>> getParamsForListNotBlank( HttpServletRequest request ){
		Map<String, List<String>> param = new HashMap<String, List<String>>();
		Set<String> keySet = request.getParameterMap().keySet();
		if (keySet != null && keySet.size() > 0) {
			for (String key : keySet) {
				String[] vs = request.getParameterValues( key);
				List<String> vList = new ArrayList<String>( vs.length );
				for( String v : vs ){
					if( !"".equals( v ) ){
						vList.add( v );
					}
				}
				
				if( vList.size() > 0 ){
					param.put( key, vList );
				}
			}
		}
		return  param;
	}
	
	
	/**
	 * 获取请求体内容
	 */
	public static String getRequestBodyContent( HttpServletRequest request ){
		try {
			InputStream is = request.getInputStream();
			StringBuilder str0 = new StringBuilder();
			while( true ){
				byte[] bs = new byte[10240];
				int n = is.read( bs );
				if( n < 1 ){
					break;
				}
				String html = new String( bs, 0, n, "utf-8" );
				str0.append( html );
			}
			return str0.toString();
		} catch (IOException e) {
			throw new RuntimeException( e );
		}
	}
	
	/**
	 * 获取请求内容，保存到文件
	 */
	public static void getRequestBodyToFile( HttpServletRequest request, File file ){
		try {
			OutputStream os = new FileOutputStream( file );
			InputStream is = request.getInputStream();
			while( true ){
				byte[] bs = new byte[10240];
				int n = is.read( bs );
				if( n < 1 ){
					os.flush();
					os.close();
					break;
				}
				os.write( bs, 0, n );
			}
		} catch (Exception e) {
			throw new RuntimeException( e );
		}
	}
	
	/**
	 * 响应给客户端(浏览器)下载文件
	 * @param response : HttpServletResponse
	 * @param txt ：文本
	 */
	public static void responseTxt( HttpServletResponse response, String txt ){
		try{
			byte[] bs = txt.getBytes( "utf-8" );
			response.reset();
			response.setContentType( "text/plain;charset=utf-8" );
			response.setContentLength( bs.length );
			response.getOutputStream().write( txt.getBytes( "utf-8" ), 0, bs.length );
		}catch( Exception e ){
			throw new RuntimeException( e );
		}
	}
	
	/**
	 * 响应给客户端(浏览器)下载文件
	 * @param response : HttpServletResponse
	 * @param filePath ：服务器端文件绝对地址
	 * @param fileName : 客户端保存的文件名，如果传null，则使用file的名称
	 * @throws IOException 
	 */
	public static void responseClientDownloadFile( HttpServletResponse response, String filePath, String clientFileName ) {
		try{
			File file = new File( filePath );
			InputStream is = new FileInputStream( file );
			
			response.reset();
			response.setContentType( "application/x-download;charset=utf-8" );
			//response.setContentType( "application/octet-stream" );
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode( clientFileName == null? file.getName() : clientFileName, "utf-8" ));
			response.setContentLength( (int) file.length() );
			
			byte[] bytes = new byte[10240];
			int len = 0;
			while( ( len = is.read( bytes ) ) > 0 ) {
				response.getOutputStream().write( bytes, 0, len );
			}
			is.close();			
		}catch( Exception e ){
			throw new RuntimeException( e );
		}
	}
}
