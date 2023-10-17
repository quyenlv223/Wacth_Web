package com.example.demo.service;




import com.example.demo.dto.respone.rom.RomRespone;

import java.util.List;

public interface RomService {
    List<RomRespone> findByProductId(Long id);
}
