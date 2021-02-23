package cn.getech.test;

import cn.getech.test.dto.ReverseEnum;
import cn.getech.test.dto.Student;
import cn.getech.test.print.PrinterUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DemoTest {

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
