package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.service.TodoListService;
import edu.kh.todoList.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/delete")
public class DeleteServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int todoNo = Integer.parseInt( req.getParameter("todoNo") );
			
			TodoListService service = new TodoListServiceImpl();
			
			int result = service.todoDelte(todoNo);
			
			HttpSession session = req.getSession();

			
			if(result > 0 ) {
				
				session.setAttribute("message", "삭제 되었습니다!");
				resp.sendRedirect("/");
				return;
			};
			
				session.setAttribute("message", "존재하지 않는 번호 입니다");
				resp.sendRedirect("/");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
} //-------------