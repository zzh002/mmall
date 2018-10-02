package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZZH
 * @date 2018/4/23 0023 13:08
 **/
public interface IFileService {
    String upload(MultipartFile file, String path);
}
