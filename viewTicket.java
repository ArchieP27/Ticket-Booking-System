import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class ViewTicket extends JFrame {

    ImageIcon imageIcon = new ImageIcon("C:\\Users\\porov\\JAVACODES\\AIRLINE RESERVATION\\Res\\ticketIcon.png");
    ResultSet resultSet;
    String status="Confirmed",date,paymentStatus="Completed", pname,fdate, from, to,departure, fno, arrival;
    public ViewTicket(String pID){
        setTitle("TICKET");
        setIconImage(imageIcon.getImage());
        setSize(900,400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(Design.createImageBackground("C:\\Users\\porov\\JAVACODES\\AIRLINE RESERVATION\\Res\\ticketTemplate.png"));
        setLayout(null);

        resultSet = LoginDB.getPassengerDetails(pID);
        try {
            while (resultSet.next()) {
                pname = resultSet.getString("passenger_name");
                date=resultSet.getString("BookingDate");
                fdate= resultSet.getString("flight_date");
                from= resultSet.getString("Departure_Airport_Code");
                to= resultSet.getString("Arrival_Airport_Code");
                departure = resultSet.getString("departure_time");
                arrival = resultSet.getString("arrival_time");
                fno=resultSet.getString("flight_number");
            }
        }catch (Exception e){}

        JLabel pid = new JLabel("Ticket ID : "+pID);
        pid.setFont(new Font("Times New Roman",Font.BOLD,25));
        pid.setBounds(670,10,300,50);
        pid.setForeground(Color.PINK);
        add(pid);

        JLabel stat = new JLabel(status);
        stat.setFont(new Font("Times New Roman",Font.BOLD,20));
        stat.setBounds(100,143,100,20);
        add(stat);

        JLabel bd = new JLabel(date);
        bd.setFont(new Font("Times New Roman",Font.BOLD,20));
        bd.setBounds(400,143,100,20);
        add(bd);

        JLabel ps = new JLabel(paymentStatus);
        ps.setFont(new Font("Times New Roman",Font.BOLD,20));
        ps.setBounds(700,143,200,20);
        add(ps);

        JLabel pn = new JLabel(pname);
        pn.setFont(new Font("Times New Roman",Font.BOLD,20));
        pn.setBounds(320,203,300,20);
        add(pn);

        JLabel fd = new JLabel(fdate);
        fd.setFont(new Font("Candara",Font.PLAIN,20));
        fd.setBounds(30,320,150,20);
        add(fd);

        JLabel fr = new JLabel(from);
        fr.setFont(new Font("Candara",Font.PLAIN,20));
        fr.setBounds(220,320,150,20);
        add(fr);

        JLabel dpt = new JLabel(departure);
        dpt.setFont(new Font("Candara",Font.PLAIN,20));
        dpt.setBounds(355,320,150,20);
        add(dpt);

        JLabel Fno = new JLabel(fno);
        Fno.setFont(new Font("Candara",Font.PLAIN,20));
        Fno.setBounds(510,320,150,20);
        add(Fno);

        JLabel To = new JLabel(to);
        To.setFont(new Font("Candara",Font.PLAIN,20));
        To.setBounds(655,320,150,20);
        add(To);

        JLabel arr = new JLabel(arrival);
        arr.setFont(new Font("Candara",Font.PLAIN,20));
        arr.setBounds(785,320,150,20);
        add(arr);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewTicket("C0024");
    }

}
