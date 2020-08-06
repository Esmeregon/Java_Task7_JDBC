import org.junit.Assert;
import org.junit.Test;

public class Main {


    @Test
    public void test(){
        String faid = "654019006898"; //Номер ЛС

        String customerId = DB.selectCustomerId(faid);
        String outflowObjid;
        Object outflow = DB.selectOutflow(customerId).get(0);
        if (outflow.equals("5911"))
            outflowObjid = "5912";
        else
            outflowObjid = "5911";


        Assert.assertTrue(true);
        System.out.println("CustomerId: " + customerId);
        System.out.println("Склонность к оттоку до апдейта: " + DB.selectOutflow(customerId).get(1));
        DB.updateOutflow(outflowObjid, customerId);
        System.out.println("Склонность к оттоку после апдейта: " + DB.selectOutflow(customerId).get(1));
    }

}
