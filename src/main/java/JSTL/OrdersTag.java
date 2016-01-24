package JSTL;

import DAObjects.EntitiesUtils;
import DAObjects.Order;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class OrdersTag extends TagSupport{
    private StringBuilder tagView;
    private int userId;

    public void setUserId(int userId) {
        System.out.println("Attribute OK in orders");
        this.userId = userId;
    }

    @Override
    public int doStartTag() throws JspException {
        tagView = new StringBuilder();
        List<Order> orders = new EntitiesUtils().getOrders(userId);
        System.out.println(orders.size() + " is a size");
        try{
            tagView.append("<table border=\"1\">");
            tagView.append("<tr>");
            tagView.append("<td> Departure </td>");
            tagView.append("<td> Destination </td>");
            tagView.append("<td> Departure Time </td>");
            tagView.append("<td> Destination Time </td>");
            tagView.append("<td> Passenger's count </td>");
            tagView.append("<td> Baggage </td>");
            tagView.append("<td> Priority queue </td>");
            tagView.append("<td> Summa (€)</td>");
            tagView.append("<td> Is paid:</td>");
            tagView.append("<tr>");
            tagView.append("</tr>");
            for (Order order: orders){
                tagView.append("<tr>");
                tagView.append("<td>" + order.getDirection().getDeparture() + "</td>");
                tagView.append("<td>" + order.getDirection().getDestination() + "</td>");
                tagView.append("<td>" + order.getDirection().getDepTime() + "</td>");
                tagView.append("<td>" + order.getDirection().getDestTime() + "</td>");
                tagView.append("<td>" + order.getQuantity() + "</td>");
                tagView.append("<td>" + order.isBaggage() + "</td>");
                tagView.append("<td>" + order.isPriorityQueue() + "</td>");
                tagView.append("<td>" + order.getSumma() + "</td>");
                tagView.append("<td>" + order.isPaid() + "</td>");
                if (!order.isPaid()){
                    tagView.append("<td> <input type=\"button\" value=\"CHANGE\" onclick=\"window.location = '/changeOrder?id=" + order.getId() + "'\"</td>");
                    tagView.append("<td> <input type=\"button\" value=\"PAY\" onclick=\"window.location = '/payOk?id=" + order.getId() + "'\"</td>");
                }
//

                tagView.append("</tr>");
            }
            pageContext.getOut().print(tagView);
            System.out.println(tagView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
