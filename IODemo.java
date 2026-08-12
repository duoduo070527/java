
import java.io.*;

public class IODemo {
    public static void main(String[] args) throws IOException {
        // ==========一、字符流：读写纯文本文件（BufferedReader/BufferedWriter缓冲字符流）==========
        String txtPath = "test.txt";
        //写入文本
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(txtPath))) {
            bw.write("Java IO流学习笔记");
            bw.newLine(); //换行
            bw.write("字节流处理图片视频；字符流只适合txt文本");
            bw.newLine();
            bw.write("缓冲流自带缓冲区，读写效率大幅提升");
        }

        //读取文本
        System.out.println("====读取txt文件内容====");
        try (BufferedReader br = new BufferedReader(new FileReader(txtPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        System.out.println();

        // ==========二、字节流复制图片：普通字节流 vs 缓冲字节流 耗时对比==========
        String srcImg = "source.jpg";    //原图片
        String dest1 = "copy1.jpg";
        String dest2 = "copy2.jpg";

        //1.基础FileInputStream+FileOutputStream 无缓冲
        long start1 = System.currentTimeMillis();
        copyByNormalByte(srcImg, dest1);
        long end1 = System.currentTimeMillis();
        System.out.println("普通字节流复制耗时：" + (end1 - start1) + "ms");

        //2.BufferedInputStream+BufferedOutputStream 缓冲字节流
        long start2 = System.currentTimeMillis();
        copyByBufferByte(srcImg, dest2);
        long end2 = System.currentTimeMillis();
        System.out.println("缓冲字节流复制耗时：" + (end2 - start2) + "ms");
    }

    //普通字节流文件复制
    public static void copyByNormalByte(String src, String dest) throws IOException {
        try (FileInputStream fis = new FileInputStream(src);
             FileOutputStream fos = new FileOutputStream(dest)) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
        }
    }

    //缓冲字节流文件复制
    public static void copyByBufferByte(String src, String dest) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest))) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bos.flush(); //刷新缓冲区
        }
    }
}