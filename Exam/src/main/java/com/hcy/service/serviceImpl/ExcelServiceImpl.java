package com.hcy.service.serviceImpl;

import com.hcy.entity.Question;
import com.hcy.service.ExcelService;
import com.hcy.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExcelServiceImpl implements ExcelService {
    private List list;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String sql;
    private Question question;

    /**
     * 查询试题表中的数据
     * @return
     */
    @Override
    public List<Question> getReport() {
        list = new ArrayList();
        //获取连接
        connection = DBUtils.getInstance().openConnection();
        try {
            statement = connection.createStatement();
            sql = "select que.q_id id,que.q_type ty,que.q_title title,que.q_select sel ,que.q_score score,que.q_answer answer,que.q_explaination exe from question que";
            resultSet = statement.executeQuery(sql);//执行sql
            int temp;
           /* for (int i = 0; i < ; i++) {

            }*/
            while(resultSet.next()){
                question = new Question();
                question.setQ_id(resultSet.getString("id"));
                String type = resultSet.getString("ty");
                if(type.contains("q1")){
                    question.setQ_type("单选题");//多选题、填空题、判断题、主观题
                }else if(type.contains("q2")){
                    question.setQ_type("多选题");
                }else if(type.contains("q3")){
                    question.setQ_type("填空题");
                }else if(type.contains("q4")){
                    question.setQ_type("判断题");
                }else{
                    question.setQ_type("主观题");
                }
//                question.setQ_type(resultSet.getString("ty"));

                question.setQ_title(resultSet.getString("title"));
                String title = resultSet.getString("title");
//                System.out.println("题目是啥："+title);
                question.setQ_select(resultSet.getString("sel"));
                question.setQ_score(resultSet.getString("score"));
                question.setQ_answer(resultSet.getString("answer"));
                question.setQ_explaination(resultSet.getString("exe"));
                list.add(question);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }
}
