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

import javax.validation.constraints.Min;
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

    /**
     * 添加笔记
     * @param token          用户标识
     * @param file           用户上传照片
     * @param noteName       笔记名称
     * @param noteContent    笔记内容
     * @return
     */
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

    /**
     * 返回笔记列表信息
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @return
     */
    @PostMapping(value = "getNoteVoList")
    public BaseResponse getNoteVoList(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        List<NoteListVO> noteListVOList = noteService.getNoteListVO(pageNum,pageSize);
        BaseResponse response = new BaseResponse(StatusCode.Success);
        response.setData(noteListVOList);
        return response;
    }

    /**
     * 通过笔记名称返回笔记列表信息
     * @param noteName    笔记名称
     * @param pageNum     页码
     * @param pageSize    每页数量
     * @return
     */
    @PostMapping(value = "getNotesVOByNoteName")
    public BaseResponse getNotesVOByNoteName(@RequestParam("noteName") String noteName,
                                             @Min(value = 1,message = "pageNum非法") @RequestParam("pageNum") Integer pageNum,
                                             @Min (value = 1,message = "pageSize非法") @RequestParam("pageSize") Integer pageSize){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<NoteListVO> noteListVOList = noteService.getNotesVOByNoteName(noteName,pageNum,pageSize);
        response.setData(noteListVOList);
        return response;
    }

    /**
     * 通过用户id返回用户喜欢的笔记列表
     * @param token      用户标识
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @return
     */
    @CheckToken
    @PostMapping(value = "getNotesVOByUserId")
    public BaseResponse getNotesVOByUserId(@RequestParam("token") String token,
                                           @Min(value = 1,message = "pageNum非法") @RequestParam("pageNum") Integer pageNum,
                                           @Min (value = 1,message = "pageSize非法") @RequestParam("pageSize") Integer pageSize){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<NoteListVO> noteListVOList = noteService.getNoteListVOByUserId(token,pageNum,pageSize);
        response.setData(noteListVOList);
        return response;
    }

    /**
     * 通过用户id返回用户发布的笔记
     * @param token    用户标识
     * @return
     */
    @CheckToken
    @PostMapping(value = "getNoteVOListByUserId")
    public BaseResponse getNoteVOListByUserId(@RequestParam("token") String token){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<NoteListVO> noteListVOList = noteService.getNoteListByUserId(token);
        response.setData(noteListVOList);
        return response;
    }

}
