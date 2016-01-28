package JSTL;

import DAO.Entities.Order;
import DAO.Utils.Orders;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Custom JSTL tag, generates html table filled by orders of current user
 */

public class OrdersTag extends TagSupport {
    private StringBuilder tagView;
    private int userId;
    private String locale;
    private ResourceBundle localedDict;

    /**
     * Required tag
     *
     * @param userId - witch orders will show
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {

        tagView = new StringBuilder();
        if (locale == "" || locale == null) {
            this.setLocale("ru_RU");
        }
        String[] sLocale = locale.split("_");
        Locale myLocale = new Locale(sLocale[0], sLocale[1]);
        localedDict = ResourceBundle.getBundle("local", myLocale);
        List<Order> orders = Orders.getOrders(userId);
        try {
            tagView.append("<table border=\"1\">" +
                    "<tr>" +
                    "<td> " + localedDict.getString("Departure") + " </td>" +
                    "<td> " + localedDict.getString("Destination") + " </td>" +
                    "<td> " + localedDict.getString("DepTime") + " </td>" +
                    "<td> " + localedDict.getString("DestTime") + " </td>" +
                    "<td> " + localedDict.getString("PassCount") + " </td>" +
                    "<td> " + localedDict.getString("Baggage") + " </td>" +
                    "<td> " + localedDict.getString("Priority") + " </td>" +
                    "<td> " + localedDict.getString("Summa") + " </td>" +
                    "<td> " + localedDict.getString("IsPaid") + " </td>" +
                    "<tr>" +
                    "</tr>");
            for (Order order : orders) {
                tagView.append("<tr>" +
                        "<td>" + order.getDirection().getDeparture() + "</td>" +
                        "<td>" + order.getDirection().getDestination() + "</td>" +
                        "<td>" + order.getDirection().getDepTime() + "</td>" +
                        "<td>" + order.getDirection().getDestTime() + "</td>" +
                        "<td>" + order.getQuantity() + "</td>" +
                        "<td>" + (order.isBaggage() ? localedDict.getString("true") : localedDict.getString("false")) + "</td>" +
                        "<td>" + (order.isPriorityQueue() ? localedDict.getString("true") : localedDict.getString("false")) + "</td>" +
                        "<td>" + order.getSumma() + "</td>" +
                        "<td>" + (order.isPaid() ? localedDict.getString("true") : localedDict.getString("false")) + "</td>");
                if (!order.isPaid()) {
                    tagView.append("<td> <input type=\"button\" value=\"" + localedDict.getString("Change") + "\" onclick=\"window.location = '/changeOrder?id=" + order.getId() + "'\"</td>" +
                            "<td> <input type=\"button\" value=\"" + localedDict.getString("Pay") + "\" onclick=\"window.location = '/payOk?id=" + order.getId() + "'\"</td>" +
                            "<td> <input type=\"button\" value=\"" + localedDict.getString("Remove") + "\" onclick=\"window.location = '/changeOrder?removeId=" + order.getId() + "'\"</td>");
                }

                tagView.append("</tr>");
            }
            pageContext.getOut().print(tagView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
