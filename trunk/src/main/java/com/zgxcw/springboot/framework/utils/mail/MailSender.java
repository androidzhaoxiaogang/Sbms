package com.zgxcw.springboot.framework.utils.mail;

/**
 * <img src="oberon_lin.gif">
 * <p>
 * <font size = "1">
 * (C) 2016 com.oberon.lin All rights reserved.
 * <font size = "3">
 * <p>
 *      Class MailReceiver is used to process mail sending.
 * <p>
 * Revision information:
 *      This is the first version of MailSender
 * @author Lin Yu
 * @version 1.0, 2016-01-14
 */

/*
 * 用于发送邮件
 * 使用时如下所示进行设置：
 *    mail mymail=new mail();                                   实例化一个mail对象
 *    mymail.setSmtphost("mail.foodchina.com");                 设置SMTP转发服务器
 *    mymail.setSmtpuser("scluo");                              设置SMTP转发的用户名
 *    mymail.setSmtppassword("scluo");                          设置SMTP转发的用户密码
 *    mymail.setFrom("scluo@mail.foodchina.com");               设置发件人
 *    mymail.setTo("jerry_luo@mail.foodchina.com");             设置收件人
 *    mymail.setSubject("This is a test mail from scluo!");     设置邮件标题
 *    mymail.setBody("How are you!");                           设置邮件内容
 *    mymail.setBodystyle("text/html;charset=GB2312");          设置邮件内容的格式
 *    mymail.attachfile("c:\\share\\test.xml");                 添加附件
 *    mymail.send()                                             发送操作
 */
 
import java.util.* ;
import javax.mail.* ;
import javax.mail.internet.* ;
import javax.activation.* ;

public class MailSender{
  private String smtphost;                                   //SMTP转发服务器
  private String smtpuser;                                   //SMTP转发的用户名
  private String smtppassword;                               //SMTP转发的用户密码
  private String from;                                       //发件人
  private String to;                                         //收件人
  private String subject;                                    //邮件标题
  private String body;                                       //邮件内容
  private String bodystyle="text/plain;charset=GB2312";      //邮件内容的格式(默认为文本格式)
  private String filename;                                   //附件的文件名
  private Vector file = new Vector();                        //用于保存发送附件的文件名的集合

  /**
   * 用于返回邮件SMTP转发服务器
   */
  public String getSmtphost(){
    return smtphost;
  }

  /**
   * 用于设置邮件SMTP转发服务器
   */
  public void setSmtphost(String inString){
    smtphost=inString;
  }

   /**
   * 用于返回邮件SMTP转发的用户名
   */
  public String getSmtpuser(){
    return smtpuser;
  }

  /**
   * 用于设置邮件SMTP转发的用户名
   */
  public void setSmtpuser(String inString){
    smtpuser=inString;
  }

   /**
   * 用于返回邮件SMTP转发的用户密码
   */
  public String getSmtppassword(){
    return smtppassword;
  }

  /**
   * 用于设置邮件SMTP转发的用户密码
   */
  public void setSmtppassword(String inString){
    smtppassword=inString;
  }

  /**
   * 用于返回邮件发件人
   */
  public String getFrom(){
    return from;
  }

  /**
   * 用于设置邮件发件人
   */
  public void setFrom(String inString){
    from=inString;
  }

  /**
   * 用于返回邮件收件人
   */
  public String getTo(){
    return to;
  }

  /**
   * 用于设置邮件收件人
   */
  public void setTo(String inString){
    to=inString;
  }

  /**
   * 用于返回邮件标题
   */
  public String getSubject(){
    return subject;
  }

  /**
   * 用于设置邮件标题
   */
  public void setSubject(String inString){
    subject=inString;
  }

  /**
   * 用于返回邮件内容
   */
  public String getBody(){
    return body;
  }

  /**
   * 用于设置邮件内容
   */
  public void setBody(String inString){
    body=inString;
  }

  /**
   * 用于设置邮件内容的格式
   */
  public void setBodyStyle(String inString){
    bodystyle=inString;
  }

  /**
   * 该方法用于收集附件名
   */
  public void attachfile(String fname){
    file.addElement(fname);
  }

  /**
   * 用于邮件发送，返回发送结果
   *   true  为发送成功
   *   false 为发送失败
   */
  public boolean send(){
    boolean result;

    if(smtphost=="" || smtphost==null){
      System.out.println("An error ocur:smtp host is invalid!");
      return false;
    }

    if(from=="" || from==null){
      System.out.println("An error ocur:mail sender is invalid!");
      return false;
    }

    if(to=="" || to==null){
      System.out.println("An error ocur:mail reciever is invalid!");
      return false;
    }

    if(subject=="" || subject==null){
      System.out.println("An error ocur:subject is invalid!");
      return false;
    }

    Properties props = System.getProperties();
    if(smtpuser=="" || smtpuser==null || smtppassword=="" || smtppassword==null){
       props.put("mail.smtp.host",smtphost);
    }else{
       props.put("mail.smtp.auth","true");
    }
    Session session=Session.getInstance(props,null);
    try {
       MimeMessage msg = new MimeMessage(session);
       msg.setFrom(new InternetAddress(from));
       //InternetAddress[] address={new InternetAddress(to)};
       msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
       msg.setSubject(subject);

       Multipart mp = new MimeMultipart();                    //后面的BodyPart将加入到此处创建的Multipart中
       Enumeration efile=file.elements();                     //利用枚举器方便的遍历集合
       while(efile.hasMoreElements()){
          MimeBodyPart mbp=new MimeBodyPart();
          filename=efile.nextElement().toString();            //选择出每一个附件名
          FileDataSource fds=new FileDataSource(filename);    //得到数据源
          mbp.setDataHandler(new DataHandler(fds));           //得到附件本身并至入BodyPart
          mbp.setFileName(fds.getName());                     //得到文件名同样至入BodyPart
          mp.addBodyPart(mbp);
       }
       file.removeAllElements();                              //移走集合中的所有元素
       MimeBodyPart mbp_body=new MimeBodyPart();
       mbp_body.setContent(body,bodystyle);                   //加入邮件正文
       mp.addBodyPart(mbp_body);
       msg.setContent(mp);                                    //Multipart加入到信件
       msg.setSentDate(new Date());                           //设置信件头的发送日期



       if(smtpuser=="" || smtpuser==null || smtppassword=="" || smtppassword==null){
          Transport.send(msg);                                 //发送信件
       }else{
          Transport trans=session.getTransport("smtp");
          trans.connect(smtphost,smtpuser,smtppassword);
          trans.sendMessage(msg,msg.getAllRecipients());       //发送信件
          trans.close();
       }
       result=true;
    }
    catch(SendFailedException e){
      e.printStackTrace();
      result=false;
    }
    catch (MessagingException mex) {
       mex.printStackTrace();
       Exception ex = null;
       if ((ex=mex.getNextException())!=null){
          ex.printStackTrace();
       }
       result=false;
    }
    return result;
  }

  /**
   * 用于在Counsle下测试本类
   */
  public static void main(String[] args) {
    MailSender mymail = new MailSender();
    mymail.setSmtphost(args[0]);
    mymail.setSmtpuser(args[1]);
    mymail.setSmtppassword(args[2]);
    mymail.setFrom(args[3]);
    mymail.setTo(args[4]);
    mymail.setSubject(args[5]);
    mymail.setBody(args[6]);
    mymail.setBodyStyle(args[7]);
    mymail.attachfile(args[8]);
    mymail.send() ;
  }
}
