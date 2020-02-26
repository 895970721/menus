package com.crf.menu.controller;

import com.crf.menu.enums.StatusCode;
import com.crf.menu.response.BaseResponse;
import com.crf.menu.service.Impl.NoteServiceImpl;
import com.crf.menu.utils.CheckToken;
import com.crf.menu.utils.UserTokenUtilImpl;
import com.crf.menu.vo.NoteListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "note")
public class NoteController {

    @Autowired
    private NoteServiceImpl noteService;

    @Autowired
    private UserTokenUtilImpl tokenUtil;

    @CheckToken
    @PostMapping(value = "add")
    public BaseResponse add(@RequestParam("token") String token, @RequestParam(required = false) MultipartFile file,
                            @NotEmpty @RequestParam("noteName" ) String noteName,@NotEmpty @RequestParam("noteContent") String noteContent){
        Integer insCnt = noteService.addNote(token,noteName,noteContent,file);
        HashMap map = new HashMap();
        map.put("insCnt",insCnt);
        BaseResponse response = new BaseResponse(StatusCode.Success);
        response.setData(map);
        return response;
    }

    @PostMapping(value = "getNoteVoList")
    public BaseResponse getNoteVoList(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        List<NoteListVO> noteListVOList = noteService.getNoteListVO(pageNum,pageSize);
        BaseResponse response = new BaseResponse(StatusCode.Success);
        response.setData(noteListVOList);
        return response;
    }

}
