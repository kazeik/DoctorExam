package com.kazeik.doctor.doctorexam.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import android.util.Log;


/**
 * Created by sunwei on 2015/7/10.
 * Email: lx_sunwei@163.com.
 * 解压
 */
public class ZipUtils {

    /**
     * 解压
     * 源文件
     * 目标
     */
    public static int unzip(String zipFile, String targetDir) {
        System.out.println("file :" + zipFile);
        File file = new File(zipFile);
        if (!file.exists()) {
            System.out.println("file  -1 :" + zipFile);
            return -1;
        }
        int BUFFER = 4096; // 这里缓冲区我们使用4KB，
        String strEntry; // 保存每个zip的条目名称
        try {
            BufferedOutputStream dest = null; // 缓冲输出流
            FileInputStream fis = new FileInputStream(zipFile);
            ZipInputStream zis = new ZipInputStream(
                    new BufferedInputStream(fis));
            ZipEntry entry; // 每个zip条目的实例

            while ((entry = zis.getNextEntry()) != null) {
                try {
                    Log.i("Unzip: ", "=" + entry);
                    int count;
                    byte data[] = new byte[BUFFER];
                    strEntry = entry.getName();

                    File entryFile = new File(targetDir);
                    File entryDir = new File(entryFile.getParent());
                    if (!entryDir.exists()) {
                        entryDir.mkdirs();
                    }
                    FileOutputStream fos = new FileOutputStream(entryFile);
                    dest = new BufferedOutputStream(fos, BUFFER);
                    while ((count = zis.read(data, 0, BUFFER)) != -1) {
                        dest.write(data, 0, count);
                    }
                    dest.flush();
                    dest.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            zis.close();
            System.out.println("file  0 :" + zipFile);
            return 0;
        } catch (Exception cwj) {
            cwj.printStackTrace();
            System.out.println("file -2 :" + zipFile);
            return -2;
        }
    }
    //    public static void unzip(String sourceZip, String destDir) {
    //        try {
    //            Project p = new Project();
    //            Expand e = new Expand();
    //            e.setProject(p);
    //            e.setSrc(new File(sourceZip));
    //            e.setOverwrite(false);
    //            e.setDest(new File(destDir));
    //            /*
    //             * ant下的zip工具默认压缩编码为UTF-8编码， 而winRAR软件压缩是用的windows默认的GBK或者GB2312编码
    //             * 所以解压缩时要制定编码格式
    //             */
    //            e.setEncoding("gbk");
    //            e.execute();
    //        } catch (BuildException e1) {
    //            e1.printStackTrace();
    //        }
    //    }

    /**
     * @param resourcesPath 源文件/文件夹
     * @param targetPath    目的压缩文件保存路径
     * @throws Exception
     */
    public static String zip(String resourcesPath, String targetPath) throws Exception {
        File resourcesFile = new File(resourcesPath);     //源文件
        File targetFile = new File(targetPath); //目的
        //如果目的路径不存在，则新建
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        String targetName = resourcesFile.getName() + ".zip";   //目的压缩文件名
        String filePath = targetPath + "/" + targetName;
        FileOutputStream outputStream = new FileOutputStream(filePath);
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));

        createCompressedFile(out, resourcesFile, "");

        out.close();
        return filePath;
    }

    /**
     * @param out  输出流
     * @param file 目标文件
     * @throws Exception
     */
    public static void createCompressedFile(ZipOutputStream out, File file, String dir) throws Exception {
        //如果当前的是文件夹，则进行进一步处理
        if (file.isDirectory()) {
            //得到文件列表信息
            File[] files = file.listFiles();
            //将文件夹添加到下一级打包目录
            out.putNextEntry(new ZipEntry(dir + "/"));

            dir = dir.length() == 0 ? "" : dir + "/";

            //循环将文件夹中的文件打包
            for (int i = 0; i < files.length; i++) {
                createCompressedFile(out, files[i], dir + files[i].getName());         //递归处理
            }
        } else {   //当前的是文件，打包处理
            //			String mp3String =mFilePath+fileName+".mp3";
            //			System.out.println("map3路劲"+mp3String);
            //			String wavString = mFilePath + fileName +".wav";
            //			lu.Convert4Exam(wavString,mFilePath+fileName+".mp3",recorder.getSamleRate());
            //			recorder = null;
            //		lu.Convert(wavString, mFilePath+fileName+".mp3");
            //文件输入流
            FileInputStream fis = new FileInputStream(file);

            out.putNextEntry(new ZipEntry(dir));
            //进行写操作
            int j = 0;
            byte[] buffer = new byte[1024];
            while ((j = fis.read(buffer)) > 0) {
                out.write(buffer, 0, j);
            }
            //关闭输入流
            fis.close();
        }
    }
}
