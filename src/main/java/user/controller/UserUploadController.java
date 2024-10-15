package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import user.bean.UserUploadDTO;
import user.service.ObjectStorageService;
import user.service.UserUploadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class UserUploadController {
    @Autowired
    private UserUploadService userUploadService;
    @Autowired
    private ObjectStorageService objectStorageService;

    private String bucketName = "bitcamp-9th-bucket-90";

    @RequestMapping(value = "/uploadForm")
    public String uploadForm() {
        return "/upload/uploadForm";
    }
    @RequestMapping(value = "/uploadAJaxForm")
    public String uploadAJaxForm() {
        return "/upload/uploadAJaxForm";
    }
    /* 1개의 파일이 넘어올때
    @ResponseBody
    @RequestMapping(value = "/user/upload",method = RequestMethod.POST)
    public String upload(@ModelAttribute UserUploadDTO userUploadDTO,
                         @RequestParam MultipartFile img,
                         HttpSession session) {
        //실제폴더 = /Users/jinhwankim/Desktop/Intellij/SpringProject/target/SpringProject-1.0.0-BUILD-SNAPSHOT/WEB-INF/storage
        String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
        System.out.println("실제폴더 = "+ filePath);

        String imageOriginalFileName = img.getOriginalFilename();

        File file =  new File(filePath, imageOriginalFileName);

        try {
            img.transferTo(file);
        }catch (IllegalStateException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = "<span>"
                    + "<img src='/storage/"+imageOriginalFileName+"' width='300' height='300' />"
                    + "</span>";

        System.out.println(userUploadDTO.getImageName()+","
                + userUploadDTO.getImageContent()+","
                + userUploadDTO.getImageFileName()+","
                + userUploadDTO.getImageOriginalFileName());

        userUploadDTO.setImageOriginalFileName(imageOriginalFileName);
        */

    //2개이상일 때
    /*@RequestMapping(value = "/user/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(@ModelAttribute UserUploadDTO userUploadDTO,
                         @RequestParam MultipartFile[] img,
                         HttpSession session) {
        //실제폴더 = /Users/jinhwankim/Desktop/Intellij/SpringProject/target/SpringProject-1.0.0-BUILD-SNAPSHOT/WEB-INF/storage
        String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
        System.out.println("실제폴더 = "+ filePath);

        String imageOriginalFileName;
        File file;
        String result;

        imageOriginalFileName = img[0].getOriginalFilename();
        file =  new File(filePath, imageOriginalFileName);

        try {
            img[0].transferTo(file);
        }catch (IllegalStateException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = "<span>"
                + "<img src='/storage/"+imageOriginalFileName+"' width='300' height='300' />"
                + "</span>";

        imageOriginalFileName = img[1].getOriginalFilename();
        file =  new File(filePath, imageOriginalFileName);

        try {
            img[1].transferTo(file);
        }catch (IllegalStateException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result += "<span>"
                + "<img src='/storage/"+imageOriginalFileName+"' width='300' height='300' />"
                + "</span>";



        return result;
    }*/

    //1개 또는 여러개
   /* @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST, produces = "text/html; charset=UTF-8;")
    public String upload(@ModelAttribute UserUploadDTO userUploadDTO,
                         @RequestParam ("img[]") List<MultipartFile> list,
                         HttpSession session) {
        //실제폴더 = /Users/jinhwankim/Desktop/Intellij/SpringProject/target/SpringProject-1.0.0-BUILD-SNAPSHOT/WEB-INF/storage
        String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
        System.out.println("실제폴더 = " + filePath);

        String imageOriginalFileName;
        File file;
        String result = "";

        //파일들을 모아서 DB로 보내기
        List<UserUploadDTO> imageUploadList = new ArrayList<>();
            for (MultipartFile img : list) {
                imageOriginalFileName = img.getOriginalFilename();
                file = new File(filePath, imageOriginalFileName);


            try {
                img.transferTo(file);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                result += "<span>"
                        + "<img src='/storage/"
                        + URLEncoder.encode(imageOriginalFileName,"UTF-8")
                        //+ imageOriginalFileName
                        + "' width='300' height='300' />"
                        + "</span>";
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            UserUploadDTO dto = new UserUploadDTO();
            dto.setImageName(userUploadDTO.getImageName());
            dto.setImageContent(userUploadDTO.getImageContent());
            dto.setImageFileName("");
            dto.setImageOriginalFileName(imageOriginalFileName);

            imageUploadList.add(dto);
        }


        return result;
    }*/

    @RequestMapping(value="/upload", method=RequestMethod.POST, produces = "text/html; charset=UTF-8")
    @ResponseBody
    public ModelAndView upload(@ModelAttribute UserUploadDTO userUploadDTO,
                         @RequestParam("img[]") List<MultipartFile> list,
                         HttpSession session) {
        //실제폴더
        String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
        System.out.println("실제 폴더 = " + filePath);

        String imageFileName;
        String imageOriginalFileName;
        File file;
        String result = "";

        //파일들을 모아서 DB로 보내기
        List<UserUploadDTO> imageUploadList = new ArrayList<>();

        for(MultipartFile img : list) {

            //imageFileName = UUID.randomUUID().toString();

            //네이버 클라우드 Object Storage -----------------------------
            imageFileName = objectStorageService.uploadFile(bucketName, "storage/", img);
            //--------------------------------------------------------

            imageOriginalFileName = img.getOriginalFilename();
            file = new File(filePath, imageOriginalFileName);

            try {
                img.transferTo(file);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

//			try {
                result += "<span>"
                       + "<img src='/storage/"

//					   + URLEncoder.encode(imageOriginalFileName, "UTF-8") => 파일명에 공백이 있으면 업로드가 안된다.

                       + imageOriginalFileName
                       + "' width='200' height='200'>"
                       + "</span>";
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}

            UserUploadDTO dto = new UserUploadDTO();
            dto.setImageName(userUploadDTO.getImageName());
            dto.setImageContent(userUploadDTO.getImageContent());
            dto.setImageFileName(imageFileName);
            dto.setImageOriginalFileName(imageOriginalFileName);

            imageUploadList.add(dto);

        }//for

        //DB
        userUploadService.upload(imageUploadList);

        return uploadList();
    }


    @RequestMapping(value="/uploadList")
    public ModelAndView uploadList() {
        List<UserUploadDTO> list = userUploadService.uploadList();

        ModelAndView mav = new ModelAndView();
        mav.addObject("list", list);
        mav.setViewName("/upload/uploadList");
        return mav;
    }
    @RequestMapping(value ="/uploadView")
    public String uploadView(@RequestParam String seq, Model model) {
        UserUploadDTO userUploadDTO = userUploadService.getUploadDTO(seq);
        model.addAttribute("userUploadDTO", userUploadDTO);
        return "/upload/uploadView";
    }
    @RequestMapping(value = "/uploadUpdateForm")
    public String uploadUpdateForm(@RequestParam String seq, Model model) {
        UserUploadDTO userUploadDTO = userUploadService.getUploadDTO(seq);
        model.addAttribute("userUploadDTO", userUploadDTO);

        return "/upload/uploadUpdateForm";
    }
    @RequestMapping(value = "/uploadUpdate", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String uploadUpdate(@ModelAttribute UserUploadDTO userUploadDTO,
                               @RequestParam("img") MultipartFile img) {
        System.out.println(img);
        userUploadService.uploadUpdate(userUploadDTO, img);
        return "이미지 수정 완료";
    }
    @RequestMapping(value="/uploadDelete")
    @ResponseBody
    public ModelAndView uploadDelete(@RequestParam("seqs[]") List<Integer> seqs) {
       for (Integer seq : seqs) {
           userUploadService.uploadDelete(seq);
       }
       return uploadList();
    }
}
