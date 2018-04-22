package com.example.springbootjpa.controller;

import com.example.springbootjpa.pojo.BookInfo;
import com.example.springbootjpa.pojo.DemoEntity;
import com.example.springbootjpa.service.BookInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//@RestController的意思就是controller里面的方法都以json格式输出
@Controller
@RequestMapping("/demo")
@Api("DemoController相关的api")
@EnableAutoConfiguration
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(DemoController.class);


    @Resource(name = "bookInfoService")
    private BookInfoService bookInfoService;


    @ApiOperation(value = "获取书籍列表",notes = "")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public String findAll(Model model, HttpServletResponse response, HttpServletRequest request) {
        List<BookInfo> bookInfos = bookInfoService.findAll();
        //logger.warn(request.getParameter("msg") + "....");
        model.addAttribute("list", bookInfos);
        model.addAttribute("msgs", "hello Thymeleaf");
        return "list";
    }

    /*@RequestMapping("/login")
    public Object login(Model model, @RequestParam String code, @RequestParam String pwd, HttpSession session,
                        HttpServletResponse response, HttpServletRequest request) {
        Users u = bookInfoService.findByUser(code, pwd);
        if (u != null) {
            Integer count = bookInfoService.findCount(null, null, null);
            int allpage = count % 5 == 0 ? count / 5 : count / 5 + 1;
            session.setAttribute("user", u);
            model.addAttribute("list", bookInfoService.findAll(null, null, null, 0, 5));
            model.addAttribute("count", bookInfoService.findCount(null, null, null));
            model.addAttribute("allpage", allpage);
            model.addAttribute("pageNo", 1);
            List<BookInfo> bookInfos = bookInfoService.findAll(null, null, null, 0, 100);
//			return "list";
            return bookInfos;
        } else {
            session.setAttribute("msg", "账号或密码错误！");
//			return "redirect:/index.jsp";
            return "账号或密码错误！";
        }
    }*/

    @RequestMapping("/del")
    @ApiOperation(value="删除书籍", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "书籍ID",paramType = "path", required = true, dataType = "Long")
    public String del(RedirectAttributesModelMap modelMap, @RequestParam(value = "id") Long id) {

        if (bookInfoService.deleteById(id)) {
            modelMap.addFlashAttribute("msg", "删除成功");
        } else {
            modelMap.addFlashAttribute("msg", "删除失败");
        }
        return "redirect:/demo/findAll";
    }

    @ApiOperation(value="获取书籍详细信息", notes="根据url的id来获取书籍详细信息")
    @ApiImplicitParam(name = "id", value = "书籍ID", paramType = "path",required = true, dataType = "Long")
    @RequestMapping("/findOne/{id}")
    public String findOne(Model model, @PathVariable("id") Long id) {
        BookInfo bookInfo = bookInfoService.findOne(id);
        model.addAttribute("bookInfo", bookInfo);
        return "update";
    }

    @RequestMapping(value = "/goAdd", method = RequestMethod.GET)
    public String goAdd() {
        System.out.println(12);
        return "add";
    }

    @ApiOperation(value="创建书籍", notes="根据bookInfo对象创建用户书籍")
    @ApiImplicitParam(name = "bookInfo", value = "书籍详细实体bookInfo",paramType = "path", required = true, dataType = "BookInfo")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, BookInfo bookInfo) {
        if (bookInfoService.save(bookInfo) != null) {
            model.addAttribute("msg", "添加成功");
        } else {
            model.addAttribute("msg", "添加失败");
            model.addAttribute("bookInfo", bookInfo);
            return "add";
        }
        return "redirect:/demo/findAll";

    }

    @ApiOperation(value="更新书籍详细信息", notes="根据url的id来指定更新对象，并根据传过来的bookInfo信息来更新书籍详细信息")
    @ApiImplicitParam(name = "bookInfo", value = "书籍详细实体bookInfo",paramType = "path", required = true, dataType = "BookInfo")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, BookInfo bookInfo) {

        if (bookInfoService.update(bookInfo) > 0) {
            model.addAttribute("msg", "修改成功");
        } else {
            model.addAttribute("msg", "修改失败");
            model.addAttribute("bookInfo", bookInfo);
            return "update";
        }
        return "redirect:/demo/findAll";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile[] files,
                                   Model model, HttpServletRequest request) {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                model.addAttribute("message", "Please select a file to upload");
                return "uploadStatus";
            }

            try {
                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                //System.out.println(request.getSession().getServletContext().getRealPath("upload"));
                Path path = Paths.get("D:\\asd\\" + file.getOriginalFilename());
                Files.write(path, bytes);
                model.addAttribute("message",
                        "You successfully uploaded '" + file.getOriginalFilename() + "'");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "uploadStatus";
    }

    @RequestMapping("/download")
    public String downLoad(HttpServletResponse response) throws UnsupportedEncodingException {
        String filename = "GitHubDesktopSetup.exe";
        String filePath = "D:/asd";
        File file = new File(filePath + "/" + filename);
        String len = file.length() + "";
        if (file.exists()) { //判断文件父目录是否存在
            //解决下载的文件中文乱码
            filename = new String(filename.getBytes(), "ISO8859-1");
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            //设置长度
            response.addHeader("Content-Length", len);
            response.addHeader("fileName", filename);
            response.addHeader("Content-Transfer-Encoding", "Binary");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "POST, GET");
            response.addHeader("Custom-Header", "Own-Data");
            response.addHeader("Access-Control-Expose-Headers", " Custom-Header");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }


    // -------------------------------------------------------------

    @RequestMapping("/test")
    @ResponseBody
    Object test() {
        DemoEntity entity = new DemoEntity();
        entity.setId("1");
        entity.setUsername("周宇峰");
        entity.setPassword("123456");
        logger.debug("这是debug信息");
        logger.info("这是info信息");
        logger.warn("这是warn信息");
        logger.error("这是error信息");
        return entity;
    }

}
