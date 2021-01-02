package com.hcy.service.serviceImpl;

import com.hcy.dao.PersonInfomationDao;
import com.hcy.dao.daoImpl.StudentInformationDaoImple;
import com.hcy.entity.Pager;
import com.hcy.entity.Student;
import com.hcy.service.PersonInformationService;

import java.util.List;

public class StudentInformationServiceImple implements PersonInformationService<Student> {

	/**
	 * 从model的Dao层获得学生信息
	 * 
	 * @param student 需要查找的学生信息
	 * @return 返回已查找的学生信息 ，如果查找成功则返回学生信息，否则返回为null
	 * 
	 */
	@Override
	public Student findPersonInformationFromModel(Student student) {
		// TODO Auto-generated method stub

		PersonInfomationDao<Student> personInfomationDao = new StudentInformationDaoImple();
		Student stu = personInfomationDao.findPersonInforFromDB(student);

		if (stu != null) {
			return stu;
		}

		return null;
	}

	/**
	 * 从model的修改学生信息
	 * 
	 * @param student 需要修改的学生信息
	 * @return 返回修该后的结果
	 * 
	 */
	@Override
	public int updatePersonInformationToModel(Student student) {
		// TODO Auto-generated method stub
		PersonInfomationDao<Student> personInfomationDao = new StudentInformationDaoImple();
		int result = personInfomationDao.updatepersonInformationToDB(student);
		if (result == 1) {
			return result;
		}

		return 0;
	}

	/**
	 * 
	 * 查找所有学生信息
	 */
	@Override
	public Pager<Student> findAllPersonInformationFromModel(int pageNum, int pageSize) {

		int startIndex = (pageNum - 1) * pageSize;// 开始查询的索引
		PersonInfomationDao<Student> personInfomationDao = new StudentInformationDaoImple();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select S_ID,S_Pass,S_Name,S_Sex ,student.CLS_ID as clazzId,classes.CLS_Name as clazzName from student left outer join classes");
		sql.append(" on student.CLS_ID = classes.CLS_ID");
		sql.append(" where 1=1");
		sql.append(" limit " + startIndex);
		sql.append("," + pageSize);
		List<Student> list = personInfomationDao.findPersonInforFromDB(sql.toString(), null);
		// 分页查询
		Pager<Student> pager = new Pager<>(pageNum, pageSize, list);
		if (pager != null) {
			return pager;
		}

		return null;
	}

	/**
	 * 添加学生信息
	 * 
	 * @param person需要添加学生的信息
	 * 
	 */
	@Override
	public int AddPersonInformationToModel(Student person) {
		// TODO Auto-generated method stub
		PersonInfomationDao<Student> personInfomationDao = new StudentInformationDaoImple();
		int result = personInfomationDao.addPersonInformationToDB(person);
		if (result == 1) {
			return result;
		}

		return 0;
	}

	/**
	 * 删除学生信息
	 * 
	 * @param persons需要shanchu学生的信息
	 * 
	 */
	@Override
	public int deletePersonInformationToMedel(List<Student> persons) {
		// TODO Auto-generated method stub
		PersonInfomationDao<Student> personInfomationDao = new StudentInformationDaoImple();
		int result = personInfomationDao.deletePersonInformationToDB(persons);
		if (result == 1) {
			return result;
		}

		return 0;
	}

}
