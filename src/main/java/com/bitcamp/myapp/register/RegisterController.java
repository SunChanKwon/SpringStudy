package com.bitcamp.myapp.register;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//현재 클래스가 컨트롤러가 되기 위해서는 클래스명 이전에 @Controller어노테이션을 기술한다.
@Controller
public class RegisterController {
   RegisterDAO dao = new RegisterDAO();
   //회원가입폼 매핑
   @RequestMapping("/registerForm")
   public String registerForm() {
      //뷰파일명을 반환한다.
      return "register/form";
   }
   /*아이디 중복검사 매핑
   @RequestMapping("/idCheck")
   public String idCheck(HttpServletRequest req) {
      String userid = req.getParameter("userid");
      System.out.println(userid);
      return "idCheck";
   } 
   */
   
      @RequestMapping("/idCheck")
      public ModelAndView idCheck(String userid) {
         int result = dao.idDoubleCheck(userid);
         System.out.println(result);
         
         ModelAndView mav = new ModelAndView(); //뷰파일에 필요한 데이터, 뷰 파일명을 저장하는 객체
         mav.addObject("userid", userid);
         mav.addObject("result", result);
         mav.setViewName("register/idCheck");
         
         return mav;
      }
      
      //우편번호 검색페이지로 이동
      @RequestMapping("/zipSearch")
      public String zipSearch() {
         return "register/zipcodeSearch";
      }
      
      //우편번호 검색
      @RequestMapping("/zipFind")
      @ResponseBody //이거써주면 뷰페이지 리턴안해줘도 됨
      public List<ZipCodeVO> zipFind(String doro){
      //   List<ZipCodeVO> list = dao.zipSearchRecord(doro);
         return dao.zipSearchRecord(doro);
      }
      
      //회원등록                        post방식의 접속일때는 반드시 명시해야 한다.
      @RequestMapping(value="/formOk",method=RequestMethod.POST)
      public String formOk(RegisterVO vo,Model model) {
         //회원등록
        int result = dao.insertRecord(vo);
        //등록여부, 뷰파일명
        model.addAttribute("result",result);
        return "register/formResult";
      }
      //로그인폼
      @RequestMapping("/login")
      public String loginForm() {
         return "register/login";
      }
      @RequestMapping(value="/loginOk",method=RequestMethod.POST)
      public ModelAndView loginOk(HttpServletRequest req,RegisterVO vo) {
         dao.loginSelect(vo);
         
         ModelAndView mav = new ModelAndView();
         if(vo.getLogStatus().equals("Y")) {
            HttpSession session = req.getSession();
            //이름,로그인상태
            session.setAttribute("logname", vo.getUsername());
            session.setAttribute("logid", vo.getUserid());
            session.setAttribute("logStatus", vo.getLogStatus());
            mav.setViewName("redirect:/");
         }else {
            mav.setViewName("redirect:login");
         }
         return mav;
      }
      @RequestMapping("/logout")
      public ModelAndView logout(HttpSession session) {
    	  session.invalidate();
    	  ModelAndView mav = new ModelAndView();
    	  mav.setViewName("redirect:/");
    	  return mav;
      }
      //수정폼
  	@RequestMapping("/registerEdit")
  	public ModelAndView registerEdit(HttpSession session) {
  		
  		RegisterVO vo = new RegisterVO();
  		vo.setUserid((String)session.getAttribute("logid"));
  		dao.selectRecord(vo);
  		
  		
  		ModelAndView mav = new ModelAndView();
  		mav.addObject("vo",vo);
  		mav.setViewName("register/edit");
  		return mav;
  	}
  	//회원정보 수정
  	@RequestMapping(value="/editOk", method=RequestMethod.POST)
  	public ModelAndView editOk(RegisterVO regVo, HttpSession session) {
  		ModelAndView mav = new ModelAndView();
  		regVo.setUserid((String)session.getAttribute("logid"));
  		int result = dao.updateRecord(regVo);
  		
  		//수정 실패시 history, 수정성공 : 수정폼으로 이동
  		if(result>0) {//수정성공
  			mav.setViewName("redirect:registerEdit");
  		}else {//수정실패
  			mav.setViewName("register/editResult");
  		}
  		return mav;
  	}
}