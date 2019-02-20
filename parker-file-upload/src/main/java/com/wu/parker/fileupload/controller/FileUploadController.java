package com.wu.parker.fileupload.controller;

import com.wu.parker.common.random.RandomUtils;
import com.wu.parker.common.web.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author wusq
 * @date 2019/2/20
 */
@Api(description = "文件上传服务")
@RestController
@RequestMapping("file/")
public class FileUploadController {

    private static final String FILE_PATH = "D:/test/";

    @ApiOperation("上传文件")
    @PostMapping("upload")
    public BaseResult fileUpload(@RequestParam("file") MultipartFile file) {

        BaseResult result = new BaseResult();

        if (file != null && file.isEmpty()) {
            result.setCode(HttpStatus.BAD_REQUEST.value());
            result.setMessage("文件是空的");
            return result;
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();

            // 获取后缀名
            String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            // 随机生成一个名字
            String randomName = RandomUtils.uuidWithoutBar();

            Path path = Paths.get(FILE_PATH + randomName + suffixName);
            Files.write(path, bytes);

            result.setData(1);
            result.setMessage("上传成功");

        } catch (IOException e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage("上传文件错误");
            e.printStackTrace();
        }

        return result;
    }

    @ApiOperation("删除文件")
    @DeleteMapping("delete/{filename}")
    public BaseResult delete(@PathVariable String filename) {

        BaseResult result = new BaseResult();

        try {
            File file = new File(FILE_PATH + filename);
            file.delete();

            result.setData(1);
            result.setMessage("删除成功");

        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage("上传文件错误");
            e.printStackTrace();
        }

        return result;
    }

}
