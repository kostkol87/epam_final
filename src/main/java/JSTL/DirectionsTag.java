package JSTL;

import DAObjects.Direction;
import DAObjects.EntitiesUtils;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 *Custom JSTL tag, generates html table filled by all available directions
 */
public class DirectionsTag extends TagSupport {
    private static final Logger log = Logger.getLogger(DirectionsTag.class);
    public static final int ON_PAGE = 25;
    private int admin;
    private int page;
    private StringBuilder tagView;

    /**
     * this is required tag
     * @param page - current page (used by paginator)
     */
    public void setPage(int page) {
        this.page = page;
    }

    public void setAdmin(int isAdmin) {
        this.admin = isAdmin;
    }

    @Override
    public int doStartTag() throws JspException {
        List<Direction> directions = EntitiesUtils.getDirections();
        int count = directions.size();
        tagView = new StringBuilder();
        String rmTitle = "";
        String rmButton1 = "";
        String rmButton2 = "";
        if(admin == 3){
            rmTitle = "<td>RM</td>";
            rmButton1 = "<td><button onclick=\"accept(";
            rmButton2 =                                     ")\"> - </button></td></tr>";
        }
        try {

            tagView.append("<table border=\"1\">"+
                            "<tr>"+
                            "<td> Departure </td>"+
                            "<td> Destination </td>"+
                            "<td> Departure Time </td>"+
                            "<td> Destination Time </td>"+
                            "<td> Price (€)</td>"+
                            "<td> Places left</td>"+
                            "<td> Buy it!</td>"+ rmTitle+
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
                                "<td> <input type=\"button\" value=\"BUY\" onclick=\"window.location = '/newOrder?id=" +
                                    directions.get(i).getId() + "'\"</td>");
                if (admin == 3){
                    tagView.append(rmButton1  + i + rmButton2);
                }

            }
            tagView.append("</table>");
            pageContext.getOut().print(tagView);
        } catch (IOException | IndexOutOfBoundsException e) {
            log.warn("troubles in Directions JST tag");
            e.printStackTrace();

        }
            return SKIP_BODY;
    }
}