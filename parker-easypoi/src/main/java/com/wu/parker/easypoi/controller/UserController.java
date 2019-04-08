package com.wu.parker.easypoi.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.wu.parker.common.web.BaseResult;
import com.wu.parker.easypoi.po.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author wusq
 * @date 2019/4/8
 */
@Api(description = "用户服务")
@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @ApiOperation("导入excel")
    @PostMapping("excel/import")
    public BaseResult importExcel(@RequestParam("file") MultipartFile file){
        BaseResult result = new BaseResult();

        try {
            ImportParams params = new ImportParams();
            params.setTitleRows(0);
            params.setHeadRows(1);
            List<User> userList = ExcelImportUtil.importExcel(file.getInputStream(), User.class, params);
            userList.forEach(o -> System.out.println(o));
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("导入excel错误{}", e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    /**
     * poi导出，没用easypoi
     * 不要用swagger测试导出功能，下载的文件异常
     * 直接在浏览器访问http://localhost:8080/swagger-ui.html
     * 火狐浏览器可能出现文件名乱码，谷歌浏览器正常
     * @param response
     * @throws Exception
     */
    @ApiOperation("导出excel")
    @GetMapping("excel/export")
    public void exportExcel(HttpServletResponse response) throws Exception{

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        try {
            //表头
            XSSFRow r0 = sheet.createRow(0);
            XSSFCell c00 = r0.createCell(0);
            c00.setCellValue("列1");
            XSSFCell c01 = r0.createCell(1);
            c01.setCellValue("列2");

            //数据
            XSSFRow r1 = sheet.createRow(1);
            XSSFCell c10 = r1.createCell(0);
            c10.setCellValue("数据1");
            XSSFCell c11 = r1.createCell(1);
            c11.setCellValue("数据2");

            response.reset();

            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("测试.xlsx", "utf-8"));

            //将文件输出
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            log.error("导出excel错误{}", e.getMessage());
            e.printStackTrace();
        } finally {
            workbook.close();
        }
    }
}
