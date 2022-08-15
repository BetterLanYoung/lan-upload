package com.lan;


import com.alibaba.fastjson.JSONObject;
import com.lan.bean.Lan;
import com.lan.excute.MExecuteUtil;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

//@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        //SpringApplication.run(Application.class, args);
        //查看当前的运行路径

        LOGGER.info(System.getProperty("user.dir"));
        String dir = System.getProperty("user.dir");

        //读取配置地址
        String configJsonPath = dir + "/config.json";
        File configFile = new File(configJsonPath);
        String s = FileUtils.readFileToString(configFile, Charsets.UTF_8);
        LOGGER.info(s);
        List<Lan> lans = JSONObject.parseArray(s, Lan.class);
        Map<String, List<Lan>> collect = lans.stream().collect(Collectors.groupingBy(i -> i.getServerName()));

        File file = new File(dir);
        File[] files = file.listFiles();

        for (File file1 : files) {
           LOGGER.info(file1.getName());
            String absolutePath = file1.getAbsolutePath();

            if (StringUtils.isNotEmpty(file1.getName()) && (collect.get(file1.getName()) != null && collect.get(file1.getName()).size() > 0)) {
               LOGGER.info(file1.getName());
                MExecuteUtil.execute(collect.get(file1.getName()), absolutePath);
            }


        }
       LOGGER.info("输入Y结束");
        Scanner in = new Scanner(System.in);//生成一个输入流对象

        in.next();//等待用户输入
    }
}
