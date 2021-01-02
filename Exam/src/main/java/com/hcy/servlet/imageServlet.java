package com.hcy.servlet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/imageServlet")
public class imageServlet extends HttpServlet {
    public static final int NUMBER = 5;
    //1,0,l,容易混淆故去掉这几个字符
    String strs = "qwertyuipasdfghjkzxcvbnm23456789一二三四五六七八九";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建模板大小
        BufferedImage bufferImage = new BufferedImage(100, 40, BufferedImage.TYPE_INT_RGB);
        //创建画笔
        Graphics graphics = bufferImage.getGraphics();
        //调整画笔颜色
        graphics.setColor(Color.green);
        //设置画笔初始位子和可画区域大小
        graphics.fillRect(0, 0, 100, 40);
        int size = strs.length();
        char[] codes = new char[NUMBER];
        Random random = new Random();
        graphics.setFont(new Font("宋体", Font.PLAIN, 16));
        for (int i=0; i<NUMBER; i++){
            int index = random.nextInt(size);
            Character c = strs.charAt(index);
            codes[i]=c;
            graphics.setColor(Color.BLUE);
            //画出单个字符大小位置
            graphics.drawString(c.toString(),i*20,30);
        }
        //将生成的验证码字符串保存在session中
        req.getSession().setAttribute("code", new String(codes));
        //回收资源
        graphics.dispose();
        //显示图像及格式
        ImageIO.write(bufferImage, "png", resp.getOutputStream());

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
