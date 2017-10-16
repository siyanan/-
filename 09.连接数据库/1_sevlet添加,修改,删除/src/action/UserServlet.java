package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

public class UserServlet extends HttpServlet {
	UserService service=new UserService();
	//构造方法
	public UserServlet() {
		super();
	}

	//销毁方法
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	//doGet
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	//doPost
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//设置请求编码
		response.setCharacterEncoding("UTF-8");//设置响应编码
		String actionType=request.getParameter("actionType");
		System.out.println("业务类型:"+actionType);
		if ("add".equals(actionType)) {
			addUser(request, response);
		}else if ("update".equals(actionType)) {
			upUser(request, response);
		}else if ("del".equals(actionType)) {
			delUser(request, response);
		}else{
			System.out.println("Servlet懵了");
		}
		
	}

	public void delUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id=request.getParameter("id");
		
		String[] param={id};
		int i=service.delete(param);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (i!=-1) {
			out.print("删除成功!");
		} else {
			out.print("删除失败!");
		}
	}

	public void upUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获得表单数据: getParameter(标签name值)
		
		String name=request.getParameter("name");
		String password=request.getParameter("pass");
		String sex=request.getParameter("sex");
		String age=request.getParameter("age");
		String id=request.getParameter("id");
		
		String[] param={name,password,sex,age,id};
		int i=service.update(param);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (i!=-1) {
			out.print("修改成功!");
		} else {
			out.print("修改失败!");
		}
	}

	public void addUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//获得表单数据: getParameter(标签name值)
		String name=request.getParameter("name");
		String password=request.getParameter("pass");
		String sex=request.getParameter("sex");
		String age=request.getParameter("age");
		
		//System.out.println(name+" "+age+" "+password+" "+sex);
		String[] param={name,password,sex,age};
		int i=service.insert(param);
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (i!=-1) {
			out.print("添加成功!");
		} else {
			out.print("添加失败!");
		}
	}

	//初始化
	public void init() throws ServletException {
		// Put your code here
	}

}
