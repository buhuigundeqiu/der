package com.atball.der.search.https;

public class SSLHandshakeExceptionLearning {
//        情况说明:
//
//            链接远程ad域，使用证书链接，证书生成后，本地测试可以练接，
//
//            于是运行服务，在服务中调用代码，后端报错（错误信息如上）。
//
//            经搜索后发现，该问题是当你在进⾏https请求时，JDK中不存在三⽅服务的信任证书，导致出现错误javax.net.ssl.SSLHandshakeException：sun.security.validator.ValidatorException：PKIX路径构建失败导致。



//            解决方法：
//
//            　　第一种：获取根证书安装证书到你的JRE的Java cacerts中（安装证书到$JAVA_HOME/JRE/lib⽬录/ cacerts中）。
    /**
     *              1、进入到java 的安装目：cd $JAVA_HOME/jre/lib/security
     *              2、执行导入命令(  命令中 证书文件名：xxx，证书路径： /usr/local/xxx.crt ；
     *                  keytool -import -alias xxx -keystore cacerts -file /usr/local/xxx.crt -trustcacerts
     *
     *                  注意: 第一个xxx是别名 ， 第二个xxx是我们需要导入的证书 （一般我们取别名和证书名称一致）
     *              3、导入时会提示输入口令，默认口令 changeit
     *              4、提示是否信任此证书? 输入y
     *
     *              5、会提示证书已添加到密钥库中
     *
     *        第二种：忽略SSL证书的校验。这⾥因为很多情况没有证书，所以采⽤第⼆种⽅案，在你的代码中进⾏忽略SSL证书校验。
     *
     * 　　       该方法需要添加代码，如下为使用实例（标黄部分为添加项）： 链接：https://www.cnblogs.com/51python/p/16512467.html
     *              import java.io.BufferedReader;
     *              import java.io.IOException;
     *              import java.io.InputStreamReader;
     *              import java.net.HttpURLConnection;
     *              import java.net.URL;
     *              import javax.net.ssl.HostnameVerifier;
     *              import javax.net.ssl.HttpsURLConnection;
     *              import javax.net.ssl.SSLSession;
     *
     *              import org.apache.log4j.Logger;
     *              import org.htmlparser.util.ParserException;
     *
     *              import com.xwtech.parser.GetRequestHtmlParser;
     *              import com.xwtech.pojo.ExtendCandidate;
     *
     *              public class GetRequest {
     *                  private String url = "https://b2b.10086.cn/b2b/main/viewNoticeContent.html?noticeBean.id=";
     *                  private Logger logger;
     *                  public GetRequest() {
     *                      logger = Logger.getLogger(GetRequest.class);
     *                  }
     *                  private static void trustAllHttpsCertificates() throws Exception {
     *                      javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
     *                      javax.net.ssl.TrustManager tm = new miTM();
     *                      trustAllCerts[0] = tm;
     *                      javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
     *                      sc.init(null, trustAllCerts, null);
     *                      javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
     *                  }
     *                  public void getData(String id) {
     *                      this.url = url + id;
     *                      BufferedReader in = null;
     *                      HttpURLConnection conn = null;
     *                      String result = "";
     *                      try {
     *              　　　　　　　　//该部分必须在获取connection前调用
     *                          trustAllHttpsCertificates();
     *                          HostnameVerifier hv = new HostnameVerifier() {
     *                              public boolean verify(String urlHostName, SSLSession session) {
     *                                  logger.info("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
     *                                  return true;
     *                              }
     *                          };
     *                          HttpsURLConnection.setDefaultHostnameVerifier(hv);
     *                          conn = (HttpURLConnection)new URL(url).openConnection();
     *                          // 发送GET请求必须设置如下两行
     *                          conn.setDoInput(true);
     *                          conn.setRequestMethod("GET");
     *                          // flush输出流的缓冲
     *                          in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
     *                          String line;
     *                          while ((line = in.readLine()) != null) {
     *                              result += line;
     *                          }
     *                      } catch (Exception e) {
     *                          logger.error("发送 GET 请求出现异常！\t请求ID:"+id+"\n"+e.getMessage()+"\n");
     *                      } finally {// 使用finally块来关闭输出流、输入流
     *                          try {
     *                              if (in != null) {
     *                                  in.close();
     *                              }
     *                          } catch (IOException ex) {
     *                              logger.error("关闭数据流出错了！\n"+ex.getMessage()+"\n");
     *                          }
     *                      }
     *                      // 获得相应结果result,可以直接处理......
     *
     *                  }
     *                  static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
     *                      public java.security.cert.X509Certificate[] getAcceptedIssuers() {
     *                          return null;
     *                      }
     *
     *                      public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
     *                          return true;
     *                      }
     *
     *                      public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
     *                          return true;
     *                      }
     *
     *                      public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
     *                              throws java.security.cert.CertificateException {
     *                          return;
     *                      }
     *
     *                      public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
     *                              throws java.security.cert.CertificateException {
     *                          return;
     *                      }
     *                  }
     *              }
     *
     *
     *
     *
     *        附：
     *
     * 　　    证书其他操作：
     *          1 查看单个证书（命令中 xxx 为证书导入时的别名）
     *          keytool -list -keystore cacerts | grep xxx
     *          2 查看所有证书
     *          keytool -list -keystore cacerts
     *          3 删除某个证书
     *          keytool -delete -alias xxx -keystore cacerts
     */


}
