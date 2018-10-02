package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author ZZH
 * @date 2018/4/23 0023 13:09
 **/
@Service("iFileService")
@Slf4j
public class FileServiceImpl implements IFileService{

    @Override
    public String upload(MultipartFile file,String path){
        String fileName = file.getOriginalFilename();
        //扩展名
        //.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        log.info("开始上传文件，上传文件名:{},上传路径为：{},新文件名：{}",fileName,path,uploadFileName);
        File fileDir = new File(path);
        if (!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);

        try {
            file.transferTo(targetFile);
            //文件上传成功
            // 将targetFile上传到我们的服务器上
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            // 上传完成之后，删除upload下面的文件
            targetFile.delete();
        } catch (IOException e) {
            log.error("文件上传异常",e);
            return null;
        }
        return targetFile.getName();
    }
}
