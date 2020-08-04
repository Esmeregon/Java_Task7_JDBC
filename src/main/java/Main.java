import org.junit.Assert;
import org.junit.Test;


public class Main {
    String faid = "654019006898"; //Номер ЛС


    @Test
    public  void test(){
        Assert.assertTrue(true);
        System.out.println("CustomerId: " + DB.selectCustomerId(faid));
        System.out.println("Склонность к оттоку до апдейта: " + DB.selectOutflow(DB.selectCustomerId(faid)).keySet());
        DB.updateOutflow(DB.selectOutflow(DB.selectCustomerId(faid)),DB.selectCustomerId(faid));
        System.out.println("Склонность к оттоку после апдейта: " + DB.selectOutflow(DB.selectCustomerId(faid)).keySet());
    }
}
