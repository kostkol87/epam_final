package JSTL;

import DAObjects.EntitiesUtils;
import DAObjects.Order;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;


/**
 * Custom JSTL tag, generates html table filled by orders of current user
 */

public class OrdersTag extends TagSupport{
    private StringBuilder tagView;
    private int userId;

    /**
     * Required tag
     * @param userId - witch orders will show
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int doStartTag() throws JspException {
        tagView = new StringBuilder();
        List<Order> orders = EntitiesUtils.getOrders(userId);
        try{
            tagView.append("<table border=\"1\">"+
                            "<tr>"+
                            "<td> Departure </td>"+
                            "<td> Destination </td>"+
                            "<td> Departure Time </td>"+
                            "<td> Destination Time </td>"+
                            "<td> Passenger's count </td>"+
                            "<td> Baggage </td>"+
                            "<td> Priority queue </td>"+
                            "<td> Summa (€)</td>"+
                            "<td> Is paid:</td>"+
                            "<tr>"+
                            "</tr>");
            for (Order order: orders){
                tagView.append("<tr>"+
                                "<td>" + order.getDirection().getDeparture() + "</td>"+
                                "<td>" + order.getDirection().getDestination() + "</td>"+
                                "<td>" + order.getDirection().getDepTime() + "</td>"+
                                "<td>" + order.getDirection().getDestTime() + "</td>"+
                                "<td>" + order.getQuantity() + "</td>"+
                                "<td>" + order.isBaggage() + "</td>"+
                                "<td>" + order.isPriorityQueue() + "</td>"+
                                "<td>" + order.getSumma() + "</td>"+
                                "<td>" + order.isPaid() + "</td>");
                if (!order.isPaid()){
                    tagView.append("<td> <input type=\"button\" value=\"CHANGE\" onclick=\"window.location = '/changeOrder?id=" + order.getId() + "'\"</td>"+
                                    "<td> <input type=\"button\" value=\"PAY\" onclick=\"window.location = '/payOk?id=" + order.getId() + "'\"</td>"+
                                    "<td> <input type=\"button\" value=\"REMOVE\" onclick=\"window.location = '/changeOrder?removeId=" + order.getId() + "'\"</td>");
                }
//

                tagView.append("</tr>");
            }
            pageContext.getOut().print(tagView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
