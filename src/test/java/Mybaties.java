import demo.config.DataConfig;
import demo.config.info.JdbcInfo;
import demo.dao.mapper.Commoditymapper;
import demo.dao.mapper.Ordermapper;
import demo.dao.mapper.UserMapper;
import demo.entity.Commodity;
import demo.entity.Order;
import demo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataConfig.class, JdbcInfo.class})
public class Mybaties {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Ordermapper ordermapper;

    @Autowired
    private Commoditymapper commoditymapper;

    @Test
    public void testuserselect(){
        User u1=userMapper.finduserbyid(1);
        if(u1!=null){
            Assert.assertEquals(1,u1.getId());
        }else {
            Assert.fail();
        }
        u1=userMapper.finduserbyup("yxl", DigestUtils.md5DigestAsHex("123456".getBytes()));
        if(u1!=null){
            Assert.assertEquals("yxl",u1.getUsername());
        }else {
            Assert.fail();
        }
        u1=userMapper.finduserbyname("test");
        if(u1!=null){
            Assert.assertEquals("test",u1.getName());
        }else {
            Assert.fail();
        }
    }
    @Test
    public void testuserinsert(){
        User u = new User();
        u.setName("test3");
        u.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        userMapper.insertUser(u);
        User result = userMapper.finduserbyname("test3");
        if (result != null) {
            Assert.assertEquals("test3", result.getName());
        } else {
            Assert.fail();
        }
    }
    @Test
    public void testuserUpdate() {
        User u = new User();
        u.setId(6);
        u.setName("test2");
        userMapper.updateUser(u);
        User result = userMapper.finduserbyid(u.getId());
        if (result != null) {
            Assert.assertEquals("test2", result.getName());
        } else {
            Assert.fail();
        }
    }

    @Test
    public void testorderinsert(){
        Order order=new Order();
        order.setCid(1);
        order.setUid(1);
        order.setTime("10.15");
        int i=ordermapper.insertOrder(order);
        if(i!=1){
            Assert.assertEquals(1,1);
        }else{
            Assert.fail();
        }
    }

    @Test
    public void testorderdelete(){
        int i=ordermapper.deleteorder(1,1);
        if(i!=1){
            Assert.assertEquals(1,1);
        }
        else{
            Assert.fail();
        }
    }
    @Test void testcommodityinsert(){
        Commodity commodity=new Commodity();
        commodity.setCname("xxx");
        commodity.setCategory("yyy");
        commodity.setPrice("zzz");
        int i=commoditymapper.insertcommodity(commodity);
        if(i!=1){
            Assert.assertEquals(1,1);
        }else{
            Assert.fail();
        }
    }
}
