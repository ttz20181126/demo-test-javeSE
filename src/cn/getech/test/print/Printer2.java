package cn.getech.test.print;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import javax.print.*;
import javax.print.attribute.standard.PrinterName;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Printer2 {

    private PrintService printService = null;//打印机服务
    private String printerURI = "";//打印机完整路径

    public static void main(String[] args) {
        Printer2 p = new Printer2("\\\\LENOVO-PC\\ZDesigner 105SL 203DPI");
        p.print("http://192.168.6.145/fx-platform/ureport/pdf?_u=mysql-gt820_qcpass.ureport.xml&_i=1&_r=1&produceId=607447799f6b4bef86b6c1f854b35ad5");
    }

    public Printer2(String printerURI){
        this.printerURI = printerURI;
        //初始化打印机
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null,null);
        if (services != null && services.length > 0) {
            for (PrintService service : services) {
                if (printerURI.equals(service.getName())) {
                    printService = service;
                    break;
                }
            }
        }
        if (printService == null) {
            System.out.println("没有找到打印机：["+printerURI+"]");
            //循环出所有的打印机
            if (services != null && services.length > 0) {
                System.out.println("可用的打印机列表：");
                for (PrintService service : services) {
                    System.out.println("["+service.getName()+"]");
                }
            }
        }else{
            System.out.println("找到打印机：["+printerURI+"]");
            System.out.println("打印机名称：["+printService.getAttribute(PrinterName.class).getValue()+"]");
        }
    }

    public void print(String pdfPath){
        if(printService==null){
            System.out.println("打印出错：没有找到打印机：["+printerURI+"]");
        }
        DocPrintJob job = printService.createPrintJob();
        //byte[] by = fileToByteArray(pdfPath);
        byte[] by = urlToByteArray(pdfPath);
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(by, flavor, null);
        try {
            job.print(doc, null);
            System.out.println("========已打印========");
        } catch (PrintException e) {
            e.printStackTrace();
        }
    }

    public static byte[] fileToByteArray(String filePath) {
        File src = new File(filePath);
        byte[] dest = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new FileInputStream(src);
            baos = new ByteArrayOutputStream();
            byte[] flush = new byte[1024*10];
            int len = -1;
            while(!((len = is.read(flush)) != -1)){
                baos.write(flush,0,len);
            }
            baos.flush();
            dest = baos.toByteArray();
            return dest;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(null != is){
                try{
                    is.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
        return null;
    }

    public static byte[] urlToByteArray(String urlStr) {
        try{
            URL url = new URL(urlStr);
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            ByteOutputStream byteOutputStream=new ByteOutputStream();
            byte[] bytes = new byte[1024*10];
            int len = 0;
            while((len = inputStream.read(bytes)) != -1){
                byteOutputStream.write(bytes,0,len);
            }
            byte[] fileData = byteOutputStream.toByteArray();
            inputStream.close();
            byteOutputStream.close();
            return fileData;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}