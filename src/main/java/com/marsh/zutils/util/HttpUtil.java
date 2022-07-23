package com.marsh.zutils.util;

import com.marsh.zutils.constant.BaseConstant;
import com.marsh.zutils.constant.CharConstant;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author lucky.zhou
 */
@Slf4j
public class HttpUtil {

	private HttpUtil() {

	}

	public static final MediaType JSON
					= MediaType.get("application/json; charset=utf-8");

	public static final MediaType MEDIA_TYPE_MARKDOWN
					= MediaType.parse("text/x-markdown; charset=utf-8");

	private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

	private static final OkHttpClient client = new OkHttpClient();


	public static void asyncGet(String uri, Consumer<ResponseBody> consumer) {

		Request request = new Request.Builder()
						.url(uri)
						.build();
		asyncResponseHandler(uri, consumer, request);
	}

	public static void asyncGet(String url, Map<String, Object> params, Consumer<ResponseBody> consumer) {
		if (params != null && params.size() > 0) {
			url = concatUrlParams(url, params);
			Request request = new Request.Builder()
							.url(url)
							.build();
			asyncResponseHandler(url, consumer, request);
		} else {
			asyncGet(url, consumer);
		}
	}


	public static void asyncPost(String url, RequestBody requestBody, Consumer<ResponseBody> consumer) {
		Request request = new Request.Builder()
						.url(url)
						.post(requestBody)
						.build();
		asyncResponseHandler(url, consumer, request);
	}


	private static void asyncResponseHandler(String url, Consumer<ResponseBody> consumer, Request request) {
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure( Call call,  IOException e) {
				log.error("com.anchong.charging.api.common http_client: com.anchong.charging.api.item.request:[{}], error:[{}]", url, e.getMessage());
			}

			@Override
			public void onResponse( Call call,  Response response) throws IOException {
				try (ResponseBody responseBody = response.body()) {
					if (!response.isSuccessful()) {
						throw new IOException("HttpClient Unexpect code " + response);
					}
					consumer.accept(responseBody);
				}
			}
		});
	}


	public static String get(String url)  {
		Request request = new Request.Builder()
						.url(url)
						.build();
		Call call = client.newCall(request);
		Response resp = null;
		try {
			resp = call.execute();
			return Objects.requireNonNull(resp.body()).string();
		} catch (IOException e) {
			log.info("网络错误");
		}
		return null;
	}


	public static String get(String url, Map<String, Object> params) {
		url = concatUrlParams(url, params);
		return get(url);
	}

	public static String getByAuth(String url, String token, Map<String, Object> params) {
      Request request = new Request.Builder()
              .url(url)
              .addHeader(BaseConstant.AUTH_HEADER, token)
              .build();
      try (Response response = client.newCall(request).execute()) {
          return Objects.requireNonNull(response.body()).string();
      } catch (IOException ex) {
          log.error("网络错误");
      }
      return null;
	}


	public static Optional<ResponseBody> post(String url, RequestBody requestBody) {
		Request request = new Request.Builder()
						.url(url)
						.post(requestBody)
						.build();
		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful())
				throw new IOException("HttpClient Unexpect code ");
			return Optional.ofNullable(response.body());
		} catch (IOException e) {
			log.error("网络错误：url[{}], params[{}]", url, requestBody.toString());
		}
		return Optional.empty();
	}

	public static Optional<String> postStr(String url, RequestBody requestBody) {
		return post(url, requestBody).map(resp -> {
			try {
				return resp.string();
			} catch (IOException e) {
				log.error("响应类型不是字符串类型");
			}
			return null;
		});

	}

	private static String concatUrlParams(String url, Map<String, Object> params) {
		String paramStr =
						params.entrySet().stream()
										.map(param ->
														StringUtils.join(param.getKey(), CharConstant.EQUALS_SIGN, param.getValue()))
										.collect(Collectors.joining(CharConstant.AMPERSAND));
		url = StringUtils.join(url, CharConstant.QUESTION_MARK, paramStr);
		return url;
	}

	public static void main(String[] args) {
		String s = HttpUtil.get("http://www.baidu.com");
		System.out.println(s);
	}


}


