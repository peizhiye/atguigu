package com.atguigu.struts2.app;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EmployeeAction implements RequestAware, ModelDriven<Employee>, Preparable{
	
	private Dao dao = new Dao();
	
	private Employee employee;
	
	public String update(){
		dao.update(employee);
		return "success";
	}
	
	public void prepareUpdate(){
		employee = new Employee();
	}

	public String edit(){	
		// 1. 获取传入的 employeeId: employee.getEmployeeId()
		
		// 2. 根据 employeeId 获取 Employee 对象
//		Employee emp = dao.get(employee.getEmployeeId());
		
		// 3. 把栈顶对象的属性装配好：此时栈顶对象是 employee
		// 目前的 employee 对象只有 employeeId 属性，其他属性为 null
		/* 
		 * Struts2 表单回显时：从值栈栈顶开始查找匹配的属性，若找到就添加到表单元素的 value 属性中。
		 * 此方法太繁琐，不推荐。
		 */
//		employee.setEmail(emp.getEmail());
//		employee.setFirstName(emp.getFirstName());
//		employee.setLastName(emp.getLastName());
		
		// 不能够进行表单的回显，因为经过重新赋值的 employee 对象已经不再是栈顶对象了
//		employee = dao.get(employee.getEmployeeId());
		
		// 手动地把从数据库中获取的 Employee 对象放到值栈的栈顶
		// 但此时值栈栈顶及第二个对象均为 Employee 对象，不够完美
//		ActionContext.getContext().getValueStack().push(dao.get(employee.getEmployeeId()));
		
		return "edit";
	}
	
	public void prepareEdit(){
		employee = dao.get(employeeId);
	}
	
	public String save(){
		// 1. 获取请求参数：通过定义对应属性的方式
		// 2. 调用 Dao 的 save 方法
		dao.save(employee);
		
		// 3. 通过 redirectAction 的方式响应结果给 emp-list
		return "success";
	}
	
	public void prepareSave(){
		employee = new Employee();
	}
	
	public String delete(){
		dao.delete(employeeId);
		// 返回结果的类型应为：redirectAction.
		// 也可以是 chain：实际上 chain 是没有必要的。因为不需要在下一个 Action 中保留当前 Action 的状态。
		// 还有，若使用 chain，则到达目标页面后，地址栏显示的依然是 删除 的那个链接，刷新时会有重复提交。
		return "success";
	}
	
	public String list(){
		request.put("emps", dao.getEmployees());
		return "list";
	}
	
	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}
	
	// 需要在当前的 EmployeeAction 中定义 employeeId 属性。
	// 以接受请求参数
	private Integer employeeId;
	
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	@Override
	public Employee getModel() {
		// 判断是 Create 还是 Edit
		// 若为 Create，则 employee = new Employee();
		// 若为 Edit，则 employee = dao.get(employeeId);
		// 判断标准为是否有 employeeId 这个请求参数。若有该参数，则为 Edit，若没有该参数，则为 Create
		// 若通过 employeeId 来判断，则需要在 modelDriven 拦截器之前先执行一个 params 拦截器
		// 而这可以通过使用 paramsPrepareParams 拦截器栈实现
		// 需要在 struts.xml 文件中配置使用 paramsPrepareParams 作为默认的拦截器栈
		
//		if (employeeId == null)
//			employee = new Employee();
//		else
//			employee = dao.get(employeeId);
		return employee;
	}

	/*
		prepare 方法的主要作用：为 getModel 方法准备 model
	*/
	@Override
	public void prepare() throws Exception {
		System.out.println("prepare...");
	}
	
}
