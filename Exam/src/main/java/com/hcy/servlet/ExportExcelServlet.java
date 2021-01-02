package com.hcy.servlet;

import com.hcy.entity.Question;
import com.hcy.service.serviceImpl.ExcelServiceImpl;
import com.hcy.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ExportExcelServlet")
public class ExportExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }
    @SuppressWarnings("deprecation")
    private void process(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.reset();
        response.setContentType("application/x-msexcel");

        String fileName = URLEncoder.encode("试题报表", "utf-8");
        response.addHeader("Content-Disposition", "attachment; filename="
                + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".xls");
        HSSFWorkbook wb = new HSSFWorkbook();
        // 表头数足
        String[] title = { "试题序号", "题目类型", "题目", "选项", "答案", "分值", "解析"};
        ExcelServiceImpl excelService = new ExcelServiceImpl();
        List<Question> list = excelService.getReport();
       /* ArrayList<Question> content = new ArrayList<Question>();
        for (int i = 0; i < 7; i++) {
            Question shiti = new Question();
            shiti.setQ_id("22");
            shiti.setQ_type("q4");
            shiti.setQ_title("题目");
            shiti.setQ_select("选项");
            shiti.setQ_answer("答案");
            shiti.setQ_score("90");
            shiti.setQ_explaination("解析112");
            content.add(shiti);
        }*/
        ExcelUtil excelUtil = new ExcelUtil();
        // 生成excel中的一张表格
//        HSSFSheet sheet = excelUtil.export(wb, "sheet1", title, content);
        HSSFSheet sheet = excelUtil.export(wb,"sheet1",title, (ArrayList<Question>) list);
        // 直接存到服务器端
        // FileOutputStream fileOut;
        // try {
        // fileOut = new FileOutputStream("workbook.xls");
        // wb.write(fileOut);
        // fileOut.close();
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // 写入输出流
        try {
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
