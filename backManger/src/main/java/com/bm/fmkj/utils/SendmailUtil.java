package com.bm.fmkj.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;


@Component
public class SendmailUtil {
    // 设置服务器
    private static String KEY_SMTP = "mail.smtp.host";
//    private static String VALUE_SMTP = "smtp.163.com";
    private static String VALUE_SMTP = "smtp.qq.com";
//    private static String VALUE_SMTP = "smtp.exmail.qq.com";
//    private static String VALUE_SMTP = "smtp.sina.com.cn";
    // 服务器验证
    private static String KEY_PROPS = "mail.smtp.auth";
    private static boolean VALUE_PROPS = true;
    // 发件人用户名、密码
//    private String SEND_USER = "abc25660663";
//    private String SEND_USER = "1481596361";
//    private String SEND_USER = "hongxy";
    private static String SEND_USER = "769356913";
    //发件人邮箱
//    private String FROMADDRESS=SEND_USER+"@163.com";
    private static String FROMADDRESS=SEND_USER+"@qq.com";
//    private String FROMADDRESS=SEND_USER+"@minstone.com.cn";
//    private String FROMADDRESS=SEND_USER+"@sina.com";
    private static String SEND_UNAME = "风云科技绿天鹅";
    //邮箱密码
//    private String SEND_PWD = "25660663abc.";
//    private String SEND_PWD = "313131abc.";
//    private String SEND_PWD = "25660663Abc.";
    private static String SEND_PWD = "oktvabuepxwpbdbj";//qq邮箱为授权码
    
    // 建立会话
    private static MimeMessage message;
    private static Session s;
 
    /*
     * 初始化方法
     */
    public SendmailUtil() {
        Properties props = System.getProperties();
        // 连接协议
        props.put("mail.transport.protocol", "smtp");
        props.put(KEY_SMTP, VALUE_SMTP);
        props.put(KEY_PROPS, "true");//true一定要加双引号
        props.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        props.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        s =  Session.getDefaultInstance(props, new Authenticator(){
              protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(SEND_UNAME, SEND_PWD);
              }}); 
        s.setDebug(true);
        message = new MimeMessage(s);
    }
 
    /**
     * 发送邮件
     * 
     * @param headName
     *            邮件头文件名
     * @param sendHtml
     *            邮件内容
     * @param receiveUser
     *            收件人地址
     */
    public static void doSendHtmlEmail(String headName, String sendHtml,
            String receiveUser){
        try {
            // 发件人
            InternetAddress from = new InternetAddress(FROMADDRESS);//发送人地址
            message.setFrom(from);
            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);
            // 邮件标题
            message.setSubject(headName);
            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=UTF-8");
            message.setSentDate(new Date());
            message.saveChanges();
            Transport transport = s.getTransport("smtp");
            //这种方式应用于163网易邮箱和QQ邮箱,最后一个参数是授权码,网易的授权码可以自动设置,而QQ邮箱的授权码需通过短信才可收到
//            transport.connect(FROMADDRESS,"25660663abc");
//            transport.connect(FROMADDRESS,"yigoehcqlbxkhfid");
            //QQ企业邮箱,新浪邮箱,由于企业QQ邮箱和新浪邮箱开通POP3/SMTP/IMAP服务时，不需要授权码
            transport.connect(VALUE_SMTP, FROMADDRESS, SEND_PWD);
            
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("send success!");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        doSendHtmlEmail("风云科技绿天鹅官网", "欢迎使用锤多宝app邮箱绑定，本邮件由系统自动发出，请勿回复。<br/>感谢您的使用。<br/>风云科技有限公司", "1824517828@qq.com");
    }
}