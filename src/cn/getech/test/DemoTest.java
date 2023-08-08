package cn.getech.test;

import cn.getech.test.constant.ConstantEnum;
import cn.getech.test.dto.*;
import cn.getech.test.mybatis.JDBC;
import cn.getech.test.mybatis.User;
import cn.getech.test.mybatis.Wife;
import cn.getech.test.print.PrinterUtil;
import cn.getech.test.util.HttpClientUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFWriter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DemoTest {

    //年份代表字段,从2010开始。
    public static final char[] yearSymbol = {'A','B','C','D','E','F','G','H','J','K','L','M','N','P','R','S','T','V','W','X','Y','1','2','3','4','5','6','7','8','9'};


    /***
     *  获取最后字符
     */
    @Test
    public void sub(){
        String a = "ZH-A";
        String sub = a.substring(a.length() - 1);
        System.out.println(sub);
    }


    /**
     * 向上取整
     */
    @Test
    public void calculateMaxQuantity(){
        int assignMax = 7;
        int layerMax = 6;
        System.out.println("会直接向下取整数结果为1:" + 7/6);
        System.out.println(("向上取整结果为2:" +  ((7+6-1)/6)));
        System.out.println("会直接向下取整数结果为0:" + 5/6);
        System.out.println("结果为12:" + 2*6);
        //6乘以7初一6的向上取整
        //new BigDecimal(6).multiply(new BigDecimal(assignMax) / new BigDecimal(layerMax))
    }

    /***
     * 拼棒   几拼
     */
    @Test
    public void spliteType(){
        String cryGroup = "CDGC2472305H9202X51N01W;CDGC2472306L0101D01Y04WF2;H2305Q10270W0100;";
        System.out.println(cryGroup.split(";").length);   //3

    }


    /**
     * 生成纸箱码的截取
     */
    @Test
    public void testSerial(){

        String fullName = "YBG247P-230619-A650000000101";
        int serialLength = 4;
        String segmentNo = "A65";


        if (StringUtils.isEmpty(fullName) || serialLength <= 0 || serialLength > 10) {
            System.out.println("=====================");
        }

        String serialNo = StringUtils.substring(fullName, fullName.length() - 10);
        String prefixName = StringUtils.substring(fullName, 0, fullName.length() - 10);

        String finalSerialNo = StringUtils.substring(serialNo, serialNo.length() - serialLength);

        StringBuilder maxValue = new StringBuilder();
        for (int i = 0; i < serialLength; i++) {
            maxValue.append("9");
        }

        if (StringUtils.equals(finalSerialNo, String.valueOf(maxValue))) {
            System.out.println("=====================");
        }

        String finalName = prefixName + finalSerialNo;
        System.out.println(finalName);
    }


    /**
     * 时间uuid
     */
    @Test
    public void timeStamp(){

        String aoi = "AOI-A";
        System.out.println(aoi.substring(aoi.length() -1 )); //A

        System.out.println(System.currentTimeMillis());  //1687597172268

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");
        String timeStamp = formatter.format(new Date());
        System.out.println(timeStamp);  //20230624165932000284
    }

    /***
     * map转json
     * {"cutNo":"P1D22222","illustrationCount":"222","productType":"pcl"}
     */
    @Test
    public void jsonMap(){
        Map<String, String> map = new HashMap<>();
        map.put("illustrationCount", "222");
        map.put("cutNo", "P1D22222");
        map.put("productType", "pcl");
        System.out.println(JSONUtil.toJsonStr(map));
    }

    /**
     * 排列编码
     */
    @Test
    public void split2(){
        String machine1 = "B18";
        String machine2 = "B02";
        String[] split1 = machine2.split("");
        String rangeName = split1[0] + split1[1];
        System.out.println(rangeName);
    }

    /**
     * 从泡沫盒截取纸箱
     */
    @Test
    public void indexOfCarton(){
        String boxCode = "YBG295P-D0A0001-01";
        String cartonCode = boxCode.substring(0,boxCode.lastIndexOf("-"));
        System.out.println(cartonCode);
    }

    /**
     * 截取最后-后面的同时去0
     */
    public void subStrZero(){
        String portName = "CPBZX-A-P12";
        String s = portName.substring(portName.lastIndexOf("-") + 2).replaceAll("^0*", "");
        System.out.println("=============" + s);
    }

    /**
     * 不等于  NormalResistanceA & LineMark
     */
    @Test
    public void testAssertLogic(){
        String param = "LineMark";
        if(!param.equals("NormalResistanceA") && !param.equals("LineMark")){
            System.out.println("------------------");
        }
    }

    /**
     * no exception
     */
    @Test
    public void testSplit(){
        String group = "qwqrwrewrqwr";
        System.out.println(group.split(";")[0]);
    }

    /**
     * DecimalFormat格式化小数点
     */
    @Test
    public void testDecimalFormat(){
        Double a = 1.2d;
        Double b = 1.29d;
        Double c = 23.295d;
        System.out.println(new DecimalFormat("0.00").format(a));  //1.20
        System.out.println(new DecimalFormat("0.00").format(b));  //1.29
        System.out.println(new DecimalFormat("0.00").format(c));  //23.30
    }


    /**
     * Double类型的尾数0不会保留。需要使用  BigDecimal类
     *
     * number(5,2)  超过2位的小数舍去,比如0.234会保存为0.23,
     *              但是0.20也会舍去0保存。只会保存0.2
     */
    @Test
    public void testDoublePrecision(){
        Double a = 0.20D;
        System.out.println(a);  //0.2
    }


    /***
     * calendar设定时间
     */
    @Test
    public void testCalendar() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse("2023-03-31 08:30:00");
            Date date2 = simpleDateFormat.parse("2023-04-30 08:30:00");
            Timestamp timestamp1 = new Timestamp(date.getTime());  //date.getTime() 就是时间戳
            Timestamp timestamp2 = new Timestamp(date2.getTime());


            Calendar calendar = Calendar.getInstance();
            Date currentTime = calendar.getTime();

            calendar.setTime(timestamp1);
            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date startTime = calendar.getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sTime = sdf.format(calendar.getTime());

            calendar.setTime(timestamp2);
            calendar.set(Calendar.HOUR_OF_DAY, 18);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date finishTime = calendar.getTime();

            String fTime = sdf.format(calendar.getTime());


            //2023-03-31 08:30:00--------------2023-04-30 18:00:00
            //2023-03-31 08:30:00============= 2023-04-30 18:00:00
            System.out.println(simpleDateFormat.format(startTime) + "--------------" + simpleDateFormat.format(finishTime));
            if (currentTime.compareTo(startTime) < 0 || currentTime.compareTo(finishTime) > 0) {
                System.out.println(sTime + "=============" + fTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

        /**
     * toUpperCase转大写
     */
    @Test
    public void toUpperCase(){
        String a  = "aaa";
        System.out.println(a.toUpperCase());
        String b = "ct_aa";
        //CT_AA
        System.out.println(b.toUpperCase());
    }

    /**
     * 把List转化成sql中in的字符串
     * @param -
     * @return  'aaaaa','bbbbb','bbbbb'
     */
    @Test
    public void changeListToInString(){
        List<String> list = new ArrayList<>();
        list.add("aaaaa");
        list.add("bbbbb");
        list.add("bbbbb");
        StringBuffer sb = new StringBuffer();
        sb.append("'");
        for(String element : list){
            sb.append(element).append("','");
        }
        System.out.println(sb.capacity());
        System.out.println(sb.toString().length());
        String a = sb.substring(0, sb.toString().length() -2 );
        System.out.println(a);
    }


    /***
     * substringBeforeLast    substringAfterLast最后一个字符
     * 条码规格流水号前加-  来截取 + 3000
     */
    @Test
    public void testAdd3000(){
        String initialCartonName = "YBG295P-230317-D01-0001";
        String prefixName = StringUtils.substringBeforeLast(initialCartonName, "-");
        String lastName =  String.valueOf(Integer.parseInt(StringUtils.substringAfterLast(initialCartonName, "-")) + 3000);
        String fullName = prefixName + lastName;
        //YBG295P-230317-D013001
        System.out.println(fullName);
    }

    /**
     * Double作减法的精度问题
     * 省去小数
     */
    @Test
    public void testDouble(){
        Double a = 150D;
        Double b = 140D;
        Double c = 160D;
        //10.0
        System.out.println(String.valueOf(a-b));

        //10
        String s = new BigDecimal(a.toString()).subtract(new BigDecimal(b.toString())).toBigInteger().toString();
        System.out.println(s);


    }

    /***
     * 字符串截取
     */
    @Test
    public void testSubstring(){
        //YBG295P-
        String cartonName = "YBG295P-230308-D110005";
        System.out.println(cartonName.substring(0,8));
    }


    /**
     * D000011
     * 指定填充字符
     */
    @Test
    public void testFillZero(){
        //D000011 6位小数,不够左侧填充0
        System.out.println("D" + StringUtils.leftPad(String.valueOf(4 + 7), 6, "0"));
    }

    /**
     * java.lang.NullPointerException
     */
    @Test
    public void foreachEmptyList(){
        List<String> list = null;
        for(String s : list){
            System.out.println("=================");
            System.out.println(s);
        }
    }


    /***
     * xml的空格不会导致转json报错
     */
    @Test
    public void xmlToJsonHavaEmpty(){
        String xml = "<MESSAGE>\n" +
                "\t<HEADER>\n" +
                "\t\t<MESSAGENAME>ModifyEnumDefValue</MESSAGENAME>\n" +
                "\t\t<TRANSACTIONID>202206241115000</TRANSACTIONID>\n" +
                "\t\t<REPLYSUBJECTNAME>CSOT.G85.BC.PRD.MES.Listen.MBABLC00</REPLYSUBJECTNAME>\n" +
                "\t\t<EVENTUSER>Taycen</EVENTUSER>\n" +
                "\t\t<EVENTCOMMENT>ModifyEnumDefValue</EVENTCOMMENT>\n" +
                "\t</HEADER>\n" +
                "\t<BODY>\n" +
                "\t\t<ENUMNAME>FullCottonAccordingGradeBranching</ENUMNAME>\n" +
                "\t\t<ENUM VALUE>N</ENUM VALUE>\n" +
                "\t\t<DEFAULTFLAG>Y</DEFAULTFLAG>\n" +
                "\t</BODY>\n" +
                "</MESSAGE>";
        System.out.println(JSONObject.toJSONString(xml));
    }


    /***
     *
     * {""} json解析时报错
     * expect ':' at 0, name
     */
    @Test
    public void jsonObjectParse() {
        String jsonString = "{\"\"}";
        JSONObject jObject = (JSONObject) JSONObject.parse(jsonString);
    }


    /**
     * java.lang.StringIndexOutOfBoundsException: String index out of range: -4
     */
    @Test
    public void subString(){
        String boxName = "G12+P306.6-T150G-230105-00102";
        System.out.println(boxName.substring(0,4));
        String boxName2 = "G12-P306.6-T150G-230105-00102";
        String productSpecType = boxName2.substring(0,4);
        System.out.println(productSpecType.substring(0,productSpecType.length()-1));

        String a = "F04000000744 and ";
        System.out.println(a.substring(0,a.length()-4));

        //NULL POINTER
        String b  = "";
        System.out.println(b.substring(b.length() - 4,b.length() - 1));

        //NULL POINTER
        Map<String,String> hashmap = null;
        System.out.println(hashmap.get("PORTNAME"));
    }

    /**
     * 1: [{"id":1,"age":4,"username":"zzz"},{"id":1,"age":4,"username":"zzz"}]
     * 1:[{"id":0,"age":4,"username":"zzz"},{"id":1,"age":4,"username":"zzz"}]
     */
    @Test
    public void modifyObjectToList(){
        User user = new User();
        user.setId(3);
        user.setUsername("zzz");
        user.setAge(4);
        List<String> hobbyList = Arrays.asList("篮球","足球","乒乓球");
        user.setHobbyList(hobbyList);
        Wife wife = new Wife();
        wife.setWifeName("西施");
        wife.setWifeSize("D");
        List<Wife> wifeArrayList = new ArrayList<>();
        wifeArrayList.add(wife);
        user.setWifeList(wifeArrayList);

        //没有创建对象，最终指向的同一个对象，打印的List元素是同一对象
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 2;i++){
            user.setId(i);
            users.add(user);
        }
        System.out.println("1:" + JSONUtil.toJsonStr(users));

        //1.创建对象，拷贝属性值有用    2.List<String>、List<Wife>都可以复制。
        List<User> users2 = new ArrayList<>();
        for(int i = 0; i < 2;i++){
            User u = new User();
            BeanUtil.copyProperties(user,u);
            u.setId(i);
            users2.add(u);
        }
        System.out.println("2:" + JSONUtil.toJsonStr(users2));

    }


    /**
     * org.apache.commons.lang3
     * isNotBlank   如果为null，length=0,当length为0或者存在空白返回true，[兼容了isNotEmpty并处理了内容为空白的]
     * isNotEmpty   如果为null或者lenth=0，返回true
     */
    @Test
    public void langStringUtils(){
        String str1 = null;
        String str2 = "";
        String str3 = " ";
        String str4 = "ABC";

        System.out.println(StringUtils.isNotBlank(str1));//false
        System.out.println(StringUtils.isNotBlank(str2));//false
        System.out.println(StringUtils.isNotBlank(str3));//false
        System.out.println(StringUtils.isNotBlank(str4));//true

        System.out.println(StringUtils.isNotEmpty(str1));//false
        System.out.println(StringUtils.isNotEmpty(str2));//false
        System.out.println(StringUtils.isNotEmpty(str3));//true
        System.out.println(StringUtils.isNotEmpty(str4));//true

    }

    /**
     * 从二维码截图箱码
     */
    @Test
    public void subCartonInCartonBox(){
        String cartonInCartonBox = "SN:YBG295P-221009-WD33064SN1:YBG295P-221009-WD33064SN2:....";
        System.out.println(cartonInCartonBox.substring(cartonInCartonBox.indexOf("SN:") + 3,cartonInCartonBox.indexOf("SN1")));
    }


    /**
     * 截取两头   LIKE  A%  &&  LIKE  %B
     *  productSpecQw.likeRight(ProductSpec::getDescription,description.substring(0,24));
     *  productSpecQw.likeLeft(ProductSpec::getDescription, description.substring(description.lastIndexOf("/")));
     */
    @Test
    public void likeRightLeft(){
        String desc = "硅片/P/G/218.2/306.60/150/0.4-1.1/GA+PXA1";
        System.out.println(desc.substring(0,24));  //硅片/P/G/218.2/306.60/150/
        System.out.println(desc.substring(desc.lastIndexOf("/")));// /GA+PXA1

    }


    /***
     * indexOf找不到就是-1.从0开始
     */
    @Test
    public void findInStr(){
        String barcode = "YBG247P-220912-WB10001";
        int i = barcode.indexOf("-WB1");
        System.out.println(i);//14

        String barcode2 = "YBG247P-220912-W20001";
        int j = barcode2.indexOf("-WB1");
        System.out.println(j);//-1

        String barcode3 = "-WB1-YBG247P-220912-W20001";
        int z = barcode3.indexOf("-WB1");
        System.out.println(z);//0

    }

    /**
     * 手写集合工具类使用集合工具类
     */
    @Test
    public void useAssertSize(){
        List<User> arrayList = new ArrayList<>();
        System.out.println(new DemoTest().assertSize(arrayList,1));
        List<User> arrayList2 = null;
        System.out.println(new DemoTest().isEmpty(arrayList));
    }


    public boolean assertSize(List<?> list,int size) {
        return list.size() == size;
    }

    public boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }


    /***
     * 托盘取中间号段
     */
    @Test
    public void subStringMiddleContent(){

        //:后的值
        String text = "WMS:TEXsvr11";
        System.out.println(text.substring(0,text.indexOf(":")));

        //托盘号取出号段--L02
        String processGroup = "YB220826-L02-0001";
        int i = processGroup.indexOf("-");
        int j = processGroup.lastIndexOf("-");
        System.out.println("i=" + i + ",j = " + j);
        System.out.println(processGroup.substring(i + 1,j));
    }

    /**
     * 对象转json数组
     *       json数组对应的字符串传输,都需要双引号。用反斜杠转义。
     * {
     * 	"bskey": null,
     * 	"ifywid": "DJKProductOutOfStockRequest",
     * 	"sysid": "YC4MES",
     * 	"zdata": "[{
     *
     *         \"SpliceType\":\"1\",
     *         \"Num\":142.357,
     * 		   \"BoxCode\":\"G2208M0613020000\",
     * 		   \"BarCodeType\":\"serialNumber\",
     * 		   \"ProductName\":\"方/P/G/210/295/GA+2/0.2-1.1/B12/1/--\",
     * 		   \"Size\":\"方/P/G/210/295/GA+2/0.2-1.1/B12/1/--\",
     * 		   \"UnitName\":\"KG\",
     * 		   \"CateName\":\"F02010\",
     * 		  \"BatchNum\":\"9999999999\",
     *         \"SupName\":null,
     *         \"PrDefine5\":null,
     *         \"IeDefine8\":\"9999999999\",
     *         \"InStorageTime\":\"2022-08-25T10:42:41.2639616+08:00\",
     *         \"Remark\":null,
     *         \"PrDefine7\":\"1000020641\",
     *         \"LGORT\":\"R016\",
     *         \"MESWorkOrder\":\"100002064112\",
     *         \"MachineId\":\"ZB-F\",
     *         \"TransportJobName\":\"20220825101217254000\",
     *         \"TargetLocation\":null,
     *         \"TaskId\":null,
     *         \"UserId\":null,
     *         \"SAPTaskId\":null,
     *         \"SAPTaskLn\":null,
     *         \"Location\":\"单晶回温库\",
     *         \"outDate\":\"2022-08-25T10:42:41.2639643+08:00\",
     *         \"batchNumber\":\"1\",
     *         \"orderNumber\":\"ZZ9BA220819030Z\",
     *         \"crystalNo\":\"G2208M0613020000\",
     *         \"norms\":\"Φ295×210\",
     *         \"StorageLength\":\"894.000\",
     *         \"crystalLength\":\"894.000\",
     *         \"chipLength\":\"0.000\",
     *         \"weight\":\"91.982\",
     *         \"oblique\":\"0.000\",
     *         \"TheoryWeight\":\"142.357\",
     *         \"CustomerType\":\"GA+2\",
     *         \"VerificationRemarks\":\"\",
     *         \"factoryCard\":\"1021\",
     *         \"MonocrystalNo\":\"G2208M0613020000;\",
     *         \"StickType\":\"方棒\",
     *         \"ProductGreySkin\":\"0\",
     *         \"SquareBarGreySkin\":\"0\",
     *         \"LineMarks\":\"0\",
     *         \"KnifeMarks\":\"0\",
     *         \"Scratches\":\"0\",
     *         \"NotGround\":\"0\",
     *         \"SpliceLength\":\"894\",
     *         \"SpliceWidth\":\"0\",
     *         \"CLXS\":null,
     *         \"WMSTaskId\":\"YCCK002828\",
     *         \"SupNo\":null,
     *         \"Torr\":null
     *     }]"
     * }
     *
     *
     */
    @Test
    public void  jsonArrayToString(){
        Student student = new Student();
        student.setId(3);
        student.setName("zhangshan");
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        String s = JSONArray.toJSONString(studentList);
        System.out.println(s);   //[{"id":3,"name":"zhangshan"}]
    }

    /**
     * 仅仅获取年月日中的日
     */
    @Test
    public void obtainDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));

        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
        System.out.println(sdf2.format(new Date()));
    }

    /**
     * idea本地调试in的入参
     * 先notepad将(String)替换成空格，
     * 再用此工具，加上单引号化为in的入参
     */
    @Test
    public void addSplit(){
        String oldStr = "F04000001637  , F04000001933  , F04000001984  , F04000001985  , F04000002028  , F04000002036  , F04000002037  , F04000002038  , F04000002040  , F04000002041  , F04000002089  , F04000001501  , F04000001503  , F04000001563  , F04000000740  , F04000001766  , F04000001767  , F04000001768  , F04000001769  , F04000001770  , F04000001771  , F04000001905  , F04000001907  , F04000001924  , F04000001932  , F04000001934  , F04000001935  , F04000001936  , F04000001982  , F04000001983  , F04000001986  , F04000002079  , F04000002084  , F04000002087  , F04000002092  , F04000002093  , F04000002094  ,";
        String[] split = oldStr.split(",");
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < split.length;i++){
            stringBuffer.append("\'").append(split[i].trim()).append("\'").append(",");
        }
        String s = stringBuffer.toString();
        System.out.println(s.substring(0,s.length()-1));
    }


    /**
     * 日期类型
     * Date和LocalDateTime
     * 连接数据库url通常配置UTC时区，
     * 那么插入的时候要主机GTM + 8,LocalDateTime不需要
     */
    @Test
    public void testDate(){
        Date date = new Date();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }

    /**
     *   / 取结果的整数
     *   % 取结果的余数
     */
    @Test
    public void division(){
        System.out.println(4/10);  //0
        System.out.println(10/10); //1
        System.out.println(11/10); //1
        System.out.println(11%10); //1
        System.out.println(4%10);  //4
        //int pageNo = total%pageSize == 0? total/pagesize : total/pageSize + 1;

    }


    /**
     * list.stream().collect(Collectors.toMap(Student::getId, Function.identity()));
     * Function.identity()  键有重复会报错
     */
    @Test
    public void identifiedConvert(){
        Student student = new Student();
        student.setId(1);
        student.setName("张三");
        Student student2 = new Student();
        student2.setId(2);
        student2.setName("李四");
        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student2);
        Map<Integer, Student> collect = list.stream().collect(Collectors.toMap(Student::getId, Function.identity()));
        System.out.println(JSON.toJSONString(collect));


        Student student3 = new Student();
        student3.setId(3);
        student3.setName("王麻子");
        Student student4 = new Student();
        student4.setId(3);
        student4.setName("张胖子");
        List<Student> list2 = new ArrayList<>();
        list2.add(student3);
        list2.add(student4);
        Map<Integer, String> collect1 = list2.stream().collect(Collectors.toMap(Student::getId, v -> v.getName(), (v1, v2) -> v1 + v2));
        System.out.println(collect1);
        /**
         * Function.identity()重复的键，会报错
         */
        Map<Integer, Student> collect2 = list2.stream().collect(Collectors.toMap(Student::getId, Function.identity()));
        System.out.println(JSON.toJSONString(collect2));

    }

    /**
     * 字符串字符替代   截取
     */
    @Test
    public void substitute(){
        String electrical = "0.4-1.1Ω.cm";
        //0.4≤1.1Ω.cm  ‘-’替换成‘≤’
        System.out.println(electrical.replace('-','≤'));

        //≤1.1Ω        ‘-’替换成‘≤’截取‘≤’后面
        String replace = electrical.replace('-', '≤');
        System.out.println(replace.substring(replace.indexOf("≤"),replace.length()-3));

        //截取部分描述
        String desc = "硅片/P/G/210.00/295.00/155/0.4-1.1/GA+XXA1";
        System.out.println(desc.substring(0,24));

        //ZB-A 截取到最后一个‘-’
        String machineName = "ZB-A-01";
        System.out.println(machineName.substring(0,machineName.lastIndexOf("-")));

        //-A
        String area = "ZB-A";
        System.out.println(area.substring(area.lastIndexOf("-")));

        //CN291010000008,CN251010011031
        String zzConsumableName = "CN291010000008,CN251010011031,";
        zzConsumableName = zzConsumableName.substring(0,zzConsumableName.length()-1);
        System.out.println(zzConsumableName);

    }


    @Test
    public void reflect() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        //https://www.cnblogs.com/ht1990/p/15356054.html
        //Class<?> aClass = Class.forName("cn.getech.test.mybatis.JDBC");

        Class<JDBC> jdbcClass = JDBC.class;
        Field reportMap = jdbcClass.getDeclaredField("reportMap");
        reportMap.setAccessible(true);
        Object o = reportMap.get(new JDBC());
        System.out.println("获取私有属性值:" + o);    //{1=333}
        Map<String, String> o2 = (Map<String, String>)reportMap.get(new JDBC());
        o2.clear();
        System.out.println(o2.size());

    }



    /**
     * float的加法
     */
    @Test
    public void testFloat(){
        Float a = 0f;
        Float b = 2.3f;
        Float c = 2f;
        Float d = a + b;
        Float e = b + c;
        System.out.println(d  + " "   + e);//2.3 4.3
    }

    /**
     * 三目运算符
     */
    @Test
    public void testOperationRes(){
        String s = testOperation();
        System.out.println(s);//非0和1返工   0标准  1外协
    }

    public String  testOperation(){
        int a = 4;
        return a == 0?"标准":a == 1 ?"外协":"返工";
    }

    /**
     * now.minusMinutes(10);获取某个时刻的前10分钟。
     */
    @Test
    public void jodaTimeTest(){
        Date date = new Date();
        System.out.println(date);//Tue May 31 10:15:02 CST 2022
        org.joda.time.DateTime now = new org.joda.time.DateTime();
        System.out.println(now);//2022-05-31T10:15:02.137+08:00
        List<Date> reList = Lists.newArrayList();
        org.joda.time.DateTime dateTime = now.minusMinutes(10);
        reList.add(dateTime.toDate());
        reList.add(now.toDate());
        Date date1 = reList.get(0);
        Date date2 = reList.get(1);
        long time = reList.get(0).getTime();
        System.out.println(date1);//Tue May 31 10:05:02 CST 2022
        System.out.println(date2);//Tue May 31 10:15:02 CST 2022
        System.out.println(time);//1653962702137
    }


    /**
     * list添加基本数据类型，后修改不变，引用类型会变。
     */
    @Test
    public void listAdd(){

        List<String> strs = new ArrayList<>();
        String str = "1111";
        strs.add(str);
        str = "2222";
        System.out.println(strs.get(0));//1111


        List<User> userList = new ArrayList<>();
        User user = new User();
        userList.add(user);
        user.setUsername("ssss");
        System.out.println(JSONUtil.toJsonStr(userList));//[{"age":0,"username":"ssss"}]
    }

    /***
     * 分隔字符
     */
    @Test
    public void splitChar(){
        String res = "车灯   注：1   注：2";
        String pname1 = "";
        String pname2 = "";
        if(res.contains("注")){
            pname1 = res.substring(0,res.indexOf("注"));
            pname2 = res.substring(res.indexOf("注"));
        }else{
            pname1 = res;
        }
        System.out.println(pname1 + "---------" + pname2);
    }


    /**
     * map赋一个字符变量，变量被赋予不同的值。
     */
    @Test
    public void putRepeatValue(){
        Map<String,String> map = new HashMap<>();
        String factory = "100";
        map.put("factory",factory);
        factory = "company";
        map.put("company",factory);
        for (String key : map.keySet()) {
            //factory :100
            //company :company
            System.out.println(key + " :" + map.get(key));
        }
    }

    /**
     * Java是值传递
     * 值传递是会拷贝一个副本，做修改
     * 传对象是，也是值传递，但是传递的是这个引用的地址，拷贝的副本和传入的参数两者指向的是同一个地址，副本去修改，原指向的值也修改了。
     */
    @Test
    public void valueTrans(){
        int a = 1;
        String str = "zhangsan";
        changeValue(a,str);
        System.out.println(a);  //1
        System.out.println(str);  //zhangsan
    }

    public void changeValue(int a,String s){
        a = 3;
        s = "lisi";

    }

    /**
     * HashMap用long做键是否存在问题。
     */
    @Test
    public void longKeyMap(){
        Map<Long,String> classNoName = new HashMap<>();
        classNoName.put(11111L,"张三");
        classNoName.put(12212313232L,"张三2");
        classNoName.put(1123323232111L,"张三3");
        System.out.println(classNoName.get(1123323232111L));
    }


    /***
     * 计算次数
     */
    @Test
    public void countStep(){
        int a = (800 + 1000 -1)/1000;
        System.out.println(a);//1

        int b = (1001 + 1000 -1)/1000;
        System.out.println(b);//2

        int c = (999 + 1000 -1)/1000;
        System.out.println(c);//1
    }

    /**
     * 隔2换行
     */
    @Test
    public void newlineEachTwo(){
        String value = "";
        for (int i = 0; i < 10; i++) {
            if (i == 10 - 1) {
                value += 4444;
            } else {
                value += (( (i+1) % 2 == 0) && i != 0) ? 3333 + "\n" : 2222 + " ";
            }
        }
        System.out.println(value);
    }


    /**
     * instanceof CharSequence
     */
    @Test
    public void testCharSequence(){

        //打印日志补字段数据 id = 152868    153614    153662     153747     153922
        //String currentYear = String.valueOf(DateUtil.year(new Date()));
        //String year = currentYear.substring(currentYear.length() - 2);
        //String dayOfYear = String.format("%03d", Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
        //System.out.println(year + "----" + dayOfYear);  //22----045

        String a = "zhang";
        System.out.println(a instanceof CharSequence);//true

        Map<String,String> map = new HashMap<>();
        if(map instanceof Iterable || map instanceof Iterator || ArrayUtil.isArray(map)){
            System.out.println("====================");//不打印
        }
    }


    /***
     * 时分秒获取子串。
     * %02d才会补零，%2d一位数是空格
     * %00d 报错java.util.DuplicateFormatFlagsException: Flags = '0'
     */
    @Test
    public void obtainSecondsLastTwoBits(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println("时分秒：" + format);

        String hourMinutes = format.substring(0,6);
        System.out.println(hourMinutes);

        //获取时分秒的最后两位数
        Integer secondsNum = Integer.parseInt(format.substring(7,8));
        System.out.println("秒针:" + secondsNum);

        String codeNum = "4258R012910 ";
        codeNum = codeNum +  hourMinutes + String.format("%02d", secondsNum + 1 - 1);
        System.out.println(codeNum);


        String codeNum2 = "4258R012910 ";
        codeNum = codeNum.replace("-serial-field-",String.format("%00d",4));
        System.out.println(codeNum);

    }

    /**
     * StringBuffer取代不存在的的字符串
     */
    @Test
    public  void noStringGoToReplace(){
        StringBuffer sb = new StringBuffer("11256323965665");
        String codeNum = sb.toString().replace("-serial_field-",String.format("%0" + 4 + "d", 5));
        System.out.println(codeNum);
    }



    /**
     * mes时分秒字段解析
     */
    @Test
    public void testHms(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println(sdf.format(new Date()));

    }


    /**
     * 遍历循环空的集合
     */
    @Test
    public void testListForeach(){
        List<String> strs = new ArrayList<>();
        for(String str : strs){
            System.out.println(str);
        }
    }

    /**
     * 字符串截取后面五位，去除-
     */
    @Test
    public void testSubstringLast(){
        String lineno = "ZS/C2-09";
        lineno = "c209";
        lineno = "c2-09";
        String substring;
        if(lineno.length() >= 5){
            if(lineno.contains("-")){
                substring = lineno.substring(lineno.length() - 5, lineno.length());
                //substring = substring.replace('-', '');
                Pattern p = Pattern.compile("-");
                substring = p.matcher(substring).replaceAll("").trim();

            }else{
                substring = lineno.substring(lineno.length() - 4, lineno.length());
            }

        }else{
            substring = lineno;
        }
        System.out.println(substring);
    }

    /**
     * PM:下午    AM：上午
     */
    @Test
    public void testPmAm(){
        Calendar calCurrent = Calendar.getInstance();
        int hour = calCurrent.get(Calendar.HOUR_OF_DAY);//0-11  24小时制
        //int hour = calCurrent.get(Calendar.HOUR);//0-11   12小时制
        int minute = calCurrent.get(Calendar.MINUTE);
        String hourSymbol = " AM";
        if(hour >= 12){
            hourSymbol = " PM";
        }
        String comResult = hour + ":" + minute + hourSymbol;
        System.out.println(comResult);
    }

    /**
     * linux获取jar的路径
     */
    @Test
    public void linuxGetJarPath(){
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        String[] pathSplit = path.split("/");
        String jarName = pathSplit[pathSplit.length - 1];
        String jarPath = path.replace(jarName, "");
        System.out.println(path);// /D:/workspace/demo-test-javaSE/out/production/demo-test/
        System.out.println(jarName);// demo-test
        System.out.println(jarPath);// /D:/workspace/-javaSE/out/production//
    }

    /**
     * File获取文件路径的方法测试
     */
    @Test
    public void testFilePath(){
        File file = new File("src");
        System.out.println(file.getParent());//null
        System.out.println(file.getPath());//src
        System.out.println(file.getAbsoluteFile());//D:\workspace\demo-test-javaSE\src
    }

    /**
     * 获取当前的年月日
     */
    @Test
    public void getYearMonthDate(){
        Calendar cal = Calendar.getInstance();
        String twoBitsYear = String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4);
        int month = cal.get(Calendar.MONTH) + 1;
        String monthStr = String.valueOf(month);
        if(month >= 10){
            if(month == 10){
                monthStr = "A";
            }else if(month == 11){
                monthStr = "B";
            } else if(month == 12){
                monthStr = "C";
            }
        }
        String dayStr = String.format("%02d",cal.get(Calendar.DAY_OF_MONTH));
        String dateCombination = twoBitsYear + monthStr + dayStr;
        System.out.println("结果：" + dateCombination);

        Calendar now = Calendar.getInstance();
        System.out.println("年: " + String.valueOf(now.get(Calendar.YEAR)).substring(2,4));
        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
        System.out.println("日: " + String.format("%02d",now.get(Calendar.DAY_OF_MONTH)));
        System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));
        System.out.println("分: " + now.get(Calendar.MINUTE));
        System.out.println("秒: " + now.get(Calendar.SECOND));
        System.out.println("当前时间毫秒数：" + now.getTimeInMillis());
        System.out.println(now.getTime());
    }


    /**
     * 第五位为L/R就显示对应，其他显示-
     */
    @Test
    public void justifyCategory(){
        String mcode = "1328R495".substring(4,5);
        if(!"L".equalsIgnoreCase(mcode) &&  !"R".equalsIgnoreCase(mcode)){
            mcode = "-";
        }
        System.out.println(mcode);

        System.out.println( new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
    }

    /**
     * 项目文件的绝对路径。
     * //D:\workspace\demo-test-javaSE
     */
    @Test
    public void testObtainDir(){
        String property = System.getProperty("user.dir");
        System.out.println(property);
    }


    /**
     * 年份代表字段的处理
     */
    @Test
    public void getYearSymbol(){
        Integer yyyy = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        int a = (yyyy - 2010)%yearSymbol.length;

        System.out.println(yyyy + " ,余数 " + a + ",取值：" + yearSymbol[a]);

    }

    /***
     * String.lastIndexOf("/")获取最后一个/的位置
     */
    // /1425377260241756161.qdf
    @Test
    public void replacePrefixTest(){
        String url = "http://kong.poros-platform.192.168.51.45.nip.io/api/poros-oss/file/download?path=http://192.168.51.45:30126/minio/app-wms/1425377260241756161.qdf";
        System.out.println(url.substring(url.lastIndexOf("/")));
    }

    /**
     * String不可变，updateString(a,"tan",student);赋值下去，复制了一个a'，它依旧指向常量值，wang，然后
     */
    @Test
    public void referenceTest(){
        String a = "wang";
        Student student = new Student();
        student.setName("first");
        String modifyValue = updateString(a,"tan",student);
        System.out.println(a + "~~~~~" + modifyValue + "~~~~~" + student.getName());//wang~~~~~zhang~~~~~secode
    }
    public String updateString(String a,String b,Student student){
        a = "zhang";
        b = "li";
        student.setName("secode");
        return a;
    }


    /**
     * public static final String
     * 枚举类 PK 静态变量 提供了更多的内置方法
     */
    @Test
    public void enumTest(){
        System.out.println(ConstantEnum.THREE_STRING.getValue() + ":" +ConstantEnum.THREE_STRING.getMsg());
    }

    /**
     * 方法传参，传递了引用。
     */
    @Test
    public void methodInvokeChangeValue(){
        Student student = new Student();
        student.setId(2);
        student.setName("张三");
        System.out.println("主方法对象哈希:" + student.hashCode());
        methodInvokeChangeValue2(student);
        //王五
        System.out.println(student.getName());
    }

    /**
     * student对象的一个声明，引用传递，指向主动调用者
     * @param student
     */
    public void methodInvokeChangeValue2(Student student){
        System.out.println("方法调用的哈希：" + student.hashCode());
        student.setName("王五");
    }

    /**
     * !null 空指针
     */
    @Test
    public void stringBuilderToString(){
        Boolean flag = null;
        Boolean flag2 = false;
        System.out.println(flag);
        System.out.println(flag2);

        //空指针
        if(!flag){
            System.out.println("!null");
        }
    }

    /**
     * 同一个对象，都是一个引用。
     */
    @Test
    public void addressQuote(){
        List<Student> students = new ArrayList<>();
        for(int j = 0; j < 3;j++){
            Student student = new Student();
            student.setId(j);
            students.add(student);
        }
        List<String> randomList = new ArrayList<>();
        for(Student s : students){
            randomList.clear();
            for(int i = 0; i < 5; i++){
                int nextInt = new Random().nextInt(10);
                System.out.println(nextInt);
                randomList.add(String.valueOf(nextInt));
                s.setPoints(randomList);
            }
        }
        //["3","0","6","0","7"] 都是。
        for(Student s : students){
            System.out.println(JSONUtil.toJsonStr(s.getPoints()));
        }
    }

    /***
     * 创建集合并添加值，一步解决式
     */
    @Test
    public void innerMethodAdd(){
        List<Long> ruleList = new ArrayList(){
            {
                add(3L);
            }
        };
        System.out.println(ruleList.get(0));

        List<Long> list = Arrays.asList(6L,7L);
        System.out.println(list.get(0));
    }

    /**
     * 天数，转化成3位
     */
    @Test
    public void stringFormat(){
        String ruleString = String.format("%03d", Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
        System.out.println(ruleString);
    }

    /**
     * JDK1.7 版本从 url下载
     */
    @Test
    public void connTest(){
        //威尔卡
        String url = "http://kong.poros-platform.172.17.17.60.nip.io/api/poros-oss/file/download?path=http://minio.poros-platform.172.17.17.60.nip.io/poros/1399978363241783298.qdf";
        //常诚
        //String url = "http://kong.poros-platform.192.168.51.10.nip.io/api/poros-oss/file/download?path=http://minio.poros-platform.192.168.51.10.nip.io/poros/1399177962492620801.qdf";
        HttpClientUtil httpsUrlConnectionMessageSender = new HttpClientUtil();
        HttpURLConnection connection;
        InputStream is = null;
        OutputStream os = null;
        try {
            connection = httpsUrlConnectionMessageSender.createConnection(url);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.connect();

            is = connection.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            os = new FileOutputStream(new File("D:\\gu.qdf"));
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            System.out.println(connection);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Arrays.asList()返回了ArrayList但实际不是我们常用的util包类,而是Arrays包
     */
    @Test
    public void arraysTest(){
        //List<String> stringList = new ArrayList<>();
        List<String> stringList = Arrays.asList("3","4","5");
        stringList.forEach(System.out::println);

        //java.lang.UnsupportedOperationException
        stringList.add("6");
        stringList.forEach(System.out::println);
    }

    /**
     * 改变子字符串同时改变了原字符串
     */
    @Test
    public void subListTest(){
        List<String> stringList = new ArrayList<>();
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        stringList.add("6");
        stringList.add("7");
        //5 6  7    从第三个开始
        List<String> subList = stringList.subList(2, 5);
        subList.forEach(System.out::println);
        subList.add("8");
        //3 4 5 6 7 8
        stringList.forEach(System.out::println);
    }

    /**
     * replace取代返回才是新的字符串    */
    @Test
    public void replaceString(){
        String sb = "1323633current_no_shift4666";
        sb.replace("current_no_shift","sfsdfsd");
        System.out.println(sb);
        String newString = sb.replace("current_no_shift","sfsdfsd");
        System.out.println(newString);


        StringBuilder sbBarcode = new StringBuilder("[>16VAAD21145A12345SWLZZ01shift_no_currentN00001");
        String result = sbBarcode.toString().replace("shift_no_current", "D1");
        sbBarcode.delete(0,sbBarcode.length());
        sbBarcode.append(result);
        System.out.println(sbBarcode);
    }

    /**
     * Map计算下标位置方法，在哈希冲突时形成链表
     */
    @Test
    public void hashMapLinked(){

        int king = hashMethodOFHashMap("king");
        System.out.println("king的哈希值：" + king);//3292069
        int peter = hashMethodOFHashMap("peter");
        System.out.println("peter的哈希值" +  peter);//106558549

        System.out.println("king计算的数组下标" + ((16-1)&king));//5
        System.out.println("peter计算的数组下标" + ((16-1)&peter));//5

        //==>上述两者哈希值不同，但是数组下标一样，哈希冲突
        //==>可以debug这个数据，哈希冲突，值不同，形成链表
    }


    /**
     * 键相同，hash冲突，返回旧值
     */
    @Test
    public void hashMapRepate(){
        Map<String, Integer> map = new HashMap<>();
        Integer king = map.put("king", 178);
        System.out.println(king);//null
        Integer king1 = map.put("king", 185);
        System.out.println(king1);//178

    }

    /**
     * 调用hashCode、equals测试
     */
    @Test
    public void hashMap2(){
        Map<HashCodeAndEqulasOverride,String> hashMap = new HashMap<>();
        HashCodeAndEqulasOverride hashCodeAndEqulasOverride = new HashCodeAndEqulasOverride(1,"asd");
        HashCodeAndEqulasOverride hashCodeAndEqulasOverrideTwo = new HashCodeAndEqulasOverride(2,"asdTwo");
        //toString 包名.类名@hashCode
        //System.out.println(hashCodeAndEqulasOverride.toString() + "   " + hashCodeAndEqulasOverrideTwo.toString());
        hashMap.put(hashCodeAndEqulasOverride,"3");
        hashMap.put(hashCodeAndEqulasOverride,"3");
        hashMap.put(hashCodeAndEqulasOverrideTwo,"4");
        System.out.println(hashMap.size());  //1
        hashCodeAndEqulasOverride.setFiledOne(3);
        hashCodeAndEqulasOverride.setFiledTwo("asdThree");
        hashMap.put(hashCodeAndEqulasOverride,"5");
        System.out.println(hashMap.size());   //1
    }

    /***
     *  HashMap先比较hashcode,然后比较equals方法
     *  hashcode相同，且equals为true
     */
    @Test
    public void hashMap(){

        Map<String,String> hashMap = new HashMap<>();
        List<String> list = Arrays.asList("Aa","BB","C#");
        List<String> a = new ArrayList<>();
        List<String> b = new LinkedList<>();

        for(String s : list){
            //2112  2112   2112
            System.out.println(s.hashCode());
            hashMap.put(s,s);
        }
        for(String key : hashMap.keySet()){
            //Aa,Aa    BB,BB   C#,C#
            System.out.println(key + "," + hashMap.get(key));
        }
    }

    /***
     * HashMap的哈希方法
     * static final int hash(Object key) {
     * int h;
     * return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
     }
     拆解：
     */
    public int hashMethodOFHashMap(Object key){
        int h;
        if(key == null){
            return 0;
        }else{
            h = key.hashCode();//哈希值,int 4个字节32位
            int temp = h>>>16; //右移16位
            int newHash = h ^ temp;  //异或运算(不同为1)
            return newHash;
        }
    }

    /**
     * 两位年份和今天属于今年多少天
     *
     */
    @Test
    public void dateFieldGenerateBarocdeTest(){
        //两位年份
        Date adjustDate = new Date();
        String yyYear = String.valueOf(DateUtil.year(adjustDate));
        String ruleString = yyYear.substring(yyYear.length()-2);
        System.out.println(ruleString);

        //今天属于今天多少天
        int i = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        System.out.println(i);

        //当前时分秒
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String format = sdf.format(new Date());
        System.out.println(format);

        //2021-05-21 18:36:045
        DateTime dateTime = DateUtil.offsetDay(new Date(), 1);
        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:sss"));
    }

    /**
     * 保留两位小数，舍去后面的
     */
    @Test
    public void bigDecemicToString(){
        BigDecimal strandValue2 = new BigDecimal("16.9011000");

        System.out.println(strandValue2.toString());
        System.out.println(strandValue2.setScale(2,BigDecimal.ROUND_DOWN));
    }

    /**
     * BigDecimal.compareTo作比较
     */
    @Test
    public void bigDecemicCompare(){
        String strandValue = "3.63";
        BigDecimal upperValue = new BigDecimal("3.65");
        BigDecimal strandValue2 = new BigDecimal("3.63");
        BigDecimal downerValue = new BigDecimal("3.62");
        if(new BigDecimal(strandValue).compareTo(upperValue)  < 0){
            System.out.println("strandValue  <  upperValue");
        }
        if(new BigDecimal(strandValue).compareTo(strandValue2)  == 0){
            System.out.println("strandValue  =  strandValue2");
        }
        if(new BigDecimal(strandValue).compareTo(downerValue)  > 0){
            System.out.println("strandValue  >  downerValue");
        }
    }

    /**
     * 比较Integer使用equals
     */
    @Test
    public void integerEqualsInteger(){
        Integer a = 1;
        Integer b = 2;
        if(!a.equals(b)){
            System.out.println("not equals");
        }

    }
    /**
     * list超过容量后自动扩容，传入的size只是初始化容量
     */
    @Test
    public void listOverSize(){
        List<String> list = new ArrayList<>(2);
        list.add("1");
        list.add("2");
        list.add("3");
        list.stream().forEach(System.out::println);

        List<String> list2 = new ArrayList<>();
        list2.addAll(list);
        list.stream().forEach(System.out::println);
    }

    /**
     * double计算 & 向上取整
     */
    @Test
    public void countMath(){

        int i = Math.floorMod(1 * 3, 1);
        System.out.println(i);  //0

        Double divide = Double.valueOf(1) /Double.valueOf(2)* Double.valueOf(3);
        int ceil = (int)Math.ceil(divide);  //2
        System.out.println(ceil);
    }

    /**
     * []   [null]
     */
    @Test
    public void testNull(){
        List<String> stringList = new ArrayList<>();
        stringList.add(null);
        String s = JSONUtil.toJsonStr(stringList);
        if(CollUtil.isEmpty(stringList)){
            System.out.println("结果集为[]");
        }
        for(String palletno : stringList){
            if(palletno  == null || "null".equals(palletno)){
                System.out.println("打印了");

            }
        }
        System.out.println(s);
    }

    /**
     * java8的时间工具：时间偏移多少天？    时间比较
     * @throws ParseException
     */
    @Test
    public void testJava8DateUtil() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse("2021-04-14 23:38:20");
        if (DateUtil.compare(date, DateUtil.beginOfDay(new Date())) < 0) {
            System.out.println("时间间隔小于0");
        }else {
            System.out.println("大于0");
        }

        //偏移28天
        Date end = DateUtil.offsetDay(new Date(), 28);
        System.out.println(simpleDateFormat.format(end));
    }


    /**
     *  绝对值 &&  时间减法
     */
    @Test
    public void absTest() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "2021-04-08";
        int remainTime = new Long(DateUtil.between(new Date(),simpleDateFormat.parse(date) , DateUnit.DAY, false)).intValue();
        System.out.println(remainTime);

        int remainTimes = -30;
        System.out.println(Math.abs(remainTimes));
    }


    /**
     * offsetDay偏移日
     */
    @Test
    public void dateTest(){
        Date end = DateUtil.offsetDay(new Date(), 30);
        System.out.println(end);
    }


    /**
     * 创建list时初始化
     */
    @Test
    public void arrayListInit(){
        List<String> snLists = new ArrayList<String>(){
            {
                add("000001210200496");
                add("000001210200404");
            }
        };
        snLists.stream().forEach(s-> System.out.println(s));
    }

    /**
     * list转成in拼接sql   ${取值}   或者foreach直接取list
     */
    @Test
    public void listToIn(){
        List<String> snList = new ArrayList<>();
        snList.add("000001210200496");
        snList.add("000001210200404");
        snList.add("000001210400644");
        snList.add("000001210200488");
        snList.add("000001210200487");
        StringBuffer barcodeSb = new StringBuffer();
        for (String barcode : snList) {
            barcodeSb.append("'").append(barcode).append("'").append(",");
        }
        String barcodeList =  barcodeSb.toString().substring(0, barcodeSb.length() - 1);
        System.out.println(barcodeList);
    }

    /**
     * Java8去重list
     */
    @Test
    public void distinctTest(){
        List<String> str = new ArrayList<>();
        str.add("000001211300256");
        str.add("000001211300256");
        List<String> collect = str.stream().distinct().collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonStr(collect));
    }


    /**
     * BigDecimal的加减乘除
     */
    @Test
    public void bigDecemic(){
        BigDecimal targetNumber = new BigDecimal(363);
        BigDecimal totalOutput = new BigDecimal(476);
        BigDecimal totalOutputRate = new BigDecimal(0);
        if(targetNumber.compareTo(BigDecimal.ZERO) != 0){
            totalOutputRate = totalOutput.divide(targetNumber,2,BigDecimal.ROUND_HALF_UP);
        }
        BigDecimal targetnubmerRate = new BigDecimal(BigInteger.ONE).subtract(totalOutputRate);
        System.out.println(totalOutputRate);
        System.out.println(targetnubmerRate);
    }

    /**
     * 年月日时分秒毫秒
     */
    @Test
    public void hmssss(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }


    /**
     * 空set包含任何字符串永为false
     */
    @Test
    public void nullNotContains(){
        if("库存sn".contains("库存sn")){
            System.out.println("fsdfsdfds f sd");
        }

        Set<String> nullSet = new HashSet<>();
        if(!nullSet.contains("contains")){
            System.out.println("********不包含**************");
        }
        Set<String> set = new HashSet<>();
        set.add("string");
        if(!set.contains("contains")){
            System.out.println("********不包含**************");
        }
    }

    /**
     * new的对象get就没有问题
     * 空对象，使用get方法会空指针
     */
    @Test
    public void nullGet(){
        Student student = new Student();
        System.out.println(student.getSex());
        Student student1 = null;
        //空指针
        System.out.println(student1.getSex());
    }


    /**
     * java8获取时间API
     * 2021/3/20 11:20测试
     * Sat Mar 20 00:00:00 CST 2021
     * Sat Mar 20 23:59:59 CST 2021
     * 2021-03-20
     */
    @Test
    public void testTime(){
        LocalDateTime startToday = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endToday = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        Date startDate = Date.from(startToday.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endToday.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(LocalDate.now());
    }

    /**
     * null.append("string")报错
     * "string".append(null) = stringnull
     */
    @Test
    public void stringBufferTest(){
        StringBuffer sb = new StringBuffer("333");
        Student s = new Student(2,null,"nan");
        sb.append(s.getName()).append("555");
        //333null555
        System.out.println(sb.toString());
    }

    /**
     * string重写了equals和hashcode,比较内容去重
     */
    @Test
    public void testSetContain(){
        Set<String> set = new HashSet<>();
        for(int i = 0; i < 10 ;i++){
            set.add(String.valueOf(i));
        }
        set.add("9");
        //set.forEach(System.out::println);
        set.stream().forEach(item ->{
            System.out.println(item);
        });
        if(set.contains("433")){
            System.out.println("contain 443");
        }
        if(set.contains("9")){
            System.out.println("contain 9");
        }
    }


    /**
     * list.stream.map(属性).collect(Collectors.toList());
     */
    @Test
    public void testStreamMap(){
        List<Student> stringList = new ArrayList<>();
        Student student = new Student();
        student.setId(1);
        student.setName("lisi");
        student.setSex("nan");
        stringList.add(student);

        Student student2 = new Student();
        student2.setId(1);
        student2.setName("zhangsan");
        student2.setSex("女");
        stringList.add(student2);
        List<Integer> collect = stringList.stream().map(item -> item.getId()).collect(Collectors.toList());

        if(CollUtil.isEmpty(collect)){
            System.out.println("==========empty=========");
        }
        for (Integer a : collect){
            System.out.println(a);
        }
    }

    /**
     * Integer为null equals和== 都空指针
     *
     */
    @Test
    public void integerEquals(){
        Integer a = 3;
        if(a.equals(3)){
            System.out.println("=====equals===========");
        }
        Integer b = null;
        if(3 == b){
            System.out.println(" ====   null == 3  ===");
        }else{
            System.out.println("  null != 3   ");
        }
        if(b.equals(3)){
            System.out.println(" =======null equals========== ");
        }else{
            System.out.println("===========not null =============");
        }
    }

    /**
     * 取产品编码尖括号
     */
    @Test
    public void subStringBracket(){
        String name = "射频拉远V5_虹信<LRRU800-ⅢB(H46CL00JC10D1)>";
        String substring = name.substring(name.lastIndexOf("<") + 1 ,name.length() -1 );
        System.out.println(substring);
    }

    /**
     * lastIndexOf测试
     */
    @Test
    public void subStringLastIndexOf(){
        String s = "012345678912456-4";
        String substring = s.substring(0, s.lastIndexOf("-"));
        //012345678912456
        System.out.println(substring);
    }


    /**
     * lambda特性
     *
     * 函数接口是只有一个抽象方法的接口，可以有多个默认方法，静态方法。
     *
     * Predicate	test(T t)	          判断真假	               9龙的身高大于185cm吗？
     * Consumer     accept(T t)	          消费消息	 	           输出一个值
     * Function	    R apply(T t)	      将T映射为R（转换功能）	   获得student对象的名字
     * Supplier	    T get()	              生产消息	               工厂方法
     * UnaryOperator	T apply(T t)	  一元操作	               逻辑非（!）
     * BinaryOperator	apply(T t, U u)	  二元操作    	           求两个数的乘积（*）
     */
    @Test
    public void lambdaTest(){
        Student student = new Student(1,"张三","男");

        //Predicate 判断   泛型,决定了后面变量的类型,从而决定了变量的方法
        Predicate<Integer> predicate = x-> x > 185;
        Predicate<String> stringPredicate = y-> y.equalsIgnoreCase("886");
        System.out.println("判断是否大于185?:" + predicate.test(98)) ;
        System.out.println("字符串是否为886?:" + stringPredicate.test("886"));

        //输出值
        Consumer<String> consumer = System.out::println;
        consumer.accept("命运由我不由天");

        //类型转化
        Function<Student,String> function = Student::getName;
        String name = function.apply(student);
        System.out.println(name);

        //工厂方法
        Supplier<Integer> supplier = () -> Integer.valueOf(BigDecimal.TEN.toString());
        System.out.println(supplier.get());

        //一元操作
        UnaryOperator<Boolean> unaryOperator = uglily -> !uglily;
        System.out.println(unaryOperator.apply(true));

        //二元操作
        BinaryOperator<Integer> operator = (x,y) -> x*y;
        System.out.println(operator.apply(2,3));

        //函数式接口
        defineFunctionInterface( () -> "我是一个演示的函数式接口" );
    }

    public interface Worker{
        String work();
    }

    public static void defineFunctionInterface(Worker worker){
        String work = worker.work();
        System.out.println(work);
    }


    /**
     * lambda测试2
     * 常用的流
     */
    @Test
    public void lambdaTest2(){

    }

    /**
     * 获取当前目录
     */
    @Test
    public void getCurrentPath(){
        File directory = new File("");//参数为空
        try {
             String current = directory.getCanonicalPath();
             System.out.println(current);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * map中put相同的键，覆盖了值
     */
    @Test
    public void mapPutSameKey(){
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"king");
        map.put(1,"i am the king of unite");
        System.out.println(JSONUtil.toJsonStr(map));
    }

    /**
     * 文件流写出到DBF  ---写不出去，无结果
     * @throws IOException
     */
    @Test
    public void writeToDbf() throws IOException {
        FileWriter f = null;
        try {
            //true 追加内容
            f = new FileWriter("C:\\Users\\Getech-200107-1\\Desktop\\sn3.dbf",true);
            f.write("\\r\\n");
            f.write("好好学习");
            f.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(f != null){
                f.close();
            }
        }
        System.out.println("文件流写出到dbf");
    }

    /**
     * 写出数据到普通文件的三种方式
     *       --文件不存在自动创建
     *
     */
    @Test
    public void writeToFile(){
        try{
            FileWriter f = new FileWriter("C:\\Users\\Getech-200107-1\\Desktop\\test.txt");
            f.write("好好学习");
            //或者：
            BufferedWriter buf = new BufferedWriter(f);
            buf.write(" 天天向上");
            buf.flush();
            f.close();
            System.out.println("文件方式一写入完毕");

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream("C:\\Users\\Getech-200107-1\\Desktop\\test2.txt");
                fos.write("方式二".getBytes());
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }
            System.out.println("文件方式二写入完毕");

            OutputStreamWriter os = null;
            FileOutputStream foss = null;
            try {
                foss = new FileOutputStream("C:\\Users\\Getech-200107-1\\Desktop\\test3.txt");
                os = new OutputStreamWriter(foss, StandardCharsets.UTF_8);
                os.write("方式三");
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (os != null) {
                    os.close();
                }
                if (foss != null) {
                    foss.close();
                }

            }
            System.out.println( "方式三文件写入数据");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * DBF文件写入测试
     */
    @Test
    public void writeDbf(){
        //OutputStream fos = null;
        try {
            //fos = new FileOutputStream("C:\\Users\\Getech-200107-1\\Desktop\\test.txt");
            DBFWriter writer = new DBFWriter(new File("C:\\Users\\Getech-200107-1\\Desktop\\test4.dbf"));
            DBFField[] fields = new DBFField[1];
            //Field name should be of length 0-10
            //Fields should be set before adding records
            //Fields has already been set  如果test4.dbf已经存在，再运行set属性
            fields[0] = new DBFField();
            fields[0].setName("SN");
            fields[0].setDataType(DBFField.FIELD_TYPE_C);
            fields[0].setFieldLength(10);

            /*fields[1] = new DBFField();
            fields[1].setName("code");
            fields[1].setDataType(DBFField.FIELD_TYPE_C);
            fields[1].setFieldLength(10);*/

            writer.setFields(fields);

            Object[] rowData = new Object[1];
            rowData[0] = "1522";
            //rowData[1] = "12356";
            writer.addRecord(rowData);
            //rowData = new Object[1];
            rowData[0] = "1556";
            //rowData[1] = "1653323";
            writer.addRecord(rowData);

            System.out.println("set的属性值：" + JSONUtil.toJsonStr(fields));
            //writer.addRecord(new String[]{"data"});
            writer.setCharactersetName("GBK");
            writer.write();
        } catch (  DBFException e) {
           e.printStackTrace();
        } finally {
            //if(fos != null){
            //    try {
            //        fos.close();
            //    } catch (IOException e) {
            //        e.printStackTrace();
            //    }
            //}
        }

    }

    /**
     * 读取DBF文件字段和数据
     */
    @Test
    public void readDbf(){
        InputStream fis = null;
        try {
            // 读取文件的输入流
            fis = new FileInputStream("C:\\Users\\Getech-200107-1\\Desktop\\test4.dbf");
            // 根据输入流初始化一个DBFReader实例，用来读取DBF文件信息
            DBFReader reader = new DBFReader(fis);
            // 调用DBFReader对实例方法得到path文件中字段的个数
            int fieldsCount = reader.getFieldCount();
            Map<String,Object> map = new HashMap<>();
            // 取出字段信息
            for (int i = 0; i < fieldsCount; i++) {
                DBFField field = reader.getField(i);
                map.put("name" + i,field.getName());
                map.put("dataType" + i,field.getDataType());
                map.put("fieldLength" + i,field.getFieldLength());
            }
            map.put("fieldCount",fieldsCount);
            System.out.println(JSONUtil.toJsonStr(map));

            Object[] rowValues;
            // 一条条取出path文件中记录
            while ((rowValues = reader.nextRecord()) != null) {
                for (int i = 0; i < rowValues.length; i++) {
                    System.out.println(rowValues[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null){
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * String类型equals一个相同值的Integer为false
     */
    @Test
    public void integerEqualsString(){
        User user = new User();
        user.setId(3);
        if("3".equals(user.getId())){
            System.out.println("string equal integer success");
        }
        if("3".equals(user.getId().toString())){
            System.out.println("standard writing method");
        }
        if("3".equals(3)){
            System.out.println("failed");
        }
    }

    /**
     * 大小写不同的属性使用lombok生成get set方法会报错
     */
    @Test
    public void getAndSetter(){
        CaseConflictGetterAndSetter caseConflictGetterAndSetter = new CaseConflictGetterAndSetter();
        caseConflictGetterAndSetter.setProcessTwoCode("zhang");
        System.out.println(caseConflictGetterAndSetter.getProcessTwoCode());

        //lombok编译失败
        CaseConflictGetterAndSetterLombok caseConflictGetterAndSetters = new CaseConflictGetterAndSetterLombok();
        caseConflictGetterAndSetters.setProcessTwoCode("zhang");
        System.out.println(caseConflictGetterAndSetters.getProcessTwoCode());
    }

    /**
     * 获取临时目录，在临时目录操作，避免有目录存在权限，无法执行操作
     * C:\Users\GETECH~1\AppData\Local\Temp\\
     */
    @Test
    public void getTempDir(){

        String tempPath = System.getProperty("java.io.tmpdir") + File.separator;
        System.out.println(System.getProperty("java.io.tmpdir")); //C:\Users\GETECH~1\AppData\Local\Temp\
        System.out.println(File.separator); //\
        System.out.println(tempPath);
        String fileName = tempPath.substring(0,tempPath.length()-1) + System.currentTimeMillis() + ".pdf";
        System.out.println(fileName);
    }

    /**
     * fastJson解析json数组
     */
    @Test
    public void fastJsonTest(){
        String qdfUrl = "[{\"fileName\":\"blue.jpg\",\"filePath\":\"https://uat.iiot.hxct.com/api/poros-oss/file/download?path=https://minio-uat.iiot.hxct.com/poros/1363323153101574146.jpg\",\"fileSize\":222931}]";
        JSONArray jsonArray = JSON.parseArray(qdfUrl);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String fileName = (String)jsonObject.get("fileName");
        String url = (String)jsonObject.get("filePath");
        System.out.println(fileName);
        System.out.println(url);

        String bdfUrl = "[{\"fileName\":\"blue2.jpg\",\"filePath\":\"https://uat.iiot.hxct.com/api/poros-oss/file/download?path=https://minio-uat.iiot.hxct.com/poros/1363323153101574146.jpg\",\"fileSize\":222931},{\"fileName\":\"yellow.png\",\"filePath\":\"https:www\",\"fileSize\":222931}]";
        JSONArray jsonArray2 = JSON.parseArray(bdfUrl);
        System.out.println("数组size:" + jsonArray2.size());
        for(int i = 0;i < jsonArray2.size(); i++){
            System.out.println("当前索引:" + i);
            JSONObject jsonObject2 = jsonArray2.getJSONObject(i);
            System.out.println((String)jsonObject2.get("fileName"));
            System.out.println((String)jsonObject2.get("filePath"));
        }

    }

    /**
     * 判断nonNull
     */
    @Test
    public void nonNullNewObject(){
        Student s = new Student();
        String str = "";
        Student student = null;
        if(Objects.nonNull(s)){
            //打印
            System.out.println("nonull");
        }
        if(Objects.nonNull(str)){
            //打印
            System.out.println("str nonull");
        }
        if(Objects.nonNull(student)){
            //不打印
            System.out.println("student nonull");
        }
    }

    /**
     * Long ++
     */
    @Test
    public void longAddTest(){
        List<String> list = new ArrayList<>();
        Long a = CollUtil.isEmpty(list)?0:3L;
        a++;
        System.out.println(a);
    }

    /**
     * BigDecimal转成int
     */
    @Test
    public void bigDecemial(){
        BigDecimal bigDecimal = new BigDecimal(2.56);
        Integer a = bigDecimal.intValue();
        System.out.println(a);
    }

    /**
     * 传0, 2021-01-08  00:00:00
     * 传1， 2021-01-09 00:00:00
     */
    @Test
    public void obtainTodayAndTomorrow(){
        Date date = new Date();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DatePattern.NORM_DATE_PATTERN);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            //calendar.add(Calendar.DATE, 0);
            calendar.add(Calendar.DATE, 1);
            date = dateFormat.parse(dateFormat.format(calendar.getTime()));
            System.out.println(date);
        } catch (Exception e) {
            System.out.println("===========error");
        }
    }

    /**
     * ISO 8601 标准  获取周数
     */
    @Test
    public void obtainIsoWeek() throws ParseException {
        String string = "2020-12-28";
        //String string = "2021-01-05";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = dateFormat.parse(string);
        //四位年份 2020
        System.out.println(DateUtil.year(parse));
        //二位年份 20
        String yyYear = String.valueOf(DateUtil.year(parse));
        System.out.println(yyYear.substring(yyYear.length()-2));
        //一年中的月份  12
        System.out.println(String.format("%02d", DateUtil.month(parse)+1));
        //一个星期的第几天  01
        int dayOfWeek=(DateUtil.dayOfWeek(parse)-1);
        dayOfWeek = dayOfWeek == 0 ? 7 : dayOfWeek;
        System.out.println(String.format("%02d", dayOfWeek));
        //一年中第几周  01
        System.out.println("error========="+   String.format("%02d", DateUtil.weekOfYear(parse)));
        //一个月的多少天  28
        System.out.println(String.format("%02d", DateUtil.dayOfMonth(parse)));

        //ISO 8601
        Calendar calendar = Calendar.getInstance();
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        /* Set date */
        calendar.setTime(parse);
        /* Get ISO8601 week number */
        int i = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println(i);
    }

    /**
     * SimpleDateFormat.format需要传入Date字段
     */
    @Test
    public void dateFormatMustDate(){
        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        System.out.println(dateNowStr);
    }

    /**
     * 增加一天
     * @throws ParseException
     */
    @Test
    public void addOneDay() throws ParseException {
        String dzDate = "20200804";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");//注意月份是MM
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date dzDateObj = sdf1.parse(dzDate);
        String startDayTime = sdf2.format(dzDateObj)+" 00:00:00"; //2020-08-04 00:00:00
        //加一天
        Calendar cal = Calendar.getInstance();
        cal.setTime(dzDateObj);
        cal.add(Calendar.DAY_OF_YEAR,1);
        Date addDate = cal.getTime();
        String endDayTime = sdf2.format(addDate)+" 00:00:00";     //2020-08-05 00:00:00
        System.out.println(startDayTime+"------"+endDayTime);
    }

    @Test
    public void nullDifferenceNew(){
        Student student = new Student();
        //null
        System.out.println(student.getSex());
        //false
        System.out.println(student ==  null);

        Student students = null;
        System.out.println(students.getSex());
    }

    @Test
    public void sbTest(){
        StringBuffer sb = new StringBuffer();
        sb.append("21323").append(",");
        String sn = null;
        if(StrUtil.isNotEmpty(sb)){
            sn = sb.toString().substring(0,sb.length()-1);
        }else{
            System.out.println("error");
        }
        if(StrUtil.isEmpty(sn)){
            System.out.println("error");
        }
        System.out.println(sn);
    }

    @Test
    public void splitNoSignal(){
        String str1 = "212132,2323424";
        System.out.println(str1.split(",")[0]);
        String str2 = "212132";
        System.out.println(str2.split(",")[0]);
    }

    @Test
    public void invokeCommand(){
        Runtime rt = Runtime.getRuntime();
        String[] cmdA = { "cmd.exe", "/c", "PDFtoPrinter test.pdf"};
        try {
            //在指定目录执行
            rt.exec(cmdA,null,new File("E:\\youdaoyun"));
            //rt.exec(cmdA);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接url写出到本地文件
     */
    @Test
    public void urlToFile(){
        String netUrl = "https://uat.iiot.hxct.com/api/mes-code/ureport/pdf?_u=mysql-20672192R1A_mp.ureport.xml&bcIds=9415";
        //String netUrl = "http://192.168.6.145/fx-platform/ureport/pdf/show?_u=mysql-20672194_mp.ureport.xml&_i=1&_r=1";

        File file = new File("D:\\data\\test2.pdf");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 构造URL
        URL url = null;
        try {
            url = new URL(netUrl);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            OutputStream os = new FileOutputStream(file);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * sockt监听到打印的内容文件流，调用打印机打印
     */
    @Test
    public void testPrint(){
        try {
            ServerSocket ss = new ServerSocket(8080);
            Socket accept = ss.accept();
            InputStream inputStream = accept.getInputStream();
            byte[] bytes = readBytes(inputStream);
            String pdfUrl = new String(bytes);
            //ip + 打印机名称  指定使用那一台打印机
            PrinterUtil printerUtil = new PrinterUtil("");
            printerUtil.print(pdfUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] readBytes(InputStream in) throws IOException {
        BufferedInputStream bufin = new BufferedInputStream(in);
        int buffSize = 1024;
        ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);


        byte[] temp = new byte[buffSize];
        int size = 0;
        while ((size = bufin.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        bufin.close();
        in.close();
        byte[] content = out.toByteArray();
        out.close();
        return content;
    }

    @Test
    public void subStr(){
        String str = "H001-1";
        int i = str.lastIndexOf("-");
        if(i != -1){
            System.out.println(str.substring(0,str.lastIndexOf("-")));
        }

    }

    @Test
    public void hex(){
        String startAddress = "19:A9:01:00:00:01" ;
        String endAddress = "19:A9:01:00:10:10";
        startAddress = startAddress.replace(":","");
        endAddress = endAddress.replace(":","");
        System.out.println(HexUtil.isHexNumber("0x"+startAddress));
        System.out.println(HexUtil.isHexNumber("0x"+endAddress));
        System.out.println(startAddress.length() == 12);
        System.out.println(endAddress.length() == 12);
        System.out.println(HexUtil.toBigInteger(endAddress).longValue()>HexUtil.toBigInteger(startAddress).longValue());

    }
    /**
     * float转化int，舍去小数
     */
    @Test
    public void float2int(){
        int width = 23;
        float scale = 0.8f;
        width = (int)(width*scale);  //23*0.8 = 18.4
        System.out.println(width);
    }

    /***
     * UnitConv类，计量单位转化
     */
    @Test
    public void unitConv(){
        int dpi = 512;
        System.out.println((1f / dpi)*25.4D);
    }

    @Test
    public void testEnum(){
        System.out.println(ReverseEnum.IS_REVERSE.getCode());
        System.out.println(ReverseEnum.IS_REVERSE.toString().toLowerCase());
    }

    /**
     * String 类型的时间转换成long
     */
    @Test
    public void stringTimeToLong(){
        String data = "2020-09-01 00:00:00";
        String end = "2020-10-10 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(data);
            long time = date.getTime();
            System.out.println(time);
            System.out.println(sdf.parse(end).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * StringBuilder的非空判断，和删除结尾字符
     */
    @Test
    public void sb(){
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.length());//0
        if(sb.length() > 0 && !"".equals(sb.toString())){
            System.out.println("---------");
        }
        sb.append("12243424").append(",");
        if(sb.length() > 0 && !"".equals(sb.toString())){
            sb = sb.deleteCharAt(sb.length()-1);
            System.out.println(sb);
        }

    }

    @Test
    public void dateFormat2(){
        long nowMills = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for(int i = 0;i<10;i++){
            String format = sdf.format(new Date(nowMills));
            System.out.println(format);
            nowMills = nowMills + 1000;
            System.out.println("===="+new Date(nowMills));
        }

    }

    /**
     * 循环打印当前时间--程序运行之快，程序执行完，时间无变化
     */
    @Test
    public void dateFormat(){
        for(int i = 0;i<10;i++){
            System.out.println(System.currentTimeMillis());
        }
    }

    /**
     * 不包含,的字符串,分割后结果
     */
    @Test
    public void split(){
        String s = "1234243545";
        String[] a = s.split(",");
        System.out.println(a[0]);

    }
    @Test
    public void mapToJson(){
        Map<String,String> map = new HashMap<>();
        map.put("code","8888");
        System.out.println(JSONObject.toJSONString(map));
    }

    /***
     * 执行if后不执行else if
     */
    @Test
    public void ifTest(){
        int a = 6;
        if(a < 8){
            System.out.println("======8========");
        }else if(a < 10){
            System.out.println("======10========");
        }
        //可使用&&操作
    }

    @Test
    public void getCurrentDate()  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Thu Aug 20 18:06:02 CST 2020 和new Date()一样,
        //但是存储到数据库自动转化成数据库datetime类型
        System.out.println(date);
    }


    /**
     * int类型的值的判断
     */
    @Test
    public void intEquals(){
        int a = 1;
        if(a == 1){
            System.out.println("equals.......");
        }
    }


    @Test
    public void setTest(){
        Set<Student> set = new HashSet<>();
        Student student = new Student();

        student.setName("zhangsan");
        student.setSex("25");
        set.add(student);
        student.setName("wangwu");
        student.setSex("25");
        set.add(student);
        student.setName("zhangsan");
        student.setSex("26");
        set.add(student);

        Object[] objects = set.toArray();
        System.out.println(Arrays.toString(objects));//走Student的toString
        System.out.println(objects[0].equals(objects[1]));
    }

    /*public boolean codeProduce(MesBcProduceAddParam mesBcProduce) {
        Assert.isFalse(MesCodeResultCodeEnum.MES_BC_RULE_NOT_ALLOW_NULL,null == mesBcProduce.getRuleIds());

        //返工工单
        if(StringConstant.ONE_String.getValue().equals(mesBcProduce.getOrderType())){
            if(StrUtil.isEmpty(mesBcProduce.getOriginNo())){
                throw new ServiceException(MesCodeResultCodeEnum.GO_BACK_PRODUCTION_HAVE_NOT_MODIFY_NO);
            }
            log.info("工艺更改单号："+mesBcProduce.getOriginNo());
            RestResponse<RouterInfoDTO> orderContentByNumsLibrary = mesCraftServiceClient.getOrderContentByNumsLibrary(mesBcProduce.getOriginNo());
            log.info("调用结果："+ JSON.toJSONString(orderContentByNumsLibrary));
            RouterInfoDTO data = orderContentByNumsLibrary.getData();
            log.info("工艺路线.feign查询："+ JSON.toJSONString(data));
            if(data == null ){
                throw new ServiceException(MesCodeResultCodeEnum.MODIFY_NO_RECORD_NOT_BE_FOUND);
            }
			*//*RouterInfoDTO data = new RouterInfoDTO();
			data.setRouterCode("2945769R1A-1");
			data.setRouterVersion("GX-GYGG20201217002-HX-SCGD202009422-2020-12-17 15:13:25-1540");
			List<MesPeBillProcessOrderDTO> orderDTOS2 = new ArrayList<>();
			MesPeBillProcessOrderDTO  mesPeBillProcessOrderDTO2 = new MesPeBillProcessOrderDTO();
			mesPeBillProcessOrderDTO2.setBillNum("GX-GYGG20201217002");
			mesPeBillProcessOrderDTO2.setMcode(null);
			mesPeBillProcessOrderDTO2.setProcessOrderNum("HX-SCGD202009422");
			mesPeBillProcessOrderDTO2.setSn("ET320510007");
			mesPeBillProcessOrderDTO2.setMname(null);
			orderDTOS2.add(mesPeBillProcessOrderDTO2);
			data.setOrderDTOS(orderDTOS2);*//*

            List<MesPeBillProcessOrderDTO> orderDTOS = data.getOrderDTOS();
            String sn = null;
            Assert.isFalse(MesCodeResultCodeEnum.PRODUCTION_NO_IS_EMPTY,StrUtil.isEmpty(mesBcProduce.getProductionno()));
            StringBuffer sb = new StringBuffer();
            for(MesPeBillProcessOrderDTO mesPeBillProcessOrderDTO : orderDTOS){
                //OriginNo 工艺更改单号  processOrderNum旧的生产工单号(相同工艺更改单号，可能不同旧的生产单号)
                sb.append(mesPeBillProcessOrderDTO.getSn()).append(",");
            }
            if(StrUtil.isNotEmpty(sb)){
                sn = sb.toString().substring(0,sb.length()-1);
            }else{
                throw new  ServiceException(MesCodeResultCodeEnum.ORIGINNO_AND_HAVA_NO_SN);
            }
            if(StrUtil.isEmpty(sn)){
                //append为+""+ ",",上述截取后为空了
                throw new  ServiceException(MesCodeResultCodeEnum.ORIGINNO_AND_HAVA_NO_SN);
            }

            //更新mes_bc_pro
            List<String> snList = new ArrayList<>();
            Collections.addAll(snList, sn.split(","));
            log.info("=========条码列表 :  " + JSON.toJSONString(snList));
            //多规则生成,produceId相同，ruleId不同
            String newProduceId = IdUtil.simpleUUID();

            //插入mes_bc_produce
            LambdaQueryWrapper<MesBcPro> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.in(MesBcPro::getBarcode,snList);
            queryWrapper.select(MesBcPro::getId,MesBcPro::getMesBcProduceId,MesBcPro::getBarcodeRuleId,MesBcPro::getBarcodeRule);
            List<MesBcPro> proList = iMesBcProService.list(queryWrapper);
            if(CollUtil.isEmpty(proList)){
                throw new ServiceException(MesCodeResultCodeEnum.NO_MES_BC_PRO_RECORD);
            }
            Map<Long,String> map = new HashMap<>();
            for(MesBcPro mesBcPro : proList){
                map.put(mesBcPro.getBarcodeRuleId(), mesBcPro.getBarcodeRule());
            }
            List<Long> ruleIdList = new ArrayList<>(map.keySet());
            log.info("=========1.条码规则id:  " + JSON.toJSONString(ruleIdList));
            String oldProduceId = proList.get(0).getMesBcProduceId();
            LambdaQueryWrapper<MesBcProduce> produceQueryWrapper = Wrappers.lambdaQuery();
            produceQueryWrapper.eq(MesBcProduce::getProduceId,oldProduceId);
            produceQueryWrapper.in(MesBcProduce::getBarcodeRuleId,ruleIdList);
            List<MesBcProduce> produceList = list(produceQueryWrapper);
            List<MesBcProduce> insertProduceList = new ArrayList<>();
            log.info("=========2.mes_bc_produce原始:  " + JSON.toJSONString(produceList));
            for(MesBcProduce mesBcProduce1 : produceList){
                MesBcProduce newProduce = new MesBcProduce();
                BeanUtil.copyProperties(mesBcProduce1,newProduce);
                newProduce.setId(null);
                newProduce.setProductionno(mesBcProduce.getProductionno());
                newProduce.setQty(snList.size());
                newProduce.setProcessCoding(data.getRouterCode());
                newProduce.setProcessVersion(data.getRouterVersion());
                newProduce.setProduceId(newProduceId);
                newProduce.setIsReverse(0);
                newProduce.setBarcodeProduceNo(mesBcProduce.getBarcodeProduceNo());
                insertProduceList.add(newProduce);
            }
            log.info("======== 插入produce表的数据: " + JSON.toJSONString(insertProduceList));
            saveBatch(insertProduceList);

            //插入生成记录 mes_bc_list
            LambdaQueryWrapper<MesBcList> bcListQueryWrapper = Wrappers.lambdaQuery();
            bcListQueryWrapper.eq(MesBcList::getMesBcProduceId,oldProduceId);
            bcListQueryWrapper.in(MesBcList::getBarcodeRuleId,ruleIdList);
            List<MesBcList> bcList = iMesBcListService.list(bcListQueryWrapper);
            List<MesBcList> insertBcList = new ArrayList<>();
            log.info("======== 查询的原始bc_list数据: " + JSON.toJSONString(insertBcList));
            for(MesBcList mesBcList : bcList){
                MesBcList insert = new MesBcList();
                BeanUtil.copyProperties(mesBcList,insert);
                insert.setId(null);
                insert.setProductionno(mesBcProduce.getProductionno());
                insert.setQty(snList.size());
                insert.setFirstBarcode("返工条码");
                insert.setEndBarcode("返工条码");
                insert.setMesBcProduceId(newProduceId);
                insertBcList.add(insert);
            }
            log.info("======== 插入produce表的数据: " + JSON.toJSONString(insertProduceList));
            iMesBcListService.saveBatch(insertBcList);

            //更新wip表
            MfCraftChangeParam mfCraftChangeParam = new MfCraftChangeParam();
            mfCraftChangeParam.setChangeBillNo(mesBcProduce.getOriginNo());
            mfCraftChangeParam.setRouteCode(data.getRouterCode());
            mfCraftChangeParam.setRouteVersion(data.getRouterVersion());
            mfCraftChangeParam.setProductionNo(mesBcProduce.getProductionno());
            mfCraftChangeParam.setReworkType(Integer.parseInt(mesBcProduce.getOrderType()));
            mfCraftChangeParam.setProductCode(mesBcProduce.getMcode());
            mfCraftChangeParam.setProductName(mesBcProduce.getMname());
            if(StrUtil.isNotEmpty(mesBcProduce.getMclassid())){
                mfCraftChangeParam.setProductType(mesBcProduce.getMclassid());
            }
            mfCraftChangeParam.setSnLsit(snList);
            mfPnsnClient.updateSnForCraftChange(mfCraftChangeParam);

            //使用updateBatchById先查询列表,再遍历set其id和修改字段,添加到集合
            LambdaUpdateWrapper<MesBcPro> updateWrapper = Wrappers.lambdaUpdate();
            updateWrapper.in(MesBcPro::getBarcode,snList);
            updateWrapper.set(MesBcPro::getMesBcProduceId,newProduceId);
            iMesBcProService.update(updateWrapper);
            return true;
        }


        //一个工单号在同一个规则下只能生成一次
        if(StrUtil.isNotEmpty(mesBcProduce.getProductionno())){
            mesBcProduce.setProductionno(mesBcProduce.getProductionno().trim());
            for(long ruleId : mesBcProduce.getRuleIds()){
                LambdaQueryWrapper<MesBcProduce> queryWrapper = Wrappers.lambdaQuery();
                queryWrapper.eq(MesBcProduce::getProductionno,mesBcProduce.getProductionno());
                queryWrapper.eq(MesBcProduce::getBarcodeRuleId,ruleId);
                queryWrapper.eq(MesBcProduce::getIsReverse,0);
                List<MesBcProduce> list = list(queryWrapper);
                if(CollUtil.isNotEmpty(list)){
                    throw new ServiceException(MesCodeResultCodeEnum.CAN_ONLY_GENERATE_ONCE_UNDER_PRODUCENO_RULE);
                }
            }
        }

        String strategy = null;
        //多规则条码生成
        //1.多规则,只存在于工单条码，零散条码只能单规则。   2.目前多规则:RCU规则(有内置RCU程式和RCU端口字段)+设备/天线SN规则   大唐天线1+大唐天线2(两个标签,但是两个标签有关联)
        if (1 < mesBcProduce.getRuleIds().size()) {
            Assert.isTrue(MesCodeResultCodeEnum.MES_BC_RULE_NOT_EXIST,StringUtils.isNotEmpty(mesBcProduce.getProductionno()));
            LambdaQueryWrapper<MesBcRuleAdd> queryWrapper = Wrappers.<MesBcRuleAdd>lambdaQuery()
                    .in(MesBcRuleAdd::getMesBarcodeRuleId, mesBcProduce.getRuleIds());
            List<MesBcRuleAdd> addList = iMesBcRuleAddService.list(queryWrapper);
            boolean rcuFlag = false;
            boolean fiveGflag=false;
            //判断是否是特殊多规则条码
            for (MesBcRuleAdd list : addList) {
                if (StrUtil.isNotEmpty(list.getAliasName())) {
                    //判断是否是rcu条码生成规则 （内置RCU程式版本字段|内置RCU端口字段）
                    //if (BcAliasNameConstants.BC_RCU_IN.equals(list.getAliasName()) || BcAliasNameConstants.BC_RCU_IN_PORT.equals(list.getAliasName())) {
                    if (BcAliasNameConstants.BC_RCU_IN_PORT.equals(list.getAliasName())) {
                        rcuFlag = true;
                        break;
                    }
                    //判断是否是大唐条码生成规则
                    //if (BcAliasNameConstants.BC_5G.equals(list.getAliasName()) || BcAliasNameConstants.BC_5G_SN.equals(list.getAliasName())) {
                    if (BcAliasNameConstants.BC_5G.equals(list.getAliasName())) {
                        fiveGflag = true;
                        break;
                    }
                }
            }
            if (rcuFlag) {
                //RCU多规则
                strategy = CodeProduceConstant.RCU_RULE_CODE_PRODUCE;
            } else if (fiveGflag){
                //大唐5g多规则
                strategy = CodeProduceConstant.FIVE_G_CODE_PRODUCE;
            }
            Assert.isFalse(MesCodeResultCodeEnum.MES_BC_CODE_PRODUCE_FAILED,strategy == null);
        }
        //一般单规则
        if (1 == mesBcProduce.getRuleIds().size()) {
            strategy = CodeProduceConstant.SINGLE_RULE_CODE_PRODUCE;
        }
        return strategyContext.doStrategy(strategy).codeProduce(mesBcProduce);
    }*/

}
