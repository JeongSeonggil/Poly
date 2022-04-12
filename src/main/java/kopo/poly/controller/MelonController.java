package kopo.poly.controller;

import kopo.poly.dto.MelonDTO;
import kopo.poly.service.IMelonService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class MelonController {

    /*
     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */

    // Map 객체를 사용한 데이터 처리
    @Resource(name = "MelonService")
    private IMelonService melonService;

    private ModelMapper modelMapper;

    @Autowired
    public MelonController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * 멜론 노래 리스트 저장하기
     */
    @GetMapping(value = "melon/collectMelonSong")
    public String collectMelonRank() throws Exception {

        log.info(this.getClass().getName() + ".collectMelonSong Start!");

        // 수집 결과 출력
        String msg;

        int res = melonService.collectMelonSong();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".collectMelonSong End!");

        return msg;
    }

    /**
     * 오늘 수집된 멜론 노래리스트 가져오기
     */
    @GetMapping(value = "melon/getSongList")
    public List<MelonDTO> getSongList() throws Exception {

        log.info(this.getClass().getName() + ".getSongList Start!");

        List<MelonDTO> rList = melonService.getSongList();

        log.info(this.getClass().getName() + ".getSongList End!");

        return rList;
    }

    /**
     * 가수별 수집된 노래의 수 가져오기
     */
    @GetMapping(value = "melon/getSingerSongCnt")
    public List<Map<String, Object>> getSingerSongCnt()
            throws Exception {

        log.info(this.getClass().getName() + ".getSingerSongCnt Start!");

        List<Map<String, Object>> rList = melonService.getSingerSongCnt();

        log.info(this.getClass().getName() + ".getSingerSongCnt End!");

        return rList;
    }

    @GetMapping("melon/getSingerSong")
    public List<MelonDTO> getSingerSong() throws Exception {
        log.info(this.getClass().getName() + ".getSingerSong Start!");
        List<MelonDTO> rList = melonService.getSingerSong();

        log.info(this.getClass().getName() + ".getSingerSong End!");

        return rList;
    }

    @GetMapping("/melon/collectMelonRankMany")
    public String collectMelonRankMany() throws Exception {

        log.info(this.getClass().getName() + ".collectMelonSongMany Start!");

        // 수집 결과 출력
        String msg;

        int res = melonService.collectMelonSongMany();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".collectMelonSongMany End!");

        return msg;
    }

    @GetMapping(value = "/melon/bts-change-nickname")
    public String btsChangeField() throws Exception {

        log.info(this.getClass().getName() + ".btsChangeNickname Start!");

        String msg;

        int res = melonService.updateBTSName();


        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".btsChangeNickname End!");


        return msg;

    }

    @GetMapping("/melon/bts-add-nickname")
    public String btsAddField() throws Exception {
        log.info(this.getClass().getName() + ".btsAddNickName Start!");

        int res = 0;

        String msg;

        res = melonService.updateAddBTSNickname();


        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }


        log.info(this.getClass().getName() + ".btsAddNicName End!");


        return msg;
    }


    @GetMapping(value = "/melon/bts-add-member")
    public String btsAddMember() throws Exception {

        log.info(this.getClass().getName() + ".btsAddMember Start!");

        String msg;

        int res = melonService.updateAddBTSMember();


        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }


        log.info(this.getClass().getName() + ".btsAddMember End!");


        return msg;
    }


    @GetMapping(value = "/melon/deleteSong")
    public String deleteSong() throws Exception {
        log.info(this.getClass().getName() + ".deleteSong Start!");

        String msg;

        int res = melonService.deleteSong();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".deleteSong End!");
        return msg;
    }

    @GetMapping("/password/{password}")
    public String password(@PathVariable String password) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        log.info("password : " + password);
        return passwordEncoder.encode(password);
    }

    @GetMapping("/password-check/{encPassword}")
    public boolean passwordCheck(@PathVariable String encPassword) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        log.info("password : " + encPassword);
        boolean check = passwordEncoder.matches("1234", encPassword);

        log.info("check : " + check);


        return check;


    }
}

