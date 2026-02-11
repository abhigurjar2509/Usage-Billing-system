import com.sun.net.httpserver.*;
import java.net.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class GymBilling {

    static Map<Integer, Member> members = new HashMap<>();
    static Map<Integer, Equipment> equipments = new HashMap<>();
    static Map<Integer, Usage> usages = new HashMap<>();

    public static void main(String[] args) throws Exception {

        // Sample Data
        members.put(1, new Member(1, "Abhishek", 1)); // 1 free hour
        equipments.put(1, new Equipment(1, "Trademill", 30));

        // Create usage sample
        Usage usage = new Usage(1, 1, 1);
        usages.put(1, usage);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/bill", exchange -> {

            String query = exchange.getRequestURI().getQuery();
            Map<String, String> params = queryToMap(query);

            int usageId = Integer.parseInt(params.get("usageId"));

            Usage u = usages.get(usageId);
            u.endTime = LocalDateTime.now();

            long minutes = ChronoUnit.MINUTES.between(
                    u.startTime, u.endTime);

            double totalHours = minutes / 60.0;

            Member m = members.get(u.memberId);
            Equipment e = equipments.get(u.equipmentId);

            double payableHours = totalHours;

            // Deduct free hours
            if (m.freeHours > 0) {
                if (m.freeHours >= payableHours) {
                    m.freeHours -= payableHours;
                    payableHours = 0;
                } else {
                    payableHours -= m.freeHours;
                    m.freeHours = 0;
                }
            }

            double totalBill = payableHours * e.ratePerHour;
            u.amount = totalBill;

            String response = "{ \"usageId\": " + usageId +
                    ", \"totalHours\": " + totalHours +
                    ", \"payableAmount\": " + totalBill + " }";

            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(30, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        });

        server.start();
        System.out.println("Billing Server running on port 8080");
    }

    private static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            result.put(pair[0], pair[1]);
        }
        return result;
    }
}
