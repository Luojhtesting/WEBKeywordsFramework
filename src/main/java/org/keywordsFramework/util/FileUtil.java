package org.keywordsFramework.util;

import java.io.File;

public class FileUtil {
    public static boolean createFile(String dastFileName) {
        File file = new File(dastFileName);
        if (file.exists()) {
            System.out.println("创建单个文件" + dastFileName + "失败，目标文件已经存在！");
            return false;
        }

        if (dastFileName.endsWith(File.separator)){
            System.out.println("创建单个文件" + dastFileName + "失败，目标文件不能为目录！");
        }

        if (!file.getParentFile().exists()){
            System.out.println("目标文件所在目录不存在，准备创建！");
            if(!file.getParentFile().mkdirs()) {
                System.out.println("创建文件失败所在目录失败！");
                return false;
            }
        }

        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + dastFileName + "成功");
                return true;
            } else {
                System.out.println("创建单个文件" + dastFileName + "失败");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("创建单个文件" + dastFileName + "失败" + e.getMessage());
            return false;
        }
    }

    public static boolean createDir(String dastDirName) {
        File file = new File(dastDirName);
        if(file.exists()) {
            System.out.println("创建单个文件" + dastDirName + "失败，目标文件已经存在！");
            return false;
        }

        if(file.mkdirs()) {
            System.out.println("创建目录" + dastDirName + "成功");
            return true;
        } else {
            System.out.println("创建目录" + dastDirName + "失败");
            return false;
        }

    }
}
