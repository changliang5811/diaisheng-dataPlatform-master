package com.qdu.diaisheng.task;

import java.io.File;
import java.util.Comparator;

/**
 * @ClassName CompratorByLastModified
 * @Description: 文件comparator方法重写
 * @Author changliang
 * @Date 2020-07-11 15:32
 * @Version V1.0
 **/
 //根据文件修改时间进行比较的内部类
//按照时间从新到旧排序
public class CompratorByLastModified implements Comparator<File> {
    public int compare(File f1, File f2) {
        long diff = f1.lastModified() - f2.lastModified();
        if (diff > 0) {
            return -1;
        } else if (diff == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
