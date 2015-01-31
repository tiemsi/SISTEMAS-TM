package es.war.tm.sistemastm.web.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

public final class UrlUtil {

	private UrlUtil() {}

	public static String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
		String enc = httpServletRequest.getCharacterEncoding();
		String encode="";
		
		if (enc == null) {
			enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
		}
		
		encode = UriUtils.encodePathSegment(pathSegment, enc);

		return encode;
	}
}
