package com.lan;


import com.alibaba.fastjson.JSONObject;
import com.lan.excute.MExecuteUtil;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import java.io.File;

//@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Application.class, args);
        //查看当前的运行路径
        System.out.println(System.getProperty("user.dir"));
        String dir = System.getProperty("user.dir");

        //读取配置地址
        String configJsonPath = dir + "/config.json";
        File configFile = new File(configJsonPath);
        String s = FileUtils.readFileToString(configFile, Charsets.UTF_8);
        JSONObject configJsonObject = JSONObject.parseObject(s);
        System.out.println(configJsonObject);

        File file = new File(dir);
        File[] files = file.listFiles();

        for (File file1 : files) {
            System.out.println(file1.getName());
            String absolutePath = file1.getAbsolutePath();
            if ("mcc".equals(file1.getName())) {
                MExecuteUtil.execute(configJsonObject, absolutePath);
            }


        }
//        // 创建 SSH客户端
//        SshClient client = SshClient.setUpDefaultClient();
//
//        // 启动 SSH客户端
//        client.start();
//        // 通过主机IP、端口和用户名，连接主机，获取Session
//        ClientSession session = client.connect("root", "120.48.91.111", 22).verify(10 * 1000).getSession();
//        // 给Session添加密码
//        session.addPasswordIdentity("Huawei@123");
//        // 校验用户名和密码的有效性
//        boolean isSuccess = session.auth().verify().isSuccess();
//        // 认证成功
//        if (isSuccess) {
//            long middleTime = System.currentTimeMillis();
//
//            ScpClientCreator creator = ScpClientCreator.instance();
//            // 创建 SCP 客户端
//            ScpClient scpClient = creator.createScpClient(session);
//
//            System.out.println("Scp beginning.");
//            // ScpClient.Option.Recursive：递归copy，可以将子文件夹和子文件遍历copy
//            scpClient.upload(dir, "/lan", ScpClient.Option.Recursive);
//            System.out.println("Scp finished.");
//
//            // 释放 SCP客户端
//            if (scpClient != null) {
//                scpClient = null;
//            }
//
//            // 关闭 Session
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//
//            // 关闭 SSH客户端
//            if (client != null && client.isOpen()) {
//                client.stop();
//                client.close();
//            }
//        }
    }
}
