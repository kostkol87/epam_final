package JSTL;

import DAObjects.Direction;
import DAObjects.EntitiesUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 *Custom JSTL tag, generates html table filled by all available directions
 */
public class DirectionsTag extends TagSupport {

    public static final int ON_PAGE = 25;
    private int page;
    private StringBuilder tagView;

    /**
     * this is required tag
     * @param page - current page (used by paginator)
     */
    public void setPage(int page) {
        System.out.println("Attribute OK in directions");
        this.page = page;
    }

    @Override
    public int doStartTag() throws JspException {
        List<Direction> directions = EntitiesUtils.getDirections();
        int count = directions.size();
        tagView = new StringBuilder();

        try {

            tagView.append("<table border=\"1\">"+
                            "<tr>"+
                            "<td> Departure </td>"+
                            "<td> Destination </td>"+
                            "<td> Departure Time </td>"+
                            "<td> Destination Time </td>"+
                            "<td> Price (€)</td>"+
                            "<td> Places left</td>"+
                            "<td> Buy it!</td>"+
                            "<tr>");

            tagView.append("</tr>");
            for (int i = (page - 1) * 25; i < ON_PAGE * page; i++) {
                if (i == count) break;
                tagView.append("<tr>"+
                                "<td>" + directions.get(i).getDeparture() + "</td>"+
                                "<td>" + directions.get(i).getDestination() + "</td>"+
                                "<td>" + directions.get(i).getDepTime() + "</td>"+
                                "<td>" + directions.get(i).getDestTime() + "</td>"+
                                "<td>" + directions.get(i).getBasicPrice() + "</td>"+
                                "<td>" + directions.get(i).getCapacity() + "/" + directions.get(i).getLeftPlaces() + "</td>"+
                                "<td> <input type=\"button\" value=\"BUY\" onclick=\"window.location = '/newOrder?id=" + directions.get(i).getId() + "'\"</td>"+
                                "</tr>");

            }
            tagView.append("</table>");
            pageContext.getOut().print(tagView);
        } catch (IOException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}