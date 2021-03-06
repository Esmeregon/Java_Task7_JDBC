import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DB {

    public static Connection getConnection() {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@10.28.43.13:1521:CRMTEST";
        String user = "DISAKOV";
        String password = "r9J0D3X3D8";
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String selectCustomerId(String faId) {
        String query = "select tc.s_customer_id\n" +
                "  from table_customer tc\n" +
                "  join table_con_fin_accnt_role con\n" +
                "    on tc.objid = con.fa_role2customer\n" +
                "  join table_fin_accnt fa\n" +
                "    on con.fin_accnt_role2fin_accnt = fa.objid\n" +
                " where fa.s_fa_id = '" + faId + "'";
        String customerId = "";
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                customerId = result.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerId;
    }


    public static List selectOutflow(String customerId){
        String query = "select hg.objid, hg.title\n" +
            "from table_customer tc\n" +
            "join table_hgbst_elm hg\n" +
            "on tc.x_propensity_drain2hgbst_elm = hg.objid\n" +
            "where tc.s_customer_id = '" + customerId + "'";
        String outflow = "";
        String hgObjid = "";
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {
        while (result.next()) {
            hgObjid = result.getString(1);
            outflow = result.getString(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> listOutflow = new ArrayList<>();
        listOutflow.add(hgObjid);
        listOutflow.add(outflow);
        return listOutflow;
    }


    public static void updateOutflow(String outflowObjid, String customerId) {

            String query = "update table_customer\n" +
                    "   set x_propensity_drain2hgbst_elm = " + outflowObjid + "\n" +
                    " where s_customer_id = '" + customerId + "'";
            try (Connection con = getConnection();
                 PreparedStatement statement = con.prepareStatement(query);) {
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


