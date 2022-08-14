package com.lan.excute;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.scp.client.ScpClient;
import org.apache.sshd.scp.client.ScpClientCreator;

import java.io.File;

public class MExecuteUtil {


    public static void execute(JSONObject configJsonObject, String filePath) throws Exception {


        JSONArray mcc = configJsonObject.getJSONArray("mcc");
        int size = mcc.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = mcc.getJSONObject(i);
            String ip = jsonObject.getString("ip");
            String userName = jsonObject.getString("userName");
            String passWord = jsonObject.getString("passWord");
            String path = jsonObject.getString("path");
            // 创建 SSH客户端
            SshClient client = SshClient.setUpDefaultClient();
            // 启动 SSH客户端
            client.start();
            // 通过主机IP、端口和用户名，连接主机，获取Session
            ClientSession session = client.connect(userName, ip, 22).verify(10 * 1000).getSession();
            // 给Session添加密码
            session.addPasswordIdentity(passWord);
            // 校验用户名和密码的有效性
            boolean isSuccess = session.auth().verify().isSuccess();
            // 认证成功
            if (isSuccess) {
                long middleTime = System.currentTimeMillis();
                ScpClientCreator creator = ScpClientCreator.instance();
                // 创建 SCP 客户端
                ScpClient scpClient = creator.createScpClient(session);

                File file = new File(filePath);
                File[] files = file.listFiles();
                // ScpClient.Option.Recursive：递归copy，可以将子文件夹和子文件遍历copy
                for (File file1 : files) {
                    String absolutePath = file1.getAbsolutePath();
                    System.out.println("Scp beginning." + absolutePath);
                    scpClient.upload(absolutePath, path, ScpClient.Option.Recursive);
                    System.out.println("Scp finished." + path);
                }


                // 释放 SCP客户端
                if (scpClient != null) {
                    scpClient = null;
                }

                // 关闭 Session
                if (session != null && session.isOpen()) {
                    session.close();
                }

                // 关闭 SSH客户端
                if (client != null && client.isOpen()) {
                    client.stop();
                    client.close();
                }
            }


        }


    }


}
