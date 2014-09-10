package com.github.cms.util;

import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.github.cms.bean.Users;
import com.github.cms.service.bean.PagerResult;

public class PageUtil {
	static final String PageStr = "nowpage";

    public static String generatePageHtml(long total, HttpServletRequest request, int nowpage, int pagesize) {
        int pageCount = (int) Math.ceil((double) total / pagesize);
        if(pageCount < 1) return "";

        String rstr = "";//返回值
        if(nowpage<=0){
        	nowpage = NumberUtils.toInt(request.getParameter(PageStr));
        }
        int ipage = nowpage;
        if(ipage <= 0) ipage = 1;//将ipage保证在合理范围
        if(ipage > pageCount) ipage = pageCount;
        int iPageWidth = 11;//每页显示的数字页数
        
        //得到QueryString，PageStr去掉
        String strQueryPara = getRequestQueryString(request, PageStr);
        String requestURL = request.getRequestURI();
        strQueryPara = strQueryPara.equals("") ? strQueryPara : "&" + strQueryPara;

        //几个图片的地址及名称
        String imgfirst = "<a href=\"" + requestURL + "?" + PageStr + "=1" + strQueryPara + "\" title=\"首页\">" +
                "首页</a>";
        String imgpre = "<a href=\"" + requestURL + "?" + PageStr + "=" + (ipage - 1) + strQueryPara + "\" title=\"前一页\">" +
                "前一页</a>";
        String imgnext = "<a href=\"" + requestURL + "?" + PageStr + "=" + (ipage + 1) + strQueryPara + "\" title=\"后一页\">" +
                "后一页</a>";
        String imglast = "<a href=\"" + requestURL + "?" + PageStr + "=" + pageCount + strQueryPara + "\" title=\"尾页\">" +
                "尾页</a>";
        if(ipage <= 1) {
            imgfirst = "<span>首页</span>";
            imgpre = "<span>前一页</span>";
        }
        if(ipage >= pageCount) {
            imgnext = "<span>后一页</span>";
            imglast = "<span>尾页</span>";
        }

        //中间部分导航页码的生成
        int pageBegin = ipage - iPageWidth / 2;
        int pageEnd = ipage + iPageWidth / 2;
        if(pageEnd < iPageWidth) pageEnd = iPageWidth;
        if(pageEnd > pageCount) {
            pageBegin = pageBegin - (pageEnd - pageCount);
            pageEnd = pageCount;
        }
        if(pageBegin < 1) pageBegin = 1;
        String strPageNumNav = "";
        for(int i = pageBegin; i <= pageEnd; i++) {
            if(i != pageBegin) strPageNumNav += " ";
            if(i == ipage)
                strPageNumNav += "[<b>" + i + "</b>]";
            else
                strPageNumNav += "<a href=\"" + requestURL + "?" + PageStr + "=" + i +
                        strQueryPara + "\">" + i + "</a>";
        }
        
        
        String filename=request.getRequestURI();
      
        //生成最后的东西
        rstr += "<span class=ipagenav>"+ imgfirst + "&nbsp;" + imgpre + "&nbsp;&nbsp;" +
                strPageNumNav +
                "&nbsp;&nbsp;" +
                imgnext + "&nbsp;" + imglast +"&nbsp;&nbsp;&nbsp;"+"跳到<input type='text' id='thepage' size=3  class=\"admTextSeven\" onkeydown='if(event.keyCode==13){self.open(\""+filename+"?"+PageStr +"=\""+"+this.value+"+"\""+getParamAnd(request,PageStr)+"\",\"_self\");}'>页"+"&nbsp;&nbsp;&nbsp;" +
                "共" + total + "个" + pageCount + "页" +
                "</span>";
        return rstr;
    }
    public static String getParamAnd(HttpServletRequest request, String droppara) {
        String r = getRequestParamString(request, droppara, "");
        return (r.length() > 0) ? "&" + r : "";
    }
    public static String getRequestParamString(HttpServletRequest request, String droppara, String urlencode) {
        Enumeration names = request.getParameterNames();
        droppara += ",";
        String rstr = "";
        try {
            while(names.hasMoreElements()) {
                String name = (String) names.nextElement();
                if(name != null && name.length() > 0 && droppara.indexOf(name + ",") < 0) {
                    rstr += (rstr.length() > 0 ? "&" : "") +
                            name + "=" + (urlencode.length() == 0 ?
                            notNull(request.getParameter(name)) :
                            URLEncoder.encode(notNull(request.getParameter(name)), urlencode));
                }
            }
        } catch(Exception e) {
        }
        return rstr;
    }
    private static String notNull(String str) {
		if(str==null){
			return "";
		}
		return str;
	}
	public static String getRequestQueryString(HttpServletRequest request,	String droppara){
    	return getRequestQueryString(request, droppara,false,"utf-8",false);
    }
	public static String getRequestQueryString(HttpServletRequest request,	String droppara, 
            boolean urlEncode, String urlEncodeCharSet,	boolean dropEmptyParam) {
		
		String[] aquery = StringUtils.split(request.getQueryString(), '&');
		if (aquery == null)
			return "";
		StringBuffer sb = new StringBuffer();
		for (String query : aquery) {
			if (query == null || "".equals(query.trim()))
				continue;
			query = query.trim();
			int _pos = query.indexOf("=");
			if (_pos < 0 && dropEmptyParam)
				continue;
			String _name = query;
			String _value = "";
			if (_pos > 0) {
				_name = query.substring(0, _pos);
				_value = query.substring(_pos + 1);
			}
			if (droppara != null
					&& ("," + droppara + ",").indexOf("," + _name + ",") > -1)
				continue;
			if ("".equals(_value) && dropEmptyParam)
				continue;
			if (!"".equals(sb.toString()))
				sb.append("&");
			sb.append(_name);
			if (_pos > 0)
				sb.append("=");
			sb.append(urlEncode ? URLEncode(_value, urlEncodeCharSet) : _value);
		}
		return sb.toString();
	}
	public static String generatePageHtml(PagerResult<Users> result,
			HttpServletRequest request) {
		
		return generatePageHtml(result.getTotal(), request, result.getNowpage(), result.getPagesize());
	}
	public static String URLEncode(String url) {
        return URLEncode(url, "gbk");
    }

    public static String URLEncode(String url, String charset) {
        if(charset == null) return URLEncode(url);
        try {
            return URLEncoder.encode(url, charset);
        } catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
