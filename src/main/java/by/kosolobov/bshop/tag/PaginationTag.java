package by.kosolobov.bshop.tag;

import by.kosolobov.bshop.entity.Book;
import by.kosolobov.bshop.entity.Service;
import by.kosolobov.bshop.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Deque;

public class PaginationTag extends SimpleTagSupport {
    private static final String[] BOOK_HEAD = {"Barber ID", "Client ID", "Service ID", "Data", "Time"};
    //language=HTML
    private static final String TABLE = "<table>%s</table>";
    //language=HTML
    private static final String THEAD = "<thead>%s</thead>";
    //language=HTML
    private static final String TBODY = "<tbody>%s</tbody>";
    //language=HTML
    private static final String TFOOT = "<tfoot>%s</tfoot>";
    //language=HTML
    private static final String ROW = "<tr>%s</tr>";
    //language=HTML
    private static final String BODY_COL = "<td>%s</td>";
    //language=HTML
    private static final String HEAD_COL = "<th>%s</th>";
    private final JspWriter out = getJspContext().getOut();
    private Deque items;

    public void setItems(Deque items) {
        this.items = items;
    }

    @Override
    public void doTag() throws JspException, IOException {
        System.out.println("tag enter");
        Object peek = items.peek();

        if (peek instanceof Book) {
            System.out.println("book start");
            doBook(items);
        } else if (peek instanceof Service) {
            doService(items);
        } else if (peek instanceof User) {
            doUser(items);
        } else {
            doNothing();
        }
        System.out.println("tag exit");
    }

    private void doBook(Deque<Book> books) throws IOException {
        String table = TABLE.formatted(THEAD, TBODY, TFOOT);

        //build thead
        StringBuilder head = new StringBuilder();
        for (String col : BOOK_HEAD) {
            head.append(HEAD_COL.formatted(col));
        }

        //build tbody
        StringBuilder body = new StringBuilder();
        for (Book book : books) {
            StringBuilder bookBuilder = new StringBuilder();
            bookBuilder.append(BODY_COL.formatted(book.getBarberId()));
            bookBuilder.append(BODY_COL.formatted(book.getClientId()));
            bookBuilder.append(BODY_COL.formatted(book.getClientId()));
            bookBuilder.append(BODY_COL.formatted(book.getBookDate()));
            bookBuilder.append(BODY_COL.formatted(book.getBookTime()));
            body.append(ROW.formatted(bookBuilder));
        }

        //build tfoot
        StringBuilder foot = new StringBuilder(); //todo: build footer for book table

        //fill thead tbody tfoot
        table = table.formatted(head, body, foot);

        //show table
        out.print(table);
    }

    private void doService(Deque<Service> services) {

    }

    private void doUser(Deque<User> users) {

    }

    private void doNothing() {

    }
}
