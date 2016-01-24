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

            tagView.append("<table border=\"1\">");
            tagView.append("<tr>");
            tagView.append("<td> Departure </td>");
            tagView.append("<td> Destination </td>");
            tagView.append("<td> Departure Time </td>");
            tagView.append("<td> Destination Time </td>");
            tagView.append("<td> Price (€)</td>");
            tagView.append("<td> Places left</td>");
            tagView.append("<td> Buy it!</td>");
            tagView.append("<tr>");

            tagView.append("</tr>");
            for (int i = (page - 1) * 25; i < ON_PAGE * page; i++) {
                if (i == count) break;
                tagView.append("<tr>");

                tagView.append("<td>" + directions.get(i).getDeparture() + "</td>");
                tagView.append("<td>" + directions.get(i).getDestination() + "</td>");
                tagView.append("<td>" + directions.get(i).getDepTime() + "</td>");
                tagView.append("<td>" + directions.get(i).getDestTime() + "</td>");
                tagView.append("<td>" + directions.get(i).getBasicPrice() + "</td>");
                tagView.append("<td>" + directions.get(i).getCapacity() + "/" + directions.get(i).getLeftPlaces() + "</td>");
                tagView.append("<td> <input type=\"button\" value=\"BUY\" onclick=\"window.location = '/newOrder?id=" + directions.get(i).getId() + "'\"</td>");
//                tagView.append("<td>" + direction.getDeparture() + "</td>");

                tagView.append("</tr>");

            }
            tagView.append("</table>");
            pageContext.getOut().print(tagView);
        } catch (IOException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}