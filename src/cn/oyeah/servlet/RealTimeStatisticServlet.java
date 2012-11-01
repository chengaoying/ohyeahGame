package cn.oyeah.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.oyeah.service.IRealTimeStatService;
import cn.oyeah.service.impl.RealTimeStatServiceImpl;
import cn.oyeah.util.DateTimeUtils;


/**
 * 实时统计
 */
public class RealTimeStatisticServlet extends HttpServlet {

	private static final long serialVersionUID = -801347960310163145L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String time = request.getParameter("time");
		if(time == null){
			time = DateTimeUtils.getCurrentDate();
		}
		System.out.println("realTimeStatistic");
		IRealTimeStatService realTimeService = new RealTimeStatServiceImpl();
		Map<String,Integer> countMap =  realTimeService.queryRealSubscribe(time);
		Map<String,Integer> allCountMap = realTimeService.getAllSubscribeDetail(time);
		Map<String,String> nameMap = realTimeService.getPurchaseGameName(time);
		request.setAttribute("countMap", countMap);
		request.setAttribute("allCountMap", allCountMap);
		request.setAttribute("nameMap", nameMap);
		request.setAttribute("time", time);
		request.getRequestDispatcher("/web/realTimeStat/realTimeStat.jsp").forward(request, response);
		
	}

}
