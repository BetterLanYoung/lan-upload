package com.lan.excute;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class TestA {


    public static void main(String[] args) throws Exception{

        JSch jsch = new JSch();

        Session session = jsch.getSession("root","192.168.137.36");
        session.setConfig("StrictHostKeyChecking","no");
        session.setPassword("Huawei@123");
        session.connect();
//        Channel channel = session.openChannel("sftp");
//        channel.connect();
//        ChannelSftp sftp = (ChannelSftp)channel;
//        InputStream inputStream = sftp.get("/lan/lan");
//        sftp.put(inputStream,"/");

        Channel channel2 = session.openChannel("exec");
        ChannelExec exec = (ChannelExec)channel2;
        exec.setCommand("cp -r  /lan /yang");
        exec.connect();


        exec.disconnect();
        session.disconnect();

    }
}
