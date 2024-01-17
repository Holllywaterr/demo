package com.thc.sprapi.controller;

import com.thc.sprapi.dto.TbboardDto;
import com.thc.sprapi.service.TbboardService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// 페이지 내에서 부분적으로 원하는 정보나 바꾸고 싶은 것이 있을 때 사용
@RequestMapping("/api")
@RestController //페이지를 보여주는 것이 아니라, Rest 방식을 사용할때!!
public class DefaultRestController {

    private final TbboardService tbboardService;
    public DefaultRestController(TbboardService tbboardService) {
        this.tbboardService = tbboardService;
    }
    //오토와이어드 쓸수도 있지만, 이렇게 주입하는 방식을 선호!!

    // 여기서 메소드에서는 원하는 정보를 가공해서 제공하는 역할
    // 보통 자세한 것은 서비스로 넘겨서 처리
    @GetMapping({"/itest"})
    public int itest(){

        return 0;
    }

    @PostMapping({"/btest"})
    public int btest(
            @RequestBody TbboardDto.TbboardCreateDto params
    ){
        System.out.println("1! params : " + params);
        int result_int = tbboardService.create1(params);
        return result_int;
    }

    public Map<String, Object> paramTest(
            @RequestParam String aaa
    ) {
        Map<String, Object> a_map = new HashMap<>();
        a_map.put("name", "sprapi!!");
        a_map.put("phone", "010!!");
        return a_map;
    }
    @GetMapping({"/test"})
    public Map<String, Object> getTest() {
        Map<String, Object> a_map = new HashMap<>();
        a_map.put("name", "sprapi!!");
        a_map.put("phone", "010!!");
        return a_map;
    }

    @GetMapping({"/create"})
    public int createTbboard(
            //@RequestParam(value = "aaa", required = true) String aaa
            /*
            @RequestParam(value = "title", required = true) String title
            ,@RequestParam(value = "content", required = true) String content
    */
    ) {
        //System.out.println("aaa//");
        String title = "122334";
        String content = "2244555";
        System.out.println(title + "//" + content);
        //등록하기
        Map<String, Object> a_map = new HashMap<>();
        a_map.put("title", title);
        a_map.put("content", content);

        int result_int = tbboardService.create(a_map);
        /*
        Tbboard tbboard = Tbboard.of("제목 예시", "내용 예시");
        tbboard = tbboardRepository.save(tbboard);
        */
        return result_int;
    }

    @GetMapping({"/update"})
    public int updateTbboard(
    ) {
        Map<String, Object> a_map = new HashMap<>();
        a_map.put("id", "09e13549bbdb4a3fb3fe64eadf5185f6");
        a_map.put("title", "updated");
        int result_int = tbboardService.update(a_map);
        return result_int;
    }

}
