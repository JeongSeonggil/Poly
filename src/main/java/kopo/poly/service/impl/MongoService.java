package kopo.poly.service.impl;

import kopo.poly.dto.MongoDTO;
import kopo.poly.persistance.mongodb.IMongoMapper;
import kopo.poly.service.IMongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "MongoService")
@Slf4j
public class MongoService implements IMongoService {

    @Resource(name = "MongoMapper")
    private IMongoMapper mongoMapper;

    @Override
    public void mongoTest() throws Exception {
        log.info(this.getClass().getName() + ".mongoTest Start!");


        String conNm = "Mongo_Test";

        MongoDTO pDTO = new MongoDTO();
        pDTO.setUser_nm("정성길");
        pDTO.setAddr("서울");
        pDTO.setEmail("dataofsg02@gmail.com");

        mongoMapper.insertData(pDTO, conNm);


        log.info(this.getClass().getName() + ".mongoTest End!");


    }
}
